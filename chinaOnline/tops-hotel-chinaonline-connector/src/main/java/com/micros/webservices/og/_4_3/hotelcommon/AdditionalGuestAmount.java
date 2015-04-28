
package com.micros.webservices.og._4_3.hotelcommon;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.micros.webservices.og._4_3.common.Amount;


/**
 * <p>Java class for AdditionalGuestAmount complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AdditionalGuestAmount">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Amount" type="{http://webservices.micros.com/og/4.3/Common/}Amount" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="additionalGuestType" use="required" type="{http://webservices.micros.com/og/4.3/HotelCommon/}AgeQualifyingCode" />
 *       &lt;attribute name="otherAdditionalGuestType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="quantity" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AdditionalGuestAmount", propOrder = {
    "amount"
})
public class AdditionalGuestAmount {

    @XmlElement(name = "Amount")
    protected Amount amount;
    @XmlAttribute(name = "additionalGuestType", required = true)
    protected AgeQualifyingCode additionalGuestType;
    @XmlAttribute(name = "otherAdditionalGuestType")
    protected String otherAdditionalGuestType;
    @XmlAttribute(name = "quantity")
    protected Integer quantity;

    /**
     * Gets the value of the amount property.
     * 
     * @return
     *     possible object is
     *     {@link Amount }
     *     
     */
    public Amount getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Amount }
     *     
     */
    public void setAmount(Amount value) {
        this.amount = value;
    }

    /**
     * Gets the value of the additionalGuestType property.
     * 
     * @return
     *     possible object is
     *     {@link AgeQualifyingCode }
     *     
     */
    public AgeQualifyingCode getAdditionalGuestType() {
        return additionalGuestType;
    }

    /**
     * Sets the value of the additionalGuestType property.
     * 
     * @param value
     *     allowed object is
     *     {@link AgeQualifyingCode }
     *     
     */
    public void setAdditionalGuestType(AgeQualifyingCode value) {
        this.additionalGuestType = value;
    }

    /**
     * Gets the value of the otherAdditionalGuestType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOtherAdditionalGuestType() {
        return otherAdditionalGuestType;
    }

    /**
     * Sets the value of the otherAdditionalGuestType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOtherAdditionalGuestType(String value) {
        this.otherAdditionalGuestType = value;
    }

    /**
     * Gets the value of the quantity property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * Sets the value of the quantity property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setQuantity(Integer value) {
        this.quantity = value;
    }

}
