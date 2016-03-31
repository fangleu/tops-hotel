package com.dianping.cat.home.dal.report;

import org.unidal.dal.jdbc.DalException;
import org.unidal.dal.jdbc.AbstractDao;
import org.unidal.dal.jdbc.Readset;
import org.unidal.dal.jdbc.Updateset;

public class BaselineDao extends AbstractDao {
   public Baseline createLocal() {
      Baseline proto = new Baseline();

      return proto;
   }

   public int deleteByPK(Baseline proto) throws DalException {
      return getQueryEngine().deleteSingle(
            BaselineEntity.DELETE_BY_PK,
            proto);
   }
   
   public Baseline findByPK(int keyId, Readset<Baseline> readset) throws DalException {
      Baseline proto = new Baseline();

      proto.setKeyId(keyId);

      Baseline result = getQueryEngine().querySingle(
            BaselineEntity.FIND_BY_PK, 
            proto,
            readset);
      
      return result;
   }
   
   public Baseline findByReportNameKeyTime(java.util.Date reportPeriod, String reportName, String indexKey, Readset<Baseline> readset) throws DalException {
      Baseline proto = new Baseline();

      proto.setReportPeriod(reportPeriod);
      proto.setReportName(reportName);
      proto.setIndexKey(indexKey);

      Baseline result = getQueryEngine().querySingle(
            BaselineEntity.FIND_BY_REPORT_NAME_KEY_TIME, 
            proto,
            readset);
      
      return result;
   }
   
   @Override
   protected Class<?>[] getEntityClasses() {
      return new Class<?>[] { BaselineEntity.class };
   }

   public int insert(Baseline proto) throws DalException {
      return getQueryEngine().insertSingle(
            BaselineEntity.INSERT,
            proto);
   }
   
   public int updateByPK(Baseline proto, Updateset<Baseline> updateset) throws DalException {
      return getQueryEngine().updateSingle(
            BaselineEntity.UPDATE_BY_PK,
            proto,
            updateset);
   }
   
}
