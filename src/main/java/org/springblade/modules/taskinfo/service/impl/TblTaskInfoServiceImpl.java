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
package org.springblade.modules.taskinfo.service.impl;

import org.springblade.modules.taskinfo.entity.TblTaskInfo;
import org.springblade.modules.taskinfo.vo.TblTaskInfoVO;
import org.springblade.modules.taskinfo.mapper.TblTaskInfoMapper;
import org.springblade.modules.taskinfo.service.ITblTaskInfoService;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 *  服务实现类
 *
 * @author BladeX
 * @since 2019-11-03
 */
@Service
public class TblTaskInfoServiceImpl extends BaseServiceImpl<TblTaskInfoMapper, TblTaskInfo> implements ITblTaskInfoService {

	@Override
	public IPage<TblTaskInfoVO> selectTblTaskInfoPage1(IPage<TblTaskInfoVO> page, String tasktype) {
		return page.setRecords(baseMapper.selectTblTaskInfoPage1(page, tasktype));
	}

	@Override
	public IPage<TblTaskInfoVO> selectTblTaskInfoPage2(IPage<TblTaskInfoVO> page, long taskId) {
		return page.setRecords(baseMapper.selectTblTaskInfoPage2(page, taskId));
	}

}
