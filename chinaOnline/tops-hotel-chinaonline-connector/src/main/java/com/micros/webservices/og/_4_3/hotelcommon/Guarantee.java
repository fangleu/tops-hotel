
package com.micros.webservices.og._4_3.hotelcommon;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for Guarantee complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Guarantee">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GuaranteesAccepted" type="{http://webservices.micros.com/og/4.3/HotelCommon/}ArrayOfGuaranteeAccepted" minOccurs="0"/>
 *         &lt;element name="GuaranteeDescription" type="{http://webservices.micros.com/og/4.3/HotelCommon/}Paragraph" minOccurs="0"/>
 *         &lt;element name="Deadline" type="{http://webservices.micros.com/og/4.3/HotelCommon/}Deadline" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="guaranteeType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="mandatoryDeposit" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="RetributionType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="GuaranteeCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="HoldTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Guarantee", propOrder = {
    "guaranteesAccepted",
    "guaranteeDescription",
    "deadline"
})
public class Guarantee {

    @XmlElement(name = "GuaranteesAccepted")
    protected ArrayOfGuaranteeAccepted guaranteesAccepted;
    @XmlElement(name = "GuaranteeDescription")
    protected Paragraph guaranteeDescription;
    @XmlElement(name = "Deadline")
    protected Deadline deadline;
    @XmlAttribute(name = "guaranteeType")
    protected String guaranteeType;
    @XmlAttribute(name = "mandatoryDeposit")
    protected Boolean mandatoryDeposit;
    @XmlAttribute(name = "RetributionType")
    protected String retributionType;
    @XmlAttribute(name = "GuaranteeCode")
    protected String guaranteeCode;
    @XmlAttribute(name = "HoldTime")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar holdTime;

    /**
     * Gets the value of the guaranteesAccepted property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfGuaranteeAccepted }
     *     
     */
    public ArrayOfGuaranteeAccepted getGuaranteesAccepted() {
        return guaranteesAccepted;
    }

    /**
     * Sets the value of the guaranteesAccepted property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfGuaranteeAccepted }
     *     
     */
    public void setGuaranteesAccepted(ArrayOfGuaranteeAccepted value) {
        this.guaranteesAccepted = value;
    }

    /**
     * Gets the value of the guaranteeDescription property.
     * 
     * @return
     *     possible object is
     *     {@link Paragraph }
     *     
     */
    public Paragraph getGuaranteeDescription() {
        return guaranteeDescription;
    }

    /**
     * Sets the value of the guaranteeDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link Paragraph }
     *     
     */
    public void setGuaranteeDescription(Paragraph value) {
        this.guaranteeDescription = value;
    }

    /**
     * Gets the value of the deadline property.
     * 
     * @return
     *     possible object is
     *     {@link Deadline }
     *     
     */
    public Deadline getDeadline() {
        return deadline;
    }

    /**
     * Sets the value of the deadline property.
     * 
     * @param value
     *     allowed object is
     *     {@link Deadline }
     *     
     */
    public void setDeadline(Deadline value) {
        this.deadline = value;
    }

    /**
     * Gets the value of the guaranteeType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGuaranteeType() {
        return guaranteeType;
    }

    /**
     * Sets the value of the guaranteeType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGuaranteeType(String value) {
        this.guaranteeType = value;
    }

    /**
     * Gets the value of the mandatoryDeposit property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isMandatoryDeposit() {
        return mandatoryDeposit;
    }

    /**
     * Sets the value of the mandatoryDeposit property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMandatoryDeposit(Boolean value) {
        this.mandatoryDeposit = value;
    }

    /**
     * Gets the value of the retributionType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRetributionType() {
        return retributionType;
    }

    /**
     * Sets the value of the retributionType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRetributionType(String value) {
        this.retributionType = value;
    }

    /**
     * Gets the value of the guaranteeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGuaranteeCode() {
        return guaranteeCode;
    }

    /**
     * Sets the value of the guaranteeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGuaranteeCode(String value) {
        this.guaranteeCode = value;
    }

    /**
     * Gets the value of the holdTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getHoldTime() {
        return holdTime;
    }

    /**
     * Sets the value of the holdTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setHoldTime(XMLGregorianCalendar value) {
        this.holdTime = value;
    }

}
