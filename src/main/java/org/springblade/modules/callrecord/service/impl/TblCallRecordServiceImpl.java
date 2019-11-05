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
package org.springblade.modules.callrecord.service.impl;

import org.springblade.modules.callrecord.entity.TblCallRecord;
import org.springblade.modules.callrecord.vo.TblCallRecordVO;
import org.springblade.modules.callrecord.mapper.TblCallRecordMapper;
import org.springblade.modules.callrecord.service.ITblCallRecordService;
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
public class TblCallRecordServiceImpl extends BaseServiceImpl<TblCallRecordMapper, TblCallRecord> implements ITblCallRecordService {

	@Override
	public IPage<TblCallRecordVO> selectTblCallRecordPage(IPage<TblCallRecordVO> page, String resultCode) {
		return page.setRecords(baseMapper.selectTblCallRecordPage(page, resultCode));
	}

	@Override
	public IPage<TblCallRecordVO> selectTblCallRecord(IPage<TblCallRecordVO> page, Long recordId) {
		return page.setRecords(baseMapper.selectTblCallRecord(page, recordId));
	}

}
