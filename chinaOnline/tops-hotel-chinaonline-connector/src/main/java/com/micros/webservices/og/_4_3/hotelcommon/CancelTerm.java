
package com.micros.webservices.og._4_3.hotelcommon;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for CancelTerm complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CancelTerm">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CancelReason" type="{http://webservices.micros.com/og/4.3/HotelCommon/}Paragraph" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="cancelType" use="required" type="{http://webservices.micros.com/og/4.3/HotelCommon/}CancelTermType" />
 *       &lt;attribute name="otherCancelType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="cancelReasonCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="cancelNumber" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="cancelDate" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="cancelBy" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CancelTerm", propOrder = {
    "cancelReason"
})
public class CancelTerm {

    @XmlElement(name = "CancelReason")
    protected Paragraph cancelReason;
    @XmlAttribute(name = "cancelType", required = true)
    protected CancelTermType cancelType;
    @XmlAttribute(name = "otherCancelType")
    protected String otherCancelType;
    @XmlAttribute(name = "cancelReasonCode")
    protected String cancelReasonCode;
    @XmlAttribute(name = "cancelNumber")
    protected String cancelNumber;
    @XmlAttribute(name = "cancelDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar cancelDate;
    @XmlAttribute(name = "cancelBy")
    protected String cancelBy;

    /**
     * Gets the value of the cancelReason property.
     * 
     * @return
     *     possible object is
     *     {@link Paragraph }
     *     
     */
    public Paragraph getCancelReason() {
        return cancelReason;
    }

    /**
     * Sets the value of the cancelReason property.
     * 
     * @param value
     *     allowed object is
     *     {@link Paragraph }
     *     
     */
    public void setCancelReason(Paragraph value) {
        this.cancelReason = value;
    }

    /**
     * Gets the value of the cancelType property.
     * 
     * @return
     *     possible object is
     *     {@link CancelTermType }
     *     
     */
    public CancelTermType getCancelType() {
        return cancelType;
    }

    /**
     * Sets the value of the cancelType property.
     * 
     * @param value
     *     allowed object is
     *     {@link CancelTermType }
     *     
     */
    public void setCancelType(CancelTermType value) {
        this.cancelType = value;
    }

    /**
     * Gets the value of the otherCancelType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOtherCancelType() {
        return otherCancelType;
    }

    /**
     * Sets the value of the otherCancelType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOtherCancelType(String value) {
        this.otherCancelType = value;
    }

    /**
     * Gets the value of the cancelReasonCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCancelReasonCode() {
        return cancelReasonCode;
    }

    /**
     * Sets the value of the cancelReasonCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCancelReasonCode(String value) {
        this.cancelReasonCode = value;
    }

    /**
     * Gets the value of the cancelNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCancelNumber() {
        return cancelNumber;
    }

    /**
     * Sets the value of the cancelNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCancelNumber(String value) {
        this.cancelNumber = value;
    }

    /**
     * Gets the value of the cancelDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCancelDate() {
        return cancelDate;
    }

    /**
     * Sets the value of the cancelDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCancelDate(XMLGregorianCalendar value) {
        this.cancelDate = value;
    }

    /**
     * Gets the value of the cancelBy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCancelBy() {
        return cancelBy;
    }

    /**
     * Sets the value of the cancelBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCancelBy(String value) {
        this.cancelBy = value;
    }

}
