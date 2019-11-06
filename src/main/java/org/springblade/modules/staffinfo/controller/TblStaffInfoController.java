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
package org.springblade.modules.staffinfo.controller;

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
import org.springblade.modules.staffinfo.entity.TblStaffInfo;
import org.springblade.modules.staffinfo.vo.TblStaffInfoVO;
import org.springblade.modules.staffinfo.service.ITblStaffInfoService;
import org.springblade.core.boot.ctrl.BladeController;

/**
 *  控制器
 *
 * @author BladeX
 * @since 2019-11-01
 */
@RestController
@AllArgsConstructor
@RequestMapping("/tblstaffinfo")
@Api(value = "", tags = "接口")
public class TblStaffInfoController extends BladeController {

	private ITblStaffInfoService tblStaffInfoService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入tblStaffInfo")
	public R<TblStaffInfo> detail(TblStaffInfo tblStaffInfo) {
		TblStaffInfo detail = tblStaffInfoService.getOne(Condition.getQueryWrapper(tblStaffInfo));
		return R.data(detail);
	}

	/**
	 * 分页 
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入tblStaffInfo")
	public R<IPage<TblStaffInfo>> list(TblStaffInfo tblStaffInfo, Query query) {
		IPage<TblStaffInfo> pages = tblStaffInfoService.page(Condition.getPage(query), Condition.getQueryWrapper(tblStaffInfo));
		return R.data(pages);
	}

	/**
	 * 自定义分页
	 * http://localhost/tblstaffinfo/page
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "分页", notes = "传入tblStaffInfo")
	public R<IPage<TblStaffInfoVO>> page(TblStaffInfoVO tblStaffInfo, Query query) {
		IPage<TblStaffInfoVO> pages = tblStaffInfoService.selectTblStaffInfoPage(Condition.getPage(query), tblStaffInfo);
		return R.data(pages);
	}

	/**
	 * 新增 
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入tblStaffInfo")
	public R save(@Valid @RequestBody TblStaffInfo tblStaffInfo) {
		return R.status(tblStaffInfoService.save(tblStaffInfo));
	}

	/**
	 * 修改 
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入tblStaffInfo")
	public R update(@Valid @RequestBody TblStaffInfo tblStaffInfo) {
		return R.status(tblStaffInfoService.updateById(tblStaffInfo));
	}

	/**
	 * 新增或修改 
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入tblStaffInfo")
	public R submit(@Valid @RequestBody TblStaffInfo tblStaffInfo) {
		return R.status(tblStaffInfoService.saveOrUpdate(tblStaffInfo));
	}

	
	/**
	 * 删除 
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(tblStaffInfoService.deleteLogic(Func.toIntList(ids)));
	}


	/**
	 * 自定义分页
	 * http://localhost/tblstaffinfo/checkuser
	 */
	@PostMapping("/checkuser")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "分页", notes = "登录校验")
	public R<IPage<TblStaffInfoVO>> checkUser(@RequestBody TblStaffInfoVO tblStaffInfo, Query query) {
		String serialNumber = tblStaffInfo.getSerialNumber();
		String staffUsrname = tblStaffInfo.getStaffUsrname();
		String staffPwd = tblStaffInfo.getStaffPwd();
		IPage<TblStaffInfoVO> pages = tblStaffInfoService.checkUser(Condition.getPage(query), staffUsrname, serialNumber, staffPwd);
		if (pages.getRecords().size() == 0)
			return R.fail("无结果返回");
		return R.data(pages);
	}

	@PostMapping("/updatePwd")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "分页", notes = "登录校验")
	public R updatePwd(@RequestBody TblStaffInfoVO tblStaffInfo, Query query) {
		String staffPwd = tblStaffInfo.getStaffPwd();
		String staffNo = tblStaffInfo.getStaffNo();
		int code = tblStaffInfoService.updatePwd(Condition.getPage(query), staffPwd, staffNo);
		return R.success(String.valueOf(code));
	}



}
