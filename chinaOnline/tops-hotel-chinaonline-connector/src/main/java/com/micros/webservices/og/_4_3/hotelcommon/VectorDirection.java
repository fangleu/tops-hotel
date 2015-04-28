
package com.micros.webservices.og._4_3.hotelcommon;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for VectorDirection complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="VectorDirection">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="vectorDirection" use="required" type="{http://webservices.micros.com/og/4.3/HotelCommon/}VectorDirectionType" />
 *       &lt;attribute name="otherVectorDirection" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VectorDirection")
public class VectorDirection {

    @XmlAttribute(name = "vectorDirection", required = true)
    protected VectorDirectionType vectorDirection;
    @XmlAttribute(name = "otherVectorDirection")
    protected String otherVectorDirection;

    /**
     * Gets the value of the vectorDirection property.
     * 
     * @return
     *     possible object is
     *     {@link VectorDirectionType }
     *     
     */
    public VectorDirectionType getVectorDirection() {
        return vectorDirection;
    }

    /**
     * Sets the value of the vectorDirection property.
     * 
     * @param value
     *     allowed object is
     *     {@link VectorDirectionType }
     *     
     */
    public void setVectorDirection(VectorDirectionType value) {
        this.vectorDirection = value;
    }

    /**
     * Gets the value of the otherVectorDirection property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOtherVectorDirection() {
        return otherVectorDirection;
    }

    /**
     * Sets the value of the otherVectorDirection property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOtherVectorDirection(String value) {
        this.otherVectorDirection = value;
    }

}
