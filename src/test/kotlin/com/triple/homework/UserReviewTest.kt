package com.triple.homework

import com.google.gson.Gson
import com.triple.homework.review.model.UserReviewDTO
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.util.LinkedMultiValueMap
import org.yaml.snakeyaml.nodes.Tag.SEQ

@SpringBootTest
@AutoConfigureMockMvc
class UserReviewTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var gson: Gson

//    @Test
//    fun setUserReview(){
//        val uri: String = "/api/v1/addReview"
//        val queryParams = LinkedMultiValueMap<String,String>()
//        queryParams.add("type","REVIEW")
//        queryParams.add("action","ADD")
//        queryParams.add("reviewId","240a0658-dc5f-4878-9381-ebb7b2667772")
//        queryParams.add("content","좋아요!")
//        queryParams.add("attachedPhotoIds","[\"e4d1a64e-a531-46de-88d0-ff0ed70c0bb8\", \"afb0cef2-851d-4a50-bb07-9cc15cbdc332\"]")
//        queryParams.add("userId","3ede0ef2-92b7-4817-a5f3-0c575361f745")
//        queryParams.add("placeId","2e4baf1c-5acb-4efb-a1af-eddada31b00f")
//
//        mockMvc.perform(MockMvcRequestBuilders.post(uri).queryParams(queryParams))
//            .andExpect(MockMvcResultMatchers.status().isOk)
//            .andDo(MockMvcResultHandlers.print())
//    }

    @Test
    fun setUserReviewJson() {
        val uri: String = "/api/v1/addReview"
        val requestDto = UserReviewDTO(
            seq = null,
            type = "REVIEW",
            action = "DELETE",
            reviewId = "240a0658-dc5f-4878-9381-ebb7b2667772",
            content = "좋아요!",
//            attachedPhotoIds = "[\"e4d1a64e-a531-46de-88d0-ff0ed70c0bb8\", \"afb0cef2-851d-4a50-bb07-9cc15cbdc332\"]",
            attachedPhotoIds = "",
            userId = "3ede0ef2-92b7-4817-a5f3-0c575361f745",
            placeId = "2e4baf1c-5acb-4efb-a1af-eddada31b0f"
        )


        mockMvc.perform(
            MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(requestDto))
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andDo(MockMvcResultHandlers.print())

    }

    // 사용자 리뷰 포인트 조회
//    @Test
//    fun getUserReviewTestJson() {
//        val uri: String = "/api/v1/getUserReviewPoint"
//        val requestDto = UserReviewDTO(
//            seq = null,
//            type = "",
//            action = "",
//            reviewId = "",
//            content = "",
//            attachedPhotoIds = "",
//            userId = "3ede0ef2-92b7-4817-a5f3-0c575361f746",    // 조회시 usertId 필수
//            placeId = ""
//        )
//
//
//        mockMvc.perform(
//            MockMvcRequestBuilders.post(uri)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(gson.toJson(requestDto))
//        )
//            .andExpect(MockMvcResultMatchers.status().isOk)
//            .andDo(MockMvcResultHandlers.print())
//
//    }

    // 사용자 리뷰 포인트 조회
    @Test
    fun setUserReview(){
        val uri: String = "/api/v1/getUserReviewPoint"
        val queryParams = LinkedMultiValueMap<String,String>()
        queryParams.add("userId","3ede0ef2-92b7-4817-a5f3-0c575361f745")

        mockMvc.perform(MockMvcRequestBuilders.post(uri).queryParams(queryParams))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andDo(MockMvcResultHandlers.print())
    }
}