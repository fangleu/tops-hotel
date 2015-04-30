package com.travelzen.tops.hotel.elong.mongo.dao.base;

import java.util.List;
import java.util.Map;

import com.github.jmkgreen.morphia.Key;
import com.github.jmkgreen.morphia.query.Query;
import com.github.jmkgreen.morphia.query.UpdateOperations;
import com.travelzen.tops.hotel.elong.mongo.dao.base.impl.Paging;
import com.travelzen.tops.hotel.elong.mongo.dao.base.impl.PagingResult;


public interface IElongCoreMongoBaseDao<E,I> extends IBasicDao<E,I>  {
	
	long getCountByProperty(Map<String,Object> properties);
	
	public List<E> findByProperty(Map<String, Object> conditions, int page, int pageSize);
	
	long getTotalCount();

	List<E> findAll();
	
	Query<E> createQuery();
	
	
	/**
	 * 查询条件 eg. findByProperty(new HashMap(),null) 查找所有
	 * 
	 * @param properties
	 *            可以为空
	 * @param paging
	 *            可以为空
	 * @return
	 */
	List<E> findByProperty(Map<String, Object> properties);
	/**
	 * 分页查找
	 * @param paging
	 * @return
	 */
	List<E> findByProperty(Paging paging);

	/**
	 * eg. findByProperty(map,"-age,name") age降序,name升序
	 * 
	 * @param properties
	 *            可以为空
	 * @param orderBy
	 *            可以为空
	 * @return
	 */
	List<E> findByProperty(Map<String, Object> properties, String orderBy);

	/**
	 * 分页查询
	 * 
	 * @param properties
	 * @param paging
	 * @return
	 */
	List<E> findByProperty(Map<String, Object> properties, Paging paging);

	/**
	 * 分页+排序
	 * 
	 * @param properties
	 * @param paging
	 * @param orderBy
	 * @return
	 */
	List<E> findByProperty(Map<String, Object> properties, Paging paging, String orderBy);

	/**
	 * 
	 * @param property
	 * @param value
	 * @return
	 */
	List<E> findByProperty(String property, Object value);
	
	List<E> containByProperty(String property,Object value);
	
	List<E> findByProperty(String property, Object value,String... retriveFields);

	List<E> findByProperty(String property, Object value, String orderBy);

	List<E> findByProperty(String property, Object value, Paging paging);

	List<E> findByProperty(String property, Object value, Paging paging, String orderBy);

	E findOneByProperty(Map<String, Object> properties);

	E findOneByProperty(String property, Object value);
	E getById(String id);
	
	/**
	 * 为空的情况下也返回PagingResult，不返回null,里面的属性为空
	 * @param property
	 * @param value
	 * @param paging
	 * @return
	 */
	PagingResult<E> getPagingResult(String property, Object value,Paging paging);
	PagingResult<E> getPagingResult(String property, Object value,Paging paging,String orderBy);
	PagingResult<E> getPagingResult(Map<String,Object> properties,Paging paging);
	PagingResult<E> getPagingResult(Map<String,Object> properties,Paging paging,String orderBy);

	/**-------保存-------*/
	boolean saveEntity(List<E> list);
	Iterable<Key<E>> saveEntityReturnKeys(List<E> list);
	String createEntity(E e);
	String saveEntity(E e);

	/**-------更新-------*/
	boolean updateEntity(E e, UpdateOperations<E> option);
	boolean updateEntity(Query<E> query, UpdateOperations<E> option);
	boolean mergeEntity(E entity);

	/**--------删除--------*/
	boolean deleteAll();
	boolean deleteById(String id);
	boolean delete(Query<E> query);

	/**其他*/
	boolean exist(Map<String,Object> Properties);
	boolean exist(String property,Object value);
	boolean exist(String id);
	
	/**构建查询*/
	public Query<E> buildQuery(String property, Object value, Paging paging, String orderBy,String... retriveFields);
	public Query<E> buildQuery(Map<String, Object> properties, Paging paging, String orderBy);
}
