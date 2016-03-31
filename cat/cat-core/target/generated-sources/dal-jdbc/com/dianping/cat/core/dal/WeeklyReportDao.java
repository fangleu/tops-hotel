package com.dianping.cat.core.dal;

import org.unidal.dal.jdbc.DalException;
import org.unidal.dal.jdbc.AbstractDao;
import org.unidal.dal.jdbc.Readset;
import org.unidal.dal.jdbc.Updateset;

public class WeeklyReportDao extends AbstractDao {
   public WeeklyReport createLocal() {
      WeeklyReport proto = new WeeklyReport();

      return proto;
   }

   public int deleteByPK(WeeklyReport proto) throws DalException {
      return getQueryEngine().deleteSingle(
            WeeklyReportEntity.DELETE_BY_PK,
            proto);
   }
   
   public int deleteReportByDomainNamePeriod(WeeklyReport proto) throws DalException {
      return getQueryEngine().deleteSingle(
            WeeklyReportEntity.DELETE_REPORT_BY_DOMAIN_NAME_PERIOD,
            proto);
   }
   
   public WeeklyReport findByPK(int keyId, Readset<WeeklyReport> readset) throws DalException {
      WeeklyReport proto = new WeeklyReport();

      proto.setKeyId(keyId);

      WeeklyReport result = getQueryEngine().querySingle(
            WeeklyReportEntity.FIND_BY_PK, 
            proto,
            readset);
      
      return result;
   }
   
   public WeeklyReport findReportByDomainNamePeriod(java.util.Date period, String domain, String name, Readset<WeeklyReport> readset) throws DalException {
      WeeklyReport proto = new WeeklyReport();

      proto.setPeriod(period);
      proto.setDomain(domain);
      proto.setName(name);

      WeeklyReport result = getQueryEngine().querySingle(
            WeeklyReportEntity.FIND_REPORT_BY_DOMAIN_NAME_PERIOD, 
            proto,
            readset);
      
      return result;
   }
   
   @Override
   protected Class<?>[] getEntityClasses() {
      return new Class<?>[] { WeeklyReportEntity.class };
   }

   public int insert(WeeklyReport proto) throws DalException {
      return getQueryEngine().insertSingle(
            WeeklyReportEntity.INSERT,
            proto);
   }
   
   public int updateByPK(WeeklyReport proto, Updateset<WeeklyReport> updateset) throws DalException {
      return getQueryEngine().updateSingle(
            WeeklyReportEntity.UPDATE_BY_PK,
            proto,
            updateset);
   }
   
}
