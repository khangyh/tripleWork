package com.triple.homework.review.model

import org.hibernate.annotations.NotFound
import org.hibernate.validator.constraints.Length
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class UserReviewDTO(

    var seq: Long?,
    @field:NotBlank
    var type: String,
    @field:NotBlank
    var action: String,
    @field:NotBlank
    var reviewId: String,

    var content: String,
    var attachedPhotoIds: String,

    @field:NotBlank
    var userId: String,
    @field:NotBlank
    var placeId: String

) {
    constructor() : this(
        seq = null,
        type = "",
        action = "",
        reviewId = "",
        content = "",
        attachedPhotoIds = "",
        userId = "",
        placeId = ""
    )
}