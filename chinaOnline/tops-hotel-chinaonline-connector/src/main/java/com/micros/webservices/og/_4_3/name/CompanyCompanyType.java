
package com.micros.webservices.og._4_3.name;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CompanyCompanyType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CompanyCompanyType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="TRAVEL_AGENT"/>
 *     &lt;enumeration value="COMPANY"/>
 *     &lt;enumeration value="SOURCE"/>
 *     &lt;enumeration value="GROUP"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CompanyCompanyType")
@XmlEnum
public enum CompanyCompanyType {

    TRAVEL_AGENT,
    COMPANY,
    SOURCE,
    GROUP;

    public String value() {
        return name();
    }

    public static CompanyCompanyType fromValue(String v) {
        return valueOf(v);
    }

}
