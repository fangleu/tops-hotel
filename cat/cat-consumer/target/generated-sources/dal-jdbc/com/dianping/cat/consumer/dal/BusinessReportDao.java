package com.dianping.cat.consumer.dal;

import java.util.List;
import org.unidal.dal.jdbc.DalException;
import org.unidal.dal.jdbc.AbstractDao;
import org.unidal.dal.jdbc.Readset;
import org.unidal.dal.jdbc.Updateset;

public class BusinessReportDao extends AbstractDao {
   public BusinessReport createLocal() {
      BusinessReport proto = new BusinessReport();

      return proto;
   }

   public int deleteByPK(BusinessReport proto) throws DalException {
      return getQueryEngine().deleteSingle(
            BusinessReportEntity.DELETE_BY_PK,
            proto);
   }
   
   public List<BusinessReport> findAllByPeriodProductLineName(java.util.Date period, String productLine, Readset<BusinessReport> readset) throws DalException {
      BusinessReport proto = new BusinessReport();

      proto.setPeriod(period);
      proto.setProductLine(productLine);

      List<BusinessReport> result = getQueryEngine().queryMultiple(
            BusinessReportEntity.FIND_ALL_BY_PERIOD_PRODUCTLINE_NAME, 
            proto,
            readset);
      
      return result;
   }
   
   public BusinessReport findByPK(int keyId, Readset<BusinessReport> readset) throws DalException {
      BusinessReport proto = new BusinessReport();

      proto.setKeyId(keyId);

      BusinessReport result = getQueryEngine().querySingle(
            BusinessReportEntity.FIND_BY_PK, 
            proto,
            readset);
      
      return result;
   }
   
   @Override
   protected Class<?>[] getEntityClasses() {
      return new Class<?>[] { BusinessReportEntity.class };
   }

   public int insert(BusinessReport proto) throws DalException {
      return getQueryEngine().insertSingle(
            BusinessReportEntity.INSERT,
            proto);
   }
   
   public int updateByPK(BusinessReport proto, Updateset<BusinessReport> updateset) throws DalException {
      return getQueryEngine().updateSingle(
            BusinessReportEntity.UPDATE_BY_PK,
            proto,
            updateset);
   }
   
}
