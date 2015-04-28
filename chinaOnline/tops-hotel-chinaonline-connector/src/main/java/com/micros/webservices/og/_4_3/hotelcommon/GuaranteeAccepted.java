
package com.micros.webservices.og._4_3.hotelcommon;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.micros.webservices.og._4_3.common.CreditCard;
import com.micros.webservices.og._4_3.common.UniqueID;


/**
 * <p>Java class for GuaranteeAccepted complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GuaranteeAccepted">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GuaranteeCreditCard" type="{http://webservices.micros.com/og/4.3/Common/}CreditCard" minOccurs="0"/>
 *         &lt;element name="GuaranteeTravelAgent" type="{http://webservices.micros.com/og/4.3/Common/}UniqueID" minOccurs="0"/>
 *         &lt;element name="GuaranteeCompany" type="{http://webservices.micros.com/og/4.3/Common/}UniqueID" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="agentPhone" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GuaranteeAccepted", propOrder = {
    "guaranteeCreditCard",
    "guaranteeTravelAgent",
    "guaranteeCompany"
})
public class GuaranteeAccepted {

    @XmlElement(name = "GuaranteeCreditCard")
    protected CreditCard guaranteeCreditCard;
    @XmlElement(name = "GuaranteeTravelAgent")
    protected UniqueID guaranteeTravelAgent;
    @XmlElement(name = "GuaranteeCompany")
    protected UniqueID guaranteeCompany;
    @XmlAttribute(name = "agentPhone")
    protected String agentPhone;

    /**
     * Gets the value of the guaranteeCreditCard property.
     * 
     * @return
     *     possible object is
     *     {@link CreditCard }
     *     
     */
    public CreditCard getGuaranteeCreditCard() {
        return guaranteeCreditCard;
    }

    /**
     * Sets the value of the guaranteeCreditCard property.
     * 
     * @param value
     *     allowed object is
     *     {@link CreditCard }
     *     
     */
    public void setGuaranteeCreditCard(CreditCard value) {
        this.guaranteeCreditCard = value;
    }

    /**
     * Gets the value of the guaranteeTravelAgent property.
     * 
     * @return
     *     possible object is
     *     {@link UniqueID }
     *     
     */
    public UniqueID getGuaranteeTravelAgent() {
        return guaranteeTravelAgent;
    }

    /**
     * Sets the value of the guaranteeTravelAgent property.
     * 
     * @param value
     *     allowed object is
     *     {@link UniqueID }
     *     
     */
    public void setGuaranteeTravelAgent(UniqueID value) {
        this.guaranteeTravelAgent = value;
    }

    /**
     * Gets the value of the guaranteeCompany property.
     * 
     * @return
     *     possible object is
     *     {@link UniqueID }
     *     
     */
    public UniqueID getGuaranteeCompany() {
        return guaranteeCompany;
    }

    /**
     * Sets the value of the guaranteeCompany property.
     * 
     * @param value
     *     allowed object is
     *     {@link UniqueID }
     *     
     */
    public void setGuaranteeCompany(UniqueID value) {
        this.guaranteeCompany = value;
    }

    /**
     * Gets the value of the agentPhone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAgentPhone() {
        return agentPhone;
    }

    /**
     * Sets the value of the agentPhone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAgentPhone(String value) {
        this.agentPhone = value;
    }

}
