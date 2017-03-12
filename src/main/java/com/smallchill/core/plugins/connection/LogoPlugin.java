/**
 * Copyright (c) 2015-2017, Chill Zhuang 庄骞 (smallchill@163.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.smallchill.core.plugins.connection;

import com.smallchill.core.plugins.IPlugin;

public class LogoPlugin implements IPlugin {

	public void start() {
        printLogo();
    }

	public void stop() {
        printLogo();
	}

    public void printLogo() {
        StringBuilder sb = new StringBuilder();
        sb.append(" _____               _                 ______  _             _\n");
        sb.append("/  ___|             (_)                | ___ \\| |           | |\n");
        sb.append("\\ `--.  _ __   _ __  _  _ __    __ _   | |_/ /| |  __ _   __| |  ___\n");
        sb.append(" `--. \\| '_ \\ | '__|| || '_ \\  / _` |  | ___ \\| | / _` | / _` | / _ \\\n");
        sb.append("/\\__/ /| |_) || |   | || | | || (_| |  | |_/ /| || (_| || (_| ||  __/\n");
        sb.append("\\____/ | .__/ |_|   |_||_| |_| \\__, |  \\____/ |_| \\__,_| \\__,_| \\___|\n");
        sb.append("       | |                      __/ |\n");
        sb.append("       |_|                     |___/");
        System.out.println(sb.toString());
    }

}
