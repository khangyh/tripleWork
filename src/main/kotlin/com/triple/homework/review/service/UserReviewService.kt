package com.triple.homework.review.service

import com.google.gson.JsonArray
import com.google.gson.JsonParser
import com.triple.homework.review.entity.UserReviewEntity
import com.triple.homework.review.entity.UserReviewPointEntity
import com.triple.homework.review.model.UserReviewDTO
import com.triple.homework.review.repository.UserReviewPointRepository
import com.triple.homework.review.repository.UserReviewRepository
import mu.KLogging
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

private val log = KLogging().logger()

@Component
class UserReviewService {

    @Autowired
    lateinit var userRepository: UserReviewRepository

    @Autowired
    lateinit var userReviewPointRepository: UserReviewPointRepository

    @Autowired
    private lateinit var modelMapper: ModelMapper

    //리뷰 등록
    @Transactional
    fun setReview(userReviewDTO: UserReviewDTO): UserReviewDTO {

        //리뷰 등록
        var result = userRepository.save(modelMapper.map(userReviewDTO, UserReviewEntity::class.java))

        //리뷰 포인트 등록
        setUserReviewPoint(userReviewDTO)

        return modelMapper.map(userReviewDTO, UserReviewDTO::class.java)
    }

    //리뷰 수정
    @Transactional
    fun setReviewUpdate(userReviewDTO: UserReviewDTO): UserReviewDTO {

        //리뷰 수정
        var userReview = userRepository.findByUserIdAndPlaceId(userReviewDTO.userId, userReviewDTO.placeId)
        userReview.content = userReviewDTO.content
        userReview.attachedPhotoIds = userReviewDTO.attachedPhotoIds
        userReview.updateDate = LocalDateTime.now()

        //리뷰 포인트 변경
        setUserReviewPoint(userReviewDTO)

        return userReviewDTO
    }

    //리뷰 포인트 적용
    @Transactional
    fun setUserReviewPoint(userReviewDTO: UserReviewDTO): UserReviewPointEntity {

        var totalPoint: Long = 0
        var photoPoint: Long = 0
        var contentPoint: Long = 0
        var placePoint: Long = 0

        var userReviewPointInfo = userReviewPointRepository
            .findTop1ByUserIdOrderByRegisterDateDesc(userReviewDTO.userId)

        var userReviewPointInfoTemp:List<UserReviewPointEntity>

        if (userReviewDTO.action == "MOD" && userReviewPointInfo.size > 0) {
            photoPoint = userReviewPointInfo[0].photoPoint ?: 0
            contentPoint = userReviewPointInfo[0].contentPoint ?: 0
            placePoint = userReviewPointInfo[0].placePoint ?: 0
        }else if (userReviewDTO.action == "ADD" && userReviewPointInfo.size > 0) {
            //등록된 리뷰가 없는 첫 리뷰 작성자 일 경우 처리
            totalPoint = userReviewPointInfo[0].point ?: 0 // 사용자 포인트 합
        }

        log.info { "> photoPoint " + photoPoint }
        log.info { "> contentPoint " + contentPoint }
        log.info { "> totalPoint " + totalPoint }
        log.info { "> placePoint " + placePoint }

        // 등록자 이외 지역 리뷰 조회
        var otherUserReview = userReviewPointRepository
            .findByPlaceIdAndPlacePointNot(userReviewDTO.placeId, 0)

        // 포인 증감 처리
        var point = PointTest(totalPoint, photoPoint, contentPoint, placePoint, userReviewDTO, otherUserReview)
        point.procPoint()

        log.info(">> point " + point.toString())

        var userReviewPoint = UserReviewPointEntity()
        userReviewPoint.userId = userReviewDTO.userId
        userReviewPoint.placeId = userReviewDTO.placeId
        userReviewPoint.point = point.component1()
        userReviewPoint.photoPoint = point.component2()
        userReviewPoint.contentPoint = point.component3()
        userReviewPoint.placePoint = point.component4()

        return userReviewPointRepository.save(userReviewPoint)
    }

    //사용자 리뷰 포인트 증감 data class
    data class PointTest(
        var totalPoint: Long = 0,
        var photoPoint: Long = 0,
        var contentPoint: Long = 0,
        var placePoint: Long = 0,
        var userReviewDTO: UserReviewDTO,
        var otherUserReview: List<UserReviewPointEntity>
    ) {
        fun procPoint() {
            //첨부 이미지 가산점 적용
            if (userReviewDTO.attachedPhotoIds.isNotEmpty()) {
                var attachedImages = JsonParser.parseString(userReviewDTO.attachedPhotoIds) as JsonArray
                log.info("> attachedImages " + attachedImages.size() + " | " + attachedImages[1])
                //첨부 이미지가 1개 이상일 시 포인트 처리
                if (attachedImages.size() > 0) {
                    if (userReviewDTO.action == "MOD" && photoPoint <= 0) { // 기존 추가 점수가 없을 경우에만 점수 증가
                        photoPoint++
                        totalPoint++
                    }else if(userReviewDTO.action == "ADD"){
                        photoPoint++
                        totalPoint++
                    }
                } else {
                    if (photoPoint > 0) {  // 기존 건의 이미지 추가 포인트가 있을 경우 차감
                        photoPoint--
                        totalPoint--
                    }
                }
            } else {
                if (photoPoint > 0) {  // 기존 건의 이미지 추가 포인트가 있을 경우 차감
                    photoPoint--
                    totalPoint--
                }
            }

            //리뷰 내용(Content)이 있을 경우 포인트 처리
            if (userReviewDTO.content.length > 0) {
                if (userReviewDTO.action == "MOD" && contentPoint <= 0) { // 기존 추가 점수가 없을 경우에만 점수 증가
                    contentPoint++
                    totalPoint++
                }else if(userReviewDTO.action == "ADD"){
                    contentPoint++
                    totalPoint++
                }
            } else {
                if (contentPoint > 0) { // 기존 건의 리뷰 내용 추가 포인트가 있을 경우 차감
                    contentPoint--
                    totalPoint--
                }
            }

            //등록자 이외 등록된 지역 리뷰가 없을 경우 가산점 적용
            if (otherUserReview.size <= 0) {
                if (totalPoint > 0) {
                    placePoint = 1
                    totalPoint++
                }
            }
        }
    }


    //리뷰 삭제
    @Transactional
    fun setDeleteReview(userReviewDTO: UserReviewDTO): UserReviewDTO {
        //사용자 리뷰 삭제
        var userReview = userRepository.findByUserIdAndPlaceId(userReviewDTO.userId, userReviewDTO.placeId)
        userRepository.delete(userReview)

        var userReviewPointInfo = userReviewPointRepository
            .findTop1ByUserIdOrderByRegisterDateDesc(userReviewDTO.userId)

        var totalPoint = userReviewPointInfo[0].point ?: 0
        var photoPoint = userReviewPointInfo[0].photoPoint ?: 0
        var contentPoint = userReviewPointInfo[0].contentPoint ?: 0
        var placePoint = userReviewPointInfo[0].placePoint ?: 0

        var point = totalPoint - (photoPoint + contentPoint + placePoint)

        //사용자 지역 사진 내용 추가 점수 0 초기화 및 포인트 차감
        var userReviewPoint = UserReviewPointEntity()
        userReviewPoint.userId = userReviewDTO.userId
        userReviewPoint.placeId = userReviewDTO.placeId
        userReviewPoint.point = point
        userReviewPoint.photoPoint = 0
        userReviewPoint.contentPoint = 0
        userReviewPoint.placePoint = 0
        userReviewPointRepository.save(userReviewPoint)

        return userReviewDTO
    }

    //사용자 지역 리뷰 조회
    fun getUserReview(userReviewDTO: UserReviewDTO): List<UserReviewEntity> {
        return userRepository.findTop1ByUserIdAndPlaceId(userReviewDTO.userId, userReviewDTO.placeId)
    }

    //리뷰 지역 별 사용자 포인트 조회
    fun getPlacePoint(userId: String): List<UserReviewPointEntity>? {
        return userReviewPointRepository.findTop1ByUserIdOrderByRegisterDateDesc(userId)
    }

    fun getReview(type: String): Any {
        return if ("point" == type)
            userReviewPointRepository.findTop50By().sortedBy {it.seq  }.reversed()
        else
            userRepository.findTop50By().sortedBy {it.seq  }.reversed()

    }

}