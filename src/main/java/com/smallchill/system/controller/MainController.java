package com.smallchill.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smallchill.common.base.BaseController;
import com.smallchill.core.constant.Const;

@Controller
@RequestMapping("/main")
public class MainController extends BaseController {

	@GetMapping
	public String index() {
		return Const.INDEX_MAIN_REALPATH;
	}
	
}
