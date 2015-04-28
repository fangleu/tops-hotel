
package com.micros.webservices.og._4_3.name;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PrivacyOptionTypeOptionType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="PrivacyOptionTypeOptionType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Promotions"/>
 *     &lt;enumeration value="MarketResearch"/>
 *     &lt;enumeration value="ThirdParties"/>
 *     &lt;enumeration value="LoyaltyProgram"/>
 *     &lt;enumeration value="Privacy"/>
 *     &lt;enumeration value="Email"/>
 *     &lt;enumeration value="Mail"/>
 *     &lt;enumeration value="Phone"/>
 *     &lt;enumeration value="SMS"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "PrivacyOptionTypeOptionType")
@XmlEnum
public enum PrivacyOptionTypeOptionType {

    @XmlEnumValue("Promotions")
    PROMOTIONS("Promotions"),
    @XmlEnumValue("MarketResearch")
    MARKET_RESEARCH("MarketResearch"),
    @XmlEnumValue("ThirdParties")
    THIRD_PARTIES("ThirdParties"),
    @XmlEnumValue("LoyaltyProgram")
    LOYALTY_PROGRAM("LoyaltyProgram"),
    @XmlEnumValue("Privacy")
    PRIVACY("Privacy"),
    @XmlEnumValue("Email")
    EMAIL("Email"),
    @XmlEnumValue("Mail")
    MAIL("Mail"),
    @XmlEnumValue("Phone")
    PHONE("Phone"),
    SMS("SMS");
    private final String value;

    PrivacyOptionTypeOptionType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PrivacyOptionTypeOptionType fromValue(String v) {
        for (PrivacyOptionTypeOptionType c: PrivacyOptionTypeOptionType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
