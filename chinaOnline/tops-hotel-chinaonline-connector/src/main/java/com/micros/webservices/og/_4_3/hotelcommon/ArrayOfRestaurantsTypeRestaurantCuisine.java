
package com.micros.webservices.og._4_3.hotelcommon;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfRestaurantsTypeRestaurantCuisine complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfRestaurantsTypeRestaurantCuisine">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Cuisine" type="{http://webservices.micros.com/og/4.3/HotelCommon/}RestaurantsTypeRestaurantCuisine" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfRestaurantsTypeRestaurantCuisine", propOrder = {
    "cuisine"
})
public class ArrayOfRestaurantsTypeRestaurantCuisine {

    @XmlElement(name = "Cuisine")
    protected List<RestaurantsTypeRestaurantCuisine> cuisine;

    /**
     * Gets the value of the cuisine property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cuisine property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCuisine().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RestaurantsTypeRestaurantCuisine }
     * 
     * 
     */
    public List<RestaurantsTypeRestaurantCuisine> getCuisine() {
        if (cuisine == null) {
            cuisine = new ArrayList<RestaurantsTypeRestaurantCuisine>();
        }
        return this.cuisine;
    }

}
