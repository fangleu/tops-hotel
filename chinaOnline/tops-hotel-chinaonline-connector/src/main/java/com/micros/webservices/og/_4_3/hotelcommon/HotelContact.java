
package com.micros.webservices.og._4_3.hotelcommon;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for HotelContact complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="HotelContact">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Addresses" type="{http://webservices.micros.com/og/4.3/HotelCommon/}ArrayOfAddress" minOccurs="0"/>
 *         &lt;element name="ContactEmails" type="{http://webservices.micros.com/og/4.3/HotelCommon/}ArrayOfEmail" minOccurs="0"/>
 *         &lt;element name="ContactPhones" type="{http://webservices.micros.com/og/4.3/HotelCommon/}ArrayOfPhone" minOccurs="0"/>
 *         &lt;element name="HotelInformation" type="{http://webservices.micros.com/og/4.3/HotelCommon/}ArrayOfHotelInfo" minOccurs="0"/>
 *         &lt;element name="URIs" type="{http://webservices.micros.com/og/4.3/HotelCommon/}ArrayOfAnyURI" minOccurs="0"/>
 *         &lt;element name="Vector" type="{http://webservices.micros.com/og/4.3/HotelCommon/}Vector" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HotelContact", propOrder = {
    "addresses",
    "contactEmails",
    "contactPhones",
    "hotelInformation",
    "urIs",
    "vector"
})
public class HotelContact {

    @XmlElement(name = "Addresses")
    protected ArrayOfAddress addresses;
    @XmlElement(name = "ContactEmails")
    protected ArrayOfEmail contactEmails;
    @XmlElement(name = "ContactPhones")
    protected ArrayOfPhone contactPhones;
    @XmlElement(name = "HotelInformation")
    protected ArrayOfHotelInfo hotelInformation;
    @XmlElement(name = "URIs")
    protected ArrayOfAnyURI urIs;
    @XmlElement(name = "Vector")
    protected Vector vector;

    /**
     * Gets the value of the addresses property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfAddress }
     *     
     */
    public ArrayOfAddress getAddresses() {
        return addresses;
    }

    /**
     * Sets the value of the addresses property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfAddress }
     *     
     */
    public void setAddresses(ArrayOfAddress value) {
        this.addresses = value;
    }

    /**
     * Gets the value of the contactEmails property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfEmail }
     *     
     */
    public ArrayOfEmail getContactEmails() {
        return contactEmails;
    }

    /**
     * Sets the value of the contactEmails property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfEmail }
     *     
     */
    public void setContactEmails(ArrayOfEmail value) {
        this.contactEmails = value;
    }

    /**
     * Gets the value of the contactPhones property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfPhone }
     *     
     */
    public ArrayOfPhone getContactPhones() {
        return contactPhones;
    }

    /**
     * Sets the value of the contactPhones property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfPhone }
     *     
     */
    public void setContactPhones(ArrayOfPhone value) {
        this.contactPhones = value;
    }

    /**
     * Gets the value of the hotelInformation property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfHotelInfo }
     *     
     */
    public ArrayOfHotelInfo getHotelInformation() {
        return hotelInformation;
    }

    /**
     * Sets the value of the hotelInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfHotelInfo }
     *     
     */
    public void setHotelInformation(ArrayOfHotelInfo value) {
        this.hotelInformation = value;
    }

    /**
     * Gets the value of the urIs property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfAnyURI }
     *     
     */
    public ArrayOfAnyURI getURIs() {
        return urIs;
    }

    /**
     * Sets the value of the urIs property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfAnyURI }
     *     
     */
    public void setURIs(ArrayOfAnyURI value) {
        this.urIs = value;
    }

    /**
     * Gets the value of the vector property.
     * 
     * @return
     *     possible object is
     *     {@link Vector }
     *     
     */
    public Vector getVector() {
        return vector;
    }

    /**
     * Sets the value of the vector property.
     * 
     * @param value
     *     allowed object is
     *     {@link Vector }
     *     
     */
    public void setVector(Vector value) {
        this.vector = value;
    }

}
