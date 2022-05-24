package com.triple.homework.review.repository

import com.triple.homework.review.entity.UserReviewEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserReviewRepository : JpaRepository<UserReviewEntity, Long> {
    fun findByUserIdAndPlaceId(userId: String, placeId: String): UserReviewEntity
    fun findTop1ByUserIdAndPlaceId(userId: String, placeId: String): List<UserReviewEntity>
    fun findBySeq(seq: Long): UserReviewEntity
    fun findAllBy(): List<UserReviewEntity>
}