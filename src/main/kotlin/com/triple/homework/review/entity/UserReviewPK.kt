package com.triple.homework.review.entity

import lombok.Getter
import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.persistence.Id

@Getter
@Embeddable
//@EqualsAndHashCode(onlyExplicitlyIncluded = true)
data class UserReviewPK(

//    @Id
    @Column(name = "USER_ID", nullable = false)
    var userId: String,

//    @Id
    @Column(name = "PLACE_ID", nullable = false)
    var placeId: String

) : Serializable