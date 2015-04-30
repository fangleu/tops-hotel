/**
 * Aug 29, 2012
 */
package com.travelzen.tops.hotel.elong.redis.dao.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Repository;

import com.travelzen.tops.hotel.elong.redis.dao.ISpringRedisDao;

/**
 * 
 * @author jiangning.cui
 * @version 1.0
 * @since 1.0
 */
@Repository("springRedisDao")
public class SpringRedisDao implements ISpringRedisDao {
	
	private final Logger LOG = LoggerFactory.getLogger(SpringRedisDao.class);
	
	public final static int DEFAULTEXPIRETIME = 30*60;// 过期时间默认30分钟

	@Resource
	private RedisTemplate<Serializable, Serializable> redisTemplate;

	@Override
	public boolean save(final String key, final Serializable object) {
		//try {
			return save(key, object, DEFAULTEXPIRETIME);
		/*} catch (Exception e) {
			e.printStackTrace();
		}*/
	}

	@Override
	public Object read(final String key) {
		try {
			return redisTemplate.execute(new RedisCallback<Object>() {
				@Override
				public Object doInRedis(RedisConnection connection) throws DataAccessException {
					try {
						byte[] keybyte = redisTemplate.getStringSerializer().serialize(key);
						if (connection.exists(keybyte)) {
							byte[] value = connection.get(keybyte);
							Object object = redisTemplate.getValueSerializer().deserialize(value);
							return object;
						}
					} catch (Exception e) {
					}
					return null;
				}
			});
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public void delete(final String key) {
		try {
			redisTemplate.execute(new RedisCallback<Object>() {
				public Object doInRedis(RedisConnection connection) {
					try {
						return connection.del(redisTemplate.getStringSerializer().serialize(key));
					} catch (Exception e) {
						return null;
					}
				}
			});
		} catch (Exception e) {

		}
	}

	@Override
	public boolean save(final String key, final Serializable value,final long expireTime) {
		if(key == null || key.length() <= 0){
			throw new RuntimeException("key 不能为空。");
		}
		if(value == null){
			throw new RuntimeException("value 不能为空。");
		}
		if(expireTime <= 0){
			throw new RuntimeException("expireTime 不能为空。");
		}
		return redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				try {
					@SuppressWarnings("unchecked")
					RedisSerializer<Serializable> valueSerializer = (RedisSerializer<Serializable>) redisTemplate.getValueSerializer();
					byte[] serializekey = redisTemplate.getStringSerializer().serialize(key);
					connection.set(serializekey, valueSerializer.serialize(value));
					return connection.expire(serializekey, expireTime);
				} catch (Exception e) {
					LOG.error(e.getMessage(),e);
				}
				return false;
			}
		});
	}
}
