package com.dianping.cat.home.activity.transform;

import com.dianping.cat.home.activity.entity.Activity;
import com.dianping.cat.home.activity.entity.ActivityConfig;

public interface ILinker {

   public boolean onActivity(ActivityConfig parent, Activity activity);
}
