
package com.micros.webservices.og._4_3.availability;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import com.micros.webservices.og._4_3.hotelcommon.ArrayOfRoomTypeInventory;


/**
 * <p>Java class for CalendarDailyDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CalendarDailyDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Rates" type="{http://webservices.micros.com/og/4.3/Availability/}CalendarRate" minOccurs="0"/>
 *         &lt;element name="Occupancy" type="{http://webservices.micros.com/og/4.3/HotelCommon/}ArrayOfRoomTypeInventory" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Date" type="{http://www.w3.org/2001/XMLSchema}date" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CalendarDailyDetail", propOrder = {
    "rates",
    "occupancy"
})
public class CalendarDailyDetail {

    @XmlElement(name = "Rates")
    protected CalendarRate rates;
    @XmlElement(name = "Occupancy")
    protected ArrayOfRoomTypeInventory occupancy;
    @XmlAttribute(name = "Date")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar date;

    /**
     * Gets the value of the rates property.
     * 
     * @return
     *     possible object is
     *     {@link CalendarRate }
     *     
     */
    public CalendarRate getRates() {
        return rates;
    }

    /**
     * Sets the value of the rates property.
     * 
     * @param value
     *     allowed object is
     *     {@link CalendarRate }
     *     
     */
    public void setRates(CalendarRate value) {
        this.rates = value;
    }

    /**
     * Gets the value of the occupancy property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfRoomTypeInventory }
     *     
     */
    public ArrayOfRoomTypeInventory getOccupancy() {
        return occupancy;
    }

    /**
     * Sets the value of the occupancy property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfRoomTypeInventory }
     *     
     */
    public void setOccupancy(ArrayOfRoomTypeInventory value) {
        this.occupancy = value;
    }

    /**
     * Gets the value of the date property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDate() {
        return date;
    }

    /**
     * Sets the value of the date property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDate(XMLGregorianCalendar value) {
        this.date = value;
    }

}
