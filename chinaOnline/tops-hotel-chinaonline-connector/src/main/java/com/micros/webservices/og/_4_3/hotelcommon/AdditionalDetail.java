
package com.micros.webservices.og._4_3.hotelcommon;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AdditionalDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AdditionalDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AdditionalDetailDescription" type="{http://webservices.micros.com/og/4.3/HotelCommon/}Paragraph" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="detailType" use="required" type="{http://webservices.micros.com/og/4.3/HotelCommon/}AdditionalDetailType" />
 *       &lt;attribute name="otherDetailType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AdditionalDetail", propOrder = {
    "additionalDetailDescription"
})
public class AdditionalDetail {

    @XmlElement(name = "AdditionalDetailDescription")
    protected Paragraph additionalDetailDescription;
    @XmlAttribute(name = "detailType", required = true)
    protected AdditionalDetailType detailType;
    @XmlAttribute(name = "otherDetailType")
    protected String otherDetailType;

    /**
     * Gets the value of the additionalDetailDescription property.
     * 
     * @return
     *     possible object is
     *     {@link Paragraph }
     *     
     */
    public Paragraph getAdditionalDetailDescription() {
        return additionalDetailDescription;
    }

    /**
     * Sets the value of the additionalDetailDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link Paragraph }
     *     
     */
    public void setAdditionalDetailDescription(Paragraph value) {
        this.additionalDetailDescription = value;
    }

    /**
     * Gets the value of the detailType property.
     * 
     * @return
     *     possible object is
     *     {@link AdditionalDetailType }
     *     
     */
    public AdditionalDetailType getDetailType() {
        return detailType;
    }

    /**
     * Sets the value of the detailType property.
     * 
     * @param value
     *     allowed object is
     *     {@link AdditionalDetailType }
     *     
     */
    public void setDetailType(AdditionalDetailType value) {
        this.detailType = value;
    }

    /**
     * Gets the value of the otherDetailType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOtherDetailType() {
        return otherDetailType;
    }

    /**
     * Sets the value of the otherDetailType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOtherDetailType(String value) {
        this.otherDetailType = value;
    }

}
