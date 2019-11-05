/**
 * Copyright (c) 2018-2028, Chill Zhuang 庄骞 (smallchill@163.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springblade.modules.staffinfo.service.impl;

import org.springblade.modules.staffinfo.entity.TblStaffInfo;
import org.springblade.modules.staffinfo.vo.TblStaffInfoVO;
import org.springblade.modules.staffinfo.mapper.TblStaffInfoMapper;
import org.springblade.modules.staffinfo.service.ITblStaffInfoService;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 *  服务实现类
 *
 * @author BladeX
 * @since 2019-11-01
 */
@Service
public class TblStaffInfoServiceImpl extends BaseServiceImpl<TblStaffInfoMapper, TblStaffInfo> implements ITblStaffInfoService {

	@Override
	public IPage<TblStaffInfoVO> selectTblStaffInfoPage(IPage<TblStaffInfoVO> page, TblStaffInfoVO tblStaffInfo) {
		return page.setRecords(baseMapper.selectTblStaffInfoPage(page, tblStaffInfo));
	}

	@Override
	public IPage<TblStaffInfoVO> checkUser(IPage<TblStaffInfoVO> page, String staffUsrname, String serialNumber, String staffPwd) {
		return page.setRecords(baseMapper.checkUser(page, staffUsrname, serialNumber, staffPwd));
	}

}
