
package com.micros.webservices.og._4_3.hotelcommon;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MinMaxRate complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MinMaxRate">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="minimumRate" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="maximumRate" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="currencyCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="decimals" type="{http://www.w3.org/2001/XMLSchema}short" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MinMaxRate")
public class MinMaxRate {

    @XmlAttribute(name = "minimumRate")
    protected Double minimumRate;
    @XmlAttribute(name = "maximumRate")
    protected Double maximumRate;
    @XmlAttribute(name = "currencyCode")
    protected String currencyCode;
    @XmlAttribute(name = "decimals")
    protected Short decimals;

    /**
     * Gets the value of the minimumRate property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMinimumRate() {
        return minimumRate;
    }

    /**
     * Sets the value of the minimumRate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMinimumRate(Double value) {
        this.minimumRate = value;
    }

    /**
     * Gets the value of the maximumRate property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMaximumRate() {
        return maximumRate;
    }

    /**
     * Sets the value of the maximumRate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMaximumRate(Double value) {
        this.maximumRate = value;
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
