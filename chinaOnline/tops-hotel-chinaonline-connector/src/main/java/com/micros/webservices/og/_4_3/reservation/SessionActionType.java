
package com.micros.webservices.og._4_3.reservation;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SessionActionType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="SessionActionType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="BOOKING"/>
 *     &lt;enumeration value="INITIATE"/>
 *     &lt;enumeration value="COMMIT"/>
 *     &lt;enumeration value="COMMITTED"/>
 *     &lt;enumeration value="IGNORE"/>
 *     &lt;enumeration value="IGNORED"/>
 *     &lt;enumeration value="ONHOLD"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "SessionActionType")
@XmlEnum
public enum SessionActionType {

    BOOKING,
    INITIATE,
    COMMIT,
    COMMITTED,
    IGNORE,
    IGNORED,
    ONHOLD;

    public String value() {
        return name();
    }

    public static SessionActionType fromValue(String v) {
        return valueOf(v);
    }

}
