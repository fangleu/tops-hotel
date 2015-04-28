
package com.micros.webservices.ows._5_1.reservation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.micros.webservices.og._4_3.hotelcommon.GDSResultStatus;
import com.micros.webservices.og._4_3.reservation.HotelReservation;


/**
 * <p>Java class for ModifyBookingResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ModifyBookingResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Result" type="{http://webservices.micros.com/og/4.3/HotelCommon/}GDSResultStatus" minOccurs="0"/>
 *         &lt;element name="HotelReservation" type="{http://webservices.micros.com/og/4.3/Reservation/}HotelReservation" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ModifyBookingResponse", propOrder = {
    "result",
    "hotelReservation"
})
public class ModifyBookingResponse {

    @XmlElement(name = "Result")
    protected GDSResultStatus result;
    @XmlElement(name = "HotelReservation")
    protected HotelReservation hotelReservation;

    /**
     * Gets the value of the result property.
     * 
     * @return
     *     possible object is
     *     {@link GDSResultStatus }
     *     
     */
    public GDSResultStatus getResult() {
        return result;
    }

    /**
     * Sets the value of the result property.
     * 
     * @param value
     *     allowed object is
     *     {@link GDSResultStatus }
     *     
     */
    public void setResult(GDSResultStatus value) {
        this.result = value;
    }

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

}
