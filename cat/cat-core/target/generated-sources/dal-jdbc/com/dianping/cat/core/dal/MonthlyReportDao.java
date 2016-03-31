package com.dianping.cat.core.dal;

import org.unidal.dal.jdbc.DalException;
import org.unidal.dal.jdbc.AbstractDao;
import org.unidal.dal.jdbc.Readset;
import org.unidal.dal.jdbc.Updateset;

public class MonthlyReportDao extends AbstractDao {
   public MonthlyReport createLocal() {
      MonthlyReport proto = new MonthlyReport();

      return proto;
   }

   public int deleteByPK(MonthlyReport proto) throws DalException {
      return getQueryEngine().deleteSingle(
            MonthlyReportEntity.DELETE_BY_PK,
            proto);
   }
   
   public int deleteReportByDomainNamePeriod(MonthlyReport proto) throws DalException {
      return getQueryEngine().deleteSingle(
            MonthlyReportEntity.DELETE_REPORT_BY_DOMAIN_NAME_PERIOD,
            proto);
   }
   
   public MonthlyReport findByPK(int keyId, Readset<MonthlyReport> readset) throws DalException {
      MonthlyReport proto = new MonthlyReport();

      proto.setKeyId(keyId);

      MonthlyReport result = getQueryEngine().querySingle(
            MonthlyReportEntity.FIND_BY_PK, 
            proto,
            readset);
      
      return result;
   }
   
   public MonthlyReport findReportByDomainNamePeriod(java.util.Date period, String domain, String name, Readset<MonthlyReport> readset) throws DalException {
      MonthlyReport proto = new MonthlyReport();

      proto.setPeriod(period);
      proto.setDomain(domain);
      proto.setName(name);

      MonthlyReport result = getQueryEngine().querySingle(
            MonthlyReportEntity.FIND_REPORT_BY_DOMAIN_NAME_PERIOD, 
            proto,
            readset);
      
      return result;
   }
   
   @Override
   protected Class<?>[] getEntityClasses() {
      return new Class<?>[] { MonthlyReportEntity.class };
   }

   public int insert(MonthlyReport proto) throws DalException {
      return getQueryEngine().insertSingle(
            MonthlyReportEntity.INSERT,
            proto);
   }
   
   public int updateByPK(MonthlyReport proto, Updateset<MonthlyReport> updateset) throws DalException {
      return getQueryEngine().updateSingle(
            MonthlyReportEntity.UPDATE_BY_PK,
            proto,
            updateset);
   }
   
}
