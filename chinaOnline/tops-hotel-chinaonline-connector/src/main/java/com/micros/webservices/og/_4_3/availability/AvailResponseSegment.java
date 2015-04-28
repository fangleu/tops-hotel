
package com.micros.webservices.og._4_3.availability;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.micros.webservices.og._4_3.hotelcommon.ArrayOfRoomStay;


/**
 * <p>Java class for AvailResponseSegment complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AvailResponseSegment">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RoomStayList" type="{http://webservices.micros.com/og/4.3/HotelCommon/}ArrayOfRoomStay" minOccurs="0"/>
 *         &lt;element name="AdditionalInformation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="moreDataEchoToken" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AvailResponseSegment", propOrder = {
    "roomStayList",
    "additionalInformation"
})
public class AvailResponseSegment {

    @XmlElement(name = "RoomStayList")
    protected ArrayOfRoomStay roomStayList;
    @XmlElement(name = "AdditionalInformation")
    protected String additionalInformation;
    @XmlAttribute(name = "moreDataEchoToken")
    protected String moreDataEchoToken;

    /**
     * Gets the value of the roomStayList property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfRoomStay }
     *     
     */
    public ArrayOfRoomStay getRoomStayList() {
        return roomStayList;
    }

    /**
     * Sets the value of the roomStayList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfRoomStay }
     *     
     */
    public void setRoomStayList(ArrayOfRoomStay value) {
        this.roomStayList = value;
    }

    /**
     * Gets the value of the additionalInformation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdditionalInformation() {
        return additionalInformation;
    }

    /**
     * Sets the value of the additionalInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdditionalInformation(String value) {
        this.additionalInformation = value;
    }

    /**
     * Gets the value of the moreDataEchoToken property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMoreDataEchoToken() {
        return moreDataEchoToken;
    }

    /**
     * Sets the value of the moreDataEchoToken property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMoreDataEchoToken(String value) {
        this.moreDataEchoToken = value;
    }

}
