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
package org.springblade.modules.taskdata.service;

import org.springblade.modules.taskdata.entity.TblTaskData;
import org.springblade.modules.taskdata.vo.TblTaskDataVO;
import org.springblade.core.mp.base.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 *  服务类
 *
 * @author BladeX
 * @since 2019-11-01
 */
public interface ITblTaskDataService extends BaseService<TblTaskData> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param tblTaskData
	 * @return
	 */
	IPage<TblTaskDataVO> selectTblTaskDataPage(IPage<TblTaskDataVO> page, TblTaskDataVO tblTaskData);

	/**
	 * 查询一条任务数据
	 *
	 * @param taskId
	 * @return TblTaskData
	 */
	TblTaskData selectTblTaskDataOne(Long taskId);

	/**
	 * 修改任务数据
	 *
	 * @param tblTaskData
	 * @return
	 */
	TblTaskData updateTblTaskData(TblTaskData tblTaskData);

}
