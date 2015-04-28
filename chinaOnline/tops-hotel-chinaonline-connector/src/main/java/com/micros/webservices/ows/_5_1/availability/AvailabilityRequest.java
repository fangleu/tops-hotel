
package com.micros.webservices.ows._5_1.availability;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.micros.webservices.og._4_3.availability.AvailRequestSegmentList;


/**
 * <p>Java class for AvailabilityRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AvailabilityRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://webservices.micros.com/og/4.3/Availability/}AvailRequestSegmentList">
 *       &lt;sequence>
 *         &lt;element name="loadRateOnly" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *       &lt;attribute name="summaryOnly" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AvailabilityRequest", propOrder = {
    "loadRateOnly"
})
public class AvailabilityRequest
    extends AvailRequestSegmentList
{

    @XmlElement(required = true, type = Boolean.class, nillable = true)
    protected Boolean loadRateOnly;
    @XmlAttribute(name = "summaryOnly", required = true)
    protected boolean summaryOnly;

    /**
     * Gets the value of the loadRateOnly property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isLoadRateOnly() {
        return loadRateOnly;
    }

    /**
     * Sets the value of the loadRateOnly property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setLoadRateOnly(Boolean value) {
        this.loadRateOnly = value;
    }

    /**
     * Gets the value of the summaryOnly property.
     * 
     */
    public boolean isSummaryOnly() {
        return summaryOnly;
    }

    /**
     * Sets the value of the summaryOnly property.
     * 
     */
    public void setSummaryOnly(boolean value) {
        this.summaryOnly = value;
    }

}
