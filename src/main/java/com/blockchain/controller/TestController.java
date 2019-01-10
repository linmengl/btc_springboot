package com.blockchain.controller;

import com.blockchain.bean.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {


	@Resource
	private FiberController fiberController;

	@RequestMapping("/a")
	public JsonResult test(int count,String a){
		if (a.equals("fiber")) {
			fiberController.fiber(count);
		} else {
			fiberController.thread(count);
		}
		return JsonResult.buildSuccessResult("success",null);
	}

}
