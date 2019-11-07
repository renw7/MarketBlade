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
package org.springblade.modules.taskdata.controller;

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
import org.springblade.modules.taskdata.entity.TblTaskData;
import org.springblade.modules.taskdata.vo.TblTaskDataVO;
import org.springblade.modules.taskdata.service.ITblTaskDataService;
import org.springblade.core.boot.ctrl.BladeController;

/**
 *  控制器
 *
 * @author BladeX
 * @since 2019-11-01
 */
@RestController
@AllArgsConstructor
@RequestMapping("/tbltaskdata")
@Api(value = "", tags = "接口")
public class TblTaskDataController extends BladeController {

	private ITblTaskDataService tblTaskDataService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入tblTaskData")
	public R<TblTaskData> detail(TblTaskData tblTaskData) {
		TblTaskData detail = tblTaskDataService.getOne(Condition.getQueryWrapper(tblTaskData));
		return R.data(detail);
	}

	/**
	 * 分页
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入tblTaskData")
	public R<IPage<TblTaskData>> list(TblTaskData tblTaskData, Query query) {
		IPage<TblTaskData> pages = tblTaskDataService.page(Condition.getPage(query), Condition.getQueryWrapper(tblTaskData));
		return R.data(pages);
	}

	/**
	 * 自定义分页
	 */
	@PostMapping("/page")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "分页", notes = "传入tblTaskData")
	public R<IPage<TblTaskDataVO>> page(TblTaskDataVO tblTaskData, Query query) {
		IPage<TblTaskDataVO> pages = tblTaskDataService.selectTblTaskDataPage(Condition.getPage(query), tblTaskData);
		return R.data(pages);
	}

	/**
	 * 查询一条任务post
	 */
	@PostMapping("/selectOnePost")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "单条查询", notes = "传入tblTaskData")
	public R<TblTaskData> selectOnePost(@Valid @RequestBody TblTaskDataVO tblTaskData, Query query) {
		Long taskId = tblTaskData.getTaskId();
		System.out.println("查询任务数据"+taskId);
		TblTaskData record = tblTaskDataService.selectTblTaskDataOne(taskId);
		if (record.getDataId() == null || record.getDataId() == 0L)
			return R.fail("无数据返回");
		return R.data(record);
	}

	/**
	 * 查询一条任务get   浏览器输入网址添加条件报错
	 */
	@GetMapping("/selectOneGet")
	@ApiOperationSupport(order = 8)
	@ApiOperation(value = "单条查询", notes = "传入tblTaskData")
	public R<TblTaskData> selectOneGet(TblTaskDataVO tblTaskData, Query query) {
		Long taskId = tblTaskData.getTaskId();
		System.out.println("taskId="+taskId);
		TblTaskData record = tblTaskDataService.selectTblTaskDataOne(taskId);
		return R.data(record);
	}

	/**
	 * 查询一条指定任务post
	 */
	@PostMapping("/selectSpePost")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "单条查询", notes = "传入tblTaskData")
	public R<TblTaskData> selectSpePost(@Valid @RequestBody TblTaskDataVO tblTaskData, Query query) {
		Long dataId = tblTaskData.getDataId();
		System.out.println("查询指定任务数据"+dataId);
		TblTaskData record = tblTaskDataService.selectTblTaskDataSpe(dataId);
		return R.data(record);
	}

	/**
	 * 修改数据
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "修改", notes = "传入tblTaskData")
	public TblTaskData update(@Valid @RequestBody TblTaskData tblTaskData) {
		return tblTaskDataService.updateTblTaskData(tblTaskData);
	}

	@GetMapping("/tblTaskDataStatistics")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "单条查询", notes = "传入tblTaskData")
	public R<IPage<TblTaskDataVO>> tblTaskDataStatistics(TblTaskDataVO tblTaskData, Query query) {
		Long staffId = tblTaskData.getStaffId();
		IPage<TblTaskDataVO> pages = tblTaskDataService.tblTaskDataStatistics(Condition.getPage(query), staffId);
		return R.data(pages);
	}


}
