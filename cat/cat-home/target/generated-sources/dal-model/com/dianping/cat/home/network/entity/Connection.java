package com.dianping.cat.home.network.entity;

import java.util.ArrayList;
import java.util.List;

import com.dianping.cat.home.network.BaseEntity;
import com.dianping.cat.home.network.IVisitor;

public class Connection extends BaseEntity<Connection> {
   private String m_from;

   private String m_to;

   private Integer m_instate;

   private Integer m_outstate;

   private List<Interface> m_interfaces = new ArrayList<Interface>();

   private Double m_insum;

   private Double m_outsum;

   private Integer m_inDiscardsState;

   private Integer m_outDiscardsState;

   private Integer m_inErrorsState;

   private Integer m_outErrorsState;

   private Double m_inDiscards;

   private Double m_outDiscards;

   private Double m_inErrors;

   private Double m_outErrors;

   public Connection() {
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitConnection(this);
   }

   public Connection addInterface(Interface _interface) {
      m_interfaces.add(_interface);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Connection) {
         Connection _o = (Connection) obj;
         String from = _o.getFrom();
         String to = _o.getTo();
         Integer instate = _o.getInstate();
         Integer outstate = _o.getOutstate();
         List<Interface> interfaces = _o.getInterfaces();
         Double insum = _o.getInsum();
         Double outsum = _o.getOutsum();
         Integer inDiscardsState = _o.getInDiscardsState();
         Integer outDiscardsState = _o.getOutDiscardsState();
         Integer inErrorsState = _o.getInErrorsState();
         Integer outErrorsState = _o.getOutErrorsState();
         Double inDiscards = _o.getInDiscards();
         Double outDiscards = _o.getOutDiscards();
         Double inErrors = _o.getInErrors();
         Double outErrors = _o.getOutErrors();
         boolean result = true;

         result &= (m_from == from || m_from != null && m_from.equals(from));
         result &= (m_to == to || m_to != null && m_to.equals(to));
         result &= (m_instate == instate || m_instate != null && m_instate.equals(instate));
         result &= (m_outstate == outstate || m_outstate != null && m_outstate.equals(outstate));
         result &= (m_interfaces == interfaces || m_interfaces != null && m_interfaces.equals(interfaces));
         result &= (m_insum == insum || m_insum != null && m_insum.equals(insum));
         result &= (m_outsum == outsum || m_outsum != null && m_outsum.equals(outsum));
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

   public String getFrom() {
      return m_from;
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

   public Double getInsum() {
      return m_insum;
   }

   public List<Interface> getInterfaces() {
      return m_interfaces;
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

   public Double getOutsum() {
      return m_outsum;
   }

   public String getTo() {
      return m_to;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_from == null ? 0 : m_from.hashCode());
      hash = hash * 31 + (m_to == null ? 0 : m_to.hashCode());
      hash = hash * 31 + (m_instate == null ? 0 : m_instate.hashCode());
      hash = hash * 31 + (m_outstate == null ? 0 : m_outstate.hashCode());
      hash = hash * 31 + (m_interfaces == null ? 0 : m_interfaces.hashCode());
      hash = hash * 31 + (m_insum == null ? 0 : m_insum.hashCode());
      hash = hash * 31 + (m_outsum == null ? 0 : m_outsum.hashCode());
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
   public void mergeAttributes(Connection other) {
      if (other.getFrom() != null) {
         m_from = other.getFrom();
      }

      if (other.getTo() != null) {
         m_to = other.getTo();
      }

      if (other.getInstate() != null) {
         m_instate = other.getInstate();
      }

      if (other.getOutstate() != null) {
         m_outstate = other.getOutstate();
      }

      if (other.getInsum() != null) {
         m_insum = other.getInsum();
      }

      if (other.getOutsum() != null) {
         m_outsum = other.getOutsum();
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

   public Connection setFrom(String from) {
      m_from = from;
      return this;
   }

   public Connection setInDiscards(Double inDiscards) {
      m_inDiscards = inDiscards;
      return this;
   }

   public Connection setInDiscardsState(Integer inDiscardsState) {
      m_inDiscardsState = inDiscardsState;
      return this;
   }

   public Connection setInErrors(Double inErrors) {
      m_inErrors = inErrors;
      return this;
   }

   public Connection setInErrorsState(Integer inErrorsState) {
      m_inErrorsState = inErrorsState;
      return this;
   }

   public Connection setInstate(Integer instate) {
      m_instate = instate;
      return this;
   }

   public Connection setInsum(Double insum) {
      m_insum = insum;
      return this;
   }

   public Connection setOutDiscards(Double outDiscards) {
      m_outDiscards = outDiscards;
      return this;
   }

   public Connection setOutDiscardsState(Integer outDiscardsState) {
      m_outDiscardsState = outDiscardsState;
      return this;
   }

   public Connection setOutErrors(Double outErrors) {
      m_outErrors = outErrors;
      return this;
   }

   public Connection setOutErrorsState(Integer outErrorsState) {
      m_outErrorsState = outErrorsState;
      return this;
   }

   public Connection setOutstate(Integer outstate) {
      m_outstate = outstate;
      return this;
   }

   public Connection setOutsum(Double outsum) {
      m_outsum = outsum;
      return this;
   }

   public Connection setTo(String to) {
      m_to = to;
      return this;
   }

}
