package com.dianping.cat.core.config;

import java.util.List;
import org.unidal.dal.jdbc.DalException;
import org.unidal.dal.jdbc.AbstractDao;
import org.unidal.dal.jdbc.Readset;
import org.unidal.dal.jdbc.Updateset;

public class ConfigDao extends AbstractDao {
   public Config createLocal() {
      Config proto = new Config();

      return proto;
   }

   public int deleteByPK(Config proto) throws DalException {
      return getQueryEngine().deleteSingle(
            ConfigEntity.DELETE_BY_PK,
            proto);
   }
   
   public List<Config> findAllConfig(Readset<Config> readset) throws DalException {
      Config proto = new Config();

      List<Config> result = getQueryEngine().queryMultiple(
            ConfigEntity.FIND_ALL_CONFIG, 
            proto,
            readset);
      
      return result;
   }
   
   public Config findByPK(int keyId, Readset<Config> readset) throws DalException {
      Config proto = new Config();

      proto.setKeyId(keyId);

      Config result = getQueryEngine().querySingle(
            ConfigEntity.FIND_BY_PK, 
            proto,
            readset);
      
      return result;
   }
   
   public Config findByName(String name, Readset<Config> readset) throws DalException {
      Config proto = new Config();

      proto.setName(name);

      Config result = getQueryEngine().querySingle(
            ConfigEntity.FIND_BY_NAME, 
            proto,
            readset);
      
      return result;
   }
   
   @Override
   protected Class<?>[] getEntityClasses() {
      return new Class<?>[] { ConfigEntity.class };
   }

   public int insert(Config proto) throws DalException {
      return getQueryEngine().insertSingle(
            ConfigEntity.INSERT,
            proto);
   }
   
   public int updateByPK(Config proto, Updateset<Config> updateset) throws DalException {
      return getQueryEngine().updateSingle(
            ConfigEntity.UPDATE_BY_PK,
            proto,
            updateset);
   }
   
}
