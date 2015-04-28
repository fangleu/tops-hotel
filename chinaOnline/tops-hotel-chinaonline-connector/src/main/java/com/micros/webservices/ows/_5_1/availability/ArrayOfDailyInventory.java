
package com.micros.webservices.ows._5_1.availability;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.micros.webservices.og._4_3.hotelcommon.DailyInventory;


/**
 * <p>Java class for ArrayOfDailyInventory complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfDailyInventory">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DailyInventory" type="{http://webservices.micros.com/og/4.3/HotelCommon/}DailyInventory" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfDailyInventory", propOrder = {
    "dailyInventory"
})
public class ArrayOfDailyInventory {

    @XmlElement(name = "DailyInventory", nillable = true)
    protected List<DailyInventory> dailyInventory;

    /**
     * Gets the value of the dailyInventory property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dailyInventory property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDailyInventory().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DailyInventory }
     * 
     * 
     */
    public List<DailyInventory> getDailyInventory() {
        if (dailyInventory == null) {
            dailyInventory = new ArrayList<DailyInventory>();
        }
        return this.dailyInventory;
    }

}
