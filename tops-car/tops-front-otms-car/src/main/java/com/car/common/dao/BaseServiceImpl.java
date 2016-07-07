package com.car.common.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import com.car.bean.Custom;


public class BaseServiceImpl<T, ID extends Serializable> implements IBaseService<T, Serializable>  {
	
	private IBaseDao<T, Serializable> dao;
	
	@Resource  
    public void setDao(IBaseDao<T,Serializable> dao) {  
        this.dao = dao;  
    }

	public void save(T t) {
		dao.save(t);
		
	}

	@Override
	public void saveOrUpdate(T t) {
		dao.saveOrUpdate(t);
		
	}

	@Override
	public T load(Serializable id) {
		return (T) dao.load(id);
	}

	@Override
	public T get(Serializable id) {
		return (T) dao.get(id);
	}

	@Override
	public boolean contains(T t) {
		return dao.contains(t);
	}

	@Override
	public void delete(T t) {
		dao.delete(t);
		
	}

	@Override
	public boolean deleteById(Serializable Id) {
		return dao.deleteById(Id);
	}

	@Override
	public void deleteAll(Collection<T> entities) {
		dao.deleteAll(entities);
	}

	@Override
	public void queryHql(String hqlString, Object... values) {
		dao.queryHql(hqlString, values);
	}

	@Override
	public void querySql(String sqlString, Object... values) {
		dao.querySql(sqlString, values);
	}

	@Override
	public T getByHQL(String hqlString, Object... values) {
		return (T) dao.getByHQL(hqlString, values);
	}

	@Override
	public T getBySQL(String sqlString, final Class<T> beanClass,  Object... values) {
		return (T) dao.getBySQL(sqlString,beanClass, values);
	}

	@Override
	public List<T> getListByHQL(String hqlString, Object... values) {
		return dao.getListByHQL(hqlString, values);
	}

	@Override
	public List<T> getListBySQL(String sqlString, final Class<T> beanClass, Object... values) {
		return dao.getListBySQL(sqlString, beanClass, values);
	}

	@Override
	public void refresh(T t) {
		dao.refresh(t);
	}

	@Override
	public void update(T t) {
		dao.update(t);
		
	}

	@Override
	public Long countByHql(String hql, Object... values) {
		return dao.countByHql(hql, values);
	}

	@Override
	public PageResults<T> findPageByFetchedHql(String hql, final Class<T> beanClass, String countHql, int pageNo, int pageSize,
			Object... values) {
		return dao.findPageByFetchedHql(hql,beanClass, countHql, pageNo, pageSize, values);
	}

}
