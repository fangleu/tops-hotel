
package com.micros.webservices.og._4_3.common;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import com.micros.webservices.og._4_3.name.NamePhone;


/**
 * <p>Java class for Phone complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Phone">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;element name="PhoneNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *           &lt;element name="PhoneData" type="{http://webservices.micros.com/og/4.3/Common/}PhonePhoneData" minOccurs="0"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *       &lt;attribute name="phoneType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="phoneRole" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Phone", propOrder = {
    "phoneNumber",
    "phoneData"
})
@XmlSeeAlso({
    NamePhone.class
})
public class Phone {

    @XmlElement(name = "PhoneNumber")
    protected String phoneNumber;
    @XmlElement(name = "PhoneData")
    protected PhonePhoneData phoneData;
    @XmlAttribute(name = "phoneType")
    protected String phoneType;
    @XmlAttribute(name = "phoneRole")
    protected String phoneRole;

    /**
     * Gets the value of the phoneNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the value of the phoneNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhoneNumber(String value) {
        this.phoneNumber = value;
    }

    /**
     * Gets the value of the phoneData property.
     * 
     * @return
     *     possible object is
     *     {@link PhonePhoneData }
     *     
     */
    public PhonePhoneData getPhoneData() {
        return phoneData;
    }

    /**
     * Sets the value of the phoneData property.
     * 
     * @param value
     *     allowed object is
     *     {@link PhonePhoneData }
     *     
     */
    public void setPhoneData(PhonePhoneData value) {
        this.phoneData = value;
    }

    /**
     * Gets the value of the phoneType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhoneType() {
        return phoneType;
    }

    /**
     * Sets the value of the phoneType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhoneType(String value) {
        this.phoneType = value;
    }

    /**
     * Gets the value of the phoneRole property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhoneRole() {
        return phoneRole;
    }

    /**
     * Sets the value of the phoneRole property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhoneRole(String value) {
        this.phoneRole = value;
    }

}
