package com.smallchill.core.constant;

import com.smallchill.core.listener.ConfigListener;

public interface ConstConfig {

	String DBTYPE = ConfigListener.map.get("master.dbType");
	String DRIVER = ConfigListener.map.get("master.driver");
	String URL = ConfigListener.map.get("master.url");
	String USERNAME = ConfigListener.map.get("master.username");
	String PASSWORD = ConfigListener.map.get("master.password");
	String INITIALSIZE = ConfigListener.map.get("druid.initialSize");
	String MAXACTIVE = ConfigListener.map.get("druid.maxActive");
	String MINIDLE = ConfigListener.map.get("druid.minIdle");
	String MAXWAIT = ConfigListener.map.get("druid.maxWait");

	String DOMAIN = ConfigListener.map.get("config.domain");
	String REMOTE_MODE = ConfigListener.map.get("config.remoteMode");
	String REMOTE_PATH = ConfigListener.map.get("config.remotePath");
	String UPLOAD_PATH = ConfigListener.map.get("config.uploadPath");
	String DOWNLOAD_PATH = ConfigListener.map.get("config.downloadPath");
	
}
