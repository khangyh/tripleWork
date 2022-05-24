package com.triple.homework.common.model

data class ResponseDTO (
    var resultCode:String = "SUCCESS",
    var resultMessage:String = "성공",
    var resultBody:Any? = null
)