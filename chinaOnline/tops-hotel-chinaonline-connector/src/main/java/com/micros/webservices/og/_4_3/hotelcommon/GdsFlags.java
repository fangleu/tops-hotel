
package com.micros.webservices.og._4_3.hotelcommon;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GdsFlags complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GdsFlags">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="LateArrivalTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CancellationCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GuarCancelFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GdsFlags", propOrder = {
    "lateArrivalTime",
    "cancellationCode",
    "guarCancelFlag"
})
public class GdsFlags {

    @XmlElement(name = "LateArrivalTime")
    protected String lateArrivalTime;
    @XmlElement(name = "CancellationCode")
    protected String cancellationCode;
    @XmlElement(name = "GuarCancelFlag")
    protected String guarCancelFlag;

    /**
     * Gets the value of the lateArrivalTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLateArrivalTime() {
        return lateArrivalTime;
    }

    /**
     * Sets the value of the lateArrivalTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLateArrivalTime(String value) {
        this.lateArrivalTime = value;
    }

    /**
     * Gets the value of the cancellationCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCancellationCode() {
        return cancellationCode;
    }

    /**
     * Sets the value of the cancellationCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCancellationCode(String value) {
        this.cancellationCode = value;
    }

    /**
     * Gets the value of the guarCancelFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGuarCancelFlag() {
        return guarCancelFlag;
    }

    /**
     * Sets the value of the guarCancelFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGuarCancelFlag(String value) {
        this.guarCancelFlag = value;
    }

}
