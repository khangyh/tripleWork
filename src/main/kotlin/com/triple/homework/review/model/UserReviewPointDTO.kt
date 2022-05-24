package com.triple.homework.review.model

import javax.persistence.Column
import javax.validation.constraints.NotBlank

data class UserReviewPointDTO(
    @field:NotBlank
    var userId: String,
    @field:NotBlank
    var placeId: String,
//    @field:NotBlank
    var point: Long,
//    @field:NotBlank
    var photoPoint: Long,
//    @field:NotBlank
    var contentPoint: Long,
    var placePoint: Long,

    ) {
    constructor() : this(
        userId = "",
        placeId = "",
        point = 0,
        photoPoint = 0,
        placePoint = 0,
        contentPoint = 0
    )
}