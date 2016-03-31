package com.dianping.cat.core.dal;

import java.util.List;
import org.unidal.dal.jdbc.DalException;
import org.unidal.dal.jdbc.AbstractDao;
import org.unidal.dal.jdbc.Readset;
import org.unidal.dal.jdbc.Updateset;

public class DailyGraphDao extends AbstractDao {
   public DailyGraph createLocal() {
      DailyGraph proto = new DailyGraph();

      return proto;
   }

   public int deleteByPK(DailyGraph proto) throws DalException {
      return getQueryEngine().deleteSingle(
            DailyGraphEntity.DELETE_BY_PK,
            proto);
   }
   
   public int deleteByDomainNamePeriodIp(DailyGraph proto) throws DalException {
      return getQueryEngine().deleteSingle(
            DailyGraphEntity.DELETE_BY_DOMAIN_NAME_PERIOD_IP,
            proto);
   }
   
   public List<DailyGraph> findByDomainNameIpDuration(java.util.Date startDate, java.util.Date endDate, String ip, String domain, String name, Readset<DailyGraph> readset) throws DalException {
      DailyGraph proto = new DailyGraph();

      proto.setStartDate(startDate);
      proto.setEndDate(endDate);
      proto.setIp(ip);
      proto.setDomain(domain);
      proto.setName(name);

      List<DailyGraph> result = getQueryEngine().queryMultiple(
            DailyGraphEntity.FIND_BY_DOMAIN_NAME_IP_DURATION, 
            proto,
            readset);
      
      return result;
   }
   
   public List<DailyGraph> findIpByDomainNameDuration(java.util.Date startDate, java.util.Date endDate, String domain, String name, Readset<DailyGraph> readset) throws DalException {
      DailyGraph proto = new DailyGraph();

      proto.setStartDate(startDate);
      proto.setEndDate(endDate);
      proto.setDomain(domain);
      proto.setName(name);

      List<DailyGraph> result = getQueryEngine().queryMultiple(
            DailyGraphEntity.FIND_IP_BY_DOMAIN_NAME_DURATION, 
            proto,
            readset);
      
      return result;
   }
   
   public List<DailyGraph> findDomainByNameDuration(java.util.Date startDate, java.util.Date endDate, String name, Readset<DailyGraph> readset) throws DalException {
      DailyGraph proto = new DailyGraph();

      proto.setStartDate(startDate);
      proto.setEndDate(endDate);
      proto.setName(name);

      List<DailyGraph> result = getQueryEngine().queryMultiple(
            DailyGraphEntity.FIND_DOMAIN_BY_NAME_DURATION, 
            proto,
            readset);
      
      return result;
   }
   
   public DailyGraph findByPK(int keyId, Readset<DailyGraph> readset) throws DalException {
      DailyGraph proto = new DailyGraph();

      proto.setKeyId(keyId);

      DailyGraph result = getQueryEngine().querySingle(
            DailyGraphEntity.FIND_BY_PK, 
            proto,
            readset);
      
      return result;
   }
   
   public DailyGraph findByDomainNameIpDate(java.util.Date startDate, String ip, String domain, String name, Readset<DailyGraph> readset) throws DalException {
      DailyGraph proto = new DailyGraph();

      proto.setStartDate(startDate);
      proto.setIp(ip);
      proto.setDomain(domain);
      proto.setName(name);

      DailyGraph result = getQueryEngine().querySingle(
            DailyGraphEntity.FIND_BY_DOMAIN_NAME_IP_DATE, 
            proto,
            readset);
      
      return result;
   }
   
   @Override
   protected Class<?>[] getEntityClasses() {
      return new Class<?>[] { DailyGraphEntity.class };
   }

   public int insert(DailyGraph proto) throws DalException {
      return getQueryEngine().insertSingle(
            DailyGraphEntity.INSERT,
            proto);
   }
   
   public int updateByPK(DailyGraph proto, Updateset<DailyGraph> updateset) throws DalException {
      return getQueryEngine().updateSingle(
            DailyGraphEntity.UPDATE_BY_PK,
            proto,
            updateset);
   }
   
}
