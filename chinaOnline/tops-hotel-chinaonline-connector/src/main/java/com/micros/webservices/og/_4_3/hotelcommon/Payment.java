
package com.micros.webservices.og._4_3.hotelcommon;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Payment complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Payment">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;element name="PaymentsAccepted" type="{http://webservices.micros.com/og/4.3/HotelCommon/}ArrayOfPaymentType" minOccurs="0"/>
 *           &lt;element name="PaymentUsed" type="{http://webservices.micros.com/og/4.3/HotelCommon/}PaymentUsed" minOccurs="0"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Payment", propOrder = {
    "paymentsAccepted",
    "paymentUsed"
})
public class Payment {

    @XmlElement(name = "PaymentsAccepted")
    protected ArrayOfPaymentType paymentsAccepted;
    @XmlElement(name = "PaymentUsed")
    protected PaymentUsed paymentUsed;

    /**
     * Gets the value of the paymentsAccepted property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfPaymentType }
     *     
     */
    public ArrayOfPaymentType getPaymentsAccepted() {
        return paymentsAccepted;
    }

    /**
     * Sets the value of the paymentsAccepted property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfPaymentType }
     *     
     */
    public void setPaymentsAccepted(ArrayOfPaymentType value) {
        this.paymentsAccepted = value;
    }

    /**
     * Gets the value of the paymentUsed property.
     * 
     * @return
     *     possible object is
     *     {@link PaymentUsed }
     *     
     */
    public PaymentUsed getPaymentUsed() {
        return paymentUsed;
    }

    /**
     * Sets the value of the paymentUsed property.
     * 
     * @param value
     *     allowed object is
     *     {@link PaymentUsed }
     *     
     */
    public void setPaymentUsed(PaymentUsed value) {
        this.paymentUsed = value;
    }

}
