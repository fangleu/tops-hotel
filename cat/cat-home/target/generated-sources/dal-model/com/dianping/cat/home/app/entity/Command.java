package com.dianping.cat.home.app.entity;

import static com.dianping.cat.home.app.Constants.ATTR_ID;
import static com.dianping.cat.home.app.Constants.ENTITY_COMMAND;

import java.util.LinkedHashMap;
import java.util.Map;

import com.dianping.cat.home.app.BaseEntity;
import com.dianping.cat.home.app.IVisitor;

public class Command extends BaseEntity<Command> {
   private int m_id;

   private String m_name;

   private long m_count;

   private double m_sum;

   private double m_avg;

   private long m_errors;

   private double m_successRatio;

   private long m_requestSum;

   private double m_requestAvg;

   private long m_responseSum;

   private double m_responseAvg;

   private Transaction m_transaction;

   private Map<String, Code> m_codes = new LinkedHashMap<String, Code>();

   public Command() {
   }

   public Command(int id) {
      m_id = id;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitCommand(this);
   }

   public Command addCode(Code code) {
      m_codes.put(code.getId(), code);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Command) {
         Command _o = (Command) obj;
         int id = _o.getId();

         return m_id == id;
      }

      return false;
   }

   public Code findCode(String id) {
      return m_codes.get(id);
   }

   public Code findOrCreateCode(String id) {
      Code code = m_codes.get(id);

      if (code == null) {
         synchronized (m_codes) {
            code = m_codes.get(id);

            if (code == null) {
               code = new Code(id);
               m_codes.put(id, code);
            }
         }
      }

      return code;
   }

   public double getAvg() {
      return m_avg;
   }

   public Map<String, Code> getCodes() {
      return m_codes;
   }

   public long getCount() {
      return m_count;
   }

   public long getErrors() {
      return m_errors;
   }

   public int getId() {
      return m_id;
   }

   public String getName() {
      return m_name;
   }

   public double getRequestAvg() {
      return m_requestAvg;
   }

   public long getRequestSum() {
      return m_requestSum;
   }

   public double getResponseAvg() {
      return m_responseAvg;
   }

   public long getResponseSum() {
      return m_responseSum;
   }

   public double getSuccessRatio() {
      return m_successRatio;
   }

   public double getSum() {
      return m_sum;
   }

   public Transaction getTransaction() {
      return m_transaction;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + m_id;

      return hash;
   }

   public Command incCount() {
      m_count++;
      return this;
   }

   public Command incCount(long count) {
      m_count += count;
      return this;
   }

   public Command incErrors() {
      m_errors++;
      return this;
   }

   public Command incErrors(long errors) {
      m_errors += errors;
      return this;
   }

   public Command incRequestSum() {
      m_requestSum++;
      return this;
   }

   public Command incRequestSum(long requestSum) {
      m_requestSum += requestSum;
      return this;
   }

   public Command incResponseSum() {
      m_responseSum++;
      return this;
   }

   public Command incResponseSum(long responseSum) {
      m_responseSum += responseSum;
      return this;
   }

   public Command incSum() {
      m_sum++;
      return this;
   }

   public Command incSum(double sum) {
      m_sum += sum;
      return this;
   }

   @Override
   public void mergeAttributes(Command other) {
      assertAttributeEquals(other, ENTITY_COMMAND, ATTR_ID, m_id, other.getId());

      if (other.getName() != null) {
         m_name = other.getName();
      }

      m_count = other.getCount();

      m_sum = other.getSum();

      m_avg = other.getAvg();

      m_errors = other.getErrors();

      m_successRatio = other.getSuccessRatio();

      m_requestSum = other.getRequestSum();

      m_requestAvg = other.getRequestAvg();

      m_responseSum = other.getResponseSum();

      m_responseAvg = other.getResponseAvg();
   }

   public boolean removeCode(String id) {
      if (m_codes.containsKey(id)) {
         m_codes.remove(id);
         return true;
      }

      return false;
   }

   public Command setAvg(double avg) {
      m_avg = avg;
      return this;
   }

   public Command setCount(long count) {
      m_count = count;
      return this;
   }

   public Command setErrors(long errors) {
      m_errors = errors;
      return this;
   }

   public Command setId(int id) {
      m_id = id;
      return this;
   }

   public Command setName(String name) {
      m_name = name;
      return this;
   }

   public Command setRequestAvg(double requestAvg) {
      m_requestAvg = requestAvg;
      return this;
   }

   public Command setRequestSum(long requestSum) {
      m_requestSum = requestSum;
      return this;
   }

   public Command setResponseAvg(double responseAvg) {
      m_responseAvg = responseAvg;
      return this;
   }

   public Command setResponseSum(long responseSum) {
      m_responseSum = responseSum;
      return this;
   }

   public Command setSuccessRatio(double successRatio) {
      m_successRatio = successRatio;
      return this;
   }

   public Command setSum(double sum) {
      m_sum = sum;
      return this;
   }

   public Command setTransaction(Transaction transaction) {
      m_transaction = transaction;
      return this;
   }

}
