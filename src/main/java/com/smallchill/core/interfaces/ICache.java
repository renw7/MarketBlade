package com.smallchill.core.interfaces;

import java.util.List;

import com.smallchill.core.interfaces.ILoader;

/**
 * 通用缓存接口
 */
public interface ICache {
	
	public void put(String cacheName, Object key, Object value);
	
	public <T> T get(String cacheName, Object key);
	
	@SuppressWarnings("rawtypes")
	public List getKeys(String cacheName);
	
	public void remove(String cacheName, Object key);
	
	public void removeAll(String cacheName);
	
	public <T> T get(String cacheName, Object key, ILoader iLoader);
	
	public <T> T get(String cacheName, Object key, Class<? extends ILoader> iLoaderClass);
	
}
