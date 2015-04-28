
package com.micros.webservices.ows._5_1.reservation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import com.micros.webservices.og._4_3.reservation.ExternalReference;
import com.micros.webservices.og._4_3.reservation.HotelReservation;


/**
 * <p>Java class for CreateBookingRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CreateBookingRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="HotelReservation" type="{http://webservices.micros.com/og/4.3/Reservation/}HotelReservation" minOccurs="0"/>
 *         &lt;element name="ExternalSystemNumber" type="{http://webservices.micros.com/og/4.3/Reservation/}ExternalReference" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CreateBookingRequest", propOrder = {
    "hotelReservation",
    "externalSystemNumber"
})
@XmlSeeAlso({
    cn.net.chinaonline.webservices._switch._1_5_1.reservation.CreateBookingRequest.class
})
public class CreateBookingRequest {

    @XmlElement(name = "HotelReservation")
    protected HotelReservation hotelReservation;
    @XmlElement(name = "ExternalSystemNumber")
    protected ExternalReference externalSystemNumber;

    /**
     * Gets the value of the hotelReservation property.
     * 
     * @return
     *     possible object is
     *     {@link HotelReservation }
     *     
     */
    public HotelReservation getHotelReservation() {
        return hotelReservation;
    }

    /**
     * Sets the value of the hotelReservation property.
     * 
     * @param value
     *     allowed object is
     *     {@link HotelReservation }
     *     
     */
    public void setHotelReservation(HotelReservation value) {
        this.hotelReservation = value;
    }

    /**
     * Gets the value of the externalSystemNumber property.
     * 
     * @return
     *     possible object is
     *     {@link ExternalReference }
     *     
     */
    public ExternalReference getExternalSystemNumber() {
        return externalSystemNumber;
    }

    /**
     * Sets the value of the externalSystemNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link ExternalReference }
     *     
     */
    public void setExternalSystemNumber(ExternalReference value) {
        this.externalSystemNumber = value;
    }

}
