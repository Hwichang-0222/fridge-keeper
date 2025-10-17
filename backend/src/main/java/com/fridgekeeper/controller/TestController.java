package com.fridgekeeper.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@GetMapping("/test")
	public String test() {
		return "냉장고지킴이 서버 연결 성공!";
	}
}
