
package com.micros.webservices.og._4_3.hotelcommon;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AmenityAvailabilityFlag.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="AmenityAvailabilityFlag">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ConfirmableComplimentary"/>
 *     &lt;enumeration value="ConfirmableCost"/>
 *     &lt;enumeration value="OnRequestComplimentary"/>
 *     &lt;enumeration value="OnRequestCost"/>
 *     &lt;enumeration value="Exists"/>
 *     &lt;enumeration value="Other"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "AmenityAvailabilityFlag")
@XmlEnum
public enum AmenityAvailabilityFlag {

    @XmlEnumValue("ConfirmableComplimentary")
    CONFIRMABLE_COMPLIMENTARY("ConfirmableComplimentary"),
    @XmlEnumValue("ConfirmableCost")
    CONFIRMABLE_COST("ConfirmableCost"),
    @XmlEnumValue("OnRequestComplimentary")
    ON_REQUEST_COMPLIMENTARY("OnRequestComplimentary"),
    @XmlEnumValue("OnRequestCost")
    ON_REQUEST_COST("OnRequestCost"),
    @XmlEnumValue("Exists")
    EXISTS("Exists"),
    @XmlEnumValue("Other")
    OTHER("Other");
    private final String value;

    AmenityAvailabilityFlag(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AmenityAvailabilityFlag fromValue(String v) {
        for (AmenityAvailabilityFlag c: AmenityAvailabilityFlag.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
