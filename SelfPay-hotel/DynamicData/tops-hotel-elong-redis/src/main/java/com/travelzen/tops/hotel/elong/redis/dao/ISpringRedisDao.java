package com.travelzen.tops.hotel.elong.redis.dao;

import java.io.Serializable;

/**
 * 
 * @author tianjiang.tang
 * @date 2014-9-19 下午1:56:40
 */
public interface ISpringRedisDao {
	/**
	 */
	boolean save(String key, Serializable object);

	/**
	 * @param key
	 * @return
	 */
	Object read(String key);

	/**
	 * @param key
	 */
	void delete(String key);

	/**
	 * save expire
	 * 其中expireTime以秒为单位
	 */
	boolean save(String key, Serializable object, long expireTime);
}
