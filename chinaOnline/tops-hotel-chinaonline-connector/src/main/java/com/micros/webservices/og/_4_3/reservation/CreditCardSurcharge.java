
package com.micros.webservices.og._4_3.reservation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.micros.webservices.og._4_3.common.Amount;


/**
 * <p>Java class for CreditCardSurcharge complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CreditCardSurcharge">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SurchargeThreshold" type="{http://webservices.micros.com/og/4.3/Common/}Amount" minOccurs="0"/>
 *         &lt;element name="SurchargeAmount" type="{http://webservices.micros.com/og/4.3/Common/}Amount" minOccurs="0"/>
 *         &lt;element name="TaxAmount" type="{http://webservices.micros.com/og/4.3/Common/}Amount" minOccurs="0"/>
 *         &lt;element name="TotalBillAmount" type="{http://webservices.micros.com/og/4.3/Common/}Amount" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="CreditCardType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="SurchargePercentage" type="{http://www.w3.org/2001/XMLSchema}float" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CreditCardSurcharge", propOrder = {
    "surchargeThreshold",
    "surchargeAmount",
    "taxAmount",
    "totalBillAmount"
})
public class CreditCardSurcharge {

    @XmlElement(name = "SurchargeThreshold")
    protected Amount surchargeThreshold;
    @XmlElement(name = "SurchargeAmount")
    protected Amount surchargeAmount;
    @XmlElement(name = "TaxAmount")
    protected Amount taxAmount;
    @XmlElement(name = "TotalBillAmount")
    protected Amount totalBillAmount;
    @XmlAttribute(name = "CreditCardType")
    protected String creditCardType;
    @XmlAttribute(name = "SurchargePercentage")
    protected Float surchargePercentage;

    /**
     * Gets the value of the surchargeThreshold property.
     * 
     * @return
     *     possible object is
     *     {@link Amount }
     *     
     */
    public Amount getSurchargeThreshold() {
        return surchargeThreshold;
    }

    /**
     * Sets the value of the surchargeThreshold property.
     * 
     * @param value
     *     allowed object is
     *     {@link Amount }
     *     
     */
    public void setSurchargeThreshold(Amount value) {
        this.surchargeThreshold = value;
    }

    /**
     * Gets the value of the surchargeAmount property.
     * 
     * @return
     *     possible object is
     *     {@link Amount }
     *     
     */
    public Amount getSurchargeAmount() {
        return surchargeAmount;
    }

    /**
     * Sets the value of the surchargeAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Amount }
     *     
     */
    public void setSurchargeAmount(Amount value) {
        this.surchargeAmount = value;
    }

    /**
     * Gets the value of the taxAmount property.
     * 
     * @return
     *     possible object is
     *     {@link Amount }
     *     
     */
    public Amount getTaxAmount() {
        return taxAmount;
    }

    /**
     * Sets the value of the taxAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Amount }
     *     
     */
    public void setTaxAmount(Amount value) {
        this.taxAmount = value;
    }

    /**
     * Gets the value of the totalBillAmount property.
     * 
     * @return
     *     possible object is
     *     {@link Amount }
     *     
     */
    public Amount getTotalBillAmount() {
        return totalBillAmount;
    }

    /**
     * Sets the value of the totalBillAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Amount }
     *     
     */
    public void setTotalBillAmount(Amount value) {
        this.totalBillAmount = value;
    }

    /**
     * Gets the value of the creditCardType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreditCardType() {
        return creditCardType;
    }

    /**
     * Sets the value of the creditCardType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreditCardType(String value) {
        this.creditCardType = value;
    }

    /**
     * Gets the value of the surchargePercentage property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getSurchargePercentage() {
        return surchargePercentage;
    }

    /**
     * Sets the value of the surchargePercentage property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setSurchargePercentage(Float value) {
        this.surchargePercentage = value;
    }

}
