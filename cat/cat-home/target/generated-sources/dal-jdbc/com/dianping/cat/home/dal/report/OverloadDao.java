package com.dianping.cat.home.dal.report;

import java.util.List;
import org.unidal.dal.jdbc.DalException;
import org.unidal.dal.jdbc.AbstractDao;
import org.unidal.dal.jdbc.Readset;
import org.unidal.dal.jdbc.Updateset;

public class OverloadDao extends AbstractDao {
   public Overload createLocal() {
      Overload proto = new Overload();

      return proto;
   }

   public int deleteByPK(Overload proto) throws DalException {
      return getQueryEngine().deleteSingle(
            OverloadEntity.DELETE_BY_PK,
            proto);
   }
   
   public List<Overload> findIdAndSizeByDuration(java.util.Date startTime, java.util.Date endTime, Readset<Overload> readset) throws DalException {
      Overload proto = new Overload();

      proto.setStartTime(startTime);
      proto.setEndTime(endTime);

      List<Overload> result = getQueryEngine().queryMultiple(
            OverloadEntity.FIND_ID_AND_SIZE_BY_DURATION, 
            proto,
            readset);
      
      return result;
   }
   
   public Overload findByPK(int keyId, Readset<Overload> readset) throws DalException {
      Overload proto = new Overload();

      proto.setKeyId(keyId);

      Overload result = getQueryEngine().querySingle(
            OverloadEntity.FIND_BY_PK, 
            proto,
            readset);
      
      return result;
   }
   
   public Overload findMaxIdByType(int type, Readset<Overload> readset) throws DalException {
      Overload proto = new Overload();

      proto.setType(type);

      Overload result = getQueryEngine().querySingle(
            OverloadEntity.FIND_MAX_ID_BY_TYPE, 
            proto,
            readset);
      
      return result;
   }
   
   public Overload findCount(Readset<Overload> readset) throws DalException {
      Overload proto = new Overload();

      Overload result = getQueryEngine().querySingle(
            OverloadEntity.FIND_COUNT, 
            proto,
            readset);
      
      return result;
   }
   
   @Override
   protected Class<?>[] getEntityClasses() {
      return new Class<?>[] { OverloadEntity.class };
   }

   public int insert(Overload proto) throws DalException {
      return getQueryEngine().insertSingle(
            OverloadEntity.INSERT,
            proto);
   }
   
   public int updateByPK(Overload proto, Updateset<Overload> updateset) throws DalException {
      return getQueryEngine().updateSingle(
            OverloadEntity.UPDATE_BY_PK,
            proto,
            updateset);
   }
   
}
