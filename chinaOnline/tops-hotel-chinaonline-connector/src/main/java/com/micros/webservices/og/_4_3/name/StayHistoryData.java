
package com.micros.webservices.og._4_3.name;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import com.micros.webservices.og._4_3.common.Amount;


/**
 * <p>Java class for StayHistoryData complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="StayHistoryData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="LastStay" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="TotalNumberOfStays" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" minOccurs="0"/>
 *         &lt;element name="LastRoomNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LastRate" type="{http://webservices.micros.com/og/4.3/Common/}Amount" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StayHistoryData", propOrder = {
    "lastStay",
    "totalNumberOfStays",
    "lastRoomNumber",
    "lastRate"
})
public class StayHistoryData {

    @XmlElement(name = "LastStay")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastStay;
    @XmlElement(name = "TotalNumberOfStays")
    @XmlSchemaType(name = "unsignedInt")
    protected Long totalNumberOfStays;
    @XmlElement(name = "LastRoomNumber")
    protected String lastRoomNumber;
    @XmlElement(name = "LastRate")
    protected Amount lastRate;

    /**
     * Gets the value of the lastStay property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastStay() {
        return lastStay;
    }

    /**
     * Sets the value of the lastStay property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastStay(XMLGregorianCalendar value) {
        this.lastStay = value;
    }

    /**
     * Gets the value of the totalNumberOfStays property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTotalNumberOfStays() {
        return totalNumberOfStays;
    }

    /**
     * Sets the value of the totalNumberOfStays property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTotalNumberOfStays(Long value) {
        this.totalNumberOfStays = value;
    }

    /**
     * Gets the value of the lastRoomNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastRoomNumber() {
        return lastRoomNumber;
    }

    /**
     * Sets the value of the lastRoomNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastRoomNumber(String value) {
        this.lastRoomNumber = value;
    }

    /**
     * Gets the value of the lastRate property.
     * 
     * @return
     *     possible object is
     *     {@link Amount }
     *     
     */
    public Amount getLastRate() {
        return lastRate;
    }

    /**
     * Sets the value of the lastRate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Amount }
     *     
     */
    public void setLastRate(Amount value) {
        this.lastRate = value;
    }

}
