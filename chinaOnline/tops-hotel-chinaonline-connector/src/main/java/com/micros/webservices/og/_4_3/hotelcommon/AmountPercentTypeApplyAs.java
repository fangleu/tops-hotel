
package com.micros.webservices.og._4_3.hotelcommon;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AmountPercentTypeApplyAs.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="AmountPercentTypeApplyAs">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="FirstNightDeposit"/>
 *     &lt;enumeration value="LastNightDepost"/>
 *     &lt;enumeration value="FirstAndLastNightDeposit"/>
 *     &lt;enumeration value="FirstNightPayment"/>
 *     &lt;enumeration value="LastNightPayment"/>
 *     &lt;enumeration value="FirstAndLastNightPayment"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "AmountPercentTypeApplyAs")
@XmlEnum
public enum AmountPercentTypeApplyAs {

    @XmlEnumValue("FirstNightDeposit")
    FIRST_NIGHT_DEPOSIT("FirstNightDeposit"),
    @XmlEnumValue("LastNightDepost")
    LAST_NIGHT_DEPOST("LastNightDepost"),
    @XmlEnumValue("FirstAndLastNightDeposit")
    FIRST_AND_LAST_NIGHT_DEPOSIT("FirstAndLastNightDeposit"),
    @XmlEnumValue("FirstNightPayment")
    FIRST_NIGHT_PAYMENT("FirstNightPayment"),
    @XmlEnumValue("LastNightPayment")
    LAST_NIGHT_PAYMENT("LastNightPayment"),
    @XmlEnumValue("FirstAndLastNightPayment")
    FIRST_AND_LAST_NIGHT_PAYMENT("FirstAndLastNightPayment");
    private final String value;

    AmountPercentTypeApplyAs(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AmountPercentTypeApplyAs fromValue(String v) {
        for (AmountPercentTypeApplyAs c: AmountPercentTypeApplyAs.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
