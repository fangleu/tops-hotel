
package com.micros.webservices.og._4_3.core;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OGHeaderAuthentication complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OGHeaderAuthentication">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="UserCredentials" type="{http://webservices.micros.com/og/4.3/Core/}OGHeaderAuthenticationUserCredentials" minOccurs="0"/>
 *         &lt;element name="Licence" type="{http://webservices.micros.com/og/4.3/Core/}OGHeaderAuthenticationLicence" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OGHeaderAuthentication", propOrder = {
    "userCredentials",
    "licence"
})
public class OGHeaderAuthentication {

    @XmlElement(name = "UserCredentials")
    protected OGHeaderAuthenticationUserCredentials userCredentials;
    @XmlElement(name = "Licence")
    protected OGHeaderAuthenticationLicence licence;

    /**
     * Gets the value of the userCredentials property.
     * 
     * @return
     *     possible object is
     *     {@link OGHeaderAuthenticationUserCredentials }
     *     
     */
    public OGHeaderAuthenticationUserCredentials getUserCredentials() {
        return userCredentials;
    }

    /**
     * Sets the value of the userCredentials property.
     * 
     * @param value
     *     allowed object is
     *     {@link OGHeaderAuthenticationUserCredentials }
     *     
     */
    public void setUserCredentials(OGHeaderAuthenticationUserCredentials value) {
        this.userCredentials = value;
    }

    /**
     * Gets the value of the licence property.
     * 
     * @return
     *     possible object is
     *     {@link OGHeaderAuthenticationLicence }
     *     
     */
    public OGHeaderAuthenticationLicence getLicence() {
        return licence;
    }

    /**
     * Sets the value of the licence property.
     * 
     * @param value
     *     allowed object is
     *     {@link OGHeaderAuthenticationLicence }
     *     
     */
    public void setLicence(OGHeaderAuthenticationLicence value) {
        this.licence = value;
    }

}
