
package com.micros.webservices.og._4_3.hotelcommon;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ExtendedHotelInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ExtendedHotelInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="HotelInformation" type="{http://webservices.micros.com/og/4.3/HotelCommon/}ArrayOfHotelInfo" minOccurs="0"/>
 *         &lt;element name="PaymentMethods" type="{http://webservices.micros.com/og/4.3/HotelCommon/}ArrayOfPaymentType1" minOccurs="0"/>
 *         &lt;element name="AmenityInfo" type="{http://webservices.micros.com/og/4.3/HotelCommon/}AmenityInfo" minOccurs="0"/>
 *         &lt;element name="Position" type="{http://webservices.micros.com/og/4.3/HotelCommon/}GeoCode" minOccurs="0"/>
 *         &lt;element name="FacilityInfo" type="{http://webservices.micros.com/og/4.3/HotelCommon/}FacilityInfoType" minOccurs="0"/>
 *         &lt;element name="AlternateProperties" type="{http://webservices.micros.com/og/4.3/HotelCommon/}ArrayOfHotelReference" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExtendedHotelInfo", propOrder = {
    "hotelInformation",
    "paymentMethods",
    "amenityInfo",
    "position",
    "facilityInfo",
    "alternateProperties"
})
public class ExtendedHotelInfo {

    @XmlElement(name = "HotelInformation")
    protected ArrayOfHotelInfo hotelInformation;
    @XmlElement(name = "PaymentMethods")
    protected ArrayOfPaymentType1 paymentMethods;
    @XmlElement(name = "AmenityInfo")
    protected AmenityInfo amenityInfo;
    @XmlElement(name = "Position")
    protected GeoCode position;
    @XmlElement(name = "FacilityInfo")
    protected FacilityInfoType facilityInfo;
    @XmlElement(name = "AlternateProperties")
    protected ArrayOfHotelReference alternateProperties;

    /**
     * Gets the value of the hotelInformation property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfHotelInfo }
     *     
     */
    public ArrayOfHotelInfo getHotelInformation() {
        return hotelInformation;
    }

    /**
     * Sets the value of the hotelInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfHotelInfo }
     *     
     */
    public void setHotelInformation(ArrayOfHotelInfo value) {
        this.hotelInformation = value;
    }

    /**
     * Gets the value of the paymentMethods property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfPaymentType1 }
     *     
     */
    public ArrayOfPaymentType1 getPaymentMethods() {
        return paymentMethods;
    }

    /**
     * Sets the value of the paymentMethods property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfPaymentType1 }
     *     
     */
    public void setPaymentMethods(ArrayOfPaymentType1 value) {
        this.paymentMethods = value;
    }

    /**
     * Gets the value of the amenityInfo property.
     * 
     * @return
     *     possible object is
     *     {@link AmenityInfo }
     *     
     */
    public AmenityInfo getAmenityInfo() {
        return amenityInfo;
    }

    /**
     * Sets the value of the amenityInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link AmenityInfo }
     *     
     */
    public void setAmenityInfo(AmenityInfo value) {
        this.amenityInfo = value;
    }

    /**
     * Gets the value of the position property.
     * 
     * @return
     *     possible object is
     *     {@link GeoCode }
     *     
     */
    public GeoCode getPosition() {
        return position;
    }

    /**
     * Sets the value of the position property.
     * 
     * @param value
     *     allowed object is
     *     {@link GeoCode }
     *     
     */
    public void setPosition(GeoCode value) {
        this.position = value;
    }

    /**
     * Gets the value of the facilityInfo property.
     * 
     * @return
     *     possible object is
     *     {@link FacilityInfoType }
     *     
     */
    public FacilityInfoType getFacilityInfo() {
        return facilityInfo;
    }

    /**
     * Sets the value of the facilityInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link FacilityInfoType }
     *     
     */
    public void setFacilityInfo(FacilityInfoType value) {
        this.facilityInfo = value;
    }

    /**
     * Gets the value of the alternateProperties property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfHotelReference }
     *     
     */
    public ArrayOfHotelReference getAlternateProperties() {
        return alternateProperties;
    }

    /**
     * Sets the value of the alternateProperties property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfHotelReference }
     *     
     */
    public void setAlternateProperties(ArrayOfHotelReference value) {
        this.alternateProperties = value;
    }

}
