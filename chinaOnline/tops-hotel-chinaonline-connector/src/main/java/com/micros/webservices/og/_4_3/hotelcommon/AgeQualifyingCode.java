
package com.micros.webservices.og._4_3.hotelcommon;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AgeQualifyingCode.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="AgeQualifyingCode">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ADULT"/>
 *     &lt;enumeration value="CHILD"/>
 *     &lt;enumeration value="OTHER"/>
 *     &lt;enumeration value="CHILDBUCKET1"/>
 *     &lt;enumeration value="CHILDBUCKET2"/>
 *     &lt;enumeration value="CHILDBUCKET3"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "AgeQualifyingCode")
@XmlEnum
public enum AgeQualifyingCode {

    ADULT("ADULT"),
    CHILD("CHILD"),
    OTHER("OTHER"),
    @XmlEnumValue("CHILDBUCKET1")
    CHILDBUCKET_1("CHILDBUCKET1"),
    @XmlEnumValue("CHILDBUCKET2")
    CHILDBUCKET_2("CHILDBUCKET2"),
    @XmlEnumValue("CHILDBUCKET3")
    CHILDBUCKET_3("CHILDBUCKET3");
    private final String value;

    AgeQualifyingCode(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AgeQualifyingCode fromValue(String v) {
        for (AgeQualifyingCode c: AgeQualifyingCode.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
