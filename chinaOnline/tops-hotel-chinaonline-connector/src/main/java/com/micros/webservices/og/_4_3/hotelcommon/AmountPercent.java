
package com.micros.webservices.og._4_3.hotelcommon;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AmountPercent complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AmountPercent">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Taxes" type="{http://webservices.micros.com/og/4.3/HotelCommon/}TaxesType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="CurrencyCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="TaxInclusive" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="FeesInclusive" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="NmbrOfNights" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="BasisType" type="{http://webservices.micros.com/og/4.3/HotelCommon/}AmountPercentTypeBasisType" />
 *       &lt;attribute name="Percent" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="Amount" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="ApplyAs" type="{http://webservices.micros.com/og/4.3/HotelCommon/}AmountPercentTypeApplyAs" />
 *       &lt;attribute name="DecimalPlaces" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AmountPercent", propOrder = {
    "taxes"
})
public class AmountPercent {

    @XmlElement(name = "Taxes")
    protected TaxesType taxes;
    @XmlAttribute(name = "CurrencyCode")
    protected String currencyCode;
    @XmlAttribute(name = "TaxInclusive")
    protected Boolean taxInclusive;
    @XmlAttribute(name = "FeesInclusive")
    protected Boolean feesInclusive;
    @XmlAttribute(name = "NmbrOfNights")
    protected Integer nmbrOfNights;
    @XmlAttribute(name = "BasisType")
    protected AmountPercentTypeBasisType basisType;
    @XmlAttribute(name = "Percent")
    protected BigDecimal percent;
    @XmlAttribute(name = "Amount")
    protected BigDecimal amount;
    @XmlAttribute(name = "ApplyAs")
    protected AmountPercentTypeApplyAs applyAs;
    @XmlAttribute(name = "DecimalPlaces")
    protected Integer decimalPlaces;

    /**
     * Gets the value of the taxes property.
     * 
     * @return
     *     possible object is
     *     {@link TaxesType }
     *     
     */
    public TaxesType getTaxes() {
        return taxes;
    }

    /**
     * Sets the value of the taxes property.
     * 
     * @param value
     *     allowed object is
     *     {@link TaxesType }
     *     
     */
    public void setTaxes(TaxesType value) {
        this.taxes = value;
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
     * Gets the value of the taxInclusive property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isTaxInclusive() {
        return taxInclusive;
    }

    /**
     * Sets the value of the taxInclusive property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTaxInclusive(Boolean value) {
        this.taxInclusive = value;
    }

    /**
     * Gets the value of the feesInclusive property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isFeesInclusive() {
        return feesInclusive;
    }

    /**
     * Sets the value of the feesInclusive property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFeesInclusive(Boolean value) {
        this.feesInclusive = value;
    }

    /**
     * Gets the value of the nmbrOfNights property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNmbrOfNights() {
        return nmbrOfNights;
    }

    /**
     * Sets the value of the nmbrOfNights property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNmbrOfNights(Integer value) {
        this.nmbrOfNights = value;
    }

    /**
     * Gets the value of the basisType property.
     * 
     * @return
     *     possible object is
     *     {@link AmountPercentTypeBasisType }
     *     
     */
    public AmountPercentTypeBasisType getBasisType() {
        return basisType;
    }

    /**
     * Sets the value of the basisType property.
     * 
     * @param value
     *     allowed object is
     *     {@link AmountPercentTypeBasisType }
     *     
     */
    public void setBasisType(AmountPercentTypeBasisType value) {
        this.basisType = value;
    }

    /**
     * Gets the value of the percent property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPercent() {
        return percent;
    }

    /**
     * Sets the value of the percent property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPercent(BigDecimal value) {
        this.percent = value;
    }

    /**
     * Gets the value of the amount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAmount(BigDecimal value) {
        this.amount = value;
    }

    /**
     * Gets the value of the applyAs property.
     * 
     * @return
     *     possible object is
     *     {@link AmountPercentTypeApplyAs }
     *     
     */
    public AmountPercentTypeApplyAs getApplyAs() {
        return applyAs;
    }

    /**
     * Sets the value of the applyAs property.
     * 
     * @param value
     *     allowed object is
     *     {@link AmountPercentTypeApplyAs }
     *     
     */
    public void setApplyAs(AmountPercentTypeApplyAs value) {
        this.applyAs = value;
    }

    /**
     * Gets the value of the decimalPlaces property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDecimalPlaces() {
        return decimalPlaces;
    }

    /**
     * Sets the value of the decimalPlaces property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDecimalPlaces(Integer value) {
        this.decimalPlaces = value;
    }

}
