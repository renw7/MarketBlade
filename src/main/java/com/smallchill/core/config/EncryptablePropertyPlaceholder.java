package com.smallchill.core.config;

import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import com.smallchill.core.toolbox.kit.AESKit;
import com.smallchill.core.toolbox.support.Convert;

public class EncryptablePropertyPlaceholder extends PropertyPlaceholderConfigurer {

	protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props) throws BeansException {
		try {
			for (Object key : props.keySet()) {
				if (Convert.toStr(key).contains("encrypt")) {
					String value = props.getProperty(Convert.toStr(key));
					if (null != value) {
						try {
							String desryptValue = AESKit.decrypt(value);
							props.setProperty(Convert.toStr(key), desryptValue);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
			super.processProperties(beanFactory, props);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BeanInitializationException(e.getMessage());
		}
	}

}
