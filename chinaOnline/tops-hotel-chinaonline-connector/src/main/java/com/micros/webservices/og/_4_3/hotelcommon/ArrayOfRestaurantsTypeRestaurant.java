
package com.micros.webservices.og._4_3.hotelcommon;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfRestaurantsTypeRestaurant complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfRestaurantsTypeRestaurant">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Restaurant" type="{http://webservices.micros.com/og/4.3/HotelCommon/}RestaurantsTypeRestaurant" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfRestaurantsTypeRestaurant", propOrder = {
    "restaurant"
})
public class ArrayOfRestaurantsTypeRestaurant {

    @XmlElement(name = "Restaurant")
    protected List<RestaurantsTypeRestaurant> restaurant;

    /**
     * Gets the value of the restaurant property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the restaurant property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRestaurant().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RestaurantsTypeRestaurant }
     * 
     * 
     */
    public List<RestaurantsTypeRestaurant> getRestaurant() {
        if (restaurant == null) {
            restaurant = new ArrayList<RestaurantsTypeRestaurant>();
        }
        return this.restaurant;
    }

}
