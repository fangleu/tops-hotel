package com.dianping.cat.home.alert.policy.transform;

import com.dianping.cat.home.alert.policy.entity.AlertPolicy;
import com.dianping.cat.home.alert.policy.entity.Group;
import com.dianping.cat.home.alert.policy.entity.Level;
import com.dianping.cat.home.alert.policy.entity.Type;

public interface ILinker {

   public boolean onGroup(Type parent, Group group);

   public boolean onLevel(Group parent, Level level);

   public boolean onType(AlertPolicy parent, Type type);
}
