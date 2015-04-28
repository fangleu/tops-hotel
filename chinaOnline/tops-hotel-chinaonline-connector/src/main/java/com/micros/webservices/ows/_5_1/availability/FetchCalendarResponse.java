
package com.micros.webservices.ows._5_1.availability;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.micros.webservices.og._4_3.availability.ArrayOfCalendarDailyDetail;
import com.micros.webservices.og._4_3.hotelcommon.GDSResultStatus;


/**
 * <p>Java class for FetchCalendarResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FetchCalendarResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Result" type="{http://webservices.micros.com/og/4.3/HotelCommon/}GDSResultStatus" minOccurs="0"/>
 *         &lt;element name="Calendar" type="{http://webservices.micros.com/og/4.3/Availability/}ArrayOfCalendarDailyDetail" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FetchCalendarResponse", propOrder = {
    "result",
    "calendar"
})
public class FetchCalendarResponse {

    @XmlElement(name = "Result")
    protected GDSResultStatus result;
    @XmlElement(name = "Calendar")
    protected ArrayOfCalendarDailyDetail calendar;

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
     * Gets the value of the calendar property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfCalendarDailyDetail }
     *     
     */
    public ArrayOfCalendarDailyDetail getCalendar() {
        return calendar;
    }

    /**
     * Sets the value of the calendar property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfCalendarDailyDetail }
     *     
     */
    public void setCalendar(ArrayOfCalendarDailyDetail value) {
        this.calendar = value;
    }

}
