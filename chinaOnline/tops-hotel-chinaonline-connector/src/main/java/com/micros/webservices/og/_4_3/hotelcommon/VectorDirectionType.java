
package com.micros.webservices.og._4_3.hotelcommon;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for VectorDirectionType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="VectorDirectionType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="North"/>
 *     &lt;enumeration value="NorthEast"/>
 *     &lt;enumeration value="East"/>
 *     &lt;enumeration value="SouthEast"/>
 *     &lt;enumeration value="South"/>
 *     &lt;enumeration value="SouthWest"/>
 *     &lt;enumeration value="West"/>
 *     &lt;enumeration value="NorthWest"/>
 *     &lt;enumeration value="Other"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "VectorDirectionType")
@XmlEnum
public enum VectorDirectionType {

    @XmlEnumValue("North")
    NORTH("North"),
    @XmlEnumValue("NorthEast")
    NORTH_EAST("NorthEast"),
    @XmlEnumValue("East")
    EAST("East"),
    @XmlEnumValue("SouthEast")
    SOUTH_EAST("SouthEast"),
    @XmlEnumValue("South")
    SOUTH("South"),
    @XmlEnumValue("SouthWest")
    SOUTH_WEST("SouthWest"),
    @XmlEnumValue("West")
    WEST("West"),
    @XmlEnumValue("NorthWest")
    NORTH_WEST("NorthWest"),
    @XmlEnumValue("Other")
    OTHER("Other");
    private final String value;

    VectorDirectionType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static VectorDirectionType fromValue(String v) {
        for (VectorDirectionType c: VectorDirectionType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
