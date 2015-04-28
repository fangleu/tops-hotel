
package com.micros.webservices.og._4_3.hotelcommon;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AdditionalDetailType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="AdditionalDetailType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="RateRules"/>
 *     &lt;enumeration value="MarketingInformation"/>
 *     &lt;enumeration value="DepositPolicy"/>
 *     &lt;enumeration value="Promotion"/>
 *     &lt;enumeration value="CommissionPolicy"/>
 *     &lt;enumeration value="GuaranteePolicy"/>
 *     &lt;enumeration value="Miscellaneous"/>
 *     &lt;enumeration value="PackageOptions"/>
 *     &lt;enumeration value="PenaltyPolicy"/>
 *     &lt;enumeration value="TASpecialRequest"/>
 *     &lt;enumeration value="TaxInformation"/>
 *     &lt;enumeration value="CancelPolicy"/>
 *     &lt;enumeration value="PointsPolicy"/>
 *     &lt;enumeration value="ProprietaryGDSCode"/>
 *     &lt;enumeration value="Other"/>
 *     &lt;enumeration value="Breakfast"/>
 *     &lt;enumeration value="Lunch"/>
 *     &lt;enumeration value="Dinner"/>
 *     &lt;enumeration value="AbsoluteDeadline"/>
 *     &lt;enumeration value="PenaltyFee"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "AdditionalDetailType")
@XmlEnum
public enum AdditionalDetailType {

    @XmlEnumValue("RateRules")
    RATE_RULES("RateRules"),
    @XmlEnumValue("MarketingInformation")
    MARKETING_INFORMATION("MarketingInformation"),
    @XmlEnumValue("DepositPolicy")
    DEPOSIT_POLICY("DepositPolicy"),
    @XmlEnumValue("Promotion")
    PROMOTION("Promotion"),
    @XmlEnumValue("CommissionPolicy")
    COMMISSION_POLICY("CommissionPolicy"),
    @XmlEnumValue("GuaranteePolicy")
    GUARANTEE_POLICY("GuaranteePolicy"),
    @XmlEnumValue("Miscellaneous")
    MISCELLANEOUS("Miscellaneous"),
    @XmlEnumValue("PackageOptions")
    PACKAGE_OPTIONS("PackageOptions"),
    @XmlEnumValue("PenaltyPolicy")
    PENALTY_POLICY("PenaltyPolicy"),
    @XmlEnumValue("TASpecialRequest")
    TA_SPECIAL_REQUEST("TASpecialRequest"),
    @XmlEnumValue("TaxInformation")
    TAX_INFORMATION("TaxInformation"),
    @XmlEnumValue("CancelPolicy")
    CANCEL_POLICY("CancelPolicy"),
    @XmlEnumValue("PointsPolicy")
    POINTS_POLICY("PointsPolicy"),
    @XmlEnumValue("ProprietaryGDSCode")
    PROPRIETARY_GDS_CODE("ProprietaryGDSCode"),
    @XmlEnumValue("Other")
    OTHER("Other"),
    @XmlEnumValue("Breakfast")
    BREAKFAST("Breakfast"),
    @XmlEnumValue("Lunch")
    LUNCH("Lunch"),
    @XmlEnumValue("Dinner")
    DINNER("Dinner"),
    @XmlEnumValue("AbsoluteDeadline")
    ABSOLUTE_DEADLINE("AbsoluteDeadline"),
    @XmlEnumValue("PenaltyFee")
    PENALTY_FEE("PenaltyFee");
    private final String value;

    AdditionalDetailType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AdditionalDetailType fromValue(String v) {
        for (AdditionalDetailType c: AdditionalDetailType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
