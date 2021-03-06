/**
 * Copyright (c) 2018-2028, Chill Zhuang 庄骞 (smallchill@163.com).
 * <p>
 * Licensed under the GNU LESSER GENERAL PUBLIC LICENSE 3.0;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.gnu.org/licenses/lgpl.html
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springblade.modules.callrecord.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;

import javax.validation.Valid;

import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.modules.callrecord.entity.TblCallRecord;
import org.springblade.modules.callrecord.vo.TblCallRecordVO;
import org.springblade.modules.callrecord.service.ITblCallRecordService;
import org.springblade.core.boot.ctrl.BladeController;


/**
 *  控制器
 *
 * @author BladeX
 * @since 2019-11-04
 */
@RestController
@AllArgsConstructor
@RequestMapping("/tblcallrecord")
@Api(value = "", tags = "接口")
public class TblCallRecordController extends BladeController {

	private ITblCallRecordService tblCallRecordService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入tblCallRecord")
	public R<TblCallRecord> detail(TblCallRecord tblCallRecord) {
		TblCallRecord detail = tblCallRecordService.getOne(Condition.getQueryWrapper(tblCallRecord));
		return R.data(detail);
	}

	/**
	 * 分页 
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入tblCallRecord")
	public R<IPage<TblCallRecord>> list(TblCallRecord tblCallRecord, Query query) {
		IPage<TblCallRecord> pages = tblCallRecordService.page(Condition.getPage(query), Condition.getQueryWrapper(tblCallRecord));
		return R.data(pages);
	}

	/**
	 * 自定义分页 
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "分页", notes = "传入tblCallRecord")
	public R<IPage<TblCallRecordVO>> page(TblCallRecordVO tblCallRecord, Query query) {
		String resultCode = tblCallRecord.getResultCode();
		Long staffId = tblCallRecord.getStaffId();
		IPage<TblCallRecordVO> pages = tblCallRecordService.selectTblCallRecordPage(Condition.getPage(query), staffId, resultCode);
		return R.data(pages);
	}

	/**
	 * 自定义分页
	 */
	@GetMapping("/info")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "分页", notes = "传入tblCallRecord")
	public R<IPage<TblCallRecordVO>> info(TblCallRecordVO tblCallRecord, Query query) {
		IPage<TblCallRecordVO> pages = tblCallRecordService.selectTblCallRecord(Condition.getPage(query), tblCallRecord.getRecordId());
		return R.data(pages);
	}

	/**
	 * 新增 
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入tblCallRecord")
	public R save(@Valid @RequestBody TblCallRecord tblCallRecord) {
		return R.status(tblCallRecordService.save(tblCallRecord));
	}

	/**
	 * 修改 
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入tblCallRecord")
	public R update(@Valid @RequestBody TblCallRecord tblCallRecord) {
		return R.status(tblCallRecordService.updateById(tblCallRecord));
	}

	/**
	 * 新增或修改 
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入tblCallRecord")
	public R submit(@Valid @RequestBody TblCallRecord tblCallRecord) {
		return R.status(tblCallRecordService.saveOrUpdate(tblCallRecord));
	}

	
	/**
	 * 删除 
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(tblCallRecordService.deleteLogic(Func.toIntList(ids)));
	}

	@GetMapping("/statistics")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "分页", notes = "传入tblCallRecord")
	public R<IPage<TblCallRecordVO>> tblCallRecordStatistics(TblCallRecordVO tblCallRecord, Query query) {
		String resultCode = tblCallRecord.getResultCode();
		Long staffId = tblCallRecord.getStaffId();
		IPage<TblCallRecordVO> pages = tblCallRecordService.tblCallRecordStatistics(Condition.getPage(query), resultCode, staffId);
		return R.data(pages);
	}

	@GetMapping("/statisticsweek")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "分页", notes = "传入tblCallRecord")
	public R<IPage<TblCallRecordVO>> tblCallRecordStatisticsWeek(TblCallRecordVO tblCallRecord, Query query) {
		String resultCode = tblCallRecord.getResultCode();
		Long staffId = tblCallRecord.getStaffId();
		IPage<TblCallRecordVO> pages = tblCallRecordService.tblCallRecordStatisticsWeek(Condition.getPage(query), resultCode, staffId);
		return R.data(pages);
	}


	/**
	 * 新增 call
	 */
	@PostMapping("/saveCall")
	@ApiOperationSupport(order = 8)
	@ApiOperation(value = "新增", notes = "传入tblCallRecord")
	@ResponseBody
	public Long savePost(@Valid @RequestBody TblCallRecord tblCallRecord) {
		System.out.println("插入数据"+tblCallRecord);
		Long RecordId=tblCallRecordService.insertTblCallRecord(tblCallRecord);
		System.out.println("插入记录RecordId"+RecordId);
		return RecordId;
	}



}
