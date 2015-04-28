
package com.micros.webservices.og._4_3.hotelcommon;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import com.micros.webservices.og._4_3.common.Amount;


/**
 * <p>Java class for RoomRate complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RoomRate">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Guarantee" type="{http://webservices.micros.com/og/4.3/HotelCommon/}Guarantee" minOccurs="0"/>
 *         &lt;element name="onRequestBookingReason" type="{http://webservices.micros.com/og/4.3/HotelCommon/}RequstBookingReason" minOccurs="0"/>
 *         &lt;element name="Rates" type="{http://webservices.micros.com/og/4.3/HotelCommon/}ArrayOfRate" minOccurs="0"/>
 *         &lt;element name="InvBlockDescription" type="{http://webservices.micros.com/og/4.3/HotelCommon/}Paragraph" minOccurs="0"/>
 *         &lt;element name="RoomRateAndPackages" type="{http://webservices.micros.com/og/4.3/HotelCommon/}ChargeList" minOccurs="0"/>
 *         &lt;element name="Total" type="{http://webservices.micros.com/og/4.3/Common/}Amount" minOccurs="0"/>
 *         &lt;element name="TotalPoints" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="GdsTotalPricing" type="{http://webservices.micros.com/og/4.3/HotelCommon/}GdsTotalPricing" minOccurs="0"/>
 *         &lt;element name="CancelPenalties" type="{http://webservices.micros.com/og/4.3/HotelCommon/}ArrayOfCancelPenalty" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="onRequestBooking" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="roomTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ratePlanCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="effectiveDate" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="expirationDate" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="suppressRate" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="redemRate" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="Breakfast" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="AvailableRooms" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RoomRate", propOrder = {
    "guarantee",
    "onRequestBookingReason",
    "rates",
    "invBlockDescription",
    "roomRateAndPackages",
    "total",
    "totalPoints",
    "gdsTotalPricing",
    "cancelPenalties"
})
public class RoomRate {

    @XmlElement(name = "Guarantee")
    protected Guarantee guarantee;
    protected RequstBookingReason onRequestBookingReason;
    @XmlElement(name = "Rates")
    protected ArrayOfRate rates;
    @XmlElement(name = "InvBlockDescription")
    protected Paragraph invBlockDescription;
    @XmlElement(name = "RoomRateAndPackages")
    protected ChargeList roomRateAndPackages;
    @XmlElement(name = "Total")
    protected Amount total;
    @XmlElement(name = "TotalPoints")
    protected Double totalPoints;
    @XmlElement(name = "GdsTotalPricing")
    protected GdsTotalPricing gdsTotalPricing;
    @XmlElement(name = "CancelPenalties")
    protected ArrayOfCancelPenalty cancelPenalties;
    @XmlAttribute(name = "onRequestBooking")
    protected Boolean onRequestBooking;
    @XmlAttribute(name = "roomTypeCode")
    protected String roomTypeCode;
    @XmlAttribute(name = "ratePlanCode")
    protected String ratePlanCode;
    @XmlAttribute(name = "effectiveDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar effectiveDate;
    @XmlAttribute(name = "expirationDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar expirationDate;
    @XmlAttribute(name = "suppressRate")
    protected Boolean suppressRate;
    @XmlAttribute(name = "redemRate")
    protected Boolean redemRate;
    @XmlAttribute(name = "Breakfast")
    protected String breakfast;
    @XmlAttribute(name = "AvailableRooms")
    protected Integer availableRooms;

    /**
     * Gets the value of the guarantee property.
     * 
     * @return
     *     possible object is
     *     {@link Guarantee }
     *     
     */
    public Guarantee getGuarantee() {
        return guarantee;
    }

    /**
     * Sets the value of the guarantee property.
     * 
     * @param value
     *     allowed object is
     *     {@link Guarantee }
     *     
     */
    public void setGuarantee(Guarantee value) {
        this.guarantee = value;
    }

    /**
     * Gets the value of the onRequestBookingReason property.
     * 
     * @return
     *     possible object is
     *     {@link RequstBookingReason }
     *     
     */
    public RequstBookingReason getOnRequestBookingReason() {
        return onRequestBookingReason;
    }

    /**
     * Sets the value of the onRequestBookingReason property.
     * 
     * @param value
     *     allowed object is
     *     {@link RequstBookingReason }
     *     
     */
    public void setOnRequestBookingReason(RequstBookingReason value) {
        this.onRequestBookingReason = value;
    }

    /**
     * Gets the value of the rates property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfRate }
     *     
     */
    public ArrayOfRate getRates() {
        return rates;
    }

    /**
     * Sets the value of the rates property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfRate }
     *     
     */
    public void setRates(ArrayOfRate value) {
        this.rates = value;
    }

    /**
     * Gets the value of the invBlockDescription property.
     * 
     * @return
     *     possible object is
     *     {@link Paragraph }
     *     
     */
    public Paragraph getInvBlockDescription() {
        return invBlockDescription;
    }

    /**
     * Sets the value of the invBlockDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link Paragraph }
     *     
     */
    public void setInvBlockDescription(Paragraph value) {
        this.invBlockDescription = value;
    }

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
     * Gets the value of the total property.
     * 
     * @return
     *     possible object is
     *     {@link Amount }
     *     
     */
    public Amount getTotal() {
        return total;
    }

    /**
     * Sets the value of the total property.
     * 
     * @param value
     *     allowed object is
     *     {@link Amount }
     *     
     */
    public void setTotal(Amount value) {
        this.total = value;
    }

    /**
     * Gets the value of the totalPoints property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getTotalPoints() {
        return totalPoints;
    }

    /**
     * Sets the value of the totalPoints property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setTotalPoints(Double value) {
        this.totalPoints = value;
    }

    /**
     * Gets the value of the gdsTotalPricing property.
     * 
     * @return
     *     possible object is
     *     {@link GdsTotalPricing }
     *     
     */
    public GdsTotalPricing getGdsTotalPricing() {
        return gdsTotalPricing;
    }

    /**
     * Sets the value of the gdsTotalPricing property.
     * 
     * @param value
     *     allowed object is
     *     {@link GdsTotalPricing }
     *     
     */
    public void setGdsTotalPricing(GdsTotalPricing value) {
        this.gdsTotalPricing = value;
    }

    /**
     * Gets the value of the cancelPenalties property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfCancelPenalty }
     *     
     */
    public ArrayOfCancelPenalty getCancelPenalties() {
        return cancelPenalties;
    }

    /**
     * Sets the value of the cancelPenalties property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfCancelPenalty }
     *     
     */
    public void setCancelPenalties(ArrayOfCancelPenalty value) {
        this.cancelPenalties = value;
    }

    /**
     * Gets the value of the onRequestBooking property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isOnRequestBooking() {
        return onRequestBooking;
    }

    /**
     * Sets the value of the onRequestBooking property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setOnRequestBooking(Boolean value) {
        this.onRequestBooking = value;
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
     * Gets the value of the ratePlanCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRatePlanCode() {
        return ratePlanCode;
    }

    /**
     * Sets the value of the ratePlanCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRatePlanCode(String value) {
        this.ratePlanCode = value;
    }

    /**
     * Gets the value of the effectiveDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEffectiveDate() {
        return effectiveDate;
    }

    /**
     * Sets the value of the effectiveDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEffectiveDate(XMLGregorianCalendar value) {
        this.effectiveDate = value;
    }

    /**
     * Gets the value of the expirationDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getExpirationDate() {
        return expirationDate;
    }

    /**
     * Sets the value of the expirationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setExpirationDate(XMLGregorianCalendar value) {
        this.expirationDate = value;
    }

    /**
     * Gets the value of the suppressRate property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSuppressRate() {
        return suppressRate;
    }

    /**
     * Sets the value of the suppressRate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSuppressRate(Boolean value) {
        this.suppressRate = value;
    }

    /**
     * Gets the value of the redemRate property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isRedemRate() {
        return redemRate;
    }

    /**
     * Sets the value of the redemRate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRedemRate(Boolean value) {
        this.redemRate = value;
    }

    /**
     * Gets the value of the breakfast property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBreakfast() {
        return breakfast;
    }

    /**
     * Sets the value of the breakfast property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBreakfast(String value) {
        this.breakfast = value;
    }

    /**
     * Gets the value of the availableRooms property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAvailableRooms() {
        return availableRooms;
    }

    /**
     * Sets the value of the availableRooms property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAvailableRooms(Integer value) {
        this.availableRooms = value;
    }

}
