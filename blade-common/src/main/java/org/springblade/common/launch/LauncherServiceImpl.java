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
package org.springblade.common.launch;

import org.springblade.common.constant.CommonConstant;
import org.springblade.core.launch.constant.AppConstant;
import org.springblade.core.launch.service.LauncherService;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.Properties;

/**
 * 启动参数拓展
 *
 * @author smallchil
 */
public class LauncherServiceImpl implements LauncherService {

	@Override
	public void launcher(SpringApplicationBuilder builder, String appName, String profile) {
		Properties props = System.getProperties();
		props.setProperty("spring.cloud.consul.host", profile.equals(AppConstant.DEV_CDOE) ? CommonConstant.CONSUL_DEV_HOST : CommonConstant.CONSUL_PROD_HOST);
		props.setProperty("spring.cloud.consul.port", CommonConstant.CONSUL_PORT);
	}

}