package com.dianping.cat.home.dal.report;

import org.unidal.dal.jdbc.DalException;
import org.unidal.dal.jdbc.AbstractDao;
import org.unidal.dal.jdbc.Readset;
import org.unidal.dal.jdbc.Updateset;

public class TopologyGraphDao extends AbstractDao {
   public TopologyGraph createLocal() {
      TopologyGraph proto = new TopologyGraph();

      return proto;
   }

   public int deleteByPK(TopologyGraph proto) throws DalException {
      return getQueryEngine().deleteSingle(
            TopologyGraphEntity.DELETE_BY_PK,
            proto);
   }
   
   public TopologyGraph findByPK(int keyId, Readset<TopologyGraph> readset) throws DalException {
      TopologyGraph proto = new TopologyGraph();

      proto.setKeyId(keyId);

      TopologyGraph result = getQueryEngine().querySingle(
            TopologyGraphEntity.FIND_BY_PK, 
            proto,
            readset);
      
      return result;
   }
   
   public TopologyGraph findByPeriod(java.util.Date period, Readset<TopologyGraph> readset) throws DalException {
      TopologyGraph proto = new TopologyGraph();

      proto.setPeriod(period);

      TopologyGraph result = getQueryEngine().querySingle(
            TopologyGraphEntity.FIND_BY_PERIOD, 
            proto,
            readset);
      
      return result;
   }
   
   @Override
   protected Class<?>[] getEntityClasses() {
      return new Class<?>[] { TopologyGraphEntity.class };
   }

   public int insert(TopologyGraph proto) throws DalException {
      return getQueryEngine().insertSingle(
            TopologyGraphEntity.INSERT,
            proto);
   }
   
   public int updateByPK(TopologyGraph proto, Updateset<TopologyGraph> updateset) throws DalException {
      return getQueryEngine().updateSingle(
            TopologyGraphEntity.UPDATE_BY_PK,
            proto,
            updateset);
   }
   
}
