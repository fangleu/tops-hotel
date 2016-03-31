package com.dianping.cat.home.activity.transform;

import com.dianping.cat.home.activity.entity.Activity;
import com.dianping.cat.home.activity.entity.ActivityConfig;

public interface IMaker<T> {

   public Activity buildActivity(T node);

   public ActivityConfig buildActivityConfig(T node);
}
