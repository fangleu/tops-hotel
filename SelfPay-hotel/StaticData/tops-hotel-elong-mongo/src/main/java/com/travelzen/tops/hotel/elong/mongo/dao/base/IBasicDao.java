package com.travelzen.tops.hotel.elong.mongo.dao.base;

import java.util.List;

import com.github.jmkgreen.morphia.Datastore;
import com.github.jmkgreen.morphia.query.Query;
import com.mongodb.WriteResult;

/**
 * 
 * @author muyuansun
 *
 * @param <E> mongo 实体对象
 * @param <I> 实体对象ID，包括传入参数和返回值
 */
public interface IBasicDao<E, I> {
	/**
	 * 设置 mongodb 数据源
	 * @param ds
	 */
	void setDatastore(Datastore ds);
	
	Datastore createDatastore();
	/**
	 * 插入实体对象到mongodb
	 * @param entity
	 * @return  返回实体对象在数据库中的记录ID
	 */
	I create(E entity);
	/**
	 * 插入或更新实体对象，如果入参的实体对象无ID信息则新增，反之更新。
	 * @param entity 
	 * @return 返回实体对象在数据库中的记录ID
	 */
	I createOrReplace(E entity);
	/**
	 * 删除记录根据ID
	 * @param id
	 * @return
	 */
	WriteResult deleteById(I id);
	/**
	 * 获得记录根据ID
	 * @param id
	 * @return
	 */
	E getById(I id);
	/**
	 * 检查记录
	 * @param id
	 * @return
	 */
	boolean isExists(I id);

	void updateAndAppend(E entity);

	List<E> getByIds(List<I> ids);

	long getCount(Query<E> query);

}
