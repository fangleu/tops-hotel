package com.dianping.cat.home.dal.report;

import org.unidal.dal.jdbc.DalException;
import org.unidal.dal.jdbc.AbstractDao;
import org.unidal.dal.jdbc.Readset;
import org.unidal.dal.jdbc.Updateset;

public class ConfigModificationDao extends AbstractDao {
   public ConfigModification createLocal() {
      ConfigModification proto = new ConfigModification();

      return proto;
   }

   public int deleteByPK(ConfigModification proto) throws DalException {
      return getQueryEngine().deleteSingle(
            ConfigModificationEntity.DELETE_BY_PK,
            proto);
   }
   
   public ConfigModification findByPK(int keyId, Readset<ConfigModification> readset) throws DalException {
      ConfigModification proto = new ConfigModification();

      proto.setKeyId(keyId);

      ConfigModification result = getQueryEngine().querySingle(
            ConfigModificationEntity.FIND_BY_PK, 
            proto,
            readset);
      
      return result;
   }
   
   @Override
   protected Class<?>[] getEntityClasses() {
      return new Class<?>[] { ConfigModificationEntity.class };
   }

   public int insert(ConfigModification proto) throws DalException {
      return getQueryEngine().insertSingle(
            ConfigModificationEntity.INSERT,
            proto);
   }
   
   public int updateByPK(ConfigModification proto, Updateset<ConfigModification> updateset) throws DalException {
      return getQueryEngine().updateSingle(
            ConfigModificationEntity.UPDATE_BY_PK,
            proto,
            updateset);
   }
   
}
