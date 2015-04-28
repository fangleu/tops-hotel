
package com.micros.webservices.og._4_3.name;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PrivacyOptionTypeOptionValue.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="PrivacyOptionTypeOptionValue">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="YES"/>
 *     &lt;enumeration value="NO"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "PrivacyOptionTypeOptionValue")
@XmlEnum
public enum PrivacyOptionTypeOptionValue {

    YES,
    NO;

    public String value() {
        return name();
    }

    public static PrivacyOptionTypeOptionValue fromValue(String v) {
        return valueOf(v);
    }

}
