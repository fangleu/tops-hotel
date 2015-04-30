package com.travelzen.tops.hotel.elong.common.factory;

import com.travelzen.tops.hotel.elong.common.utils.ElongConfiguration;

public abstract class ConfigurationFactory {
	
	protected static ElongConfiguration configuration = null;
	
	public String get(String key) {
		return configuration.get(key);
	}
	
	public String getString(String key) {
		return configuration.getString(key);
	}
	
	public Integer getInt(String key) {
		return configuration.getInt(key);
	}
	
	public Integer getInt(String key,int defaultValue) {
		return configuration.getInt(key,defaultValue);
	}
	
	public boolean getBoolean(String key,boolean defaultValue) {
		return configuration.getBoolean(key,defaultValue);
	}

}
