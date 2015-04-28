
package com.micros.webservices.og._4_3.reservation;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ReservationStatusType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ReservationStatusType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="RESERVED"/>
 *     &lt;enumeration value="PROSPECT"/>
 *     &lt;enumeration value="NOSHOW"/>
 *     &lt;enumeration value="CANCELED"/>
 *     &lt;enumeration value="INHOUSE"/>
 *     &lt;enumeration value="CHECKEDOUT"/>
 *     &lt;enumeration value="CHANGED"/>
 *     &lt;enumeration value="WAITLISTED"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ReservationStatusType")
@XmlEnum
public enum ReservationStatusType {

    RESERVED,
    PROSPECT,
    NOSHOW,
    CANCELED,
    INHOUSE,
    CHECKEDOUT,
    CHANGED,
    WAITLISTED;

    public String value() {
        return name();
    }

    public static ReservationStatusType fromValue(String v) {
        return valueOf(v);
    }

}
