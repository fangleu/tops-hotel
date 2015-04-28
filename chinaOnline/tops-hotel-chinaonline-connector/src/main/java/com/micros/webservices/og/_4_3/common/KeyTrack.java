
package com.micros.webservices.og._4_3.common;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for KeyTrack complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="KeyTrack">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="Key1Track" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Key2Track" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Key3Track" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Key4Track" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "KeyTrack")
public class KeyTrack {

    @XmlAttribute(name = "Key1Track")
    protected String key1Track;
    @XmlAttribute(name = "Key2Track")
    protected String key2Track;
    @XmlAttribute(name = "Key3Track")
    protected String key3Track;
    @XmlAttribute(name = "Key4Track")
    protected String key4Track;

    /**
     * Gets the value of the key1Track property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKey1Track() {
        return key1Track;
    }

    /**
     * Sets the value of the key1Track property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKey1Track(String value) {
        this.key1Track = value;
    }

    /**
     * Gets the value of the key2Track property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKey2Track() {
        return key2Track;
    }

    /**
     * Sets the value of the key2Track property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKey2Track(String value) {
        this.key2Track = value;
    }

    /**
     * Gets the value of the key3Track property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKey3Track() {
        return key3Track;
    }

    /**
     * Sets the value of the key3Track property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKey3Track(String value) {
        this.key3Track = value;
    }

    /**
     * Gets the value of the key4Track property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKey4Track() {
        return key4Track;
    }

    /**
     * Sets the value of the key4Track property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKey4Track(String value) {
        this.key4Track = value;
    }

}
