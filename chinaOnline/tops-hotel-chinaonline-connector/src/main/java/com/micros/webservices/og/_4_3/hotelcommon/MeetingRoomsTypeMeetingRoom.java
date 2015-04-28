
package com.micros.webservices.og._4_3.hotelcommon;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MeetingRoomsTypeMeetingRoom complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MeetingRoomsTypeMeetingRoom">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Codes" type="{http://webservices.micros.com/og/4.3/HotelCommon/}ArrayOfMeetingRoomsTypeMeetingRoomCode" minOccurs="0"/>
 *         &lt;element name="Description" type="{http://webservices.micros.com/og/4.3/HotelCommon/}Paragraph" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Irregular" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="PropertySystemName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="RoomName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Sort" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *       &lt;attribute name="MeetingRoomCapacity" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MeetingRoomsTypeMeetingRoom", propOrder = {
    "codes",
    "description"
})
public class MeetingRoomsTypeMeetingRoom {

    @XmlElement(name = "Codes")
    protected ArrayOfMeetingRoomsTypeMeetingRoomCode codes;
    @XmlElement(name = "Description")
    protected Paragraph description;
    @XmlAttribute(name = "Irregular")
    protected Boolean irregular;
    @XmlAttribute(name = "PropertySystemName")
    protected String propertySystemName;
    @XmlAttribute(name = "RoomName")
    protected String roomName;
    @XmlAttribute(name = "Sort")
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger sort;
    @XmlAttribute(name = "MeetingRoomCapacity")
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger meetingRoomCapacity;

    /**
     * Gets the value of the codes property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfMeetingRoomsTypeMeetingRoomCode }
     *     
     */
    public ArrayOfMeetingRoomsTypeMeetingRoomCode getCodes() {
        return codes;
    }

    /**
     * Sets the value of the codes property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfMeetingRoomsTypeMeetingRoomCode }
     *     
     */
    public void setCodes(ArrayOfMeetingRoomsTypeMeetingRoomCode value) {
        this.codes = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link Paragraph }
     *     
     */
    public Paragraph getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link Paragraph }
     *     
     */
    public void setDescription(Paragraph value) {
        this.description = value;
    }

    /**
     * Gets the value of the irregular property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIrregular() {
        return irregular;
    }

    /**
     * Sets the value of the irregular property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIrregular(Boolean value) {
        this.irregular = value;
    }

    /**
     * Gets the value of the propertySystemName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPropertySystemName() {
        return propertySystemName;
    }

    /**
     * Sets the value of the propertySystemName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPropertySystemName(String value) {
        this.propertySystemName = value;
    }

    /**
     * Gets the value of the roomName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoomName() {
        return roomName;
    }

    /**
     * Sets the value of the roomName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoomName(String value) {
        this.roomName = value;
    }

    /**
     * Gets the value of the sort property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getSort() {
        return sort;
    }

    /**
     * Sets the value of the sort property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setSort(BigInteger value) {
        this.sort = value;
    }

    /**
     * Gets the value of the meetingRoomCapacity property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMeetingRoomCapacity() {
        return meetingRoomCapacity;
    }

    /**
     * Sets the value of the meetingRoomCapacity property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMeetingRoomCapacity(BigInteger value) {
        this.meetingRoomCapacity = value;
    }

}
