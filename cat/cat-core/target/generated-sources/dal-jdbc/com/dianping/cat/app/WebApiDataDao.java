package com.dianping.cat.app;

import java.util.List;
import org.unidal.dal.jdbc.DalException;
import org.unidal.dal.jdbc.AbstractDao;
import org.unidal.dal.jdbc.Readset;
import org.unidal.dal.jdbc.Updateset;

public class WebApiDataDao extends AbstractDao {
   public WebApiData createLocal() {
      WebApiData proto = new WebApiData();

      return proto;
   }

   public int deleteByPK(WebApiData proto) throws DalException {
      return getQueryEngine().deleteSingle(
            WebApiDataEntity.DELETE_BY_PK,
            proto);
   }
   
   public int deleteBeforePeriod(WebApiData proto) throws DalException {
      return getQueryEngine().deleteSingle(
            WebApiDataEntity.DELETE_BEFORE_PERIOD,
            proto);
   }
   
   public List<WebApiData> findDataByMinute(int apiId, java.util.Date period, int city, int operator, int code, Readset<WebApiData> readset) throws DalException {
      WebApiData proto = new WebApiData();

      proto.setApiId(apiId);
      proto.setPeriod(period);
      proto.setCity(city);
      proto.setOperator(operator);
      proto.setCode(code);

      List<WebApiData> result = getQueryEngine().queryMultiple(
            WebApiDataEntity.FIND_DATA_BY_MINUTE, 
            proto,
            readset);
      
      return result;
   }
   
   public List<WebApiData> findDailyDataByCode(int apiId, java.util.Date period, Readset<WebApiData> readset) throws DalException {
      WebApiData proto = new WebApiData();

      proto.setApiId(apiId);
      proto.setPeriod(period);

      List<WebApiData> result = getQueryEngine().queryMultiple(
            WebApiDataEntity.FIND_DAILY_DATA_BY_CODE, 
            proto,
            readset);
      
      return result;
   }
   
   public List<WebApiData> findDataByMinuteCode(int apiId, java.util.Date period, int city, int operator, int code, Readset<WebApiData> readset) throws DalException {
      WebApiData proto = new WebApiData();

      proto.setApiId(apiId);
      proto.setPeriod(period);
      proto.setCity(city);
      proto.setOperator(operator);
      proto.setCode(code);

      List<WebApiData> result = getQueryEngine().queryMultiple(
            WebApiDataEntity.FIND_DATA_BY_MINUTE_CODE, 
            proto,
            readset);
      
      return result;
   }
   
   public List<WebApiData> findDataByCode(int apiId, java.util.Date period, int city, int operator, int code, int startMinuteOrder, int endMinuteOrder, Readset<WebApiData> readset) throws DalException {
      WebApiData proto = new WebApiData();

      proto.setApiId(apiId);
      proto.setPeriod(period);
      proto.setCity(city);
      proto.setOperator(operator);
      proto.setCode(code);
      proto.setStartMinuteOrder(startMinuteOrder);
      proto.setEndMinuteOrder(endMinuteOrder);

      List<WebApiData> result = getQueryEngine().queryMultiple(
            WebApiDataEntity.FIND_DATA_BY_CODE, 
            proto,
            readset);
      
      return result;
   }
   
   public List<WebApiData> findDataByOperator(int apiId, java.util.Date period, int city, int operator, int code, int startMinuteOrder, int endMinuteOrder, Readset<WebApiData> readset) throws DalException {
      WebApiData proto = new WebApiData();

      proto.setApiId(apiId);
      proto.setPeriod(period);
      proto.setCity(city);
      proto.setOperator(operator);
      proto.setCode(code);
      proto.setStartMinuteOrder(startMinuteOrder);
      proto.setEndMinuteOrder(endMinuteOrder);

      List<WebApiData> result = getQueryEngine().queryMultiple(
            WebApiDataEntity.FIND_DATA_BY_OPERATOR, 
            proto,
            readset);
      
      return result;
   }
   
   public List<WebApiData> findDataByCity(int apiId, java.util.Date period, int city, int operator, int code, int startMinuteOrder, int endMinuteOrder, Readset<WebApiData> readset) throws DalException {
      WebApiData proto = new WebApiData();

      proto.setApiId(apiId);
      proto.setPeriod(period);
      proto.setCity(city);
      proto.setOperator(operator);
      proto.setCode(code);
      proto.setStartMinuteOrder(startMinuteOrder);
      proto.setEndMinuteOrder(endMinuteOrder);

      List<WebApiData> result = getQueryEngine().queryMultiple(
            WebApiDataEntity.FIND_DATA_BY_CITY, 
            proto,
            readset);
      
      return result;
   }
   
   public WebApiData findByPK(int keyId, Readset<WebApiData> readset) throws DalException {
      WebApiData proto = new WebApiData();

      proto.setKeyId(keyId);

      WebApiData result = getQueryEngine().querySingle(
            WebApiDataEntity.FIND_BY_PK, 
            proto,
            readset);
      
      return result;
   }
   
   @Override
   protected Class<?>[] getEntityClasses() {
      return new Class<?>[] { WebApiDataEntity.class };
   }

   public int[] insert(WebApiData[] protos) throws DalException {
      return getQueryEngine().insertBatch(
            WebApiDataEntity.INSERT,
            protos);
   }
   
   public int[] insertOrUpdate(WebApiData[] protos) throws DalException {
      return getQueryEngine().insertBatch(
            WebApiDataEntity.INSERT_OR_UPDATE,
            protos);
   }
   
   public int insert(WebApiData proto) throws DalException {
      return getQueryEngine().insertSingle(
            WebApiDataEntity.INSERT,
            proto);
   }
   
   public int insertOrUpdate(WebApiData proto) throws DalException {
      return getQueryEngine().insertSingle(
            WebApiDataEntity.INSERT_OR_UPDATE,
            proto);
   }
   
   public int updateByPK(WebApiData proto, Updateset<WebApiData> updateset) throws DalException {
      return getQueryEngine().updateSingle(
            WebApiDataEntity.UPDATE_BY_PK,
            proto,
            updateset);
   }
   
}
