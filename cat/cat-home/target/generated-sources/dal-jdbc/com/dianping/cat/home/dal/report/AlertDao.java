package com.dianping.cat.home.dal.report;

import java.util.List;
import org.unidal.dal.jdbc.DalException;
import org.unidal.dal.jdbc.AbstractDao;
import org.unidal.dal.jdbc.Readset;
import org.unidal.dal.jdbc.Updateset;

public class AlertDao extends AbstractDao {
   public Alert createLocal() {
      Alert proto = new Alert();

      return proto;
   }

   public int deleteByPK(Alert proto) throws DalException {
      return getQueryEngine().deleteSingle(
            AlertEntity.DELETE_BY_PK,
            proto);
   }
   
   public List<Alert> queryAlertsByTimeDomain(java.util.Date startTime, java.util.Date endTime, String domain, Readset<Alert> readset) throws DalException {
      Alert proto = new Alert();

      proto.setStartTime(startTime);
      proto.setEndTime(endTime);
      proto.setDomain(domain);

      List<Alert> result = getQueryEngine().queryMultiple(
            AlertEntity.QUERY_ALERTS_BY_TIME_DOMAIN, 
            proto,
            readset);
      
      return result;
   }
   
   public List<Alert> queryAlertsByTimeDomainCategories(java.util.Date startTime, java.util.Date endTime, String domain, String[] categories, Readset<Alert> readset) throws DalException {
      Alert proto = new Alert();

      proto.setStartTime(startTime);
      proto.setEndTime(endTime);
      proto.setDomain(domain);
      proto.setCategories(categories);

      List<Alert> result = getQueryEngine().queryMultiple(
            AlertEntity.QUERY_ALERTS_BY_TIME_DOMAIN_CATEGORIES, 
            proto,
            readset);
      
      return result;
   }
   
   public List<Alert> queryAlertsByTimeCategoryDomain(java.util.Date startTime, java.util.Date endTime, String category, String domain, Readset<Alert> readset) throws DalException {
      Alert proto = new Alert();

      proto.setStartTime(startTime);
      proto.setEndTime(endTime);
      proto.setCategory(category);
      proto.setDomain(domain);

      List<Alert> result = getQueryEngine().queryMultiple(
            AlertEntity.QUERY_ALERTS_BY_TIME_CATEGORY_DOMAIN, 
            proto,
            readset);
      
      return result;
   }
   
   public List<Alert> queryAlertsByTimeCategory(java.util.Date startTime, java.util.Date endTime, String category, Readset<Alert> readset) throws DalException {
      Alert proto = new Alert();

      proto.setStartTime(startTime);
      proto.setEndTime(endTime);
      proto.setCategory(category);

      List<Alert> result = getQueryEngine().queryMultiple(
            AlertEntity.QUERY_ALERTS_BY_TIME_CATEGORY, 
            proto,
            readset);
      
      return result;
   }
   
   public Alert findByPK(int keyId, Readset<Alert> readset) throws DalException {
      Alert proto = new Alert();

      proto.setKeyId(keyId);

      Alert result = getQueryEngine().querySingle(
            AlertEntity.FIND_BY_PK, 
            proto,
            readset);
      
      return result;
   }
   
   @Override
   protected Class<?>[] getEntityClasses() {
      return new Class<?>[] { AlertEntity.class };
   }

   public int insert(Alert proto) throws DalException {
      return getQueryEngine().insertSingle(
            AlertEntity.INSERT,
            proto);
   }
   
   public int updateByPK(Alert proto, Updateset<Alert> updateset) throws DalException {
      return getQueryEngine().updateSingle(
            AlertEntity.UPDATE_BY_PK,
            proto,
            updateset);
   }
   
}
