package com.dianping.cat.home.app.transform;

import static com.dianping.cat.home.app.Constants.ATTR_AVG;
import static com.dianping.cat.home.app.Constants.ATTR_COUNT;
import static com.dianping.cat.home.app.Constants.ATTR_END_TIME;
import static com.dianping.cat.home.app.Constants.ATTR_ERRORS;
import static com.dianping.cat.home.app.Constants.ATTR_ID;
import static com.dianping.cat.home.app.Constants.ATTR_NAME;
import static com.dianping.cat.home.app.Constants.ATTR_REQUESTAVG;
import static com.dianping.cat.home.app.Constants.ATTR_REQUESTSUM;
import static com.dianping.cat.home.app.Constants.ATTR_RESPONSEAVG;
import static com.dianping.cat.home.app.Constants.ATTR_RESPONSESUM;
import static com.dianping.cat.home.app.Constants.ATTR_START_TIME;
import static com.dianping.cat.home.app.Constants.ATTR_SUCCESSRATIO;
import static com.dianping.cat.home.app.Constants.ATTR_SUM;
import static com.dianping.cat.home.app.Constants.ATTR_URL;

import org.xml.sax.Attributes;

import com.dianping.cat.home.app.entity.AppReport;
import com.dianping.cat.home.app.entity.Code;
import com.dianping.cat.home.app.entity.Command;
import com.dianping.cat.home.app.entity.Transaction;

public class DefaultSaxMaker implements IMaker<Attributes> {

   @Override
   public AppReport buildAppReport(Attributes attributes) {
      String id = attributes.getValue(ATTR_ID);
      String startTime = attributes.getValue(ATTR_START_TIME);
      String endTime = attributes.getValue(ATTR_END_TIME);
      AppReport appReport = new AppReport(id);

      if (startTime != null) {
         appReport.setStartTime(toDate(startTime, "yyyy-MM-dd HH:mm:ss", null));
      }

      if (endTime != null) {
         appReport.setEndTime(toDate(endTime, "yyyy-MM-dd HH:mm:ss", null));
      }

      return appReport;
   }

   @Override
   public Code buildCode(Attributes attributes) {
      String id = attributes.getValue(ATTR_ID);
      String count = attributes.getValue(ATTR_COUNT);
      String sum = attributes.getValue(ATTR_SUM);
      String avg = attributes.getValue(ATTR_AVG);
      String errors = attributes.getValue(ATTR_ERRORS);
      String successRatio = attributes.getValue(ATTR_SUCCESSRATIO);
      Code code = new Code(id);

      if (count != null) {
         code.setCount(convert(Long.class, count, 0L));
      }

      if (sum != null) {
         code.setSum(toNumber(sum, "0.00", 0).doubleValue());
      }

      if (avg != null) {
         code.setAvg(toNumber(avg, "0.00", 0).doubleValue());
      }

      if (errors != null) {
         code.setErrors(convert(Long.class, errors, 0L));
      }

      if (successRatio != null) {
         code.setSuccessRatio(toNumber(successRatio, "0.000", 0).doubleValue());
      }

      return code;
   }

   @Override
   public Command buildCommand(Attributes attributes) {
      String id = attributes.getValue(ATTR_ID);
      String name = attributes.getValue(ATTR_NAME);
      String count = attributes.getValue(ATTR_COUNT);
      String sum = attributes.getValue(ATTR_SUM);
      String avg = attributes.getValue(ATTR_AVG);
      String errors = attributes.getValue(ATTR_ERRORS);
      String successRatio = attributes.getValue(ATTR_SUCCESSRATIO);
      String requestSum = attributes.getValue(ATTR_REQUESTSUM);
      String requestAvg = attributes.getValue(ATTR_REQUESTAVG);
      String responseSum = attributes.getValue(ATTR_RESPONSESUM);
      String responseAvg = attributes.getValue(ATTR_RESPONSEAVG);
      Command command = new Command(id == null ? 0 : convert(Integer.class, id, 0));

      if (name != null) {
         command.setName(name);
      }

      if (count != null) {
         command.setCount(convert(Long.class, count, 0L));
      }

      if (sum != null) {
         command.setSum(toNumber(sum, "0.00", 0).doubleValue());
      }

      if (avg != null) {
         command.setAvg(toNumber(avg, "0.00", 0).doubleValue());
      }

      if (errors != null) {
         command.setErrors(convert(Long.class, errors, 0L));
      }

      if (successRatio != null) {
         command.setSuccessRatio(toNumber(successRatio, "0.000", 0).doubleValue());
      }

      if (requestSum != null) {
         command.setRequestSum(convert(Long.class, requestSum, 0L));
      }

      if (requestAvg != null) {
         command.setRequestAvg(toNumber(requestAvg, "0.00", 0).doubleValue());
      }

      if (responseSum != null) {
         command.setResponseSum(convert(Long.class, responseSum, 0L));
      }

      if (responseAvg != null) {
         command.setResponseAvg(toNumber(responseAvg, "0.00", 0).doubleValue());
      }

      return command;
   }

   @Override
   public Transaction buildTransaction(Attributes attributes) {
      String url = attributes.getValue(ATTR_URL);
      String count = attributes.getValue(ATTR_COUNT);
      String avg = attributes.getValue(ATTR_AVG);
      Transaction transaction = new Transaction(url);

      if (count != null) {
         transaction.setCount(convert(Long.class, count, 0L));
      }

      if (avg != null) {
         transaction.setAvg(toNumber(avg, "0.00", 0).doubleValue());
      }

      return transaction;
   }

   @SuppressWarnings("unchecked")
   protected <T> T convert(Class<T> type, String value, T defaultValue) {
      if (value == null) {
         return defaultValue;
      }

      if (type == Boolean.class) {
         return (T) Boolean.valueOf(value);
      } else if (type == Integer.class) {
         return (T) Integer.valueOf(value);
      } else if (type == Long.class) {
         return (T) Long.valueOf(value);
      } else if (type == Short.class) {
         return (T) Short.valueOf(value);
      } else if (type == Float.class) {
         return (T) Float.valueOf(value);
      } else if (type == Double.class) {
         return (T) Double.valueOf(value);
      } else if (type == Byte.class) {
         return (T) Byte.valueOf(value);
      } else if (type == Character.class) {
         return (T) (Character) value.charAt(0);
      } else {
         return (T) value;
      }
   }

   protected java.util.Date toDate(String str, String format, java.util.Date defaultValue) {
      if (str == null || str.length() == 0) {
         return defaultValue;
      }

      try {
         return new java.text.SimpleDateFormat(format).parse(str);
      } catch (java.text.ParseException e) {
         throw new RuntimeException(String.format("Unable to parse date(%s) in format(%s)!", str, format), e);
      }
   }

   protected Number toNumber(String str, String format, Number defaultValue) {
      if (str == null || str.length() == 0) {
         return defaultValue;
      }

      try {
         return new java.text.DecimalFormat(format).parse(str);
      } catch (java.text.ParseException e) {
         throw new RuntimeException(String.format("Unable to parse number(%s) in format(%s)!", str, format), e);
      }
   }
}
