package com.dianping.cat.home.dal.report;

import java.util.List;
import org.unidal.dal.jdbc.DalException;
import org.unidal.dal.jdbc.AbstractDao;
import org.unidal.dal.jdbc.Readset;
import org.unidal.dal.jdbc.Updateset;

public class AlterationDao extends AbstractDao {
   public Alteration createLocal() {
      Alteration proto = new Alteration();

      return proto;
   }

   public int deleteByPK(Alteration proto) throws DalException {
      return getQueryEngine().deleteSingle(
            AlterationEntity.DELETE_BY_PK,
            proto);
   }
   
   public List<Alteration> findByTypeDruation(java.util.Date startTime, java.util.Date endTime, String type, Readset<Alteration> readset) throws DalException {
      Alteration proto = new Alteration();

      proto.setStartTime(startTime);
      proto.setEndTime(endTime);
      proto.setType(type);

      List<Alteration> result = getQueryEngine().queryMultiple(
            AlterationEntity.FIND_BY_TYPE_DRUATION, 
            proto,
            readset);
      
      return result;
   }
   
   public List<Alteration> findByDtdh(java.util.Date startTime, java.util.Date endTime, String type, String domain, String hostname, Readset<Alteration> readset) throws DalException {
      Alteration proto = new Alteration();

      proto.setStartTime(startTime);
      proto.setEndTime(endTime);
      proto.setType(type);
      proto.setDomain(domain);
      proto.setHostname(hostname);

      List<Alteration> result = getQueryEngine().queryMultiple(
            AlterationEntity.FIND_BY_DTDH, 
            proto,
            readset);
      
      return result;
   }
   
   public List<Alteration> findByDtdhTypes(java.util.Date startTime, java.util.Date endTime, String type, String domain, String hostname, String[] types, Readset<Alteration> readset) throws DalException {
      Alteration proto = new Alteration();

      proto.setStartTime(startTime);
      proto.setEndTime(endTime);
      proto.setType(type);
      proto.setDomain(domain);
      proto.setHostname(hostname);
      proto.setTypes(types);

      List<Alteration> result = getQueryEngine().queryMultiple(
            AlterationEntity.FIND_BY_DTDH_TYPES, 
            proto,
            readset);
      
      return result;
   }
   
   public List<Alteration> findByDomainAndTime(java.util.Date startTime, java.util.Date endTime, String domain, Readset<Alteration> readset) throws DalException {
      Alteration proto = new Alteration();

      proto.setStartTime(startTime);
      proto.setEndTime(endTime);
      proto.setDomain(domain);

      List<Alteration> result = getQueryEngine().queryMultiple(
            AlterationEntity.FIND_BY_DOMAIN_AND_TIME, 
            proto,
            readset);
      
      return result;
   }
   
   public Alteration findByPK(int keyId, Readset<Alteration> readset) throws DalException {
      Alteration proto = new Alteration();

      proto.setKeyId(keyId);

      Alteration result = getQueryEngine().querySingle(
            AlterationEntity.FIND_BY_PK, 
            proto,
            readset);
      
      return result;
   }
   
   @Override
   protected Class<?>[] getEntityClasses() {
      return new Class<?>[] { AlterationEntity.class };
   }

   public int insert(Alteration proto) throws DalException {
      return getQueryEngine().insertSingle(
            AlterationEntity.INSERT,
            proto);
   }
   
   public int updateByPK(Alteration proto, Updateset<Alteration> updateset) throws DalException {
      return getQueryEngine().updateSingle(
            AlterationEntity.UPDATE_BY_PK,
            proto,
            updateset);
   }
   
}
