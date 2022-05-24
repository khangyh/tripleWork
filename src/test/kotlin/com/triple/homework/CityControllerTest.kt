package com.triple.homework

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.util.LinkedMultiValueMap

@SpringBootTest
@AutoConfigureMockMvc
class CityControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun getTest(){
        val uri: String = "/api/v1/user"

        mockMvc.perform(MockMvcRequestBuilders.get(uri))
            .andExpect(MockMvcResultMatchers.status().isOk)
//            .andExpect(MockMvcResultMatchers.content().string("Hello"))
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun setUser(){
        val uri: String = "/api/v1/setUser"
        val queryParams = LinkedMultiValueMap<String,String>()
        queryParams.add("userId","test_999333111")
        queryParams.add("userName","테스터_01011111")

        mockMvc.perform(MockMvcRequestBuilders.put(uri).queryParams(queryParams))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andDo(MockMvcResultHandlers.print())
    }


}