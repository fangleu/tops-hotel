package com.dianping.cat.core.dal;

import java.util.List;
import org.unidal.dal.jdbc.DalException;
import org.unidal.dal.jdbc.AbstractDao;
import org.unidal.dal.jdbc.Readset;
import org.unidal.dal.jdbc.Updateset;

public class DailyReportContentDao extends AbstractDao {
   public DailyReportContent createLocal() {
      DailyReportContent proto = new DailyReportContent();

      return proto;
   }

   public int deleteByPK(DailyReportContent proto) throws DalException {
      return getQueryEngine().deleteSingle(
            DailyReportContentEntity.DELETE_BY_PK,
            proto);
   }
   
   public List<DailyReportContent> findOverloadReport(int startId, Readset<DailyReportContent> readset) throws DalException {
      DailyReportContent proto = new DailyReportContent();

      proto.setStartId(startId);

      List<DailyReportContent> result = getQueryEngine().queryMultiple(
            DailyReportContentEntity.FIND_OVERLOAD_REPORT, 
            proto,
            readset);
      
      return result;
   }
   
   public DailyReportContent findByPK(int keyReportId, Readset<DailyReportContent> readset) throws DalException {
      DailyReportContent proto = new DailyReportContent();

      proto.setKeyReportId(keyReportId);

      DailyReportContent result = getQueryEngine().querySingle(
            DailyReportContentEntity.FIND_BY_PK, 
            proto,
            readset);
      
      return result;
   }
   
   @Override
   protected Class<?>[] getEntityClasses() {
      return new Class<?>[] { DailyReportContentEntity.class };
   }

   public int insert(DailyReportContent proto) throws DalException {
      return getQueryEngine().insertSingle(
            DailyReportContentEntity.INSERT,
            proto);
   }
   
   public int updateByPK(DailyReportContent proto, Updateset<DailyReportContent> updateset) throws DalException {
      return getQueryEngine().updateSingle(
            DailyReportContentEntity.UPDATE_BY_PK,
            proto,
            updateset);
   }
   
}
