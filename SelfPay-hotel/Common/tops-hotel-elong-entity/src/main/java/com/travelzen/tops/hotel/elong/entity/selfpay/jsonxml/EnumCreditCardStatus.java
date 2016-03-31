//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.12.23 at 05:20:41 PM CST 
//


package com.travelzen.tops.hotel.elong.entity.selfpay.jsonxml;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EnumCreditCardStatus.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="EnumCreditCardStatus">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="UnProcess"/>
 *     &lt;enumeration value="Succeed"/>
 *     &lt;enumeration value="Processing"/>
 *     &lt;enumeration value="Fail"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "EnumCreditCardStatus")
@XmlEnum
public enum EnumCreditCardStatus {

    @XmlEnumValue("UnProcess")
    UnProcess("UnProcess"),
    @XmlEnumValue("Succeed")
    Succeed("Succeed"),
    @XmlEnumValue("Processing")
    Processing("Processing"),
    @XmlEnumValue("Fail")
    Fail("Fail");
    private final String value;

    EnumCreditCardStatus(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnumCreditCardStatus fromValue(String v) {
        for (EnumCreditCardStatus c: EnumCreditCardStatus.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}