package com.triple.homework.review.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class ReviewController {
    @GetMapping("/review")
    fun getHello(model:Model):String{
        return "review"
    }
}