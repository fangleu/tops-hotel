
package com.micros.webservices.og._4_3.reservation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import com.micros.webservices.og._4_3.hotelcommon.AgeQualifyingCode;
import com.micros.webservices.og._4_3.hotelcommon.ArrayOfReservationComment;
import com.micros.webservices.og._4_3.hotelcommon.ArrayOfSpecialRequest;
import com.micros.webservices.og._4_3.hotelcommon.GuestCountList;
import com.micros.webservices.og._4_3.hotelcommon.TimeSpan;
import com.micros.webservices.og._4_3.hotelcommon.TransportInfo;
import com.micros.webservices.og._4_3.name.ArrayOfProfile;


/**
 * <p>Java class for ResGuest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResGuest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Profiles" type="{http://webservices.micros.com/og/4.3/Name/}ArrayOfProfile" minOccurs="0"/>
 *         &lt;element name="SpecialRequests" type="{http://webservices.micros.com/og/4.3/HotelCommon/}ArrayOfSpecialRequest" minOccurs="0"/>
 *         &lt;element name="Comments" type="{http://webservices.micros.com/og/4.3/HotelCommon/}ArrayOfReservationComment" minOccurs="0"/>
 *         &lt;element name="ArrivalTransport" type="{http://webservices.micros.com/og/4.3/HotelCommon/}TransportInfo" minOccurs="0"/>
 *         &lt;element name="DepartureTransport" type="{http://webservices.micros.com/og/4.3/HotelCommon/}TransportInfo" minOccurs="0"/>
 *         &lt;element name="GuestCounts" type="{http://webservices.micros.com/og/4.3/HotelCommon/}GuestCountList" minOccurs="0"/>
 *         &lt;element name="InHouseTimeSpan" type="{http://webservices.micros.com/og/4.3/HotelCommon/}TimeSpan" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="resGuestRPH" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="ageQualifyingCode" type="{http://webservices.micros.com/og/4.3/HotelCommon/}AgeQualifyingCode" />
 *       &lt;attribute name="otherAgeQualifyingCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="arrivalTime" type="{http://www.w3.org/2001/XMLSchema}time" />
 *       &lt;attribute name="departureTime" type="{http://www.w3.org/2001/XMLSchema}time" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResGuest", propOrder = {
    "profiles",
    "specialRequests",
    "comments",
    "arrivalTransport",
    "departureTransport",
    "guestCounts",
    "inHouseTimeSpan"
})
public class ResGuest {

    @XmlElement(name = "Profiles")
    protected ArrayOfProfile profiles;
    @XmlElement(name = "SpecialRequests")
    protected ArrayOfSpecialRequest specialRequests;
    @XmlElement(name = "Comments")
    protected ArrayOfReservationComment comments;
    @XmlElement(name = "ArrivalTransport")
    protected TransportInfo arrivalTransport;
    @XmlElement(name = "DepartureTransport")
    protected TransportInfo departureTransport;
    @XmlElement(name = "GuestCounts")
    protected GuestCountList guestCounts;
    @XmlElement(name = "InHouseTimeSpan")
    protected TimeSpan inHouseTimeSpan;
    @XmlAttribute(name = "resGuestRPH", required = true)
    protected int resGuestRPH;
    @XmlAttribute(name = "ageQualifyingCode")
    protected AgeQualifyingCode ageQualifyingCode;
    @XmlAttribute(name = "otherAgeQualifyingCode")
    protected String otherAgeQualifyingCode;
    @XmlAttribute(name = "arrivalTime")
    @XmlSchemaType(name = "time")
    protected XMLGregorianCalendar arrivalTime;
    @XmlAttribute(name = "departureTime")
    @XmlSchemaType(name = "time")
    protected XMLGregorianCalendar departureTime;

    /**
     * Gets the value of the profiles property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfProfile }
     *     
     */
    public ArrayOfProfile getProfiles() {
        return profiles;
    }

    /**
     * Sets the value of the profiles property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfProfile }
     *     
     */
    public void setProfiles(ArrayOfProfile value) {
        this.profiles = value;
    }

    /**
     * Gets the value of the specialRequests property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfSpecialRequest }
     *     
     */
    public ArrayOfSpecialRequest getSpecialRequests() {
        return specialRequests;
    }

    /**
     * Sets the value of the specialRequests property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfSpecialRequest }
     *     
     */
    public void setSpecialRequests(ArrayOfSpecialRequest value) {
        this.specialRequests = value;
    }

    /**
     * Gets the value of the comments property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfReservationComment }
     *     
     */
    public ArrayOfReservationComment getComments() {
        return comments;
    }

    /**
     * Sets the value of the comments property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfReservationComment }
     *     
     */
    public void setComments(ArrayOfReservationComment value) {
        this.comments = value;
    }

    /**
     * Gets the value of the arrivalTransport property.
     * 
     * @return
     *     possible object is
     *     {@link TransportInfo }
     *     
     */
    public TransportInfo getArrivalTransport() {
        return arrivalTransport;
    }

    /**
     * Sets the value of the arrivalTransport property.
     * 
     * @param value
     *     allowed object is
     *     {@link TransportInfo }
     *     
     */
    public void setArrivalTransport(TransportInfo value) {
        this.arrivalTransport = value;
    }

    /**
     * Gets the value of the departureTransport property.
     * 
     * @return
     *     possible object is
     *     {@link TransportInfo }
     *     
     */
    public TransportInfo getDepartureTransport() {
        return departureTransport;
    }

    /**
     * Sets the value of the departureTransport property.
     * 
     * @param value
     *     allowed object is
     *     {@link TransportInfo }
     *     
     */
    public void setDepartureTransport(TransportInfo value) {
        this.departureTransport = value;
    }

    /**
     * Gets the value of the guestCounts property.
     * 
     * @return
     *     possible object is
     *     {@link GuestCountList }
     *     
     */
    public GuestCountList getGuestCounts() {
        return guestCounts;
    }

    /**
     * Sets the value of the guestCounts property.
     * 
     * @param value
     *     allowed object is
     *     {@link GuestCountList }
     *     
     */
    public void setGuestCounts(GuestCountList value) {
        this.guestCounts = value;
    }

    /**
     * Gets the value of the inHouseTimeSpan property.
     * 
     * @return
     *     possible object is
     *     {@link TimeSpan }
     *     
     */
    public TimeSpan getInHouseTimeSpan() {
        return inHouseTimeSpan;
    }

    /**
     * Sets the value of the inHouseTimeSpan property.
     * 
     * @param value
     *     allowed object is
     *     {@link TimeSpan }
     *     
     */
    public void setInHouseTimeSpan(TimeSpan value) {
        this.inHouseTimeSpan = value;
    }

    /**
     * Gets the value of the resGuestRPH property.
     * 
     */
    public int getResGuestRPH() {
        return resGuestRPH;
    }

    /**
     * Sets the value of the resGuestRPH property.
     * 
     */
    public void setResGuestRPH(int value) {
        this.resGuestRPH = value;
    }

    /**
     * Gets the value of the ageQualifyingCode property.
     * 
     * @return
     *     possible object is
     *     {@link AgeQualifyingCode }
     *     
     */
    public AgeQualifyingCode getAgeQualifyingCode() {
        return ageQualifyingCode;
    }

    /**
     * Sets the value of the ageQualifyingCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link AgeQualifyingCode }
     *     
     */
    public void setAgeQualifyingCode(AgeQualifyingCode value) {
        this.ageQualifyingCode = value;
    }

    /**
     * Gets the value of the otherAgeQualifyingCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOtherAgeQualifyingCode() {
        return otherAgeQualifyingCode;
    }

    /**
     * Sets the value of the otherAgeQualifyingCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOtherAgeQualifyingCode(String value) {
        this.otherAgeQualifyingCode = value;
    }

    /**
     * Gets the value of the arrivalTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getArrivalTime() {
        return arrivalTime;
    }

    /**
     * Sets the value of the arrivalTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setArrivalTime(XMLGregorianCalendar value) {
        this.arrivalTime = value;
    }

    /**
     * Gets the value of the departureTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDepartureTime() {
        return departureTime;
    }

    /**
     * Sets the value of the departureTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDepartureTime(XMLGregorianCalendar value) {
        this.departureTime = value;
    }

}
