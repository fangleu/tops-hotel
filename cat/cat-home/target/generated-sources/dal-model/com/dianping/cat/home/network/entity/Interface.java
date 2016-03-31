package com.dianping.cat.home.network.entity;

import com.dianping.cat.home.network.BaseEntity;
import com.dianping.cat.home.network.IVisitor;

public class Interface extends BaseEntity<Interface> {
   private String m_group;

   private String m_domain;

   private String m_key;

   private Integer m_instate;

   private Integer m_outstate;

   private Double m_in;

   private Double m_out;

   private Integer m_inDiscardsState;

   private Integer m_outDiscardsState;

   private Integer m_inErrorsState;

   private Integer m_outErrorsState;

   private Double m_inDiscards;

   private Double m_outDiscards;

   private Double m_inErrors;

   private Double m_outErrors;

   public Interface() {
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitInterface(this);
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Interface) {
         Interface _o = (Interface) obj;
         String group = _o.getGroup();
         String domain = _o.getDomain();
         String key = _o.getKey();
         Integer instate = _o.getInstate();
         Integer outstate = _o.getOutstate();
         Double in = _o.getIn();
         Double out = _o.getOut();
         Integer inDiscardsState = _o.getInDiscardsState();
         Integer outDiscardsState = _o.getOutDiscardsState();
         Integer inErrorsState = _o.getInErrorsState();
         Integer outErrorsState = _o.getOutErrorsState();
         Double inDiscards = _o.getInDiscards();
         Double outDiscards = _o.getOutDiscards();
         Double inErrors = _o.getInErrors();
         Double outErrors = _o.getOutErrors();
         boolean result = true;

         result &= (m_group == group || m_group != null && m_group.equals(group));
         result &= (m_domain == domain || m_domain != null && m_domain.equals(domain));
         result &= (m_key == key || m_key != null && m_key.equals(key));
         result &= (m_instate == instate || m_instate != null && m_instate.equals(instate));
         result &= (m_outstate == outstate || m_outstate != null && m_outstate.equals(outstate));
         result &= (m_in == in || m_in != null && m_in.equals(in));
         result &= (m_out == out || m_out != null && m_out.equals(out));
         result &= (m_inDiscardsState == inDiscardsState || m_inDiscardsState != null && m_inDiscardsState.equals(inDiscardsState));
         result &= (m_outDiscardsState == outDiscardsState || m_outDiscardsState != null && m_outDiscardsState.equals(outDiscardsState));
         result &= (m_inErrorsState == inErrorsState || m_inErrorsState != null && m_inErrorsState.equals(inErrorsState));
         result &= (m_outErrorsState == outErrorsState || m_outErrorsState != null && m_outErrorsState.equals(outErrorsState));
         result &= (m_inDiscards == inDiscards || m_inDiscards != null && m_inDiscards.equals(inDiscards));
         result &= (m_outDiscards == outDiscards || m_outDiscards != null && m_outDiscards.equals(outDiscards));
         result &= (m_inErrors == inErrors || m_inErrors != null && m_inErrors.equals(inErrors));
         result &= (m_outErrors == outErrors || m_outErrors != null && m_outErrors.equals(outErrors));

         return result;
      }

      return false;
   }

   public String getDomain() {
      return m_domain;
   }

   public String getGroup() {
      return m_group;
   }

   public Double getIn() {
      return m_in;
   }

   public Double getInDiscards() {
      return m_inDiscards;
   }

   public Integer getInDiscardsState() {
      return m_inDiscardsState;
   }

   public Double getInErrors() {
      return m_inErrors;
   }

   public Integer getInErrorsState() {
      return m_inErrorsState;
   }

   public Integer getInstate() {
      return m_instate;
   }

   public String getKey() {
      return m_key;
   }

   public Double getOut() {
      return m_out;
   }

   public Double getOutDiscards() {
      return m_outDiscards;
   }

   public Integer getOutDiscardsState() {
      return m_outDiscardsState;
   }

   public Double getOutErrors() {
      return m_outErrors;
   }

   public Integer getOutErrorsState() {
      return m_outErrorsState;
   }

   public Integer getOutstate() {
      return m_outstate;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_group == null ? 0 : m_group.hashCode());
      hash = hash * 31 + (m_domain == null ? 0 : m_domain.hashCode());
      hash = hash * 31 + (m_key == null ? 0 : m_key.hashCode());
      hash = hash * 31 + (m_instate == null ? 0 : m_instate.hashCode());
      hash = hash * 31 + (m_outstate == null ? 0 : m_outstate.hashCode());
      hash = hash * 31 + (m_in == null ? 0 : m_in.hashCode());
      hash = hash * 31 + (m_out == null ? 0 : m_out.hashCode());
      hash = hash * 31 + (m_inDiscardsState == null ? 0 : m_inDiscardsState.hashCode());
      hash = hash * 31 + (m_outDiscardsState == null ? 0 : m_outDiscardsState.hashCode());
      hash = hash * 31 + (m_inErrorsState == null ? 0 : m_inErrorsState.hashCode());
      hash = hash * 31 + (m_outErrorsState == null ? 0 : m_outErrorsState.hashCode());
      hash = hash * 31 + (m_inDiscards == null ? 0 : m_inDiscards.hashCode());
      hash = hash * 31 + (m_outDiscards == null ? 0 : m_outDiscards.hashCode());
      hash = hash * 31 + (m_inErrors == null ? 0 : m_inErrors.hashCode());
      hash = hash * 31 + (m_outErrors == null ? 0 : m_outErrors.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(Interface other) {
      if (other.getGroup() != null) {
         m_group = other.getGroup();
      }

      if (other.getDomain() != null) {
         m_domain = other.getDomain();
      }

      if (other.getKey() != null) {
         m_key = other.getKey();
      }

      if (other.getInstate() != null) {
         m_instate = other.getInstate();
      }

      if (other.getOutstate() != null) {
         m_outstate = other.getOutstate();
      }

      if (other.getIn() != null) {
         m_in = other.getIn();
      }

      if (other.getOut() != null) {
         m_out = other.getOut();
      }

      if (other.getInDiscardsState() != null) {
         m_inDiscardsState = other.getInDiscardsState();
      }

      if (other.getOutDiscardsState() != null) {
         m_outDiscardsState = other.getOutDiscardsState();
      }

      if (other.getInErrorsState() != null) {
         m_inErrorsState = other.getInErrorsState();
      }

      if (other.getOutErrorsState() != null) {
         m_outErrorsState = other.getOutErrorsState();
      }

      if (other.getInDiscards() != null) {
         m_inDiscards = other.getInDiscards();
      }

      if (other.getOutDiscards() != null) {
         m_outDiscards = other.getOutDiscards();
      }

      if (other.getInErrors() != null) {
         m_inErrors = other.getInErrors();
      }

      if (other.getOutErrors() != null) {
         m_outErrors = other.getOutErrors();
      }
   }

   public Interface setDomain(String domain) {
      m_domain = domain;
      return this;
   }

   public Interface setGroup(String group) {
      m_group = group;
      return this;
   }

   public Interface setIn(Double in) {
      m_in = in;
      return this;
   }

   public Interface setInDiscards(Double inDiscards) {
      m_inDiscards = inDiscards;
      return this;
   }

   public Interface setInDiscardsState(Integer inDiscardsState) {
      m_inDiscardsState = inDiscardsState;
      return this;
   }

   public Interface setInErrors(Double inErrors) {
      m_inErrors = inErrors;
      return this;
   }

   public Interface setInErrorsState(Integer inErrorsState) {
      m_inErrorsState = inErrorsState;
      return this;
   }

   public Interface setInstate(Integer instate) {
      m_instate = instate;
      return this;
   }

   public Interface setKey(String key) {
      m_key = key;
      return this;
   }

   public Interface setOut(Double out) {
      m_out = out;
      return this;
   }

   public Interface setOutDiscards(Double outDiscards) {
      m_outDiscards = outDiscards;
      return this;
   }

   public Interface setOutDiscardsState(Integer outDiscardsState) {
      m_outDiscardsState = outDiscardsState;
      return this;
   }

   public Interface setOutErrors(Double outErrors) {
      m_outErrors = outErrors;
      return this;
   }

   public Interface setOutErrorsState(Integer outErrorsState) {
      m_outErrorsState = outErrorsState;
      return this;
   }

   public Interface setOutstate(Integer outstate) {
      m_outstate = outstate;
      return this;
   }

}
