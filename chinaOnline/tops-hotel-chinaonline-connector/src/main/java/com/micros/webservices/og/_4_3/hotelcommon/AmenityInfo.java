
package com.micros.webservices.og._4_3.hotelcommon;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AmenityInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AmenityInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Amenities" type="{http://webservices.micros.com/og/4.3/HotelCommon/}ArrayOfAmenity" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AmenityInfo", propOrder = {
    "amenities"
})
public class AmenityInfo {

    @XmlElement(name = "Amenities")
    protected ArrayOfAmenity amenities;

    /**
     * Gets the value of the amenities property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfAmenity }
     *     
     */
    public ArrayOfAmenity getAmenities() {
        return amenities;
    }

    /**
     * Sets the value of the amenities property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfAmenity }
     *     
     */
    public void setAmenities(ArrayOfAmenity value) {
        this.amenities = value;
    }

}
