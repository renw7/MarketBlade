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
package org.springblade.modules.workreprot.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springblade.common.tool.POIUtil;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.modules.workreprot.entity.TblWorkReport;
import org.springblade.modules.workreprot.vo.TblWorkReportVO;
import org.springblade.modules.workreprot.service.ITblWorkReportService;
import org.springblade.core.boot.ctrl.BladeController;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *  控制器
 *
 * @author BladeX
 * @since 2019-11-09
 */
@RestController
@AllArgsConstructor
@RequestMapping("/tblworkreport")
@Api(value = "", tags = "接口")
public class TblWorkReportController extends BladeController {

	private ITblWorkReportService tblWorkReportService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入tblWorkReport")
	public R<TblWorkReport> detail(TblWorkReport tblWorkReport) {
		TblWorkReport detail = tblWorkReportService.getOne(Condition.getQueryWrapper(tblWorkReport));
		return R.data(detail);
	}

	/**
	 * 分页 
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入tblWorkReport")
	public R<IPage<TblWorkReport>> list(TblWorkReport tblWorkReport, Query query) {
		IPage<TblWorkReport> pages = tblWorkReportService.page(Condition.getPage(query), Condition.getQueryWrapper(tblWorkReport));
		return R.data(pages);
	}



	/**
	 * 新增 
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入tblWorkReport")
	public R save(@Valid @RequestBody TblWorkReport tblWorkReport) {
		return R.status(tblWorkReportService.save(tblWorkReport));
	}

	/**
	 * 修改 
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入tblWorkReport")
	public R update(@Valid @RequestBody TblWorkReport tblWorkReport) {
		return R.status(tblWorkReportService.updateById(tblWorkReport));
	}

	/**
	 * 新增或修改 
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入tblWorkReport")
	public R submit(@Valid @RequestBody TblWorkReport tblWorkReport) {
		return R.status(tblWorkReportService.saveOrUpdate(tblWorkReport));
	}

	
	/**
	 * 删除 
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(tblWorkReportService.deleteLogic(Func.toIntList(ids)));
	}



	/**
	 * 分页
	 */
	@PostMapping("/page")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "分页", notes = "传入tblCallRecord")
	public R<IPage<TblWorkReportVO>> page(@RequestBody TblWorkReportVO tblWorkReportVO, Query query) {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Long staffId = tblWorkReportVO.getStaffId();
		IPage<TblWorkReportVO> pages = tblWorkReportService.selectTblWorkReportPage(Condition.getPage(query), staffId);
		return R.data(pages);
	}



}
