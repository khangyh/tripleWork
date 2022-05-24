package com.triple.homework.review.controller

import com.triple.homework.common.model.ResponseDTO
import com.triple.homework.review.entity.UserReviewPointEntity
import com.triple.homework.review.model.UserReviewDTO
import com.triple.homework.review.service.UserReviewService
import mu.KLogging
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

//private val logger = KotlinLogging.logger {}
private val log = KLogging().logger()

@RestController
@RequestMapping("/api/v1")
class UserReviewController {

    @Autowired
    private lateinit var modelMapper: ModelMapper

    @Autowired
    lateinit var tourService: UserReviewService

    //리뷰등록
    @PostMapping("/addReview")
    fun setUserReview(@RequestBody userReviewDTO: UserReviewDTO): ResponseEntity<ResponseDTO> {

        var responseDTO = ResponseDTO()

        var userReviews = tourService.getUserReview(userReviewDTO)

        //리뷰 등록 일 경우
        if ("ADD" == userReviewDTO.action) {
            //리뷰 조회
            if (userReviews.size > 0) {
                responseDTO.resultCode = "FAIL"
                responseDTO.resultMessage = "등록 된 리뷰가 존재 합니다."
            } else {
                //리뷰 등록
                responseDTO.resultBody = tourService.setReview(userReviewDTO)
            }
        } else if ("MOD" == userReviewDTO.action) {
            if (userReviews.size == 0) {
                responseDTO.resultCode = "FAIL"
                responseDTO.resultMessage = "등록 된 리뷰가 없습니다."
            } else {
                //리뷰 수정
                responseDTO.resultBody = tourService.setReviewUpdate(userReviewDTO)
            }

        } else if ("DELETE".equals(userReviewDTO.action)) {
            if (userReviews.size == 0) {
                responseDTO.resultCode = "FAIL"
                responseDTO.resultMessage = "등록 된 리뷰가 없습니다."
            } else {
                //리뷰 삭제
                tourService.setDeleteReview(userReviewDTO)
            }

        }
        return ResponseEntity.ok().body(responseDTO)
    }

    @PostMapping("/getUserReviewPoint")
    fun setUserReviewTest(@RequestParam userId: String): ResponseEntity<ResponseDTO> {

        var responseDTO = ResponseDTO()

        var result = tourService.getPlacePoint(userId)

        if (result != null && result.size == 0) {
            responseDTO.resultCode = "FAIL"
            responseDTO.resultMessage = "등록된 리뷰가 없습니다."
        } else {
            responseDTO.resultBody = modelMapper.map(result?.get(0), UserReviewPointEntity::class.java)
        }

        return ResponseEntity.ok().body(responseDTO)
    }
}