package com.dianping.cat.app;

import java.util.List;
import org.unidal.dal.jdbc.DalException;
import org.unidal.dal.jdbc.AbstractDao;
import org.unidal.dal.jdbc.Readset;
import org.unidal.dal.jdbc.Updateset;

public class AppConnectionDataDao extends AbstractDao {
   public AppConnectionData createLocal() {
      AppConnectionData proto = new AppConnectionData();

      return proto;
   }

   public int deleteByPK(AppConnectionData proto) throws DalException {
      return getQueryEngine().deleteSingle(
            AppConnectionDataEntity.DELETE_BY_PK,
            proto);
   }
   
   public int deleteBeforePeriod(AppConnectionData proto) throws DalException {
      return getQueryEngine().deleteSingle(
            AppConnectionDataEntity.DELETE_BEFORE_PERIOD,
            proto);
   }
   
   public List<AppConnectionData> findDataByMinute(int commandId, java.util.Date period, int city, int operator, int network, int appVersion, int connectType, int code, int platform, Readset<AppConnectionData> readset) throws DalException {
      AppConnectionData proto = new AppConnectionData();

      proto.setCommandId(commandId);
      proto.setPeriod(period);
      proto.setCity(city);
      proto.setOperator(operator);
      proto.setNetwork(network);
      proto.setAppVersion(appVersion);
      proto.setConnectType(connectType);
      proto.setCode(code);
      proto.setPlatform(platform);

      List<AppConnectionData> result = getQueryEngine().queryMultiple(
            AppConnectionDataEntity.FIND_DATA_BY_MINUTE, 
            proto,
            readset);
      
      return result;
   }
   
   public List<AppConnectionData> findDataByMinuteCode(int commandId, java.util.Date period, int city, int operator, int network, int appVersion, int connectType, int code, int platform, Readset<AppConnectionData> readset) throws DalException {
      AppConnectionData proto = new AppConnectionData();

      proto.setCommandId(commandId);
      proto.setPeriod(period);
      proto.setCity(city);
      proto.setOperator(operator);
      proto.setNetwork(network);
      proto.setAppVersion(appVersion);
      proto.setConnectType(connectType);
      proto.setCode(code);
      proto.setPlatform(platform);

      List<AppConnectionData> result = getQueryEngine().queryMultiple(
            AppConnectionDataEntity.FIND_DATA_BY_MINUTE_CODE, 
            proto,
            readset);
      
      return result;
   }
   
   public List<AppConnectionData> findDataByCode(int commandId, java.util.Date period, int city, int operator, int network, int appVersion, int connectType, int code, int platform, int startMinuteOrder, int endMinuteOrder, Readset<AppConnectionData> readset) throws DalException {
      AppConnectionData proto = new AppConnectionData();

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

      List<AppConnectionData> result = getQueryEngine().queryMultiple(
            AppConnectionDataEntity.FIND_DATA_BY_CODE, 
            proto,
            readset);
      
      return result;
   }
   
   public List<AppConnectionData> findDataByOperatorCode(int commandId, java.util.Date period, int city, int operator, int network, int appVersion, int connectType, int code, int platform, Readset<AppConnectionData> readset) throws DalException {
      AppConnectionData proto = new AppConnectionData();

      proto.setCommandId(commandId);
      proto.setPeriod(period);
      proto.setCity(city);
      proto.setOperator(operator);
      proto.setNetwork(network);
      proto.setAppVersion(appVersion);
      proto.setConnectType(connectType);
      proto.setCode(code);
      proto.setPlatform(platform);

      List<AppConnectionData> result = getQueryEngine().queryMultiple(
            AppConnectionDataEntity.FIND_DATA_BY_OPERATOR_CODE, 
            proto,
            readset);
      
      return result;
   }
   
   public List<AppConnectionData> findDataByNetworkCode(int commandId, java.util.Date period, int city, int operator, int network, int appVersion, int connectType, int code, int platform, Readset<AppConnectionData> readset) throws DalException {
      AppConnectionData proto = new AppConnectionData();

      proto.setCommandId(commandId);
      proto.setPeriod(period);
      proto.setCity(city);
      proto.setOperator(operator);
      proto.setNetwork(network);
      proto.setAppVersion(appVersion);
      proto.setConnectType(connectType);
      proto.setCode(code);
      proto.setPlatform(platform);

      List<AppConnectionData> result = getQueryEngine().queryMultiple(
            AppConnectionDataEntity.FIND_DATA_BY_NETWORK_CODE, 
            proto,
            readset);
      
      return result;
   }
   
   public List<AppConnectionData> findDataByAppVersionCode(int commandId, java.util.Date period, int city, int operator, int network, int appVersion, int connectType, int code, int platform, Readset<AppConnectionData> readset) throws DalException {
      AppConnectionData proto = new AppConnectionData();

      proto.setCommandId(commandId);
      proto.setPeriod(period);
      proto.setCity(city);
      proto.setOperator(operator);
      proto.setNetwork(network);
      proto.setAppVersion(appVersion);
      proto.setConnectType(connectType);
      proto.setCode(code);
      proto.setPlatform(platform);

      List<AppConnectionData> result = getQueryEngine().queryMultiple(
            AppConnectionDataEntity.FIND_DATA_BY_APP_VERSION_CODE, 
            proto,
            readset);
      
      return result;
   }
   
   public List<AppConnectionData> findDataByConnectTypeCode(int commandId, java.util.Date period, int city, int operator, int network, int appVersion, int connectType, int code, int platform, Readset<AppConnectionData> readset) throws DalException {
      AppConnectionData proto = new AppConnectionData();

      proto.setCommandId(commandId);
      proto.setPeriod(period);
      proto.setCity(city);
      proto.setOperator(operator);
      proto.setNetwork(network);
      proto.setAppVersion(appVersion);
      proto.setConnectType(connectType);
      proto.setCode(code);
      proto.setPlatform(platform);

      List<AppConnectionData> result = getQueryEngine().queryMultiple(
            AppConnectionDataEntity.FIND_DATA_BY_CONNECT_TYPE_CODE, 
            proto,
            readset);
      
      return result;
   }
   
   public List<AppConnectionData> findDataByPlatformCode(int commandId, java.util.Date period, int city, int operator, int network, int appVersion, int connectType, int code, int platform, Readset<AppConnectionData> readset) throws DalException {
      AppConnectionData proto = new AppConnectionData();

      proto.setCommandId(commandId);
      proto.setPeriod(period);
      proto.setCity(city);
      proto.setOperator(operator);
      proto.setNetwork(network);
      proto.setAppVersion(appVersion);
      proto.setConnectType(connectType);
      proto.setCode(code);
      proto.setPlatform(platform);

      List<AppConnectionData> result = getQueryEngine().queryMultiple(
            AppConnectionDataEntity.FIND_DATA_BY_PLATFORM_CODE, 
            proto,
            readset);
      
      return result;
   }
   
   public List<AppConnectionData> findDataByCityCode(int commandId, java.util.Date period, int city, int operator, int network, int appVersion, int connectType, int code, int platform, Readset<AppConnectionData> readset) throws DalException {
      AppConnectionData proto = new AppConnectionData();

      proto.setCommandId(commandId);
      proto.setPeriod(period);
      proto.setCity(city);
      proto.setOperator(operator);
      proto.setNetwork(network);
      proto.setAppVersion(appVersion);
      proto.setConnectType(connectType);
      proto.setCode(code);
      proto.setPlatform(platform);

      List<AppConnectionData> result = getQueryEngine().queryMultiple(
            AppConnectionDataEntity.FIND_DATA_BY_CITY_CODE, 
            proto,
            readset);
      
      return result;
   }
   
   public List<AppConnectionData> findDataByOperator(int commandId, java.util.Date period, int city, int operator, int network, int appVersion, int connectType, int code, int platform, int startMinuteOrder, int endMinuteOrder, Readset<AppConnectionData> readset) throws DalException {
      AppConnectionData proto = new AppConnectionData();

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

      List<AppConnectionData> result = getQueryEngine().queryMultiple(
            AppConnectionDataEntity.FIND_DATA_BY_OPERATOR, 
            proto,
            readset);
      
      return result;
   }
   
   public List<AppConnectionData> findDataByNetwork(int commandId, java.util.Date period, int city, int operator, int network, int appVersion, int connectType, int code, int platform, int startMinuteOrder, int endMinuteOrder, Readset<AppConnectionData> readset) throws DalException {
      AppConnectionData proto = new AppConnectionData();

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

      List<AppConnectionData> result = getQueryEngine().queryMultiple(
            AppConnectionDataEntity.FIND_DATA_BY_NETWORK, 
            proto,
            readset);
      
      return result;
   }
   
   public List<AppConnectionData> findDataByAppVersion(int commandId, java.util.Date period, int city, int operator, int network, int appVersion, int connectType, int code, int platform, int startMinuteOrder, int endMinuteOrder, Readset<AppConnectionData> readset) throws DalException {
      AppConnectionData proto = new AppConnectionData();

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

      List<AppConnectionData> result = getQueryEngine().queryMultiple(
            AppConnectionDataEntity.FIND_DATA_BY_APP_VERSION, 
            proto,
            readset);
      
      return result;
   }
   
   public List<AppConnectionData> findDataByConnectType(int commandId, java.util.Date period, int city, int operator, int network, int appVersion, int connectType, int code, int platform, int startMinuteOrder, int endMinuteOrder, Readset<AppConnectionData> readset) throws DalException {
      AppConnectionData proto = new AppConnectionData();

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

      List<AppConnectionData> result = getQueryEngine().queryMultiple(
            AppConnectionDataEntity.FIND_DATA_BY_CONNECT_TYPE, 
            proto,
            readset);
      
      return result;
   }
   
   public List<AppConnectionData> findDataByPlatform(int commandId, java.util.Date period, int city, int operator, int network, int appVersion, int connectType, int code, int platform, int startMinuteOrder, int endMinuteOrder, Readset<AppConnectionData> readset) throws DalException {
      AppConnectionData proto = new AppConnectionData();

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

      List<AppConnectionData> result = getQueryEngine().queryMultiple(
            AppConnectionDataEntity.FIND_DATA_BY_PLATFORM, 
            proto,
            readset);
      
      return result;
   }
   
   public List<AppConnectionData> findDataByCity(int commandId, java.util.Date period, int city, int operator, int network, int appVersion, int connectType, int code, int platform, int startMinuteOrder, int endMinuteOrder, Readset<AppConnectionData> readset) throws DalException {
      AppConnectionData proto = new AppConnectionData();

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

      List<AppConnectionData> result = getQueryEngine().queryMultiple(
            AppConnectionDataEntity.FIND_DATA_BY_CITY, 
            proto,
            readset);
      
      return result;
   }
   
   public AppConnectionData findByPK(int keyId, Readset<AppConnectionData> readset) throws DalException {
      AppConnectionData proto = new AppConnectionData();

      proto.setKeyId(keyId);

      AppConnectionData result = getQueryEngine().querySingle(
            AppConnectionDataEntity.FIND_BY_PK, 
            proto,
            readset);
      
      return result;
   }
   
   @Override
   protected Class<?>[] getEntityClasses() {
      return new Class<?>[] { AppConnectionDataEntity.class };
   }

   public int[] insert(AppConnectionData[] protos) throws DalException {
      return getQueryEngine().insertBatch(
            AppConnectionDataEntity.INSERT,
            protos);
   }
   
   public int[] insertOrUpdate(AppConnectionData[] protos) throws DalException {
      return getQueryEngine().insertBatch(
            AppConnectionDataEntity.INSERT_OR_UPDATE,
            protos);
   }
   
   public int insert(AppConnectionData proto) throws DalException {
      return getQueryEngine().insertSingle(
            AppConnectionDataEntity.INSERT,
            proto);
   }
   
   public int insertOrUpdate(AppConnectionData proto) throws DalException {
      return getQueryEngine().insertSingle(
            AppConnectionDataEntity.INSERT_OR_UPDATE,
            proto);
   }
   
   public int updateByPK(AppConnectionData proto, Updateset<AppConnectionData> updateset) throws DalException {
      return getQueryEngine().updateSingle(
            AppConnectionDataEntity.UPDATE_BY_PK,
            proto,
            updateset);
   }
   
}
