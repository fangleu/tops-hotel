
package com.micros.webservices.og._4_3.hotelcommon;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DistanceUnitType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="DistanceUnitType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Km"/>
 *     &lt;enumeration value="Mi"/>
 *     &lt;enumeration value="Other"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "DistanceUnitType")
@XmlEnum
public enum DistanceUnitType {

    @XmlEnumValue("Km")
    KM("Km"),
    @XmlEnumValue("Mi")
    MI("Mi"),
    @XmlEnumValue("Other")
    OTHER("Other");
    private final String value;

    DistanceUnitType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DistanceUnitType fromValue(String v) {
        for (DistanceUnitType c: DistanceUnitType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
