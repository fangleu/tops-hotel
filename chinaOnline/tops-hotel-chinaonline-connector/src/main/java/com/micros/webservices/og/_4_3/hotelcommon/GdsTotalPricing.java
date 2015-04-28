
package com.micros.webservices.og._4_3.hotelcommon;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.micros.webservices.og._4_3.common.Amount;


/**
 * <p>Java class for GdsTotalPricing complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GdsTotalPricing">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TotalInclusiveRoomRate" type="{http://webservices.micros.com/og/4.3/Common/}Amount" minOccurs="0"/>
 *         &lt;element name="TotalExclusiveRoomRate" type="{http://webservices.micros.com/og/4.3/Common/}Amount" minOccurs="0"/>
 *         &lt;element name="TotalTax" type="{http://webservices.micros.com/og/4.3/Common/}Amount" minOccurs="0"/>
 *         &lt;element name="TotalSurCharge" type="{http://webservices.micros.com/og/4.3/Common/}Amount" minOccurs="0"/>
 *         &lt;element name="TaxQualifier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MatchingQualifier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GdsTotalPricing", propOrder = {
    "totalInclusiveRoomRate",
    "totalExclusiveRoomRate",
    "totalTax",
    "totalSurCharge",
    "taxQualifier",
    "matchingQualifier"
})
public class GdsTotalPricing {

    @XmlElement(name = "TotalInclusiveRoomRate")
    protected Amount totalInclusiveRoomRate;
    @XmlElement(name = "TotalExclusiveRoomRate")
    protected Amount totalExclusiveRoomRate;
    @XmlElement(name = "TotalTax")
    protected Amount totalTax;
    @XmlElement(name = "TotalSurCharge")
    protected Amount totalSurCharge;
    @XmlElement(name = "TaxQualifier")
    protected String taxQualifier;
    @XmlElement(name = "MatchingQualifier")
    protected String matchingQualifier;

    /**
     * Gets the value of the totalInclusiveRoomRate property.
     * 
     * @return
     *     possible object is
     *     {@link Amount }
     *     
     */
    public Amount getTotalInclusiveRoomRate() {
        return totalInclusiveRoomRate;
    }

    /**
     * Sets the value of the totalInclusiveRoomRate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Amount }
     *     
     */
    public void setTotalInclusiveRoomRate(Amount value) {
        this.totalInclusiveRoomRate = value;
    }

    /**
     * Gets the value of the totalExclusiveRoomRate property.
     * 
     * @return
     *     possible object is
     *     {@link Amount }
     *     
     */
    public Amount getTotalExclusiveRoomRate() {
        return totalExclusiveRoomRate;
    }

    /**
     * Sets the value of the totalExclusiveRoomRate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Amount }
     *     
     */
    public void setTotalExclusiveRoomRate(Amount value) {
        this.totalExclusiveRoomRate = value;
    }

    /**
     * Gets the value of the totalTax property.
     * 
     * @return
     *     possible object is
     *     {@link Amount }
     *     
     */
    public Amount getTotalTax() {
        return totalTax;
    }

    /**
     * Sets the value of the totalTax property.
     * 
     * @param value
     *     allowed object is
     *     {@link Amount }
     *     
     */
    public void setTotalTax(Amount value) {
        this.totalTax = value;
    }

    /**
     * Gets the value of the totalSurCharge property.
     * 
     * @return
     *     possible object is
     *     {@link Amount }
     *     
     */
    public Amount getTotalSurCharge() {
        return totalSurCharge;
    }

    /**
     * Sets the value of the totalSurCharge property.
     * 
     * @param value
     *     allowed object is
     *     {@link Amount }
     *     
     */
    public void setTotalSurCharge(Amount value) {
        this.totalSurCharge = value;
    }

    /**
     * Gets the value of the taxQualifier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaxQualifier() {
        return taxQualifier;
    }

    /**
     * Sets the value of the taxQualifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaxQualifier(String value) {
        this.taxQualifier = value;
    }

    /**
     * Gets the value of the matchingQualifier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMatchingQualifier() {
        return matchingQualifier;
    }

    /**
     * Sets the value of the matchingQualifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMatchingQualifier(String value) {
        this.matchingQualifier = value;
    }

}
