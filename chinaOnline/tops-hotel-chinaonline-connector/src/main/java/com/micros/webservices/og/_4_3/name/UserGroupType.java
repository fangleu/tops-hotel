
package com.micros.webservices.og._4_3.name;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UserGroupType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="UserGroupType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="TAM"/>
 *     &lt;enumeration value="BOOKER"/>
 *     &lt;enumeration value="BOOKER2"/>
 *     &lt;enumeration value="COMPANY"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "UserGroupType")
@XmlEnum
public enum UserGroupType {

    TAM("TAM"),
    BOOKER("BOOKER"),
    @XmlEnumValue("BOOKER2")
    BOOKER_2("BOOKER2"),
    COMPANY("COMPANY");
    private final String value;

    UserGroupType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static UserGroupType fromValue(String v) {
        for (UserGroupType c: UserGroupType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
