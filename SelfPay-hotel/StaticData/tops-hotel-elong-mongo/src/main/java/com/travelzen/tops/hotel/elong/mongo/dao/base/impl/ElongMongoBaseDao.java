package com.travelzen.tops.hotel.elong.mongo.dao.base.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.jmkgreen.morphia.Datastore;
import com.github.jmkgreen.morphia.Key;
import com.github.jmkgreen.morphia.query.Query;
import com.github.jmkgreen.morphia.query.UpdateOperations;
import com.travelzen.tops.hotel.elong.mongo.dao.base.IElongCoreMongoBaseDao;
import com.travelzen.tops.hotel.elong.mongo.entity.base.MorphiaEntity;

public class ElongMongoBaseDao<E extends MorphiaEntity<I>, I> extends MorphiaBasicDao<E, I> implements IElongCoreMongoBaseDao<E, I>  {

	Logger LOG = LoggerFactory.getLogger(ElongMongoBaseDao.class);
	
    @Resource(name = "hotelElongMongoDatastore")
    protected Datastore hotelElongMongoDatastore;

    @PostConstruct
    public void replaceDatastore() {
        super.setDatastore(hotelElongMongoDatastore);
    }
    
	@Override
	public Datastore createDatastore() {
		return super.getDatastore();
	}
    
    /**----------查询---------*/
	@Override
	public long getCountByProperty(Map<String, Object> properties) {
		Query<E> query = getDatastore().createQuery(getEntityClass());
		for (Entry<String, Object> entry : properties.entrySet()) {
			query.field(entry.getKey()).contains(entry.getValue().toString());
		}
		return query.countAll(); 
	}
	
	@Override
	public List<E> findByProperty(Map<String, Object> conditions, int page,
			int pageSize){
		if (page < 1) {
			page = 1;
		}

		if (pageSize < 1) {
			pageSize = 10;
		}

		Query<E> lvQuery = getDatastore().createQuery(getEntityClass());
		for (Entry<String, Object> entry : conditions.entrySet()) {
			lvQuery.field(entry.getKey()).contains(entry.getValue().toString());
		}
		int offset = (page - 1) * pageSize;
		lvQuery.offset(offset);
		lvQuery.limit(pageSize);
		List<E> lvResult = lvQuery.asList();
		return lvResult;
	}

    @Override
    public List<E> findByProperty(Map<String, Object> properties) {
        return buildQuery(properties,null,null).asList();
    }

    @Override
    public List<E> findByProperty(Paging paging) {
        return buildQuery(null,paging,null).asList();
    }

    @Override
    public List<E> findByProperty(Map<String, Object> properties, String orderBy) {
        return buildQuery(properties,null,orderBy).asList();
    }

    @Override
    public List<E> findByProperty(Map<String, Object> properties, Paging paging, String orderBy) {
        return buildQuery(properties,paging,orderBy).asList();
    }

    @Override
    /**
     * 先判断参数值是否为空
     * 由于
     */
    public List<E> findByProperty(String property, Object value) {
        Query<E> query = buildQuery(property,value,null,null);
        return query.asList();
    }
    
    public List<E> containByProperty(String property,Object value) {
    	Query<E> query = buildContainQuery(property, value, null,null);
    	return query.asList();
    }
    
    public List<E> findByProperty(String property, Object value,String... retriveFields) {
        Query<E> query = buildQuery(property,value,null,null,retriveFields);
        return query.asList();
    }

    @Override
    public List<E> findByProperty(String property, Object value, String orderBy) {
        return buildQuery(property,value,null,orderBy).asList();
    }

    @Override
    public List<E> findByProperty(String property, Object value, Paging paging) {
        return buildQuery(property,value,paging,null).asList();
    }

    @Override
    public List<E> findByProperty(String property, Object value, Paging paging, String orderBy) {
        return buildQuery(property,value,paging,orderBy).asList();
    }
    
    @Override
    public Query<E> buildQuery(String property, Object value, Paging paging, String orderBy,String... retriveFields){
        Query<E> query = super.createQuery();
        if(value != null){
            query.filter(property, value);
        }
        if(paging != null){
            query.offset(paging.getOffset()).limit(paging.getLimit());
        }
        if(StringUtils.isNotBlank(orderBy)){
            query.order(orderBy);
        }
        if(retriveFields.length >0){
            query.retrievedFields(true, retriveFields);
        }
        return query;
    }
    
    @Override
    public Query<E> buildQuery(Map<String, Object> properties, Paging paging, String orderBy){
        Query<E> query = super.createQuery();
        if(properties != null && !properties.isEmpty()){
            Iterator<Entry<String,Object>> it = properties.entrySet().iterator();
            while(it.hasNext()){
                Entry<String,Object> entry = it.next();
                query.filter(entry.getKey(), entry.getValue());
            }
        }
        if(paging != null){
            query.offset(paging.getOffset()).limit(paging.getLimit());
        }
        if(StringUtils.isNotBlank(orderBy)){
            query.order(orderBy);
        }
        return query;
    }
    
    protected Query<E> buildContainQuery(String property, Object value, Paging paging, String orderBy,String... retriveFields){
        Query<E> query = super.createQuery();
        if(value != null){
            query.field(property).contains(value.toString());
        }
        if(paging != null){
            query.offset(paging.getOffset()).limit(paging.getLimit());
        }
        if(StringUtils.isNotBlank(orderBy)){
            query.order(orderBy);
        }
        if(retriveFields.length >0){
            query.retrievedFields(true, retriveFields);
        }
        return query;
    }

    @Override
    public long getTotalCount() {
        return this.getDatastore().getCount(getEntityClass());
    }

    @Override
    public List<E> findAll() {
        return this.getDatastore().createQuery(getEntityClass()).asList();
    }

    @Override
    public E getById(String id) {
        return hotelElongMongoDatastore.get(super.getEntityClass(), buildObjectId(id));
    }
    
    @Override
    public Query<E> createQuery(){
    	return super.createQuery();
    }

    @Override
    public List<E> findByProperty(Map<String, Object> properties,Paging paging) {
        Query<E> query = super.createQuery();
        if(properties != null && !properties.isEmpty()){
            Iterator<Entry<String,Object>> it = properties.entrySet().iterator();
            while(it.hasNext()){
                Entry<String,Object> entry = it.next();
                query.filter(entry.getKey(), entry.getValue());
            }

        }
        if(paging != null){
            query.offset(paging.getOffset()).limit(paging.getLimit());
        }
        return query.asList();
    }


    @Override
    public  E findOneByProperty(Map<String, Object> properties) {
        List<E> result = findByProperty(properties,null,null);
        if(result != null && !result.isEmpty()){
            return result.get(0);
        }
        return null;
    }

    @Override
    public E findOneByProperty(String property, Object value) {
        List<E> result = findByProperty(property,value,null,null);
        if(result != null && !result.isEmpty()){
            return result.get(0);
        }
        return null;
    }

    private PagingResult<E> buildPagingResult(Paging paging,List<E> list,long totalCount){
        PagingResult<E> result = new PagingResult<E>();
        result.setPaging(paging);
        result.setResult(list);
        result.setTotalCount(totalCount);
        return result;
    }
    @Override
    public PagingResult<E> getPagingResult(String property, Object value, Paging paging) {
        Query<E> query = buildQuery(property,value,paging,null);
        return buildPagingResult(paging,query.asList(),query.countAll());
    }

    @Override
    public PagingResult<E> getPagingResult(String property, Object value, Paging paging, String orderBy) {
        Query<E> query = buildQuery(property,value,paging,orderBy);
        return buildPagingResult(paging,query.asList(),query.countAll());
    }

    @Override
    public PagingResult<E> getPagingResult(Map<String, Object> properties, Paging paging) {
        Query<E> query = buildQuery(properties,paging,null);
        return buildPagingResult(paging,query.asList(),query.countAll());
    }

    @Override
    public PagingResult<E> getPagingResult(Map<String, Object> properties, Paging paging, String orderBy) {
        Query<E> query = buildQuery(properties,paging,orderBy);
        return buildPagingResult(paging,query.asList(),query.countAll());
    }

    @Override
    public boolean exist(Map<String, Object> Properties) {
        E e  = findOneByProperty(Properties);
        return e==null?false:true;
    }

    @Override
    public boolean exist(String property, Object value) {
        E e  = findOneByProperty(property,value);
        return e==null?false:true;
    }
    @Override
    public boolean exist(String id) {
        E e  = findOneByProperty("id",buildObjectId(id));
        return e==null?false:true;
    }



    /**----------保存---------*/
    /**
     *
     * 由于和MophiaBasicDao已经有create,save方法，此处返回值不同，所以不能重载，所以替换了方法名
     * @param list
     * @return
     */

    @Override
    public boolean saveEntity(List<E> list) {
        try{
            this.getDatastore().save(list);
        }catch(RuntimeException ex){
            LOG.error("数据库[保存]失败,当前时间:{},保存数据信息:{},错误详细信息:{}",new DateTime(),list.toString(),ex.getMessage());
            throw ex;
        }
        return true;
    }
    
	@Override
	public Iterable<Key<E>> saveEntityReturnKeys(List<E> list) {
		try{
			return this.getDatastore().save(list);
		}catch(RuntimeException ex){
			LOG.error("数据库[保存]失败,当前时间:{},保存数据信息:{},错误详细信息:{}",new DateTime(),list.toString(),ex.getMessage());
            throw ex;
		}
	}
    /**
     * 只能插入新的数据，不能更新
     */
    @Override
    public String createEntity(E e) {
        try{
            return super.create(e).toString();
        }catch(RuntimeException ex){
            LOG.error("数据库[保存]失败,当前时间:{},保存数据信息:{},错误详细信息:{}",new DateTime(),e.toString(),ex.getMessage());
            throw ex;
        }
    }
    /**
     * 保存或更新
     */
    @Override
    public String saveEntity(E e) {
        try{
            return super.createOrReplace(e).toString();
        }catch(RuntimeException ex){
            LOG.error("数据库[保存]失败,当前时间:{},保存数据信息:{},错误详细信息:{}",new DateTime(),e.toString(),ex.getMessage());
            throw ex;
        }

    }




    /**----------更新---------*/
    @Override
    public boolean updateEntity(E e, UpdateOperations<E> ops) {
        try{
            this.getDatastore().update(e, ops);
        }catch(RuntimeException ex){
            LOG.error("数据库[更新]失败,当前时间:{},更新数据信息:{},错误详细信息:{}",new DateTime(),e.toString(),ex.getMessage());
            throw ex;
        }
        return true;
    }
    @Override
    public boolean updateEntity(Query<E> e, UpdateOperations<E> ops) {
        try{
            this.getDatastore().update(e, ops);
        }catch(RuntimeException ex){
            LOG.error("数据库[更新]失败,当前时间:{},更新数据信息:{},错误详细信息:{}",new DateTime(),e.toString(),ex.getMessage());
            throw ex;
        }
        return true;
    }
    public boolean mergeEntity(E e){
        try{
            this.getDatastore().merge(e);
        }catch(RuntimeException ex){
            LOG.error("数据库[更新]失败,当前时间:{},更新数据信息:{},错误详细信息:{}",new DateTime(),e.toString(),ex.getMessage());
            throw ex;
        }
        return true;
    }

    /**----------删除---------*/
    @Override
    public boolean deleteAll() {
        try{
            this.getDatastore().delete(createQuery());
        }catch(RuntimeException ex){
            LOG.error("数据库[删除]失败,当前时间:{},错误详细信息:{}",new DateTime(),ex.getMessage());
            throw ex;
        }
        return true;
    }


    @Override
    public boolean deleteById(String id) {
        try{
            this.getDatastore().delete(super.getEntityClass(), buildObjectId(id));
        }catch(RuntimeException ex){
            LOG.error("数据库[删除]失败,当前时间:{},删除数据Id:{},错误详细信息:{}",new DateTime(),id,ex.getMessage());
            throw ex;
        }
        return true;
    }

    @Override
    public boolean delete(Query<E> query) {
        try{
            this.getDatastore().delete(super.getEntityClass(), query);
        }catch(RuntimeException ex){
            LOG.error("数据库[删除]失败,当前时间:{},删除条件信息:{},错误详细信息:{}",new DateTime(),query.toString(),ex.getMessage());
            throw ex;
        }
        return true;
    }
    /**
     * idStr为monogodb的id字符串，24位
     * 如果id不合法会抛出运行时异常
     * @param idStr
     * @return
     */
    protected ObjectId buildObjectId(String idStr){
        return new ObjectId(idStr);
    }
	
}
