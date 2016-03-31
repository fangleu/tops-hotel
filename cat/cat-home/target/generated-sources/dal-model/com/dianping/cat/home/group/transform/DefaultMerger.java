package com.dianping.cat.home.group.transform;

import java.util.Stack;

import com.dianping.cat.home.group.IEntity;
import com.dianping.cat.home.group.IVisitor;
import com.dianping.cat.home.group.entity.Domain;
import com.dianping.cat.home.group.entity.DomainGroup;
import com.dianping.cat.home.group.entity.Group;

public class DefaultMerger implements IVisitor {

   private Stack<Object> m_objs = new Stack<Object>();

   private DomainGroup m_domainGroup;

   public DefaultMerger() {
   }

   public DefaultMerger(DomainGroup domainGroup) {
      m_domainGroup = domainGroup;
      m_objs.push(domainGroup);
   }

   public DomainGroup getDomainGroup() {
      return m_domainGroup;
   }

   protected Stack<Object> getObjects() {
      return m_objs;
   }

   public <T> void merge(IEntity<T> to, IEntity<T> from) {
      m_objs.push(to);
      from.accept(this);
      m_objs.pop();
   }

   protected void mergeDomain(Domain to, Domain from) {
      to.mergeAttributes(from);
   }

   protected void mergeDomainGroup(DomainGroup to, DomainGroup from) {
      to.mergeAttributes(from);
   }

   protected void mergeGroup(Group to, Group from) {
      to.mergeAttributes(from);
      to.getIps().addAll(from.getIps());
   }

   @Override
   public void visitDomain(Domain from) {
      Domain to = (Domain) m_objs.peek();

      mergeDomain(to, from);
      visitDomainChildren(to, from);
   }

   protected void visitDomainChildren(Domain to, Domain from) {
      for (Group source : from.getGroups().values()) {
         Group target = to.findGroup(source.getId());

         if (target == null) {
            target = new Group(source.getId());
            to.addGroup(target);
         }

         m_objs.push(target);
         source.accept(this);
         m_objs.pop();
      }
   }

   @Override
   public void visitDomainGroup(DomainGroup from) {
      DomainGroup to = (DomainGroup) m_objs.peek();

      mergeDomainGroup(to, from);
      visitDomainGroupChildren(to, from);
   }

   protected void visitDomainGroupChildren(DomainGroup to, DomainGroup from) {
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
   }

   @Override
   public void visitGroup(Group from) {
      Group to = (Group) m_objs.peek();

      mergeGroup(to, from);
      visitGroupChildren(to, from);
   }

   protected void visitGroupChildren(Group to, Group from) {
   }
}
