package com.dianping.cat.core.dal;

import java.util.List;
import org.unidal.dal.jdbc.DalException;
import org.unidal.dal.jdbc.AbstractDao;
import org.unidal.dal.jdbc.Readset;
import org.unidal.dal.jdbc.Updateset;

public class HourlyReportDao extends AbstractDao {
   public HourlyReport createLocal() {
      HourlyReport proto = new HourlyReport();

      return proto;
   }

   public int deleteByPK(HourlyReport proto) throws DalException {
      return getQueryEngine().deleteSingle(
            HourlyReportEntity.DELETE_BY_PK,
            proto);
   }
   
   public List<HourlyReport> findAllByDomainNamePeriod(java.util.Date period, String domain, String name, Readset<HourlyReport> readset) throws DalException {
      HourlyReport proto = new HourlyReport();

      proto.setPeriod(period);
      proto.setDomain(domain);
      proto.setName(name);

      List<HourlyReport> result = getQueryEngine().queryMultiple(
            HourlyReportEntity.FIND_ALL_BY_DOMAIN_NAME_PERIOD, 
            proto,
            readset);
      
      return result;
   }
   
   public List<HourlyReport> findAllByPeriodName(java.util.Date period, String name, Readset<HourlyReport> readset) throws DalException {
      HourlyReport proto = new HourlyReport();

      proto.setPeriod(period);
      proto.setName(name);

      List<HourlyReport> result = getQueryEngine().queryMultiple(
            HourlyReportEntity.FIND_ALL_BY_PERIOD_NAME, 
            proto,
            readset);
      
      return result;
   }
   
   public HourlyReport findByPK(int keyId, Readset<HourlyReport> readset) throws DalException {
      HourlyReport proto = new HourlyReport();

      proto.setKeyId(keyId);

      HourlyReport result = getQueryEngine().querySingle(
            HourlyReportEntity.FIND_BY_PK, 
            proto,
            readset);
      
      return result;
   }
   
   @Override
   protected Class<?>[] getEntityClasses() {
      return new Class<?>[] { HourlyReportEntity.class };
   }

   public int insert(HourlyReport proto) throws DalException {
      return getQueryEngine().insertSingle(
            HourlyReportEntity.INSERT,
            proto);
   }
   
   public int updateByPK(HourlyReport proto, Updateset<HourlyReport> updateset) throws DalException {
      return getQueryEngine().updateSingle(
            HourlyReportEntity.UPDATE_BY_PK,
            proto,
            updateset);
   }
   
}
