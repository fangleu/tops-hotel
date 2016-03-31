package com.dianping.cat.core.dal;

import org.unidal.dal.jdbc.DalException;
import org.unidal.dal.jdbc.AbstractDao;
import org.unidal.dal.jdbc.Readset;
import org.unidal.dal.jdbc.Updateset;

public class DailyReportDao extends AbstractDao {
   public DailyReport createLocal() {
      DailyReport proto = new DailyReport();

      return proto;
   }

   public int deleteByPK(DailyReport proto) throws DalException {
      return getQueryEngine().deleteSingle(
            DailyReportEntity.DELETE_BY_PK,
            proto);
   }
   
   public DailyReport findByPK(int keyId, Readset<DailyReport> readset) throws DalException {
      DailyReport proto = new DailyReport();

      proto.setKeyId(keyId);

      DailyReport result = getQueryEngine().querySingle(
            DailyReportEntity.FIND_BY_PK, 
            proto,
            readset);
      
      return result;
   }
   
   public DailyReport findByDomainNamePeriod(String domain, String name, java.util.Date period, Readset<DailyReport> readset) throws DalException {
      DailyReport proto = new DailyReport();

      proto.setDomain(domain);
      proto.setName(name);
      proto.setPeriod(period);

      DailyReport result = getQueryEngine().querySingle(
            DailyReportEntity.FIND_BY_DOMAIN_NAME_PERIOD, 
            proto,
            readset);
      
      return result;
   }
   
   @Override
   protected Class<?>[] getEntityClasses() {
      return new Class<?>[] { DailyReportEntity.class };
   }

   public int insert(DailyReport proto) throws DalException {
      return getQueryEngine().insertSingle(
            DailyReportEntity.INSERT,
            proto);
   }
   
   public int updateByPK(DailyReport proto, Updateset<DailyReport> updateset) throws DalException {
      return getQueryEngine().updateSingle(
            DailyReportEntity.UPDATE_BY_PK,
            proto,
            updateset);
   }
   
}
