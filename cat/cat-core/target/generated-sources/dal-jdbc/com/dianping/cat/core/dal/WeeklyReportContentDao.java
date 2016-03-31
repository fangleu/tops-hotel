package com.dianping.cat.core.dal;

import java.util.List;
import org.unidal.dal.jdbc.DalException;
import org.unidal.dal.jdbc.AbstractDao;
import org.unidal.dal.jdbc.Readset;
import org.unidal.dal.jdbc.Updateset;

public class WeeklyReportContentDao extends AbstractDao {
   public WeeklyReportContent createLocal() {
      WeeklyReportContent proto = new WeeklyReportContent();

      return proto;
   }

   public int deleteByPK(WeeklyReportContent proto) throws DalException {
      return getQueryEngine().deleteSingle(
            WeeklyReportContentEntity.DELETE_BY_PK,
            proto);
   }
   
   public List<WeeklyReportContent> findOverloadReport(int startId, Readset<WeeklyReportContent> readset) throws DalException {
      WeeklyReportContent proto = new WeeklyReportContent();

      proto.setStartId(startId);

      List<WeeklyReportContent> result = getQueryEngine().queryMultiple(
            WeeklyReportContentEntity.FIND_OVERLOAD_REPORT, 
            proto,
            readset);
      
      return result;
   }
   
   public WeeklyReportContent findByPK(int keyReportId, Readset<WeeklyReportContent> readset) throws DalException {
      WeeklyReportContent proto = new WeeklyReportContent();

      proto.setKeyReportId(keyReportId);

      WeeklyReportContent result = getQueryEngine().querySingle(
            WeeklyReportContentEntity.FIND_BY_PK, 
            proto,
            readset);
      
      return result;
   }
   
   @Override
   protected Class<?>[] getEntityClasses() {
      return new Class<?>[] { WeeklyReportContentEntity.class };
   }

   public int insert(WeeklyReportContent proto) throws DalException {
      return getQueryEngine().insertSingle(
            WeeklyReportContentEntity.INSERT,
            proto);
   }
   
   public int updateByPK(WeeklyReportContent proto, Updateset<WeeklyReportContent> updateset) throws DalException {
      return getQueryEngine().updateSingle(
            WeeklyReportContentEntity.UPDATE_BY_PK,
            proto,
            updateset);
   }
   
}
