package com.dianping.cat.home.alert.thirdparty.transform;

import java.util.ArrayList;
import java.util.List;
import com.dianping.cat.home.alert.thirdparty.entity.Http;
import com.dianping.cat.home.alert.thirdparty.entity.Par;
import com.dianping.cat.home.alert.thirdparty.entity.Socket;
import com.dianping.cat.home.alert.thirdparty.entity.ThirdPartyConfig;

public class DefaultLinker implements ILinker {
   @SuppressWarnings("unused")
   private boolean m_deferrable;

   private List<Runnable> m_deferedJobs = new ArrayList<Runnable>();

   public DefaultLinker(boolean deferrable) {
      m_deferrable = deferrable;
   }

   public void finish() {
      for (Runnable job : m_deferedJobs) {
         job.run();
      }
   }

   @Override
   public boolean onHttp(final ThirdPartyConfig parent, final Http http) {
      parent.addHttp(http);
      return true;
   }

   @Override
   public boolean onPar(final Http parent, final Par par) {
      parent.addPar(par);
      return true;
   }

   @Override
   public boolean onSocket(final ThirdPartyConfig parent, final Socket socket) {
      parent.addSocket(socket);
      return true;
   }
}
