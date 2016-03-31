package com.dianping.cat.app;

import java.util.List;
import org.unidal.dal.jdbc.DalException;
import org.unidal.dal.jdbc.AbstractDao;
import org.unidal.dal.jdbc.Readset;
import org.unidal.dal.jdbc.Updateset;

public class AppCommandDataDao extends AbstractDao {
   public AppCommandData createLocal() {
      AppCommandData proto = new AppCommandData();

      return proto;
   }

   public int deleteByPK(AppCommandData proto) throws DalException {
      return getQueryEngine().deleteSingle(
            AppCommandDataEntity.DELETE_BY_PK,
            proto);
   }
   
   public int deleteBeforePeriod(AppCommandData proto) throws DalException {
      return getQueryEngine().deleteSingle(
            AppCommandDataEntity.DELETE_BEFORE_PERIOD,
            proto);
   }
   
   public List<AppCommandData> findDataByMinute(int commandId, java.util.Date period, int city, int operator, int network, int appVersion, int connectType, int code, int platform, Readset<AppCommandData> readset) throws DalException {
      AppCommandData proto = new AppCommandData();

      proto.setCommandId(commandId);
      proto.setPeriod(period);
      proto.setCity(city);
      proto.setOperator(operator);
      proto.setNetwork(network);
      proto.setAppVersion(appVersion);
      proto.setConnectType(connectType);
      proto.setCode(code);
      proto.setPlatform(platform);

      List<AppCommandData> result = getQueryEngine().queryMultiple(
            AppCommandDataEntity.FIND_DATA_BY_MINUTE, 
            proto,
            readset);
      
      return result;
   }
   
   public List<AppCommandData> findDailyDataByCode(int commandId, java.util.Date period, Readset<AppCommandData> readset) throws DalException {
      AppCommandData proto = new AppCommandData();

      proto.setCommandId(commandId);
      proto.setPeriod(period);

      List<AppCommandData> result = getQueryEngine().queryMultiple(
            AppCommandDataEntity.FIND_DAILY_DATA_BY_CODE, 
            proto,
            readset);
      
      return result;
   }
   
   public List<AppCommandData> findDataByMinuteCode(int commandId, java.util.Date period, int city, int operator, int network, int appVersion, int connectType, int code, int platform, Readset<AppCommandData> readset) throws DalException {
      AppCommandData proto = new AppCommandData();

      proto.setCommandId(commandId);
      proto.setPeriod(period);
      proto.setCity(city);
      proto.setOperator(operator);
      proto.setNetwork(network);
      proto.setAppVersion(appVersion);
      proto.setConnectType(connectType);
      proto.setCode(code);
      proto.setPlatform(platform);

      List<AppCommandData> result = getQueryEngine().queryMultiple(
            AppCommandDataEntity.FIND_DATA_BY_MINUTE_CODE, 
            proto,
            readset);
      
      return result;
   }
   
   public List<AppCommandData> findDataByCode(int commandId, java.util.Date period, int city, int operator, int network, int appVersion, int connectType, int code, int platform, int startMinuteOrder, int endMinuteOrder, Readset<AppCommandData> readset) throws DalException {
      AppCommandData proto = new AppCommandData();

      proto.setCommandId(commandId);
      proto.setPeriod(period);
      proto.setCity(city);
      proto.setOperator(operator);
      proto.setNetwork(network);
      proto.setAppVersion(appVersion);
      proto.setConnectType(connectType);
      proto.setCode(code);
      proto.setPlatform(platform);
      proto.setStartMinuteOrder(startMinuteOrder);
      proto.setEndMinuteOrder(endMinuteOrder);

      List<AppCommandData> result = getQueryEngine().queryMultiple(
            AppCommandDataEntity.FIND_DATA_BY_CODE, 
            proto,
            readset);
      
      return result;
   }
   
   public List<AppCommandData> findDataByOperatorCode(int commandId, java.util.Date period, int city, int operator, int network, int appVersion, int connectType, int code, int platform, Readset<AppCommandData> readset) throws DalException {
      AppCommandData proto = new AppCommandData();

      proto.setCommandId(commandId);
      proto.setPeriod(period);
      proto.setCity(city);
      proto.setOperator(operator);
      proto.setNetwork(network);
      proto.setAppVersion(appVersion);
      proto.setConnectType(connectType);
      proto.setCode(code);
      proto.setPlatform(platform);

      List<AppCommandData> result = getQueryEngine().queryMultiple(
            AppCommandDataEntity.FIND_DATA_BY_OPERATOR_CODE, 
            proto,
            readset);
      
      return result;
   }
   
   public List<AppCommandData> findDataByNetworkCode(int commandId, java.util.Date period, int city, int operator, int network, int appVersion, int connectType, int code, int platform, Readset<AppCommandData> readset) throws DalException {
      AppCommandData proto = new AppCommandData();

      proto.setCommandId(commandId);
      proto.setPeriod(period);
      proto.setCity(city);
      proto.setOperator(operator);
      proto.setNetwork(network);
      proto.setAppVersion(appVersion);
      proto.setConnectType(connectType);
      proto.setCode(code);
      proto.setPlatform(platform);

      List<AppCommandData> result = getQueryEngine().queryMultiple(
            AppCommandDataEntity.FIND_DATA_BY_NETWORK_CODE, 
            proto,
            readset);
      
      return result;
   }
   
   public List<AppCommandData> findDataByAppVersionCode(int commandId, java.util.Date period, int city, int operator, int network, int appVersion, int connectType, int code, int platform, Readset<AppCommandData> readset) throws DalException {
      AppCommandData proto = new AppCommandData();

      proto.setCommandId(commandId);
      proto.setPeriod(period);
      proto.setCity(city);
      proto.setOperator(operator);
      proto.setNetwork(network);
      proto.setAppVersion(appVersion);
      proto.setConnectType(connectType);
      proto.setCode(code);
      proto.setPlatform(platform);

      List<AppCommandData> result = getQueryEngine().queryMultiple(
            AppCommandDataEntity.FIND_DATA_BY_APP_VERSION_CODE, 
            proto,
            readset);
      
      return result;
   }
   
   public List<AppCommandData> findDataByConnectTypeCode(int commandId, java.util.Date period, int city, int operator, int network, int appVersion, int connectType, int code, int platform, Readset<AppCommandData> readset) throws DalException {
      AppCommandData proto = new AppCommandData();

      proto.setCommandId(commandId);
      proto.setPeriod(period);
      proto.setCity(city);
      proto.setOperator(operator);
      proto.setNetwork(network);
      proto.setAppVersion(appVersion);
      proto.setConnectType(connectType);
      proto.setCode(code);
      proto.setPlatform(platform);

      List<AppCommandData> result = getQueryEngine().queryMultiple(
            AppCommandDataEntity.FIND_DATA_BY_CONNECT_TYPE_CODE, 
            proto,
            readset);
      
      return result;
   }
   
   public List<AppCommandData> findDataByPlatformCode(int commandId, java.util.Date period, int city, int operator, int network, int appVersion, int connectType, int code, int platform, Readset<AppCommandData> readset) throws DalException {
      AppCommandData proto = new AppCommandData();

      proto.setCommandId(commandId);
      proto.setPeriod(period);
      proto.setCity(city);
      proto.setOperator(operator);
      proto.setNetwork(network);
      proto.setAppVersion(appVersion);
      proto.setConnectType(connectType);
      proto.setCode(code);
      proto.setPlatform(platform);

      List<AppCommandData> result = getQueryEngine().queryMultiple(
            AppCommandDataEntity.FIND_DATA_BY_PLATFORM_CODE, 
            proto,
            readset);
      
      return result;
   }
   
   public List<AppCommandData> findDataByCityCode(int commandId, java.util.Date period, int city, int operator, int network, int appVersion, int connectType, int code, int platform, Readset<AppCommandData> readset) throws DalException {
      AppCommandData proto = new AppCommandData();

      proto.setCommandId(commandId);
      proto.setPeriod(period);
      proto.setCity(city);
      proto.setOperator(operator);
      proto.setNetwork(network);
      proto.setAppVersion(appVersion);
      proto.setConnectType(connectType);
      proto.setCode(code);
      proto.setPlatform(platform);

      List<AppCommandData> result = getQueryEngine().queryMultiple(
            AppCommandDataEntity.FIND_DATA_BY_CITY_CODE, 
            proto,
            readset);
      
      return result;
   }
   
   public List<AppCommandData> findDataByOperator(int commandId, java.util.Date period, int city, int operator, int network, int appVersion, int connectType, int code, int platform, int startMinuteOrder, int endMinuteOrder, Readset<AppCommandData> readset) throws DalException {
      AppCommandData proto = new AppCommandData();

      proto.setCommandId(commandId);
      proto.setPeriod(period);
      proto.setCity(city);
      proto.setOperator(operator);
      proto.setNetwork(network);
      proto.setAppVersion(appVersion);
      proto.setConnectType(connectType);
      proto.setCode(code);
      proto.setPlatform(platform);
      proto.setStartMinuteOrder(startMinuteOrder);
      proto.setEndMinuteOrder(endMinuteOrder);

      List<AppCommandData> result = getQueryEngine().queryMultiple(
            AppCommandDataEntity.FIND_DATA_BY_OPERATOR, 
            proto,
            readset);
      
      return result;
   }
   
   public List<AppCommandData> findDataByNetwork(int commandId, java.util.Date period, int city, int operator, int network, int appVersion, int connectType, int code, int platform, int startMinuteOrder, int endMinuteOrder, Readset<AppCommandData> readset) throws DalException {
      AppCommandData proto = new AppCommandData();

      proto.setCommandId(commandId);
      proto.setPeriod(period);
      proto.setCity(city);
      proto.setOperator(operator);
      proto.setNetwork(network);
      proto.setAppVersion(appVersion);
      proto.setConnectType(connectType);
      proto.setCode(code);
      proto.setPlatform(platform);
      proto.setStartMinuteOrder(startMinuteOrder);
      proto.setEndMinuteOrder(endMinuteOrder);

      List<AppCommandData> result = getQueryEngine().queryMultiple(
            AppCommandDataEntity.FIND_DATA_BY_NETWORK, 
            proto,
            readset);
      
      return result;
   }
   
   public List<AppCommandData> findDataByAppVersion(int commandId, java.util.Date period, int city, int operator, int network, int appVersion, int connectType, int code, int platform, int startMinuteOrder, int endMinuteOrder, Readset<AppCommandData> readset) throws DalException {
      AppCommandData proto = new AppCommandData();

      proto.setCommandId(commandId);
      proto.setPeriod(period);
      proto.setCity(city);
      proto.setOperator(operator);
      proto.setNetwork(network);
      proto.setAppVersion(appVersion);
      proto.setConnectType(connectType);
      proto.setCode(code);
      proto.setPlatform(platform);
      proto.setStartMinuteOrder(startMinuteOrder);
      proto.setEndMinuteOrder(endMinuteOrder);

      List<AppCommandData> result = getQueryEngine().queryMultiple(
            AppCommandDataEntity.FIND_DATA_BY_APP_VERSION, 
            proto,
            readset);
      
      return result;
   }
   
   public List<AppCommandData> findDataByConnectType(int commandId, java.util.Date period, int city, int operator, int network, int appVersion, int connectType, int code, int platform, int startMinuteOrder, int endMinuteOrder, Readset<AppCommandData> readset) throws DalException {
      AppCommandData proto = new AppCommandData();

      proto.setCommandId(commandId);
      proto.setPeriod(period);
      proto.setCity(city);
      proto.setOperator(operator);
      proto.setNetwork(network);
      proto.setAppVersion(appVersion);
      proto.setConnectType(connectType);
      proto.setCode(code);
      proto.setPlatform(platform);
      proto.setStartMinuteOrder(startMinuteOrder);
      proto.setEndMinuteOrder(endMinuteOrder);

      List<AppCommandData> result = getQueryEngine().queryMultiple(
            AppCommandDataEntity.FIND_DATA_BY_CONNECT_TYPE, 
            proto,
            readset);
      
      return result;
   }
   
   public List<AppCommandData> findDataByPlatform(int commandId, java.util.Date period, int city, int operator, int network, int appVersion, int connectType, int code, int platform, int startMinuteOrder, int endMinuteOrder, Readset<AppCommandData> readset) throws DalException {
      AppCommandData proto = new AppCommandData();

      proto.setCommandId(commandId);
      proto.setPeriod(period);
      proto.setCity(city);
      proto.setOperator(operator);
      proto.setNetwork(network);
      proto.setAppVersion(appVersion);
      proto.setConnectType(connectType);
      proto.setCode(code);
      proto.setPlatform(platform);
      proto.setStartMinuteOrder(startMinuteOrder);
      proto.setEndMinuteOrder(endMinuteOrder);

      List<AppCommandData> result = getQueryEngine().queryMultiple(
            AppCommandDataEntity.FIND_DATA_BY_PLATFORM, 
            proto,
            readset);
      
      return result;
   }
   
   public List<AppCommandData> findDataByCity(int commandId, java.util.Date period, int city, int operator, int network, int appVersion, int connectType, int code, int platform, int startMinuteOrder, int endMinuteOrder, Readset<AppCommandData> readset) throws DalException {
      AppCommandData proto = new AppCommandData();

      proto.setCommandId(commandId);
      proto.setPeriod(period);
      proto.setCity(city);
      proto.setOperator(operator);
      proto.setNetwork(network);
      proto.setAppVersion(appVersion);
      proto.setConnectType(connectType);
      proto.setCode(code);
      proto.setPlatform(platform);
      proto.setStartMinuteOrder(startMinuteOrder);
      proto.setEndMinuteOrder(endMinuteOrder);

      List<AppCommandData> result = getQueryEngine().queryMultiple(
            AppCommandDataEntity.FIND_DATA_BY_CITY, 
            proto,
            readset);
      
      return result;
   }
   
   public AppCommandData findByPK(int keyId, Readset<AppCommandData> readset) throws DalException {
      AppCommandData proto = new AppCommandData();

      proto.setKeyId(keyId);

      AppCommandData result = getQueryEngine().querySingle(
            AppCommandDataEntity.FIND_BY_PK, 
            proto,
            readset);
      
      return result;
   }
   
   @Override
   protected Class<?>[] getEntityClasses() {
      return new Class<?>[] { AppCommandDataEntity.class };
   }

   public int[] insert(AppCommandData[] protos) throws DalException {
      return getQueryEngine().insertBatch(
            AppCommandDataEntity.INSERT,
            protos);
   }
   
   public int[] insertOrUpdate(AppCommandData[] protos) throws DalException {
      return getQueryEngine().insertBatch(
            AppCommandDataEntity.INSERT_OR_UPDATE,
            protos);
   }
   
   public int insert(AppCommandData proto) throws DalException {
      return getQueryEngine().insertSingle(
            AppCommandDataEntity.INSERT,
            proto);
   }
   
   public int insertOrUpdate(AppCommandData proto) throws DalException {
      return getQueryEngine().insertSingle(
            AppCommandDataEntity.INSERT_OR_UPDATE,
            proto);
   }
   
   public int updateByPK(AppCommandData proto, Updateset<AppCommandData> updateset) throws DalException {
      return getQueryEngine().updateSingle(
            AppCommandDataEntity.UPDATE_BY_PK,
            proto,
            updateset);
   }
   
}
