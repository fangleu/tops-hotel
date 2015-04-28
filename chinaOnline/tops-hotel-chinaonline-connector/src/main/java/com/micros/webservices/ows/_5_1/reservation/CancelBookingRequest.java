
package com.micros.webservices.ows._5_1.reservation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.micros.webservices.og._4_3.common.UniqueID;
import com.micros.webservices.og._4_3.hotelcommon.CancelTerm;
import com.micros.webservices.og._4_3.hotelcommon.HotelReference;
import com.micros.webservices.og._4_3.hotelcommon.TimeSpan;


/**
 * <p>Java class for CancelBookingRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CancelBookingRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CancelTerm" type="{http://webservices.micros.com/og/4.3/HotelCommon/}CancelTerm" minOccurs="0"/>
 *         &lt;element name="ConfirmationNumber" type="{http://webservices.micros.com/og/4.3/Common/}UniqueID" minOccurs="0"/>
 *         &lt;element name="GDSID" type="{http://webservices.micros.com/og/4.3/Common/}UniqueID" minOccurs="0"/>
 *         &lt;element name="HotelReference" type="{http://webservices.micros.com/og/4.3/HotelCommon/}HotelReference" minOccurs="0"/>
 *         &lt;element name="LegNumber" type="{http://webservices.micros.com/og/4.3/Common/}UniqueID" minOccurs="0"/>
 *         &lt;element name="LastName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FirstName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RatePlanCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ReservationTimeSpan" type="{http://webservices.micros.com/og/4.3/HotelCommon/}TimeSpan" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CancelBookingRequest", propOrder = {
    "cancelTerm",
    "confirmationNumber",
    "gdsid",
    "hotelReference",
    "legNumber",
    "lastName",
    "firstName",
    "ratePlanCode",
    "reservationTimeSpan"
})
public class CancelBookingRequest {

    @XmlElement(name = "CancelTerm")
    protected CancelTerm cancelTerm;
    @XmlElement(name = "ConfirmationNumber")
    protected UniqueID confirmationNumber;
    @XmlElement(name = "GDSID")
    protected UniqueID gdsid;
    @XmlElement(name = "HotelReference")
    protected HotelReference hotelReference;
    @XmlElement(name = "LegNumber")
    protected UniqueID legNumber;
    @XmlElement(name = "LastName")
    protected String lastName;
    @XmlElement(name = "FirstName")
    protected String firstName;
    @XmlElement(name = "RatePlanCode")
    protected String ratePlanCode;
    @XmlElement(name = "ReservationTimeSpan")
    protected TimeSpan reservationTimeSpan;

    /**
     * Gets the value of the cancelTerm property.
     * 
     * @return
     *     possible object is
     *     {@link CancelTerm }
     *     
     */
    public CancelTerm getCancelTerm() {
        return cancelTerm;
    }

    /**
     * Sets the value of the cancelTerm property.
     * 
     * @param value
     *     allowed object is
     *     {@link CancelTerm }
     *     
     */
    public void setCancelTerm(CancelTerm value) {
        this.cancelTerm = value;
    }

    /**
     * Gets the value of the confirmationNumber property.
     * 
     * @return
     *     possible object is
     *     {@link UniqueID }
     *     
     */
    public UniqueID getConfirmationNumber() {
        return confirmationNumber;
    }

    /**
     * Sets the value of the confirmationNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link UniqueID }
     *     
     */
    public void setConfirmationNumber(UniqueID value) {
        this.confirmationNumber = value;
    }

    /**
     * Gets the value of the gdsid property.
     * 
     * @return
     *     possible object is
     *     {@link UniqueID }
     *     
     */
    public UniqueID getGDSID() {
        return gdsid;
    }

    /**
     * Sets the value of the gdsid property.
     * 
     * @param value
     *     allowed object is
     *     {@link UniqueID }
     *     
     */
    public void setGDSID(UniqueID value) {
        this.gdsid = value;
    }

    /**
     * Gets the value of the hotelReference property.
     * 
     * @return
     *     possible object is
     *     {@link HotelReference }
     *     
     */
    public HotelReference getHotelReference() {
        return hotelReference;
    }

    /**
     * Sets the value of the hotelReference property.
     * 
     * @param value
     *     allowed object is
     *     {@link HotelReference }
     *     
     */
    public void setHotelReference(HotelReference value) {
        this.hotelReference = value;
    }

    /**
     * Gets the value of the legNumber property.
     * 
     * @return
     *     possible object is
     *     {@link UniqueID }
     *     
     */
    public UniqueID getLegNumber() {
        return legNumber;
    }

    /**
     * Sets the value of the legNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link UniqueID }
     *     
     */
    public void setLegNumber(UniqueID value) {
        this.legNumber = value;
    }

    /**
     * Gets the value of the lastName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the value of the lastName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastName(String value) {
        this.lastName = value;
    }

    /**
     * Gets the value of the firstName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the value of the firstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstName(String value) {
        this.firstName = value;
    }

    /**
     * Gets the value of the ratePlanCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRatePlanCode() {
        return ratePlanCode;
    }

    /**
     * Sets the value of the ratePlanCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRatePlanCode(String value) {
        this.ratePlanCode = value;
    }

    /**
     * Gets the value of the reservationTimeSpan property.
     * 
     * @return
     *     possible object is
     *     {@link TimeSpan }
     *     
     */
    public TimeSpan getReservationTimeSpan() {
        return reservationTimeSpan;
    }

    /**
     * Sets the value of the reservationTimeSpan property.
     * 
     * @param value
     *     allowed object is
     *     {@link TimeSpan }
     *     
     */
    public void setReservationTimeSpan(TimeSpan value) {
        this.reservationTimeSpan = value;
    }

}
