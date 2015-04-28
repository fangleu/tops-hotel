
package com.micros.webservices.og._4_3.hotelcommon;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RoomTypeInventory complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RoomTypeInventory">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RoomTypeRestriction" type="{http://webservices.micros.com/og/4.3/HotelCommon/}Restriction" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="roomTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="totalRooms" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="overBookingLimit" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="soldDeductible" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="soldNonDeductible" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="totalAvailableRooms" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="isWarning" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RoomTypeInventory", propOrder = {
    "roomTypeRestriction"
})
public class RoomTypeInventory {

    @XmlElement(name = "RoomTypeRestriction")
    protected List<Restriction> roomTypeRestriction;
    @XmlAttribute(name = "roomTypeCode")
    protected String roomTypeCode;
    @XmlAttribute(name = "totalRooms")
    protected BigInteger totalRooms;
    @XmlAttribute(name = "overBookingLimit")
    protected BigInteger overBookingLimit;
    @XmlAttribute(name = "soldDeductible")
    protected BigInteger soldDeductible;
    @XmlAttribute(name = "soldNonDeductible")
    protected BigInteger soldNonDeductible;
    @XmlAttribute(name = "totalAvailableRooms")
    protected BigInteger totalAvailableRooms;
    @XmlAttribute(name = "isWarning", required = true)
    protected boolean isWarning;

    /**
     * Gets the value of the roomTypeRestriction property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the roomTypeRestriction property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRoomTypeRestriction().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Restriction }
     * 
     * 
     */
    public List<Restriction> getRoomTypeRestriction() {
        if (roomTypeRestriction == null) {
            roomTypeRestriction = new ArrayList<Restriction>();
        }
        return this.roomTypeRestriction;
    }

    /**
     * Gets the value of the roomTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoomTypeCode() {
        return roomTypeCode;
    }

    /**
     * Sets the value of the roomTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoomTypeCode(String value) {
        this.roomTypeCode = value;
    }

    /**
     * Gets the value of the totalRooms property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTotalRooms() {
        return totalRooms;
    }

    /**
     * Sets the value of the totalRooms property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTotalRooms(BigInteger value) {
        this.totalRooms = value;
    }

    /**
     * Gets the value of the overBookingLimit property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getOverBookingLimit() {
        return overBookingLimit;
    }

    /**
     * Sets the value of the overBookingLimit property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setOverBookingLimit(BigInteger value) {
        this.overBookingLimit = value;
    }

    /**
     * Gets the value of the soldDeductible property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getSoldDeductible() {
        return soldDeductible;
    }

    /**
     * Sets the value of the soldDeductible property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setSoldDeductible(BigInteger value) {
        this.soldDeductible = value;
    }

    /**
     * Gets the value of the soldNonDeductible property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getSoldNonDeductible() {
        return soldNonDeductible;
    }

    /**
     * Sets the value of the soldNonDeductible property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setSoldNonDeductible(BigInteger value) {
        this.soldNonDeductible = value;
    }

    /**
     * Gets the value of the totalAvailableRooms property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTotalAvailableRooms() {
        return totalAvailableRooms;
    }

    /**
     * Sets the value of the totalAvailableRooms property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTotalAvailableRooms(BigInteger value) {
        this.totalAvailableRooms = value;
    }

    /**
     * Gets the value of the isWarning property.
     * 
     */
    public boolean isIsWarning() {
        return isWarning;
    }

    /**
     * Sets the value of the isWarning property.
     * 
     */
    public void setIsWarning(boolean value) {
        this.isWarning = value;
    }

}
