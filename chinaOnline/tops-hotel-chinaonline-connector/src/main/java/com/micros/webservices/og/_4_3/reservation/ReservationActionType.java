
package com.micros.webservices.og._4_3.reservation;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ReservationActionType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ReservationActionType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ADD"/>
 *     &lt;enumeration value="EDIT"/>
 *     &lt;enumeration value="CANCEL"/>
 *     &lt;enumeration value="CHECKIN"/>
 *     &lt;enumeration value="CHECKOUT"/>
 *     &lt;enumeration value="NOSHOW"/>
 *     &lt;enumeration value="REINSTATE"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ReservationActionType")
@XmlEnum
public enum ReservationActionType {

    ADD,
    EDIT,
    CANCEL,
    CHECKIN,
    CHECKOUT,
    NOSHOW,
    REINSTATE;

    public String value() {
        return name();
    }

    public static ReservationActionType fromValue(String v) {
        return valueOf(v);
    }

}
