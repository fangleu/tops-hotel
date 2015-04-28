
package com.micros.webservices.og._4_3.availability;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ChildAge complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ChildAge">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="age" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="ageSpecified" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ChildAge")
public class ChildAge {

    @XmlAttribute(name = "age")
    protected Integer age;
    @XmlAttribute(name = "ageSpecified", required = true)
    protected boolean ageSpecified;

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
     * Gets the value of the ageSpecified property.
     * 
     */
    public boolean isAgeSpecified() {
        return ageSpecified;
    }

    /**
     * Sets the value of the ageSpecified property.
     * 
     */
    public void setAgeSpecified(boolean value) {
        this.ageSpecified = value;
    }

}
