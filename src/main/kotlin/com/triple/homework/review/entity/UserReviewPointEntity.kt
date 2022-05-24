package com.triple.homework.review.entity

import lombok.Getter
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "USER_REVIEW_POINT")
@Getter
@EntityListeners(AuditingEntityListener::class)
class UserReviewPointEntity(

) : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SEQ", nullable = false)
    var seq: Long? = null

    @Column(name = "USER_ID", nullable = false)
    var userId: String? = null

    @Column(name = "PLACE_ID", nullable = false)
    var placeId: String? = null

    @Column(name = "POINT", nullable = false)
    var point: Long? = null

    @Column(name = "PHOTO_POINT", nullable = false)
    var photoPoint: Long? = null

    @Column(name = "CONTENT_POINT", nullable = false)
    var contentPoint: Long? = null

    @Column(name = "PLACE_POINT", nullable = false)
    var placePoint: Long? = null

    @CreatedDate
    @Column(name = "REGISTER_DATE")
    var registerDate: LocalDateTime? = LocalDateTime.now()

    @LastModifiedDate
    @Column(name = "UPDATE_DATE")
    var updateDate: LocalDateTime? = LocalDateTime.now()
}