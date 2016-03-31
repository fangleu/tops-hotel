package com.dianping.cat.core.dal;

import java.util.List;
import org.unidal.dal.jdbc.DalException;
import org.unidal.dal.jdbc.AbstractDao;
import org.unidal.dal.jdbc.Readset;
import org.unidal.dal.jdbc.Updateset;

public class HourlyReportContentDao extends AbstractDao {
   public HourlyReportContent createLocal() {
      HourlyReportContent proto = new HourlyReportContent();

      return proto;
   }

   public int deleteByPK(HourlyReportContent proto) throws DalException {
      return getQueryEngine().deleteSingle(
            HourlyReportContentEntity.DELETE_BY_PK,
            proto);
   }
   
   public List<HourlyReportContent> findOverloadReport(int startId, Readset<HourlyReportContent> readset) throws DalException {
      HourlyReportContent proto = new HourlyReportContent();

      proto.setStartId(startId);

      List<HourlyReportContent> result = getQueryEngine().queryMultiple(
            HourlyReportContentEntity.FIND_OVERLOAD_REPORT, 
            proto,
            readset);
      
      return result;
   }
   
   public HourlyReportContent findByPK(int keyReportId, Readset<HourlyReportContent> readset) throws DalException {
      HourlyReportContent proto = new HourlyReportContent();

      proto.setKeyReportId(keyReportId);

      HourlyReportContent result = getQueryEngine().querySingle(
            HourlyReportContentEntity.FIND_BY_PK, 
            proto,
            readset);
      
      return result;
   }
   
   @Override
   protected Class<?>[] getEntityClasses() {
      return new Class<?>[] { HourlyReportContentEntity.class };
   }

   public int insert(HourlyReportContent proto) throws DalException {
      return getQueryEngine().insertSingle(
            HourlyReportContentEntity.INSERT,
            proto);
   }
   
   public int updateByPK(HourlyReportContent proto, Updateset<HourlyReportContent> updateset) throws DalException {
      return getQueryEngine().updateSingle(
            HourlyReportContentEntity.UPDATE_BY_PK,
            proto,
            updateset);
   }
   
}
