
package com.micros.webservices.og._4_3.hotelcommon;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FacilityInfoTypeGuestRooms complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FacilityInfoTypeGuestRooms">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GuestRoom" type="{http://webservices.micros.com/og/4.3/HotelCommon/}FacilityInfoTypeGuestRoomsGuestRoom" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="totalRooms" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FacilityInfoTypeGuestRooms", propOrder = {
    "guestRoom"
})
public class FacilityInfoTypeGuestRooms {

    @XmlElement(name = "GuestRoom")
    protected List<FacilityInfoTypeGuestRoomsGuestRoom> guestRoom;
    @XmlAttribute(name = "totalRooms")
    protected String totalRooms;

    /**
     * Gets the value of the guestRoom property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the guestRoom property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGuestRoom().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FacilityInfoTypeGuestRoomsGuestRoom }
     * 
     * 
     */
    public List<FacilityInfoTypeGuestRoomsGuestRoom> getGuestRoom() {
        if (guestRoom == null) {
            guestRoom = new ArrayList<FacilityInfoTypeGuestRoomsGuestRoom>();
        }
        return this.guestRoom;
    }

    /**
     * Gets the value of the totalRooms property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalRooms() {
        return totalRooms;
    }

    /**
     * Sets the value of the totalRooms property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalRooms(String value) {
        this.totalRooms = value;
    }

}
