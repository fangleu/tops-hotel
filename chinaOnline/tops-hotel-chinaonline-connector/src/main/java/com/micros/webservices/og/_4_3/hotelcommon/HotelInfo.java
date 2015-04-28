
package com.micros.webservices.og._4_3.hotelcommon;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import com.micros.webservices.og._4_3.common.DescriptiveText;


/**
 * <p>Java class for HotelInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="HotelInfo">
 *   &lt;complexContent>
 *     &lt;extension base="{http://webservices.micros.com/og/4.3/Common/}DescriptiveText">
 *       &lt;attribute name="hotelInfoType" use="required" type="{http://webservices.micros.com/og/4.3/HotelCommon/}HotelInfoType" />
 *       &lt;attribute name="otherHotelInfoType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HotelInfo")
public class HotelInfo
    extends DescriptiveText
{

    @XmlAttribute(name = "hotelInfoType", required = true)
    protected HotelInfoType hotelInfoType;
    @XmlAttribute(name = "otherHotelInfoType")
    protected String otherHotelInfoType;

    /**
     * Gets the value of the hotelInfoType property.
     * 
     * @return
     *     possible object is
     *     {@link HotelInfoType }
     *     
     */
    public HotelInfoType getHotelInfoType() {
        return hotelInfoType;
    }

    /**
     * Sets the value of the hotelInfoType property.
     * 
     * @param value
     *     allowed object is
     *     {@link HotelInfoType }
     *     
     */
    public void setHotelInfoType(HotelInfoType value) {
        this.hotelInfoType = value;
    }

    /**
     * Gets the value of the otherHotelInfoType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOtherHotelInfoType() {
        return otherHotelInfoType;
    }

    /**
     * Sets the value of the otherHotelInfoType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOtherHotelInfoType(String value) {
        this.otherHotelInfoType = value;
    }

}
