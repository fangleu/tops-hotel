package com.dianping.cat.home.activity;

import com.dianping.cat.home.activity.entity.Activity;
import com.dianping.cat.home.activity.entity.ActivityConfig;

public interface IVisitor {

   public void visitActivity(Activity activity);

   public void visitActivityConfig(ActivityConfig activityConfig);
}
