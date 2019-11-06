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
package org.springblade.modules.taskdata.service.impl;

import org.springblade.modules.taskdata.entity.TblTaskData;
import org.springblade.modules.taskdata.vo.TblTaskDataVO;
import org.springblade.modules.taskdata.mapper.TblTaskDataMapper;
import org.springblade.modules.taskdata.service.ITblTaskDataService;
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
public class TblTaskDataServiceImpl extends BaseServiceImpl<TblTaskDataMapper, TblTaskData> implements ITblTaskDataService {

	@Override
	public IPage<TblTaskDataVO> selectTblTaskDataPage(IPage<TblTaskDataVO> page, TblTaskDataVO tblTaskData) {
		return page.setRecords(baseMapper.selectTblTaskDataPage(page, tblTaskData));
	}

	@Override
	public TblTaskData selectTblTaskDataOne(Long taskId) {
		return baseMapper.selectTblTaskDataOne(taskId);
	}

	@Override
	public TblTaskData updateTblTaskData(TblTaskData tblTaskData) {
		TblTaskData t;
		synchronized("data"){
			t=baseMapper.updateTblTaskData(tblTaskData);
		}
		return t;
	}

}
