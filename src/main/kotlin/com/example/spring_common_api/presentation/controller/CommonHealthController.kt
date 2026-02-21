package com.example.spring_common_api.presentation.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("\${common.api.base-path:/common}")
class CommonHealthController {

    @GetMapping("/health")
    fun health() = "OK"
}