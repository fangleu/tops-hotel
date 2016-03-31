package com.dianping.cat.home.dal.report;

import org.unidal.dal.jdbc.DalException;
import org.unidal.dal.jdbc.AbstractDao;
import org.unidal.dal.jdbc.Readset;
import org.unidal.dal.jdbc.Updateset;

public class UserDefineRuleDao extends AbstractDao {
   public UserDefineRule createLocal() {
      UserDefineRule proto = new UserDefineRule();

      return proto;
   }

   public int deleteByPK(UserDefineRule proto) throws DalException {
      return getQueryEngine().deleteSingle(
            UserDefineRuleEntity.DELETE_BY_PK,
            proto);
   }
   
   public UserDefineRule findByPK(int keyId, Readset<UserDefineRule> readset) throws DalException {
      UserDefineRule proto = new UserDefineRule();

      proto.setKeyId(keyId);

      UserDefineRule result = getQueryEngine().querySingle(
            UserDefineRuleEntity.FIND_BY_PK, 
            proto,
            readset);
      
      return result;
   }
   
   public UserDefineRule findMaxId(Readset<UserDefineRule> readset) throws DalException {
      UserDefineRule proto = new UserDefineRule();

      UserDefineRule result = getQueryEngine().querySingle(
            UserDefineRuleEntity.FIND_MAX_ID, 
            proto,
            readset);
      
      return result;
   }
   
   @Override
   protected Class<?>[] getEntityClasses() {
      return new Class<?>[] { UserDefineRuleEntity.class };
   }

   public int insert(UserDefineRule proto) throws DalException {
      return getQueryEngine().insertSingle(
            UserDefineRuleEntity.INSERT,
            proto);
   }
   
   public int updateByPK(UserDefineRule proto, Updateset<UserDefineRule> updateset) throws DalException {
      return getQueryEngine().updateSingle(
            UserDefineRuleEntity.UPDATE_BY_PK,
            proto,
            updateset);
   }
   
}
