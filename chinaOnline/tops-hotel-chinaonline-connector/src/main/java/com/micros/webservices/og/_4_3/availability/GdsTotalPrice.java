
package com.micros.webservices.og._4_3.availability;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.micros.webservices.og._4_3.hotelcommon.RateCalculation;


/**
 * <p>Java class for GdsTotalPrice complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GdsTotalPrice">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RateCalculation" type="{http://webservices.micros.com/og/4.3/HotelCommon/}RateCalculation" minOccurs="0"/>
 *         &lt;element name="AdditionalBedAmounts" type="{http://webservices.micros.com/og/4.3/Availability/}ArrayOfAdditionalBedAmount" minOccurs="0"/>
 *         &lt;element name="RoomQuality" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BedType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GdsTotalPrice", propOrder = {
    "rateCalculation",
    "additionalBedAmounts",
    "roomQuality",
    "bedType"
})
public class GdsTotalPrice {

    @XmlElement(name = "RateCalculation")
    protected RateCalculation rateCalculation;
    @XmlElement(name = "AdditionalBedAmounts")
    protected ArrayOfAdditionalBedAmount additionalBedAmounts;
    @XmlElement(name = "RoomQuality")
    protected String roomQuality;
    @XmlElement(name = "BedType")
    protected String bedType;

    /**
     * Gets the value of the rateCalculation property.
     * 
     * @return
     *     possible object is
     *     {@link RateCalculation }
     *     
     */
    public RateCalculation getRateCalculation() {
        return rateCalculation;
    }

    /**
     * Sets the value of the rateCalculation property.
     * 
     * @param value
     *     allowed object is
     *     {@link RateCalculation }
     *     
     */
    public void setRateCalculation(RateCalculation value) {
        this.rateCalculation = value;
    }

    /**
     * Gets the value of the additionalBedAmounts property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfAdditionalBedAmount }
     *     
     */
    public ArrayOfAdditionalBedAmount getAdditionalBedAmounts() {
        return additionalBedAmounts;
    }

    /**
     * Sets the value of the additionalBedAmounts property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfAdditionalBedAmount }
     *     
     */
    public void setAdditionalBedAmounts(ArrayOfAdditionalBedAmount value) {
        this.additionalBedAmounts = value;
    }

    /**
     * Gets the value of the roomQuality property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoomQuality() {
        return roomQuality;
    }

    /**
     * Sets the value of the roomQuality property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoomQuality(String value) {
        this.roomQuality = value;
    }

    /**
     * Gets the value of the bedType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBedType() {
        return bedType;
    }

    /**
     * Sets the value of the bedType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBedType(String value) {
        this.bedType = value;
    }

}
