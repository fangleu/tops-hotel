
package com.micros.webservices.og._4_3.hotelcommon;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * <p>Java class for VectorDistance complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="VectorDistance">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>double">
 *       &lt;attribute name="distanceUnit" use="required" type="{http://webservices.micros.com/og/4.3/HotelCommon/}DistanceUnitType" />
 *       &lt;attribute name="otherDistanceUnit" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VectorDistance", propOrder = {
    "value"
})
public class VectorDistance {

    @XmlValue
    protected double value;
    @XmlAttribute(name = "distanceUnit", required = true)
    protected DistanceUnitType distanceUnit;
    @XmlAttribute(name = "otherDistanceUnit")
    protected String otherDistanceUnit;

    /**
     * Gets the value of the value property.
     * 
     */
    public double getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     */
    public void setValue(double value) {
        this.value = value;
    }

    /**
     * Gets the value of the distanceUnit property.
     * 
     * @return
     *     possible object is
     *     {@link DistanceUnitType }
     *     
     */
    public DistanceUnitType getDistanceUnit() {
        return distanceUnit;
    }

    /**
     * Sets the value of the distanceUnit property.
     * 
     * @param value
     *     allowed object is
     *     {@link DistanceUnitType }
     *     
     */
    public void setDistanceUnit(DistanceUnitType value) {
        this.distanceUnit = value;
    }

    /**
     * Gets the value of the otherDistanceUnit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOtherDistanceUnit() {
        return otherDistanceUnit;
    }

    /**
     * Sets the value of the otherDistanceUnit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOtherDistanceUnit(String value) {
        this.otherDistanceUnit = value;
    }

}
