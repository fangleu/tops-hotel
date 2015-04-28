
package com.micros.webservices.og._4_3.hotelcommon;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Vector complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Vector">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Direction" type="{http://webservices.micros.com/og/4.3/HotelCommon/}VectorDirection" minOccurs="0"/>
 *         &lt;element name="Distance" type="{http://webservices.micros.com/og/4.3/HotelCommon/}VectorDistance" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Vector", propOrder = {
    "direction",
    "distance"
})
public class Vector {

    @XmlElement(name = "Direction")
    protected VectorDirection direction;
    @XmlElement(name = "Distance")
    protected VectorDistance distance;

    /**
     * Gets the value of the direction property.
     * 
     * @return
     *     possible object is
     *     {@link VectorDirection }
     *     
     */
    public VectorDirection getDirection() {
        return direction;
    }

    /**
     * Sets the value of the direction property.
     * 
     * @param value
     *     allowed object is
     *     {@link VectorDirection }
     *     
     */
    public void setDirection(VectorDirection value) {
        this.direction = value;
    }

    /**
     * Gets the value of the distance property.
     * 
     * @return
     *     possible object is
     *     {@link VectorDistance }
     *     
     */
    public VectorDistance getDistance() {
        return distance;
    }

    /**
     * Sets the value of the distance property.
     * 
     * @param value
     *     allowed object is
     *     {@link VectorDistance }
     *     
     */
    public void setDistance(VectorDistance value) {
        this.distance = value;
    }

}
