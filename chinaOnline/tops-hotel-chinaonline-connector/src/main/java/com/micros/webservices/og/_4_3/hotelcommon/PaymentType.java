
package com.micros.webservices.og._4_3.hotelcommon;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.micros.webservices.og._4_3.common.CreditCard;


/**
 * <p>Java class for PaymentType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PaymentType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;element name="OtherPayment" type="{http://webservices.micros.com/og/4.3/HotelCommon/}OtherPaymentType" minOccurs="0"/>
 *           &lt;element name="PaymentCard" type="{http://webservices.micros.com/og/4.3/Common/}CreditCard" minOccurs="0"/>
 *           &lt;element name="PaymentVoucher" type="{http://webservices.micros.com/og/4.3/HotelCommon/}Voucher" minOccurs="0"/>
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
@XmlType(name = "PaymentType", propOrder = {
    "otherPayment",
    "paymentCard",
    "paymentVoucher"
})
public class PaymentType {

    @XmlElement(name = "OtherPayment")
    protected OtherPaymentType otherPayment;
    @XmlElement(name = "PaymentCard")
    protected CreditCard paymentCard;
    @XmlElement(name = "PaymentVoucher")
    protected Voucher paymentVoucher;

    /**
     * Gets the value of the otherPayment property.
     * 
     * @return
     *     possible object is
     *     {@link OtherPaymentType }
     *     
     */
    public OtherPaymentType getOtherPayment() {
        return otherPayment;
    }

    /**
     * Sets the value of the otherPayment property.
     * 
     * @param value
     *     allowed object is
     *     {@link OtherPaymentType }
     *     
     */
    public void setOtherPayment(OtherPaymentType value) {
        this.otherPayment = value;
    }

    /**
     * Gets the value of the paymentCard property.
     * 
     * @return
     *     possible object is
     *     {@link CreditCard }
     *     
     */
    public CreditCard getPaymentCard() {
        return paymentCard;
    }

    /**
     * Sets the value of the paymentCard property.
     * 
     * @param value
     *     allowed object is
     *     {@link CreditCard }
     *     
     */
    public void setPaymentCard(CreditCard value) {
        this.paymentCard = value;
    }

    /**
     * Gets the value of the paymentVoucher property.
     * 
     * @return
     *     possible object is
     *     {@link Voucher }
     *     
     */
    public Voucher getPaymentVoucher() {
        return paymentVoucher;
    }

    /**
     * Sets the value of the paymentVoucher property.
     * 
     * @param value
     *     allowed object is
     *     {@link Voucher }
     *     
     */
    public void setPaymentVoucher(Voucher value) {
        this.paymentVoucher = value;
    }

}
