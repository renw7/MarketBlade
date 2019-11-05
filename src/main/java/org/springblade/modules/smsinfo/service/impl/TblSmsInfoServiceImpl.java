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
package org.springblade.modules.smsinfo.service.impl;

import org.springblade.modules.smsinfo.entity.TblSmsInfo;
import org.springblade.modules.smsinfo.vo.TblSmsInfoVO;
import org.springblade.modules.smsinfo.mapper.TblSmsInfoMapper;
import org.springblade.modules.smsinfo.service.ITblSmsInfoService;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 *  服务实现类
 *
 * @author BladeX
 * @since 2019-11-04
 */
@Service
public class TblSmsInfoServiceImpl extends BaseServiceImpl<TblSmsInfoMapper, TblSmsInfo> implements ITblSmsInfoService {

	@Override
	public IPage<TblSmsInfoVO> selectTblSmsInfoPage(IPage<TblSmsInfoVO> page, TblSmsInfoVO tblSmsInfo) {
		return page.setRecords(baseMapper.selectTblSmsInfoPage(page, tblSmsInfo));
	}

	@Override
	public TblSmsInfo insertTblSmsInfo(TblSmsInfo TblSmsInfo) {
		return baseMapper.insertTblSmsInfo(TblSmsInfo);
	}

}
