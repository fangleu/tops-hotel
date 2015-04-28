
package com.micros.webservices.og._4_3.hotelcommon;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.micros.webservices.og._4_3.common.ResultStatus;


/**
 * <p>Java class for GDSResultStatus complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GDSResultStatus">
 *   &lt;complexContent>
 *     &lt;extension base="{http://webservices.micros.com/og/4.3/Common/}ResultStatus">
 *       &lt;sequence>
 *         &lt;element name="GDSError" type="{http://webservices.micros.com/og/4.3/HotelCommon/}GDSError" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GDSResultStatus", propOrder = {
    "gdsError"
})
public class GDSResultStatus
    extends ResultStatus
{

    @XmlElement(name = "GDSError")
    protected GDSError gdsError;

    /**
     * Gets the value of the gdsError property.
     * 
     * @return
     *     possible object is
     *     {@link GDSError }
     *     
     */
    public GDSError getGDSError() {
        return gdsError;
    }

    /**
     * Sets the value of the gdsError property.
     * 
     * @param value
     *     allowed object is
     *     {@link GDSError }
     *     
     */
    public void setGDSError(GDSError value) {
        this.gdsError = value;
    }

}
