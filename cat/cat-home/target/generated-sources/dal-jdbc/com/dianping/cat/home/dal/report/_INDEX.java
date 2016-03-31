package com.dianping.cat.home.dal.report;

public class _INDEX {
   public static Class<?>[] getEntityClasses() {
      return new Class<?>[] { AlertEntity.class, AlertSummaryEntity.class, AlterationEntity.class, BaselineEntity.class, ConfigModificationEntity.class, OverloadEntity.class, TopologyGraphEntity.class, UserDefineRuleEntity.class };
   }

   public static Class<?>[] getDaoClasses() {
      return new Class<?>[] { AlertDao.class, AlertSummaryDao.class, AlterationDao.class, BaselineDao.class, ConfigModificationDao.class, OverloadDao.class, TopologyGraphDao.class, UserDefineRuleDao.class };
   }

   public static Class<?>[] getDoClasses() {
      return new Class<?>[] { Alert.class, AlertSummary.class, Alteration.class, Baseline.class, ConfigModification.class, Overload.class, TopologyGraph.class, UserDefineRule.class };
   }

}
