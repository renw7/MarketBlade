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
package org.springblade.modules.workreprot.entity;

import org.springblade.core.mp.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Timestamp;

/**
 * 实体类
 *
 * @author BladeX
 * @since 2019-11-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "TblWorkReport对象", description = "TblWorkReport对象")
public class TblWorkReport extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private long reportId;
	private String cycleId;
	private long kpi ;
	private String compRate;
	private String level;
	private Timestamp updateTime;
	private long staffId ;

}
