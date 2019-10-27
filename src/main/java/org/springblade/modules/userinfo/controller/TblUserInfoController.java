/*
 *      Copyright (c) 2018-2028, Chill Zhuang All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 *  Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  Neither the name of the dreamlu.net developer nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *  Author: Chill 庄骞 (smallchill@163.com)
 */
package org.springblade.modules.userinfo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;

import org.springblade.modules.userinfo.entity.TblUserInfo;
import org.springblade.modules.userinfo.service.ITblUserInfoService;
import org.springblade.modules.userinfo.vo.TblUserInfoVO;
import org.springblade.modules.userinfo.wrapper.TblUserInfoWrapper;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;

/**
 *  控制器
 *
 * @author BladeX
 * @since 2019-09-04
 */
@RestController
@AllArgsConstructor
@RequestMapping("/tbluserinfo")
@Api(value = "", tags = "接口")
public class TblUserInfoController extends BladeController {

	private ITblUserInfoService tblUserInfoService;

	/**
	* 详情
	*/
	@GetMapping("/detail")
	@ApiOperation(value = "详情", notes = "传入tblUserInfo", position = 1)
	public R<TblUserInfoVO> detail(TblUserInfo tblUserInfo) {
		TblUserInfo detail = tblUserInfoService.getOne(Condition.getQueryWrapper(tblUserInfo));
		return R.data(TblUserInfoWrapper.build().entityVO(detail));
	}

	/**
	* 分页 
	*/
	@PostMapping("/list")
	@ApiOperation(value = "分页", notes = "传入tblUserInfo", position = 2)
	public R<IPage<TblUserInfoVO>> list(TblUserInfoVO tblUserInfo, Query query) {
		IPage<TblUserInfoVO> pages = tblUserInfoService.selectTblUserInfoPage(Condition.getPage(query), tblUserInfo);
//		IPage<TblUserInfo> pages = tblUserInfoService.page(Condition.getPage(query), Condition.getQueryWrapper(tblUserInfo));
		return R.data(pages);
	}

	/**
	* 自定义分页 
	*/
	@GetMapping("/page")
	@ApiOperation(value = "分页", notes = "传入tblUserInfo", position = 3)
	public R<IPage<TblUserInfoVO>> page(TblUserInfoVO tblUserInfo, Query query) {
		IPage<TblUserInfoVO> pages = tblUserInfoService.selectTblUserInfoPage(Condition.getPage(query), tblUserInfo);
		return R.data(pages);
	}


	/**
	 * 带条件查询
	 */
	@PostMapping("/queryUserInfo")
	@ApiOperation(value = "带条件查询", notes = "传入tblUserInfo", position = 7)
	public R<IPage<TblUserInfoVO>> queryUserInfo(@RequestBody TblUserInfoVO tblUserInfo, Query query) {
		String id = tblUserInfo.getId();
		String name = tblUserInfo.getName();
		String sex = tblUserInfo.getSex();
		IPage<TblUserInfoVO> pages = tblUserInfoService.selectTblUserInfo(Condition.getPage(query), id, name, sex);
		return R.data(pages);
	}


	/**
	* 新增 
	*/
	@PostMapping("/save")
	@ApiOperation(value = "新增", notes = "传入tblUserInfo", position = 4)
	public R save(@Valid @RequestBody TblUserInfo tblUserInfo) {
		tblUserInfo.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		return R.status(tblUserInfoService.save(tblUserInfo));
	}

	/**
	* 修改 
	*/
	@PostMapping("/update")
	@ApiOperation(value = "修改", notes = "传入tblUserInfo", position = 5)
	public R update(@Valid @RequestBody TblUserInfo tblUserInfo) {
		tblUserInfo.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		return R.status(tblUserInfoService.updateById(tblUserInfo));
	}

	/**
	* 新增或修改 
	*/
	@PostMapping("/submit")
	@ApiOperation(value = "新增或修改", notes = "传入tblUserInfo", position = 6)
	public R submit(@Valid @RequestBody TblUserInfo tblUserInfo) {
		return R.status(tblUserInfoService.saveOrUpdate(tblUserInfo));
	}

	

	
}
