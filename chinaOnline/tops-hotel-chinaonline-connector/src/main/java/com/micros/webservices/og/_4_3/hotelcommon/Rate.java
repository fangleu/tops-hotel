
package com.micros.webservices.og._4_3.hotelcommon;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import com.micros.webservices.og._4_3.common.Amount;


/**
 * <p>Java class for Rate complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Rate">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Base" type="{http://webservices.micros.com/og/4.3/Common/}Amount" minOccurs="0"/>
 *         &lt;element name="TotalTaxesAndFees" type="{http://webservices.micros.com/og/4.3/Common/}Amount" minOccurs="0"/>
 *         &lt;element name="AdditionalGuestAmounts" type="{http://webservices.micros.com/og/4.3/HotelCommon/}ArrayOfAdditionalGuestAmount" minOccurs="0"/>
 *         &lt;element name="AdditionalBedAmounts" type="{http://webservices.micros.com/og/4.3/HotelCommon/}ArrayOfAdditionalBedAmount" minOccurs="0"/>
 *         &lt;element name="GdsTotalPricingTaxes" type="{http://webservices.micros.com/og/4.3/HotelCommon/}ArrayOfGdsTotalPricingTax" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="effectiveDate" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="expirationDate" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="rateOccurrence" type="{http://webservices.micros.com/og/4.3/HotelCommon/}RateOccurrenceType" />
 *       &lt;attribute name="otherRateOccurrence" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="rateChangeIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Rate", propOrder = {
    "base",
    "totalTaxesAndFees",
    "additionalGuestAmounts",
    "additionalBedAmounts",
    "gdsTotalPricingTaxes"
})
public class Rate {

    @XmlElement(name = "Base")
    protected Amount base;
    @XmlElement(name = "TotalTaxesAndFees")
    protected Amount totalTaxesAndFees;
    @XmlElement(name = "AdditionalGuestAmounts")
    protected ArrayOfAdditionalGuestAmount additionalGuestAmounts;
    @XmlElement(name = "AdditionalBedAmounts")
    protected ArrayOfAdditionalBedAmount additionalBedAmounts;
    @XmlElement(name = "GdsTotalPricingTaxes")
    protected ArrayOfGdsTotalPricingTax gdsTotalPricingTaxes;
    @XmlAttribute(name = "effectiveDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar effectiveDate;
    @XmlAttribute(name = "expirationDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar expirationDate;
    @XmlAttribute(name = "rateOccurrence")
    protected RateOccurrenceType rateOccurrence;
    @XmlAttribute(name = "otherRateOccurrence")
    protected String otherRateOccurrence;
    @XmlAttribute(name = "rateChangeIndicator")
    protected Boolean rateChangeIndicator;

    /**
     * Gets the value of the base property.
     * 
     * @return
     *     possible object is
     *     {@link Amount }
     *     
     */
    public Amount getBase() {
        return base;
    }

    /**
     * Sets the value of the base property.
     * 
     * @param value
     *     allowed object is
     *     {@link Amount }
     *     
     */
    public void setBase(Amount value) {
        this.base = value;
    }

    /**
     * Gets the value of the totalTaxesAndFees property.
     * 
     * @return
     *     possible object is
     *     {@link Amount }
     *     
     */
    public Amount getTotalTaxesAndFees() {
        return totalTaxesAndFees;
    }

    /**
     * Sets the value of the totalTaxesAndFees property.
     * 
     * @param value
     *     allowed object is
     *     {@link Amount }
     *     
     */
    public void setTotalTaxesAndFees(Amount value) {
        this.totalTaxesAndFees = value;
    }

    /**
     * Gets the value of the additionalGuestAmounts property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfAdditionalGuestAmount }
     *     
     */
    public ArrayOfAdditionalGuestAmount getAdditionalGuestAmounts() {
        return additionalGuestAmounts;
    }

    /**
     * Sets the value of the additionalGuestAmounts property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfAdditionalGuestAmount }
     *     
     */
    public void setAdditionalGuestAmounts(ArrayOfAdditionalGuestAmount value) {
        this.additionalGuestAmounts = value;
    }

    /**
     * Gets the value of the additionalBedAmounts property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfAdditionalBedAmount }
     *     
     */
    public ArrayOfAdditionalBedAmount getAdditionalBedAmounts() {
        return additionalBedAmounts;
    }

    /**
     * Sets the value of the additionalBedAmounts property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfAdditionalBedAmount }
     *     
     */
    public void setAdditionalBedAmounts(ArrayOfAdditionalBedAmount value) {
        this.additionalBedAmounts = value;
    }

    /**
     * Gets the value of the gdsTotalPricingTaxes property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfGdsTotalPricingTax }
     *     
     */
    public ArrayOfGdsTotalPricingTax getGdsTotalPricingTaxes() {
        return gdsTotalPricingTaxes;
    }

    /**
     * Sets the value of the gdsTotalPricingTaxes property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfGdsTotalPricingTax }
     *     
     */
    public void setGdsTotalPricingTaxes(ArrayOfGdsTotalPricingTax value) {
        this.gdsTotalPricingTaxes = value;
    }

    /**
     * Gets the value of the effectiveDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEffectiveDate() {
        return effectiveDate;
    }

    /**
     * Sets the value of the effectiveDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEffectiveDate(XMLGregorianCalendar value) {
        this.effectiveDate = value;
    }

    /**
     * Gets the value of the expirationDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getExpirationDate() {
        return expirationDate;
    }

    /**
     * Sets the value of the expirationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setExpirationDate(XMLGregorianCalendar value) {
        this.expirationDate = value;
    }

    /**
     * Gets the value of the rateOccurrence property.
     * 
     * @return
     *     possible object is
     *     {@link RateOccurrenceType }
     *     
     */
    public RateOccurrenceType getRateOccurrence() {
        return rateOccurrence;
    }

    /**
     * Sets the value of the rateOccurrence property.
     * 
     * @param value
     *     allowed object is
     *     {@link RateOccurrenceType }
     *     
     */
    public void setRateOccurrence(RateOccurrenceType value) {
        this.rateOccurrence = value;
    }

    /**
     * Gets the value of the otherRateOccurrence property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOtherRateOccurrence() {
        return otherRateOccurrence;
    }

    /**
     * Sets the value of the otherRateOccurrence property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOtherRateOccurrence(String value) {
        this.otherRateOccurrence = value;
    }

    /**
     * Gets the value of the rateChangeIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isRateChangeIndicator() {
        return rateChangeIndicator;
    }

    /**
     * Sets the value of the rateChangeIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRateChangeIndicator(Boolean value) {
        this.rateChangeIndicator = value;
    }

}
