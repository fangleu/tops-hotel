
package com.micros.webservices.og._4_3.hotelcommon;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RestrictionType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="RestrictionType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="OPEN"/>
 *     &lt;enumeration value="CLOSED"/>
 *     &lt;enumeration value="OPEN_FOR_ARRIVAL"/>
 *     &lt;enumeration value="CLOSED_FOR_ARRIVAL"/>
 *     &lt;enumeration value="OPEN_FOR_DEPARTURE"/>
 *     &lt;enumeration value="CLOSED_FOR_DEPARTURE"/>
 *     &lt;enumeration value="MINIMUM_STAY_THROUGH"/>
 *     &lt;enumeration value="MAXIMUM_STAY_THROUGH"/>
 *     &lt;enumeration value="MINIMUM_LENGTH_OF_STAY"/>
 *     &lt;enumeration value="MAXIMUM_LENGTH_OF_STAY"/>
 *     &lt;enumeration value="MINIMUM_ADVANCE_BOOKING"/>
 *     &lt;enumeration value="MAXIMUM_ADVANCE_BOOKING"/>
 *     &lt;enumeration value="LENGTH_OF_STAY_NOT_AVAILABLE"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "RestrictionType")
@XmlEnum
public enum RestrictionType {

    OPEN,
    CLOSED,
    OPEN_FOR_ARRIVAL,
    CLOSED_FOR_ARRIVAL,
    OPEN_FOR_DEPARTURE,
    CLOSED_FOR_DEPARTURE,
    MINIMUM_STAY_THROUGH,
    MAXIMUM_STAY_THROUGH,
    MINIMUM_LENGTH_OF_STAY,
    MAXIMUM_LENGTH_OF_STAY,
    MINIMUM_ADVANCE_BOOKING,
    MAXIMUM_ADVANCE_BOOKING,
    LENGTH_OF_STAY_NOT_AVAILABLE;

    public String value() {
        return name();
    }

    public static RestrictionType fromValue(String v) {
        return valueOf(v);
    }

}
