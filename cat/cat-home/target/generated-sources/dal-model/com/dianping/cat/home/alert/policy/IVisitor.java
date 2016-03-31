package com.dianping.cat.home.alert.policy;

import com.dianping.cat.home.alert.policy.entity.AlertPolicy;
import com.dianping.cat.home.alert.policy.entity.Group;
import com.dianping.cat.home.alert.policy.entity.Level;
import com.dianping.cat.home.alert.policy.entity.Type;

public interface IVisitor {

   public void visitAlertPolicy(AlertPolicy alertPolicy);

   public void visitGroup(Group group);

   public void visitLevel(Level level);

   public void visitType(Type type);
}
