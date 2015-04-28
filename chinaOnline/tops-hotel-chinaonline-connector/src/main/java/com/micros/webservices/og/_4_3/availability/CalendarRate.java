
package com.micros.webservices.og._4_3.availability;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CalendarRate complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CalendarRate">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RestrictionList" type="{http://webservices.micros.com/og/4.3/Availability/}ArrayOfRestriction" minOccurs="0"/>
 *         &lt;element name="RateList" type="{http://webservices.micros.com/og/4.3/Availability/}ArrayOfRoomRate" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CalendarRate", propOrder = {
    "restrictionList",
    "rateList"
})
public class CalendarRate {

    @XmlElement(name = "RestrictionList")
    protected ArrayOfRestriction restrictionList;
    @XmlElement(name = "RateList")
    protected ArrayOfRoomRate rateList;

    /**
     * Gets the value of the restrictionList property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfRestriction }
     *     
     */
    public ArrayOfRestriction getRestrictionList() {
        return restrictionList;
    }

    /**
     * Sets the value of the restrictionList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfRestriction }
     *     
     */
    public void setRestrictionList(ArrayOfRestriction value) {
        this.restrictionList = value;
    }

    /**
     * Gets the value of the rateList property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfRoomRate }
     *     
     */
    public ArrayOfRoomRate getRateList() {
        return rateList;
    }

    /**
     * Sets the value of the rateList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfRoomRate }
     *     
     */
    public void setRateList(ArrayOfRoomRate value) {
        this.rateList = value;
    }

}
