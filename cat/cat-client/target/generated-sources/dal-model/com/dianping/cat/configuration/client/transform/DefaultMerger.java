package com.dianping.cat.configuration.client.transform;

import java.util.Stack;

import com.dianping.cat.configuration.client.IEntity;
import com.dianping.cat.configuration.client.IVisitor;
import com.dianping.cat.configuration.client.entity.Bind;
import com.dianping.cat.configuration.client.entity.ClientConfig;
import com.dianping.cat.configuration.client.entity.Domain;
import com.dianping.cat.configuration.client.entity.Property;
import com.dianping.cat.configuration.client.entity.Server;

public class DefaultMerger implements IVisitor {

   private Stack<Object> m_objs = new Stack<Object>();

   private ClientConfig m_config;

   public DefaultMerger() {
   }

   public DefaultMerger(ClientConfig config) {
      m_config = config;
      m_objs.push(config);
   }

   public ClientConfig getConfig() {
      return m_config;
   }

   protected Stack<Object> getObjects() {
      return m_objs;
   }

   public <T> void merge(IEntity<T> to, IEntity<T> from) {
      m_objs.push(to);
      from.accept(this);
      m_objs.pop();
   }

   protected void mergeBind(Bind to, Bind from) {
      to.mergeAttributes(from);
   }

   protected void mergeConfig(ClientConfig to, ClientConfig from) {
      to.mergeAttributes(from);
      to.setBaseLogDir(from.getBaseLogDir());
   }

   protected void mergeDomain(Domain to, Domain from) {
      to.mergeAttributes(from);
   }

   protected void mergeProperty(Property to, Property from) {
      to.mergeAttributes(from);
      to.setText(from.getText());
   }

   protected void mergeServer(Server to, Server from) {
      to.mergeAttributes(from);
   }

   @Override
   public void visitBind(Bind from) {
      Bind to = (Bind) m_objs.peek();

      mergeBind(to, from);
      visitBindChildren(to, from);
   }

   protected void visitBindChildren(Bind to, Bind from) {
   }

   @Override
   public void visitConfig(ClientConfig from) {
      ClientConfig to = (ClientConfig) m_objs.peek();

      mergeConfig(to, from);
      visitConfigChildren(to, from);
   }

   protected void visitConfigChildren(ClientConfig to, ClientConfig from) {
      for (Server source : from.getServers()) {
         Server target = to.findServer(source.getIp());

         if (target == null) {
            target = new Server(source.getIp());
            to.addServer(target);
         }

         m_objs.push(target);
         source.accept(this);
         m_objs.pop();
      }

      for (Domain source : from.getDomains().values()) {
         Domain target = to.findDomain(source.getId());

         if (target == null) {
            target = new Domain(source.getId());
            to.addDomain(target);
         }

         m_objs.push(target);
         source.accept(this);
         m_objs.pop();
      }

      if (from.getBind() != null) {
         Bind target = to.getBind();

         if (target == null) {
            target = new Bind();
            to.setBind(target);
         }

         m_objs.push(target);
         from.getBind().accept(this);
         m_objs.pop();
      }

      for (Property source : from.getProperties().values()) {
         Property target = to.findProperty(source.getName());

         if (target == null) {
            target = new Property(source.getName());
            to.addProperty(target);
         }

         m_objs.push(target);
         source.accept(this);
         m_objs.pop();
      }
   }

   @Override
   public void visitDomain(Domain from) {
      Domain to = (Domain) m_objs.peek();

      mergeDomain(to, from);
      visitDomainChildren(to, from);
   }

   protected void visitDomainChildren(Domain to, Domain from) {
   }

   @Override
   public void visitProperty(Property from) {
      Property to = (Property) m_objs.peek();

      mergeProperty(to, from);
      visitPropertyChildren(to, from);
   }

   protected void visitPropertyChildren(Property to, Property from) {
   }

   @Override
   public void visitServer(Server from) {
      Server to = (Server) m_objs.peek();

      mergeServer(to, from);
      visitServerChildren(to, from);
   }

   protected void visitServerChildren(Server to, Server from) {
   }
}
