
package com.micros.webservices.og._4_3.common;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BlackListFlag.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="BlackListFlag">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="REMOVE"/>
 *     &lt;enumeration value="SET"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "BlackListFlag")
@XmlEnum
public enum BlackListFlag {

    REMOVE,
    SET;

    public String value() {
        return name();
    }

    public static BlackListFlag fromValue(String v) {
        return valueOf(v);
    }

}
