
package com.micros.webservices.og._4_3.hotelcommon;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RateCalculation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RateCalculation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="rateCategory" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="rateChangeIndicator" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="rateChangeMethod" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="calculatePercentagesIndicator" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="taxApplicationIndicator" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RateCalculation")
public class RateCalculation {

    @XmlAttribute(name = "rateCategory")
    protected String rateCategory;
    @XmlAttribute(name = "rateChangeIndicator")
    protected String rateChangeIndicator;
    @XmlAttribute(name = "rateChangeMethod")
    protected String rateChangeMethod;
    @XmlAttribute(name = "calculatePercentagesIndicator")
    protected String calculatePercentagesIndicator;
    @XmlAttribute(name = "taxApplicationIndicator")
    protected String taxApplicationIndicator;

    /**
     * Gets the value of the rateCategory property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRateCategory() {
        return rateCategory;
    }

    /**
     * Sets the value of the rateCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRateCategory(String value) {
        this.rateCategory = value;
    }

    /**
     * Gets the value of the rateChangeIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRateChangeIndicator() {
        return rateChangeIndicator;
    }

    /**
     * Sets the value of the rateChangeIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRateChangeIndicator(String value) {
        this.rateChangeIndicator = value;
    }

    /**
     * Gets the value of the rateChangeMethod property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRateChangeMethod() {
        return rateChangeMethod;
    }

    /**
     * Sets the value of the rateChangeMethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRateChangeMethod(String value) {
        this.rateChangeMethod = value;
    }

    /**
     * Gets the value of the calculatePercentagesIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCalculatePercentagesIndicator() {
        return calculatePercentagesIndicator;
    }

    /**
     * Sets the value of the calculatePercentagesIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCalculatePercentagesIndicator(String value) {
        this.calculatePercentagesIndicator = value;
    }

    /**
     * Gets the value of the taxApplicationIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaxApplicationIndicator() {
        return taxApplicationIndicator;
    }

    /**
     * Sets the value of the taxApplicationIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaxApplicationIndicator(String value) {
        this.taxApplicationIndicator = value;
    }

}
