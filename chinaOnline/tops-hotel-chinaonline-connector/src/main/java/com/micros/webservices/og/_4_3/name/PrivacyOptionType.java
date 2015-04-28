
package com.micros.webservices.og._4_3.name;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PrivacyOptionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PrivacyOptionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="OptionType" use="required" type="{http://webservices.micros.com/og/4.3/Name/}PrivacyOptionTypeOptionType" />
 *       &lt;attribute name="OptionValue" use="required" type="{http://webservices.micros.com/og/4.3/Name/}PrivacyOptionTypeOptionValue" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PrivacyOptionType")
public class PrivacyOptionType {

    @XmlAttribute(name = "OptionType", required = true)
    protected PrivacyOptionTypeOptionType optionType;
    @XmlAttribute(name = "OptionValue", required = true)
    protected PrivacyOptionTypeOptionValue optionValue;

    /**
     * Gets the value of the optionType property.
     * 
     * @return
     *     possible object is
     *     {@link PrivacyOptionTypeOptionType }
     *     
     */
    public PrivacyOptionTypeOptionType getOptionType() {
        return optionType;
    }

    /**
     * Sets the value of the optionType property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrivacyOptionTypeOptionType }
     *     
     */
    public void setOptionType(PrivacyOptionTypeOptionType value) {
        this.optionType = value;
    }

    /**
     * Gets the value of the optionValue property.
     * 
     * @return
     *     possible object is
     *     {@link PrivacyOptionTypeOptionValue }
     *     
     */
    public PrivacyOptionTypeOptionValue getOptionValue() {
        return optionValue;
    }

    /**
     * Sets the value of the optionValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrivacyOptionTypeOptionValue }
     *     
     */
    public void setOptionValue(PrivacyOptionTypeOptionValue value) {
        this.optionValue = value;
    }

}
