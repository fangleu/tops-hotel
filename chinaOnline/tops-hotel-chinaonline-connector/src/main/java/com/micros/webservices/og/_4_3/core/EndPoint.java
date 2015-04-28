
package com.micros.webservices.og._4_3.core;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EndPoint complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EndPoint">
 *   &lt;complexContent>
 *     &lt;extension base="{http://webservices.micros.com/og/4.3/Core/}SystemID">
 *       &lt;attribute name="systemType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EndPoint")
public class EndPoint
    extends SystemID
{

    @XmlAttribute(name = "systemType")
    protected String systemType;

    /**
     * Gets the value of the systemType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSystemType() {
        return systemType;
    }

    /**
     * Sets the value of the systemType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSystemType(String value) {
        this.systemType = value;
    }

}
