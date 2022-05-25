package com.triple.homework.review.repository

import com.triple.homework.review.entity.UserReviewPointEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserReviewPointRepository : JpaRepository<UserReviewPointEntity, Long> {
    fun findByPlaceIdAndUserIdNot(placeId: String, userId: String): List<UserReviewPointEntity>
    fun findByPlaceIdAndPlacePointNot(placeId: String, placePoint: Long): List<UserReviewPointEntity>
    fun findTop1ByUserIdAndPlaceIdOrderByRegisterDateDesc(userId: String, placeId: String): List<UserReviewPointEntity>
    fun findTop1ByUserIdOrderByRegisterDateDesc(userId: String): List<UserReviewPointEntity>
    fun findBySeq(seq: Long): UserReviewPointEntity
    fun findTop50By(): List<UserReviewPointEntity>

}
