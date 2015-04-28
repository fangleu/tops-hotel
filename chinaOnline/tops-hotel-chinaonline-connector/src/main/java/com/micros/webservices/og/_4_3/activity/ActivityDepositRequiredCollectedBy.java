
package com.micros.webservices.og._4_3.activity;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ActivityDepositRequiredCollectedBy.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ActivityDepositRequiredCollectedBy">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Vendor"/>
 *     &lt;enumeration value="Agent"/>
 *     &lt;enumeration value="Other"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ActivityDepositRequiredCollectedBy")
@XmlEnum
public enum ActivityDepositRequiredCollectedBy {

    @XmlEnumValue("Vendor")
    VENDOR("Vendor"),
    @XmlEnumValue("Agent")
    AGENT("Agent"),
    @XmlEnumValue("Other")
    OTHER("Other");
    private final String value;

    ActivityDepositRequiredCollectedBy(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ActivityDepositRequiredCollectedBy fromValue(String v) {
        for (ActivityDepositRequiredCollectedBy c: ActivityDepositRequiredCollectedBy.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
