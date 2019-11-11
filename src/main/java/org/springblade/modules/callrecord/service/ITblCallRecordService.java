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
package org.springblade.modules.callrecord.service;

import org.springblade.modules.callrecord.entity.TblCallRecord;
import org.springblade.modules.callrecord.vo.TblCallRecordVO;
import org.springblade.core.mp.base.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 *  服务类
 *
 * @author BladeX
 * @since 2019-11-04
 */
public interface ITblCallRecordService extends BaseService<TblCallRecord> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param tblCallRecord
	 * @return
	 */
	IPage<TblCallRecordVO> selectTblCallRecordPage(IPage<TblCallRecordVO> page, Long staffId, String resultCode);

	IPage<TblCallRecordVO> selectTblCallRecord(IPage<TblCallRecordVO> page, Long recordId);

	IPage<TblCallRecordVO> tblCallRecordStatistics(IPage<TblCallRecordVO> page, String resultCoded, Long staffId);

	IPage<TblCallRecordVO> tblCallRecordStatisticsWeek(IPage<TblCallRecordVO> page, String resultCoded, Long staffId);

	/**
	 * 插入通话记录
	 *
	 * @param tblCallRecord
	 * @return TblCallRecord
	 */
	Long insertTblCallRecord(TblCallRecord tblCallRecord);
}
