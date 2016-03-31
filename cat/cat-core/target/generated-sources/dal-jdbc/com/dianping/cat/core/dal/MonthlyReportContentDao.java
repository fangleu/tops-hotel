package com.dianping.cat.core.dal;

import java.util.List;
import org.unidal.dal.jdbc.DalException;
import org.unidal.dal.jdbc.AbstractDao;
import org.unidal.dal.jdbc.Readset;
import org.unidal.dal.jdbc.Updateset;

public class MonthlyReportContentDao extends AbstractDao {
   public MonthlyReportContent createLocal() {
      MonthlyReportContent proto = new MonthlyReportContent();

      return proto;
   }

   public int deleteByPK(MonthlyReportContent proto) throws DalException {
      return getQueryEngine().deleteSingle(
            MonthlyReportContentEntity.DELETE_BY_PK,
            proto);
   }
   
   public List<MonthlyReportContent> findOverloadReport(int startId, Readset<MonthlyReportContent> readset) throws DalException {
      MonthlyReportContent proto = new MonthlyReportContent();

      proto.setStartId(startId);

      List<MonthlyReportContent> result = getQueryEngine().queryMultiple(
            MonthlyReportContentEntity.FIND_OVERLOAD_REPORT, 
            proto,
            readset);
      
      return result;
   }
   
   public MonthlyReportContent findByPK(int keyReportId, Readset<MonthlyReportContent> readset) throws DalException {
      MonthlyReportContent proto = new MonthlyReportContent();

      proto.setKeyReportId(keyReportId);

      MonthlyReportContent result = getQueryEngine().querySingle(
            MonthlyReportContentEntity.FIND_BY_PK, 
            proto,
            readset);
      
      return result;
   }
   
   @Override
   protected Class<?>[] getEntityClasses() {
      return new Class<?>[] { MonthlyReportContentEntity.class };
   }

   public int insert(MonthlyReportContent proto) throws DalException {
      return getQueryEngine().insertSingle(
            MonthlyReportContentEntity.INSERT,
            proto);
   }
   
   public int updateByPK(MonthlyReportContent proto, Updateset<MonthlyReportContent> updateset) throws DalException {
      return getQueryEngine().updateSingle(
            MonthlyReportContentEntity.UPDATE_BY_PK,
            proto,
            updateset);
   }
   
}
