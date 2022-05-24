package com.triple.homework.review.entity

import lombok.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "USER_REVIEW")
@Getter
//@IdClass(UserReviewEntity::class)
@EntityListeners(AuditingEntityListener::class)
class UserReviewEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SEQ", nullable = false)
    var seq: Long? = null,

    @Column(name = "USER_ID", nullable = false)
    var userId: String? = null,

    @Column(name = "PLACE_ID", nullable = false)
    var placeId: String? = null,

    @Column(name = "REVIEW_ID", nullable = false)
    var reviewId: String? = null,

    @Column(name = "CONTENT", nullable = false)
    var content: String? = null,

    @Column(name = "ATTACHED_PHOTO_ID", nullable = false)
    var attachedPhotoIds: String? = null,

    @CreatedDate
    @Column(name = "REGISTER_DATE")
    var registerDate: LocalDateTime? = LocalDateTime.now(),

    @LastModifiedDate
    @Column(name = "UPDATE_DATE")
    var updateDate: LocalDateTime? = LocalDateTime.now()

) : Serializable