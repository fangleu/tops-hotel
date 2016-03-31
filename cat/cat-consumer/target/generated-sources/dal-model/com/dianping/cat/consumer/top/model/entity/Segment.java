package com.dianping.cat.consumer.top.model.entity;

import static com.dianping.cat.consumer.top.model.Constants.ATTR_ID;
import static com.dianping.cat.consumer.top.model.Constants.ENTITY_SEGMENT;

import java.util.LinkedHashMap;
import java.util.Map;

import com.dianping.cat.consumer.top.model.BaseEntity;
import com.dianping.cat.consumer.top.model.IVisitor;

public class Segment extends BaseEntity<Segment> {
   private Integer m_id;

   private long m_error;

   private long m_url;

   private double m_urlDuration;

   private long m_service;

   private double m_serviceDuration;

   private long m_sql;

   private double m_sqlDuration;

   private long m_call;

   private double m_callDuration;

   private long m_cache;

   private double m_cacheDuration;

   private long m_urlError;

   private double m_urlSum;

   private long m_serviceError;

   private double m_serviceSum;

   private long m_sqlError;

   private double m_sqlSum;

   private long m_callError;

   private double m_callSum;

   private long m_cacheError;

   private double m_cacheSum;

   private Map<String, Error> m_errors = new LinkedHashMap<String, Error>();

   private Map<String, Machine> m_machines = new LinkedHashMap<String, Machine>();

   public Segment() {
   }

   public Segment(Integer id) {
      m_id = id;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitSegment(this);
   }

   public Segment addError(Error error) {
      m_errors.put(error.getId(), error);
      return this;
   }

   public Segment addMachine(Machine machine) {
      m_machines.put(machine.getId(), machine);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Segment) {
         Segment _o = (Segment) obj;
         Integer id = _o.getId();

         return m_id == id || m_id != null && m_id.equals(id);
      }

      return false;
   }

   public Error findError(String id) {
      return m_errors.get(id);
   }

   public Machine findMachine(String id) {
      return m_machines.get(id);
   }

   public Error findOrCreateError(String id) {
      Error error = m_errors.get(id);

      if (error == null) {
         synchronized (m_errors) {
            error = m_errors.get(id);

            if (error == null) {
               error = new Error(id);
               m_errors.put(id, error);
            }
         }
      }

      return error;
   }

   public Machine findOrCreateMachine(String id) {
      Machine machine = m_machines.get(id);

      if (machine == null) {
         synchronized (m_machines) {
            machine = m_machines.get(id);

            if (machine == null) {
               machine = new Machine(id);
               m_machines.put(id, machine);
            }
         }
      }

      return machine;
   }

   public long getCache() {
      return m_cache;
   }

   public double getCacheDuration() {
      return m_cacheDuration;
   }

   public long getCacheError() {
      return m_cacheError;
   }

   public double getCacheSum() {
      return m_cacheSum;
   }

   public long getCall() {
      return m_call;
   }

   public double getCallDuration() {
      return m_callDuration;
   }

   public long getCallError() {
      return m_callError;
   }

   public double getCallSum() {
      return m_callSum;
   }

   public long getError() {
      return m_error;
   }

   public Map<String, Error> getErrors() {
      return m_errors;
   }

   public Integer getId() {
      return m_id;
   }

   public Map<String, Machine> getMachines() {
      return m_machines;
   }

   public long getService() {
      return m_service;
   }

   public double getServiceDuration() {
      return m_serviceDuration;
   }

   public long getServiceError() {
      return m_serviceError;
   }

   public double getServiceSum() {
      return m_serviceSum;
   }

   public long getSql() {
      return m_sql;
   }

   public double getSqlDuration() {
      return m_sqlDuration;
   }

   public long getSqlError() {
      return m_sqlError;
   }

   public double getSqlSum() {
      return m_sqlSum;
   }

   public long getUrl() {
      return m_url;
   }

   public double getUrlDuration() {
      return m_urlDuration;
   }

   public long getUrlError() {
      return m_urlError;
   }

   public double getUrlSum() {
      return m_urlSum;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_id == null ? 0 : m_id.hashCode());

      return hash;
   }

   public Segment incError() {
      m_error++;
      return this;
   }

   public Segment incError(long error) {
      m_error += error;
      return this;
   }

   @Override
   public void mergeAttributes(Segment other) {
      assertAttributeEquals(other, ENTITY_SEGMENT, ATTR_ID, m_id, other.getId());

      m_error = other.getError();

      m_url = other.getUrl();

      m_urlDuration = other.getUrlDuration();

      m_service = other.getService();

      m_serviceDuration = other.getServiceDuration();

      m_sql = other.getSql();

      m_sqlDuration = other.getSqlDuration();

      m_call = other.getCall();

      m_callDuration = other.getCallDuration();

      m_cache = other.getCache();

      m_cacheDuration = other.getCacheDuration();

      m_urlError = other.getUrlError();

      m_urlSum = other.getUrlSum();

      m_serviceError = other.getServiceError();

      m_serviceSum = other.getServiceSum();

      m_sqlError = other.getSqlError();

      m_sqlSum = other.getSqlSum();

      m_callError = other.getCallError();

      m_callSum = other.getCallSum();

      m_cacheError = other.getCacheError();

      m_cacheSum = other.getCacheSum();
   }

   public boolean removeError(String id) {
      if (m_errors.containsKey(id)) {
         m_errors.remove(id);
         return true;
      }

      return false;
   }

   public boolean removeMachine(String id) {
      if (m_machines.containsKey(id)) {
         m_machines.remove(id);
         return true;
      }

      return false;
   }

   public Segment setCache(long cache) {
      m_cache = cache;
      return this;
   }

   public Segment setCacheDuration(double cacheDuration) {
      m_cacheDuration = cacheDuration;
      return this;
   }

   public Segment setCacheError(long cacheError) {
      m_cacheError = cacheError;
      return this;
   }

   public Segment setCacheSum(double cacheSum) {
      m_cacheSum = cacheSum;
      return this;
   }

   public Segment setCall(long call) {
      m_call = call;
      return this;
   }

   public Segment setCallDuration(double callDuration) {
      m_callDuration = callDuration;
      return this;
   }

   public Segment setCallError(long callError) {
      m_callError = callError;
      return this;
   }

   public Segment setCallSum(double callSum) {
      m_callSum = callSum;
      return this;
   }

   public Segment setError(long error) {
      m_error = error;
      return this;
   }

   public Segment setId(Integer id) {
      m_id = id;
      return this;
   }

   public Segment setService(long service) {
      m_service = service;
      return this;
   }

   public Segment setServiceDuration(double serviceDuration) {
      m_serviceDuration = serviceDuration;
      return this;
   }

   public Segment setServiceError(long serviceError) {
      m_serviceError = serviceError;
      return this;
   }

   public Segment setServiceSum(double serviceSum) {
      m_serviceSum = serviceSum;
      return this;
   }

   public Segment setSql(long sql) {
      m_sql = sql;
      return this;
   }

   public Segment setSqlDuration(double sqlDuration) {
      m_sqlDuration = sqlDuration;
      return this;
   }

   public Segment setSqlError(long sqlError) {
      m_sqlError = sqlError;
      return this;
   }

   public Segment setSqlSum(double sqlSum) {
      m_sqlSum = sqlSum;
      return this;
   }

   public Segment setUrl(long url) {
      m_url = url;
      return this;
   }

   public Segment setUrlDuration(double urlDuration) {
      m_urlDuration = urlDuration;
      return this;
   }

   public Segment setUrlError(long urlError) {
      m_urlError = urlError;
      return this;
   }

   public Segment setUrlSum(double urlSum) {
      m_urlSum = urlSum;
      return this;
   }

}
