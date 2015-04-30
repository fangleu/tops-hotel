package com.travelzen.tops.hotel.elong.redis.entity;

import java.io.Serializable;

public class RedisEntity implements Serializable {
	
	/**
	 * @author muyuansun
	 * @date 2014-9-19 下午6:16:22
	 */
	private static final long serialVersionUID = -7004353082021532410L;
	
	public byte[] cacheContent = null;

	public byte[] getCacheContent() {
		return cacheContent;
	}

	public void setCacheContent(byte[] cacheContent) {
		this.cacheContent = cacheContent;
	}
	
}
