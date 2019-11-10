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
package org.springblade.modules.staffinfo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import org.springblade.core.mp.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 实体类
 *
 * @author BladeX
 * @since 2019-11-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "TblStaffInfo对象", description = "TblStaffInfo对象")
public class TblStaffInfo extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户名
	 */
	@ApiModelProperty(value = "用户名")
	private String staffUsername;

	@TableId(value = "staffId", type = IdType.AUTO)
	@ApiModelProperty(value = "id")
	private Long staffId;

	@ApiModelProperty(value = "用户名")
	private String staffName;

	@ApiModelProperty(value = "用户名")
	private String staffNo;

	@ApiModelProperty(value = "用户名")
	private String staffPwd;

	@ApiModelProperty(value = "用户名")
	private String serialNumber;
}


