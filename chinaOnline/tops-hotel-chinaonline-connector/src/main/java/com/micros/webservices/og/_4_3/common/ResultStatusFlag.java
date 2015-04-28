
package com.micros.webservices.og._4_3.common;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ResultStatusFlag.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ResultStatusFlag">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="FAIL"/>
 *     &lt;enumeration value="SUCCESS"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ResultStatusFlag")
@XmlEnum
public enum ResultStatusFlag {

    FAIL,
    SUCCESS;

    public String value() {
        return name();
    }

    public static ResultStatusFlag fromValue(String v) {
        return valueOf(v);
    }

}
