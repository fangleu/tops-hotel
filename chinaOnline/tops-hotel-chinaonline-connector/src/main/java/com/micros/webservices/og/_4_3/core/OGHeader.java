
package com.micros.webservices.og._4_3.core;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


/**
 * <p>Java class for OGHeader complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OGHeader">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Origin" type="{http://webservices.micros.com/og/4.3/Core/}EndPoint" minOccurs="0"/>
 *         &lt;element name="Destination" type="{http://webservices.micros.com/og/4.3/Core/}EndPoint" minOccurs="0"/>
 *         &lt;element name="Intermediaries" type="{http://webservices.micros.com/og/4.3/Core/}ArrayOfEndPoint" minOccurs="0"/>
 *         &lt;element name="Authentication" type="{http://webservices.micros.com/og/4.3/Core/}OGHeaderAuthentication" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="transactionID" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="authToken" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="timeStamp" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="primaryLangID" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;anyAttribute/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OGHeader", propOrder = {
    "origin",
    "destination",
    "intermediaries",
    "authentication"
})
public class OGHeader {

    @XmlElement(name = "Origin")
    protected EndPoint origin;
    @XmlElement(name = "Destination")
    protected EndPoint destination;
    @XmlElement(name = "Intermediaries")
    protected ArrayOfEndPoint intermediaries;
    @XmlElement(name = "Authentication")
    protected OGHeaderAuthentication authentication;
    @XmlAttribute(name = "transactionID")
    protected String transactionID;
    @XmlAttribute(name = "authToken")
    protected String authToken;
    @XmlAttribute(name = "timeStamp")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar timeStamp;
    @XmlAttribute(name = "primaryLangID")
    protected String primaryLangID;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    /**
     * Gets the value of the origin property.
     * 
     * @return
     *     possible object is
     *     {@link EndPoint }
     *     
     */
    public EndPoint getOrigin() {
        return origin;
    }

    /**
     * Sets the value of the origin property.
     * 
     * @param value
     *     allowed object is
     *     {@link EndPoint }
     *     
     */
    public void setOrigin(EndPoint value) {
        this.origin = value;
    }

    /**
     * Gets the value of the destination property.
     * 
     * @return
     *     possible object is
     *     {@link EndPoint }
     *     
     */
    public EndPoint getDestination() {
        return destination;
    }

    /**
     * Sets the value of the destination property.
     * 
     * @param value
     *     allowed object is
     *     {@link EndPoint }
     *     
     */
    public void setDestination(EndPoint value) {
        this.destination = value;
    }

    /**
     * Gets the value of the intermediaries property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfEndPoint }
     *     
     */
    public ArrayOfEndPoint getIntermediaries() {
        return intermediaries;
    }

    /**
     * Sets the value of the intermediaries property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfEndPoint }
     *     
     */
    public void setIntermediaries(ArrayOfEndPoint value) {
        this.intermediaries = value;
    }

    /**
     * Gets the value of the authentication property.
     * 
     * @return
     *     possible object is
     *     {@link OGHeaderAuthentication }
     *     
     */
    public OGHeaderAuthentication getAuthentication() {
        return authentication;
    }

    /**
     * Sets the value of the authentication property.
     * 
     * @param value
     *     allowed object is
     *     {@link OGHeaderAuthentication }
     *     
     */
    public void setAuthentication(OGHeaderAuthentication value) {
        this.authentication = value;
    }

    /**
     * Gets the value of the transactionID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionID() {
        return transactionID;
    }

    /**
     * Sets the value of the transactionID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionID(String value) {
        this.transactionID = value;
    }

    /**
     * Gets the value of the authToken property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthToken() {
        return authToken;
    }

    /**
     * Sets the value of the authToken property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthToken(String value) {
        this.authToken = value;
    }

    /**
     * Gets the value of the timeStamp property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTimeStamp() {
        return timeStamp;
    }

    /**
     * Sets the value of the timeStamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTimeStamp(XMLGregorianCalendar value) {
        this.timeStamp = value;
    }

    /**
     * Gets the value of the primaryLangID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrimaryLangID() {
        return primaryLangID;
    }

    /**
     * Sets the value of the primaryLangID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrimaryLangID(String value) {
        this.primaryLangID = value;
    }

    /**
     * Gets a map that contains attributes that aren't bound to any typed property on this class.
     * 
     * <p>
     * the map is keyed by the name of the attribute and 
     * the value is the string value of the attribute.
     * 
     * the map returned by this method is live, and you can add new attribute
     * by updating the map directly. Because of this design, there's no setter.
     * 
     * 
     * @return
     *     always non-null
     */
    public Map<QName, String> getOtherAttributes() {
        return otherAttributes;
    }

}
