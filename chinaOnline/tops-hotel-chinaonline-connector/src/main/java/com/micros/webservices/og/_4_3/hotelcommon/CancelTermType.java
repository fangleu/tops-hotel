
package com.micros.webservices.og._4_3.hotelcommon;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CancelTermType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CancelTermType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Cancel"/>
 *     &lt;enumeration value="NoShow"/>
 *     &lt;enumeration value="Other"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CancelTermType")
@XmlEnum
public enum CancelTermType {

    @XmlEnumValue("Cancel")
    CANCEL("Cancel"),
    @XmlEnumValue("NoShow")
    NO_SHOW("NoShow"),
    @XmlEnumValue("Other")
    OTHER("Other");
    private final String value;

    CancelTermType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CancelTermType fromValue(String v) {
        for (CancelTermType c: CancelTermType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
