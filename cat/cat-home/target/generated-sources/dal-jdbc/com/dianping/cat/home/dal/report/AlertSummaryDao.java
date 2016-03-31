package com.dianping.cat.home.dal.report;

import org.unidal.dal.jdbc.DalException;
import org.unidal.dal.jdbc.AbstractDao;
import org.unidal.dal.jdbc.Readset;
import org.unidal.dal.jdbc.Updateset;

public class AlertSummaryDao extends AbstractDao {
   public AlertSummary createLocal() {
      AlertSummary proto = new AlertSummary();

      return proto;
   }

   public int deleteByPK(AlertSummary proto) throws DalException {
      return getQueryEngine().deleteSingle(
            AlertSummaryEntity.DELETE_BY_PK,
            proto);
   }
   
   public AlertSummary findByPK(int keyId, Readset<AlertSummary> readset) throws DalException {
      AlertSummary proto = new AlertSummary();

      proto.setKeyId(keyId);

      AlertSummary result = getQueryEngine().querySingle(
            AlertSummaryEntity.FIND_BY_PK, 
            proto,
            readset);
      
      return result;
   }
   
   @Override
   protected Class<?>[] getEntityClasses() {
      return new Class<?>[] { AlertSummaryEntity.class };
   }

   public int insert(AlertSummary proto) throws DalException {
      return getQueryEngine().insertSingle(
            AlertSummaryEntity.INSERT,
            proto);
   }
   
   public int updateByPK(AlertSummary proto, Updateset<AlertSummary> updateset) throws DalException {
      return getQueryEngine().updateSingle(
            AlertSummaryEntity.UPDATE_BY_PK,
            proto,
            updateset);
   }
   
}
