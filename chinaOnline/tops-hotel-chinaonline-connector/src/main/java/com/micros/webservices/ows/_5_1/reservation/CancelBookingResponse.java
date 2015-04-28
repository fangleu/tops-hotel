
package com.micros.webservices.ows._5_1.reservation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.micros.webservices.og._4_3.common.UniqueID;
import com.micros.webservices.og._4_3.hotelcommon.GDSResultStatus;


/**
 * <p>Java class for CancelBookingResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CancelBookingResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Result" type="{http://webservices.micros.com/og/4.3/HotelCommon/}GDSResultStatus" minOccurs="0"/>
 *         &lt;element name="CancellationNumber" type="{http://webservices.micros.com/og/4.3/Common/}UniqueID" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CancelBookingResponse", propOrder = {
    "result",
    "cancellationNumber"
})
public class CancelBookingResponse {

    @XmlElement(name = "Result")
    protected GDSResultStatus result;
    @XmlElement(name = "CancellationNumber")
    protected UniqueID cancellationNumber;

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
     * Gets the value of the cancellationNumber property.
     * 
     * @return
     *     possible object is
     *     {@link UniqueID }
     *     
     */
    public UniqueID getCancellationNumber() {
        return cancellationNumber;
    }

    /**
     * Sets the value of the cancellationNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link UniqueID }
     *     
     */
    public void setCancellationNumber(UniqueID value) {
        this.cancellationNumber = value;
    }

}
