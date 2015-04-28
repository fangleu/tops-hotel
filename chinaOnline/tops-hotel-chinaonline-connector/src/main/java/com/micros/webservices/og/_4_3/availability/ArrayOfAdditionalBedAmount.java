
package com.micros.webservices.og._4_3.availability;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.micros.webservices.og._4_3.hotelcommon.AdditionalBedAmount;


/**
 * <p>Java class for ArrayOfAdditionalBedAmount complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfAdditionalBedAmount">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AdditionalBedAmount" type="{http://webservices.micros.com/og/4.3/HotelCommon/}AdditionalBedAmount" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfAdditionalBedAmount", propOrder = {
    "additionalBedAmount"
})
public class ArrayOfAdditionalBedAmount {

    @XmlElement(name = "AdditionalBedAmount", nillable = true)
    protected List<AdditionalBedAmount> additionalBedAmount;

    /**
     * Gets the value of the additionalBedAmount property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the additionalBedAmount property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAdditionalBedAmount().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AdditionalBedAmount }
     * 
     * 
     */
    public List<AdditionalBedAmount> getAdditionalBedAmount() {
        if (additionalBedAmount == null) {
            additionalBedAmount = new ArrayList<AdditionalBedAmount>();
        }
        return this.additionalBedAmount;
    }

}
