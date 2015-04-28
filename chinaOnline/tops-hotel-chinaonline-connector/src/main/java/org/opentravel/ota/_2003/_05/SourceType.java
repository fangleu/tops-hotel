
package org.opentravel.ota._2003._05;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SourceType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SourceType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RequestorID" type="{http://www.opentravel.org/OTA/2003/05}RequestorIDType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SourceType", propOrder = {
    "requestorID"
})
public class SourceType {

    @XmlElement(name = "RequestorID")
    protected RequestorIDType requestorID;

    /**
     * Gets the value of the requestorID property.
     * 
     * @return
     *     possible object is
     *     {@link RequestorIDType }
     *     
     */
    public RequestorIDType getRequestorID() {
        return requestorID;
    }

    /**
     * Sets the value of the requestorID property.
     * 
     * @param value
     *     allowed object is
     *     {@link RequestorIDType }
     *     
     */
    public void setRequestorID(RequestorIDType value) {
        this.requestorID = value;
    }

}
