
package com.micros.webservices.og._4_3.hotelcommon;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GuestCount complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GuestCount">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="ageQualifyingCode" type="{http://webservices.micros.com/og/4.3/HotelCommon/}AgeQualifyingCode" />
 *       &lt;attribute name="otherAgeQualifyingCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="age" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="count" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GuestCount")
public class GuestCount {

    @XmlAttribute(name = "ageQualifyingCode")
    protected AgeQualifyingCode ageQualifyingCode;
    @XmlAttribute(name = "otherAgeQualifyingCode")
    protected String otherAgeQualifyingCode;
    @XmlAttribute(name = "age")
    protected Integer age;
    @XmlAttribute(name = "count")
    protected Integer count;

    /**
     * Gets the value of the ageQualifyingCode property.
     * 
     * @return
     *     possible object is
     *     {@link AgeQualifyingCode }
     *     
     */
    public AgeQualifyingCode getAgeQualifyingCode() {
        return ageQualifyingCode;
    }

    /**
     * Sets the value of the ageQualifyingCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link AgeQualifyingCode }
     *     
     */
    public void setAgeQualifyingCode(AgeQualifyingCode value) {
        this.ageQualifyingCode = value;
    }

    /**
     * Gets the value of the otherAgeQualifyingCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOtherAgeQualifyingCode() {
        return otherAgeQualifyingCode;
    }

    /**
     * Sets the value of the otherAgeQualifyingCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOtherAgeQualifyingCode(String value) {
        this.otherAgeQualifyingCode = value;
    }

    /**
     * Gets the value of the age property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAge() {
        return age;
    }

    /**
     * Sets the value of the age property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAge(Integer value) {
        this.age = value;
    }

    /**
     * Gets the value of the count property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCount() {
        return count;
    }

    /**
     * Sets the value of the count property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCount(Integer value) {
        this.count = value;
    }

}
