
package com.micros.webservices.og._4_3.hotelcommon;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for HotelInfoType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="HotelInfoType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="DIRECTIONS"/>
 *     &lt;enumeration value="CHECKININFO"/>
 *     &lt;enumeration value="CHECKOUTINFO"/>
 *     &lt;enumeration value="OTHER"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "HotelInfoType")
@XmlEnum
public enum HotelInfoType {

    DIRECTIONS,
    CHECKININFO,
    CHECKOUTINFO,
    OTHER;

    public String value() {
        return name();
    }

    public static HotelInfoType fromValue(String v) {
        return valueOf(v);
    }

}
