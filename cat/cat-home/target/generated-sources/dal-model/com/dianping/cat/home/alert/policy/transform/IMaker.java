package com.dianping.cat.home.alert.policy.transform;

import com.dianping.cat.home.alert.policy.entity.AlertPolicy;
import com.dianping.cat.home.alert.policy.entity.Group;
import com.dianping.cat.home.alert.policy.entity.Level;
import com.dianping.cat.home.alert.policy.entity.Type;

public interface IMaker<T> {

   public AlertPolicy buildAlertPolicy(T node);

   public Group buildGroup(T node);

   public Level buildLevel(T node);

   public Type buildType(T node);
}
