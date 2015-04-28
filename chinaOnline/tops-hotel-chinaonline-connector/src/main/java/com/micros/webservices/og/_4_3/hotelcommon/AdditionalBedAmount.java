
package com.micros.webservices.og._4_3.hotelcommon;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.micros.webservices.og._4_3.common.Amount;


/**
 * <p>Java class for AdditionalBedAmount complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AdditionalBedAmount">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Amount" type="{http://webservices.micros.com/og/4.3/Common/}Amount" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="extraBedType" use="required" type="{http://webservices.micros.com/og/4.3/HotelCommon/}AdditionalBedType" />
 *       &lt;attribute name="otherExtraBedType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="quantity" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AdditionalBedAmount", propOrder = {
    "amount"
})
public class AdditionalBedAmount {

    @XmlElement(name = "Amount")
    protected Amount amount;
    @XmlAttribute(name = "extraBedType", required = true)
    protected AdditionalBedType extraBedType;
    @XmlAttribute(name = "otherExtraBedType")
    protected String otherExtraBedType;
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
     * Gets the value of the extraBedType property.
     * 
     * @return
     *     possible object is
     *     {@link AdditionalBedType }
     *     
     */
    public AdditionalBedType getExtraBedType() {
        return extraBedType;
    }

    /**
     * Sets the value of the extraBedType property.
     * 
     * @param value
     *     allowed object is
     *     {@link AdditionalBedType }
     *     
     */
    public void setExtraBedType(AdditionalBedType value) {
        this.extraBedType = value;
    }

    /**
     * Gets the value of the otherExtraBedType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOtherExtraBedType() {
        return otherExtraBedType;
    }

    /**
     * Sets the value of the otherExtraBedType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOtherExtraBedType(String value) {
        this.otherExtraBedType = value;
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
