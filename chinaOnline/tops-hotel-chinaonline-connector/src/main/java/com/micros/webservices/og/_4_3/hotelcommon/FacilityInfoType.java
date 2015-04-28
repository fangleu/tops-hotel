
package com.micros.webservices.og._4_3.hotelcommon;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for FacilityInfoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FacilityInfoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GuestRooms" type="{http://webservices.micros.com/og/4.3/HotelCommon/}FacilityInfoTypeGuestRooms" minOccurs="0"/>
 *         &lt;element name="Restaurants" type="{http://webservices.micros.com/og/4.3/HotelCommon/}ArrayOfRestaurantsTypeRestaurant" minOccurs="0"/>
 *         &lt;element name="MeetingRooms" type="{http://webservices.micros.com/og/4.3/HotelCommon/}MeetingRoomsType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="dateOpened" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="dateRennovated" type="{http://www.w3.org/2001/XMLSchema}date" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FacilityInfoType", propOrder = {
    "guestRooms",
    "restaurants",
    "meetingRooms"
})
public class FacilityInfoType {

    @XmlElement(name = "GuestRooms")
    protected FacilityInfoTypeGuestRooms guestRooms;
    @XmlElement(name = "Restaurants")
    protected ArrayOfRestaurantsTypeRestaurant restaurants;
    @XmlElement(name = "MeetingRooms")
    protected MeetingRoomsType meetingRooms;
    @XmlAttribute(name = "dateOpened")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dateOpened;
    @XmlAttribute(name = "dateRennovated")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dateRennovated;

    /**
     * Gets the value of the guestRooms property.
     * 
     * @return
     *     possible object is
     *     {@link FacilityInfoTypeGuestRooms }
     *     
     */
    public FacilityInfoTypeGuestRooms getGuestRooms() {
        return guestRooms;
    }

    /**
     * Sets the value of the guestRooms property.
     * 
     * @param value
     *     allowed object is
     *     {@link FacilityInfoTypeGuestRooms }
     *     
     */
    public void setGuestRooms(FacilityInfoTypeGuestRooms value) {
        this.guestRooms = value;
    }

    /**
     * Gets the value of the restaurants property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfRestaurantsTypeRestaurant }
     *     
     */
    public ArrayOfRestaurantsTypeRestaurant getRestaurants() {
        return restaurants;
    }

    /**
     * Sets the value of the restaurants property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfRestaurantsTypeRestaurant }
     *     
     */
    public void setRestaurants(ArrayOfRestaurantsTypeRestaurant value) {
        this.restaurants = value;
    }

    /**
     * Gets the value of the meetingRooms property.
     * 
     * @return
     *     possible object is
     *     {@link MeetingRoomsType }
     *     
     */
    public MeetingRoomsType getMeetingRooms() {
        return meetingRooms;
    }

    /**
     * Sets the value of the meetingRooms property.
     * 
     * @param value
     *     allowed object is
     *     {@link MeetingRoomsType }
     *     
     */
    public void setMeetingRooms(MeetingRoomsType value) {
        this.meetingRooms = value;
    }

    /**
     * Gets the value of the dateOpened property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateOpened() {
        return dateOpened;
    }

    /**
     * Sets the value of the dateOpened property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateOpened(XMLGregorianCalendar value) {
        this.dateOpened = value;
    }

    /**
     * Gets the value of the dateRennovated property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateRennovated() {
        return dateRennovated;
    }

    /**
     * Sets the value of the dateRennovated property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateRennovated(XMLGregorianCalendar value) {
        this.dateRennovated = value;
    }

}
