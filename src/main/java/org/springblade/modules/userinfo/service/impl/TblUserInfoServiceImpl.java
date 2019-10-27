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
package org.springblade.modules.userinfo.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.modules.userinfo.entity.TblUserInfo;
import org.springblade.modules.userinfo.mapper.TblUserInfoMapper;
import org.springblade.modules.userinfo.service.ITblUserInfoService;
import org.springblade.modules.userinfo.vo.TblUserInfoVO;
import org.springframework.stereotype.Service;


/**
 *  服务实现类
 *
 * @author BladeX
 * @since 2019-09-04
 */
@Service
public class TblUserInfoServiceImpl extends ServiceImpl<TblUserInfoMapper, TblUserInfo> implements ITblUserInfoService {

	@Override
	public IPage<TblUserInfoVO> selectTblUserInfoPage(IPage<TblUserInfoVO> page, TblUserInfoVO tblUserInfo) {
		return page.setRecords(baseMapper.selectTblUserInfoPage(page, tblUserInfo));
	}

	@Override
	public IPage<TblUserInfoVO> selectTblUserInfo(IPage<TblUserInfoVO> page, String id, String name, String sex) {
		return page.setRecords(baseMapper.selectTblUserInfo(id, name, sex));
	}


}
