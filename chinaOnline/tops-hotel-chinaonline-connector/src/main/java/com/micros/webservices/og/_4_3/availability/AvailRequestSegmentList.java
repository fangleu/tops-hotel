
package com.micros.webservices.og._4_3.availability;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import com.micros.webservices.ows._5_1.availability.AreaAvailabilityRequest;
import com.micros.webservices.ows._5_1.availability.AvailabilityRequest;


/**
 * <p>Java class for AvailRequestSegmentList complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AvailRequestSegmentList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AvailRequestSegment" type="{http://webservices.micros.com/og/4.3/Availability/}AvailRequestSegment" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AvailRequestSegmentList", propOrder = {
    "availRequestSegment"
})
@XmlSeeAlso({
    AvailabilityRequest.class,
    AreaAvailabilityRequest.class
})
public class AvailRequestSegmentList {

    @XmlElement(name = "AvailRequestSegment")
    protected List<AvailRequestSegment> availRequestSegment;

    /**
     * Gets the value of the availRequestSegment property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the availRequestSegment property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAvailRequestSegment().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AvailRequestSegment }
     * 
     * 
     */
    public List<AvailRequestSegment> getAvailRequestSegment() {
        if (availRequestSegment == null) {
            availRequestSegment = new ArrayList<AvailRequestSegment>();
        }
        return this.availRequestSegment;
    }

}
