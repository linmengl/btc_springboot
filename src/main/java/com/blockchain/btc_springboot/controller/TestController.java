package com.blockchain.btc_springboot.controller;

import com.blockchain.btc_springboot.bean.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

	@RequestMapping("/a")
	public JsonResult test(String s){
		log.info("请求成功",s);
		return JsonResult.buildSuccessResult("success",null);
	}
}
