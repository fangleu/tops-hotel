package com.dianping.cat.app;

import java.util.List;
import org.unidal.dal.jdbc.DalException;
import org.unidal.dal.jdbc.AbstractDao;
import org.unidal.dal.jdbc.Readset;
import org.unidal.dal.jdbc.Updateset;

public class AppSpeedDataDao extends AbstractDao {
   public AppSpeedData createLocal() {
      AppSpeedData proto = new AppSpeedData();

      return proto;
   }

   public int deleteByPK(AppSpeedData proto) throws DalException {
      return getQueryEngine().deleteSingle(
            AppSpeedDataEntity.DELETE_BY_PK,
            proto);
   }
   
   public int deleteBeforePeriod(AppSpeedData proto) throws DalException {
      return getQueryEngine().deleteSingle(
            AppSpeedDataEntity.DELETE_BEFORE_PERIOD,
            proto);
   }
   
   public List<AppSpeedData> findDataByMinute(int speedId, java.util.Date period, int city, int operator, int network, int appVersion, int platform, Readset<AppSpeedData> readset) throws DalException {
      AppSpeedData proto = new AppSpeedData();

      proto.setSpeedId(speedId);
      proto.setPeriod(period);
      proto.setCity(city);
      proto.setOperator(operator);
      proto.setNetwork(network);
      proto.setAppVersion(appVersion);
      proto.setPlatform(platform);

      List<AppSpeedData> result = getQueryEngine().queryMultiple(
            AppSpeedDataEntity.FIND_DATA_BY_MINUTE, 
            proto,
            readset);
      
      return result;
   }
   
   public AppSpeedData findByPK(int keyId, Readset<AppSpeedData> readset) throws DalException {
      AppSpeedData proto = new AppSpeedData();

      proto.setKeyId(keyId);

      AppSpeedData result = getQueryEngine().querySingle(
            AppSpeedDataEntity.FIND_BY_PK, 
            proto,
            readset);
      
      return result;
   }
   
   @Override
   protected Class<?>[] getEntityClasses() {
      return new Class<?>[] { AppSpeedDataEntity.class };
   }

   public int[] insert(AppSpeedData[] protos) throws DalException {
      return getQueryEngine().insertBatch(
            AppSpeedDataEntity.INSERT,
            protos);
   }
   
   public int[] insertOrUpdate(AppSpeedData[] protos) throws DalException {
      return getQueryEngine().insertBatch(
            AppSpeedDataEntity.INSERT_OR_UPDATE,
            protos);
   }
   
   public int insert(AppSpeedData proto) throws DalException {
      return getQueryEngine().insertSingle(
            AppSpeedDataEntity.INSERT,
            proto);
   }
   
   public int insertOrUpdate(AppSpeedData proto) throws DalException {
      return getQueryEngine().insertSingle(
            AppSpeedDataEntity.INSERT_OR_UPDATE,
            proto);
   }
   
   public int updateByPK(AppSpeedData proto, Updateset<AppSpeedData> updateset) throws DalException {
      return getQueryEngine().updateSingle(
            AppSpeedDataEntity.UPDATE_BY_PK,
            proto,
            updateset);
   }
   
}
