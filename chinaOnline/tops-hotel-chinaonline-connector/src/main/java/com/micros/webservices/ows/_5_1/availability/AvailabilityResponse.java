
package com.micros.webservices.ows._5_1.availability;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.micros.webservices.og._4_3.availability.ArrayOfAvailResponseSegment;
import com.micros.webservices.og._4_3.hotelcommon.GDSResultStatus;
import com.micros.webservices.og._4_3.hotelcommon.TimeSpan;


/**
 * <p>Java class for AvailabilityResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AvailabilityResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Result" type="{http://webservices.micros.com/og/4.3/HotelCommon/}GDSResultStatus" minOccurs="0"/>
 *         &lt;element name="AlternateDates" type="{http://webservices.micros.com/og/4.3/HotelCommon/}TimeSpan" minOccurs="0"/>
 *         &lt;element name="AvailResponseSegments" type="{http://webservices.micros.com/og/4.3/Availability/}ArrayOfAvailResponseSegment" minOccurs="0"/>
 *         &lt;element name="DailyInventories" type="{http://webservices.micros.com/ows/5.1/Availability.wsdl}ArrayOfDailyInventory" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="summaryOnly" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="alternateDatesSpecified" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AvailabilityResponse", propOrder = {
    "result",
    "alternateDates",
    "availResponseSegments",
    "dailyInventories"
})
public class AvailabilityResponse {

    @XmlElement(name = "Result")
    protected GDSResultStatus result;
    @XmlElement(name = "AlternateDates")
    protected TimeSpan alternateDates;
    @XmlElement(name = "AvailResponseSegments")
    protected ArrayOfAvailResponseSegment availResponseSegments;
    @XmlElement(name = "DailyInventories")
    protected ArrayOfDailyInventory dailyInventories;
    @XmlAttribute(name = "summaryOnly", required = true)
    protected boolean summaryOnly;
    @XmlAttribute(name = "alternateDatesSpecified")
    protected Boolean alternateDatesSpecified;

    /**
     * Gets the value of the result property.
     * 
     * @return
     *     possible object is
     *     {@link GDSResultStatus }
     *     
     */
    public GDSResultStatus getResult() {
        return result;
    }

    /**
     * Sets the value of the result property.
     * 
     * @param value
     *     allowed object is
     *     {@link GDSResultStatus }
     *     
     */
    public void setResult(GDSResultStatus value) {
        this.result = value;
    }

    /**
     * Gets the value of the alternateDates property.
     * 
     * @return
     *     possible object is
     *     {@link TimeSpan }
     *     
     */
    public TimeSpan getAlternateDates() {
        return alternateDates;
    }

    /**
     * Sets the value of the alternateDates property.
     * 
     * @param value
     *     allowed object is
     *     {@link TimeSpan }
     *     
     */
    public void setAlternateDates(TimeSpan value) {
        this.alternateDates = value;
    }

    /**
     * Gets the value of the availResponseSegments property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfAvailResponseSegment }
     *     
     */
    public ArrayOfAvailResponseSegment getAvailResponseSegments() {
        return availResponseSegments;
    }

    /**
     * Sets the value of the availResponseSegments property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfAvailResponseSegment }
     *     
     */
    public void setAvailResponseSegments(ArrayOfAvailResponseSegment value) {
        this.availResponseSegments = value;
    }

    /**
     * Gets the value of the dailyInventories property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfDailyInventory }
     *     
     */
    public ArrayOfDailyInventory getDailyInventories() {
        return dailyInventories;
    }

    /**
     * Sets the value of the dailyInventories property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfDailyInventory }
     *     
     */
    public void setDailyInventories(ArrayOfDailyInventory value) {
        this.dailyInventories = value;
    }

    /**
     * Gets the value of the summaryOnly property.
     * 
     */
    public boolean isSummaryOnly() {
        return summaryOnly;
    }

    /**
     * Sets the value of the summaryOnly property.
     * 
     */
    public void setSummaryOnly(boolean value) {
        this.summaryOnly = value;
    }

    /**
     * Gets the value of the alternateDatesSpecified property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAlternateDatesSpecified() {
        return alternateDatesSpecified;
    }

    /**
     * Sets the value of the alternateDatesSpecified property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAlternateDatesSpecified(Boolean value) {
        this.alternateDatesSpecified = value;
    }

}
