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
package org.springblade.modules.smsinfo.controller;

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
import org.springblade.modules.smsinfo.entity.TblSmsInfo;
import org.springblade.modules.smsinfo.vo.TblSmsInfoVO;
import org.springblade.modules.smsinfo.service.ITblSmsInfoService;
import org.springblade.core.boot.ctrl.BladeController;

/**
 *  控制器
 *
 * @author BladeX
 * @since 2019-11-04
 */
@RestController
@AllArgsConstructor
@RequestMapping("/tblsmsinfo")
@Api(value = "", tags = "接口")
public class TblSmsInfoController extends BladeController {

	private ITblSmsInfoService tblSmsInfoService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入tblSmsInfo")
	public R<TblSmsInfo> detail(TblSmsInfo tblSmsInfo) {
		TblSmsInfo detail = tblSmsInfoService.getOne(Condition.getQueryWrapper(tblSmsInfo));
		return R.data(detail);
	}

	/**
	 * 分页
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入tblSmsInfo")
	public R<IPage<TblSmsInfo>> list(TblSmsInfo tblSmsInfo, Query query) {
		IPage<TblSmsInfo> pages = tblSmsInfoService.page(Condition.getPage(query), Condition.getQueryWrapper(tblSmsInfo));
		return R.data(pages);
	}

	/**
	 * 自定义分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "分页", notes = "传入tblSmsInfo")
	public R<IPage<TblSmsInfoVO>> page(TblSmsInfoVO tblSmsInfo, Query query) {
		IPage<TblSmsInfoVO> pages = tblSmsInfoService.selectTblSmsInfoPage(Condition.getPage(query), tblSmsInfo);
		return R.data(pages);
	}

	/**
	 * 新增
	 */
	@PostMapping("/saveSms")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入tblSmsInfo")
	public TblSmsInfo saveSms(@Valid @RequestBody TblSmsInfo tblSmsInfo) {
		System.out.println("短信插入"+tblSmsInfo);
		return tblSmsInfoService.insertTblSmsInfo(tblSmsInfo);
	}



}
