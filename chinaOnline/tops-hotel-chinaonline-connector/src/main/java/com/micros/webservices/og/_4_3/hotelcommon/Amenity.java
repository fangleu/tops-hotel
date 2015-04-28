
package com.micros.webservices.og._4_3.hotelcommon;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Amenity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Amenity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="amenityDescription" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="amenityCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="amenityType" type="{http://webservices.micros.com/og/4.3/HotelCommon/}AmenityAmenityType" />
 *       &lt;attribute name="otherType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="availabilityFlag" type="{http://webservices.micros.com/og/4.3/HotelCommon/}AmenityAvailabilityFlag" />
 *       &lt;attribute name="otherFlag" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Amenity", propOrder = {
    "amenityDescription"
})
public class Amenity {

    protected List<String> amenityDescription;
    @XmlAttribute(name = "amenityCode")
    protected String amenityCode;
    @XmlAttribute(name = "amenityType")
    protected AmenityAmenityType amenityType;
    @XmlAttribute(name = "otherType")
    protected String otherType;
    @XmlAttribute(name = "availabilityFlag")
    protected AmenityAvailabilityFlag availabilityFlag;
    @XmlAttribute(name = "otherFlag")
    protected String otherFlag;

    /**
     * Gets the value of the amenityDescription property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the amenityDescription property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAmenityDescription().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getAmenityDescription() {
        if (amenityDescription == null) {
            amenityDescription = new ArrayList<String>();
        }
        return this.amenityDescription;
    }

    /**
     * Gets the value of the amenityCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAmenityCode() {
        return amenityCode;
    }

    /**
     * Sets the value of the amenityCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAmenityCode(String value) {
        this.amenityCode = value;
    }

    /**
     * Gets the value of the amenityType property.
     * 
     * @return
     *     possible object is
     *     {@link AmenityAmenityType }
     *     
     */
    public AmenityAmenityType getAmenityType() {
        return amenityType;
    }

    /**
     * Sets the value of the amenityType property.
     * 
     * @param value
     *     allowed object is
     *     {@link AmenityAmenityType }
     *     
     */
    public void setAmenityType(AmenityAmenityType value) {
        this.amenityType = value;
    }

    /**
     * Gets the value of the otherType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOtherType() {
        return otherType;
    }

    /**
     * Sets the value of the otherType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOtherType(String value) {
        this.otherType = value;
    }

    /**
     * Gets the value of the availabilityFlag property.
     * 
     * @return
     *     possible object is
     *     {@link AmenityAvailabilityFlag }
     *     
     */
    public AmenityAvailabilityFlag getAvailabilityFlag() {
        return availabilityFlag;
    }

    /**
     * Sets the value of the availabilityFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link AmenityAvailabilityFlag }
     *     
     */
    public void setAvailabilityFlag(AmenityAvailabilityFlag value) {
        this.availabilityFlag = value;
    }

    /**
     * Gets the value of the otherFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOtherFlag() {
        return otherFlag;
    }

    /**
     * Sets the value of the otherFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOtherFlag(String value) {
        this.otherFlag = value;
    }

}
