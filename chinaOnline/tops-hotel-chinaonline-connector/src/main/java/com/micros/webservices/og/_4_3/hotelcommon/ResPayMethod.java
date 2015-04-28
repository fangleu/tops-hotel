
package com.micros.webservices.og._4_3.hotelcommon;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ResPayMethod complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResPayMethod">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PaymentMethod" type="{http://webservices.micros.com/og/4.3/HotelCommon/}OtherPaymentType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Owner" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Window" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResPayMethod", propOrder = {
    "paymentMethod"
})
public class ResPayMethod {

    @XmlElement(name = "PaymentMethod")
    protected OtherPaymentType paymentMethod;
    @XmlAttribute(name = "Owner")
    protected String owner;
    @XmlAttribute(name = "Window", required = true)
    protected int window;

    /**
     * Gets the value of the paymentMethod property.
     * 
     * @return
     *     possible object is
     *     {@link OtherPaymentType }
     *     
     */
    public OtherPaymentType getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * Sets the value of the paymentMethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link OtherPaymentType }
     *     
     */
    public void setPaymentMethod(OtherPaymentType value) {
        this.paymentMethod = value;
    }

    /**
     * Gets the value of the owner property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Sets the value of the owner property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOwner(String value) {
        this.owner = value;
    }

    /**
     * Gets the value of the window property.
     * 
     */
    public int getWindow() {
        return window;
    }

    /**
     * Sets the value of the window property.
     * 
     */
    public void setWindow(int value) {
        this.window = value;
    }

}
