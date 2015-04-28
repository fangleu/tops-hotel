
package com.micros.webservices.og._4_3.availability;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.micros.webservices.og._4_3.hotelcommon.CityReference;
import com.micros.webservices.og._4_3.hotelcommon.HotelReference;


/**
 * <p>Java class for CitySearchCriterion complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CitySearchCriterion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="HotelRef" type="{http://webservices.micros.com/og/4.3/HotelCommon/}HotelReference" minOccurs="0"/>
 *         &lt;element name="CityRef" type="{http://webservices.micros.com/og/4.3/HotelCommon/}CityReference" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CitySearchCriterion", propOrder = {
    "hotelRef",
    "cityRef"
})
public class CitySearchCriterion {

    @XmlElement(name = "HotelRef")
    protected HotelReference hotelRef;
    @XmlElement(name = "CityRef")
    protected CityReference cityRef;

    /**
     * Gets the value of the hotelRef property.
     * 
     * @return
     *     possible object is
     *     {@link HotelReference }
     *     
     */
    public HotelReference getHotelRef() {
        return hotelRef;
    }

    /**
     * Sets the value of the hotelRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link HotelReference }
     *     
     */
    public void setHotelRef(HotelReference value) {
        this.hotelRef = value;
    }

    /**
     * Gets the value of the cityRef property.
     * 
     * @return
     *     possible object is
     *     {@link CityReference }
     *     
     */
    public CityReference getCityRef() {
        return cityRef;
    }

    /**
     * Sets the value of the cityRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link CityReference }
     *     
     */
    public void setCityRef(CityReference value) {
        this.cityRef = value;
    }

}
