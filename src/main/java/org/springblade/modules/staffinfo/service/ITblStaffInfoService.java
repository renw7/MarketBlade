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
package org.springblade.modules.staffinfo.service;

import org.springblade.modules.staffinfo.entity.TblStaffInfo;
import org.springblade.modules.staffinfo.vo.TblStaffInfoVO;
import org.springblade.core.mp.base.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 *  服务类
 *
 * @author BladeX
 * @since 2019-11-01
 */
public interface ITblStaffInfoService extends BaseService<TblStaffInfo> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param tblStaffInfo
	 * @return
	 */
	IPage<TblStaffInfoVO> selectTblStaffInfoPage(IPage<TblStaffInfoVO> page, TblStaffInfoVO tblStaffInfo);

	IPage<TblStaffInfoVO> checkUser(IPage<TblStaffInfoVO> page, String staffUsrname, String serialNumber, String staffPwd);

	int updatePwd(IPage page, String staffPwd, String staffNo);

}
