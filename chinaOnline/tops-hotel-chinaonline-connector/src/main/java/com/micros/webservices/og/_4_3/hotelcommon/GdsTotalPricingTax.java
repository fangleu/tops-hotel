
package com.micros.webservices.og._4_3.hotelcommon;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for GdsTotalPricingTax complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GdsTotalPricingTax">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="EffectiveStartDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="EffectiveEndDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="TaxOrFeeOrder" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TaxOrFeeBasis" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TaxOrFeeAmount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TaxOrFeeApplication" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TaxOrFeeTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ChargeType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TaxDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CurrencyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TaxOrFeeIncluded" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="decimals" type="{http://www.w3.org/2001/XMLSchema}short" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GdsTotalPricingTax", propOrder = {
    "effectiveStartDate",
    "effectiveEndDate",
    "taxOrFeeOrder",
    "taxOrFeeBasis",
    "taxOrFeeAmount",
    "taxOrFeeApplication",
    "taxOrFeeTypeCode",
    "chargeType",
    "taxDescription",
    "currencyCode",
    "taxOrFeeIncluded"
})
public class GdsTotalPricingTax {

    @XmlElement(name = "EffectiveStartDate", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar effectiveStartDate;
    @XmlElement(name = "EffectiveEndDate", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar effectiveEndDate;
    @XmlElement(name = "TaxOrFeeOrder")
    protected String taxOrFeeOrder;
    @XmlElement(name = "TaxOrFeeBasis")
    protected String taxOrFeeBasis;
    @XmlElement(name = "TaxOrFeeAmount")
    protected String taxOrFeeAmount;
    @XmlElement(name = "TaxOrFeeApplication")
    protected String taxOrFeeApplication;
    @XmlElement(name = "TaxOrFeeTypeCode")
    protected String taxOrFeeTypeCode;
    @XmlElement(name = "ChargeType")
    protected String chargeType;
    @XmlElement(name = "TaxDescription")
    protected String taxDescription;
    @XmlElement(name = "CurrencyCode")
    protected String currencyCode;
    @XmlElement(name = "TaxOrFeeIncluded")
    protected String taxOrFeeIncluded;
    @XmlAttribute(name = "decimals")
    protected Short decimals;

    /**
     * Gets the value of the effectiveStartDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEffectiveStartDate() {
        return effectiveStartDate;
    }

    /**
     * Sets the value of the effectiveStartDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEffectiveStartDate(XMLGregorianCalendar value) {
        this.effectiveStartDate = value;
    }

    /**
     * Gets the value of the effectiveEndDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEffectiveEndDate() {
        return effectiveEndDate;
    }

    /**
     * Sets the value of the effectiveEndDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEffectiveEndDate(XMLGregorianCalendar value) {
        this.effectiveEndDate = value;
    }

    /**
     * Gets the value of the taxOrFeeOrder property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaxOrFeeOrder() {
        return taxOrFeeOrder;
    }

    /**
     * Sets the value of the taxOrFeeOrder property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaxOrFeeOrder(String value) {
        this.taxOrFeeOrder = value;
    }

    /**
     * Gets the value of the taxOrFeeBasis property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaxOrFeeBasis() {
        return taxOrFeeBasis;
    }

    /**
     * Sets the value of the taxOrFeeBasis property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaxOrFeeBasis(String value) {
        this.taxOrFeeBasis = value;
    }

    /**
     * Gets the value of the taxOrFeeAmount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaxOrFeeAmount() {
        return taxOrFeeAmount;
    }

    /**
     * Sets the value of the taxOrFeeAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaxOrFeeAmount(String value) {
        this.taxOrFeeAmount = value;
    }

    /**
     * Gets the value of the taxOrFeeApplication property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaxOrFeeApplication() {
        return taxOrFeeApplication;
    }

    /**
     * Sets the value of the taxOrFeeApplication property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaxOrFeeApplication(String value) {
        this.taxOrFeeApplication = value;
    }

    /**
     * Gets the value of the taxOrFeeTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaxOrFeeTypeCode() {
        return taxOrFeeTypeCode;
    }

    /**
     * Sets the value of the taxOrFeeTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaxOrFeeTypeCode(String value) {
        this.taxOrFeeTypeCode = value;
    }

    /**
     * Gets the value of the chargeType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChargeType() {
        return chargeType;
    }

    /**
     * Sets the value of the chargeType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChargeType(String value) {
        this.chargeType = value;
    }

    /**
     * Gets the value of the taxDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaxDescription() {
        return taxDescription;
    }

    /**
     * Sets the value of the taxDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaxDescription(String value) {
        this.taxDescription = value;
    }

    /**
     * Gets the value of the currencyCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrencyCode() {
        return currencyCode;
    }

    /**
     * Sets the value of the currencyCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrencyCode(String value) {
        this.currencyCode = value;
    }

    /**
     * Gets the value of the taxOrFeeIncluded property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaxOrFeeIncluded() {
        return taxOrFeeIncluded;
    }

    /**
     * Sets the value of the taxOrFeeIncluded property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaxOrFeeIncluded(String value) {
        this.taxOrFeeIncluded = value;
    }

    /**
     * Gets the value of the decimals property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getDecimals() {
        return decimals;
    }

    /**
     * Sets the value of the decimals property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setDecimals(Short value) {
        this.decimals = value;
    }

}
