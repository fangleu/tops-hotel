
package com.micros.webservices.og._4_3.hotelcommon;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for ChargesForTheDay complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ChargesForTheDay">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RoomRateAndPackages" type="{http://webservices.micros.com/og/4.3/HotelCommon/}ChargeList" minOccurs="0"/>
 *         &lt;element name="TaxesAndFees" type="{http://webservices.micros.com/og/4.3/HotelCommon/}ChargeList" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="PostingDate" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="TotalCharges" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="breakfastType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ChargesForTheDay", propOrder = {
    "roomRateAndPackages",
    "taxesAndFees"
})
public class ChargesForTheDay {

    @XmlElement(name = "RoomRateAndPackages")
    protected ChargeList roomRateAndPackages;
    @XmlElement(name = "TaxesAndFees")
    protected ChargeList taxesAndFees;
    @XmlAttribute(name = "PostingDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar postingDate;
    @XmlAttribute(name = "TotalCharges")
    protected Double totalCharges;
    @XmlAttribute(name = "breakfastType")
    protected String breakfastType;

    /**
     * Gets the value of the roomRateAndPackages property.
     * 
     * @return
     *     possible object is
     *     {@link ChargeList }
     *     
     */
    public ChargeList getRoomRateAndPackages() {
        return roomRateAndPackages;
    }

    /**
     * Sets the value of the roomRateAndPackages property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChargeList }
     *     
     */
    public void setRoomRateAndPackages(ChargeList value) {
        this.roomRateAndPackages = value;
    }

    /**
     * Gets the value of the taxesAndFees property.
     * 
     * @return
     *     possible object is
     *     {@link ChargeList }
     *     
     */
    public ChargeList getTaxesAndFees() {
        return taxesAndFees;
    }

    /**
     * Sets the value of the taxesAndFees property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChargeList }
     *     
     */
    public void setTaxesAndFees(ChargeList value) {
        this.taxesAndFees = value;
    }

    /**
     * Gets the value of the postingDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPostingDate() {
        return postingDate;
    }

    /**
     * Sets the value of the postingDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPostingDate(XMLGregorianCalendar value) {
        this.postingDate = value;
    }

    /**
     * Gets the value of the totalCharges property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getTotalCharges() {
        return totalCharges;
    }

    /**
     * Sets the value of the totalCharges property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setTotalCharges(Double value) {
        this.totalCharges = value;
    }

    /**
     * Gets the value of the breakfastType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBreakfastType() {
        return breakfastType;
    }

    /**
     * Sets the value of the breakfastType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBreakfastType(String value) {
        this.breakfastType = value;
    }

}
