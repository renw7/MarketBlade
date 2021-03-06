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
package org.springblade.modules.taskinfo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2019-11-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "TblTaskInfo对象", description = "TblTaskInfo对象")
public class TblTaskInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

	@TableId(value = "dataId", type = IdType.AUTO)
    private long taskId;
    private String taskName;
	private String taskType;
	private String taskStatus;
	private String productId;
	private String productName;
	private String voiceContent;
	private String smsContent;
	private Timestamp updateTime;


}
