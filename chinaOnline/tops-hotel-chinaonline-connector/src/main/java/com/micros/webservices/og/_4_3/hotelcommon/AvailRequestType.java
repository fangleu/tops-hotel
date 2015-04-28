
package com.micros.webservices.og._4_3.hotelcommon;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AvailRequestType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="AvailRequestType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Room"/>
 *     &lt;enumeration value="NonRoom"/>
 *     &lt;enumeration value="Both"/>
 *     &lt;enumeration value="Other"/>
 *     &lt;enumeration value="BestAvailable"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "AvailRequestType")
@XmlEnum
public enum AvailRequestType {

    @XmlEnumValue("Room")
    ROOM("Room"),
    @XmlEnumValue("NonRoom")
    NON_ROOM("NonRoom"),
    @XmlEnumValue("Both")
    BOTH("Both"),
    @XmlEnumValue("Other")
    OTHER("Other"),
    @XmlEnumValue("BestAvailable")
    BEST_AVAILABLE("BestAvailable");
    private final String value;

    AvailRequestType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AvailRequestType fromValue(String v) {
        for (AvailRequestType c: AvailRequestType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
