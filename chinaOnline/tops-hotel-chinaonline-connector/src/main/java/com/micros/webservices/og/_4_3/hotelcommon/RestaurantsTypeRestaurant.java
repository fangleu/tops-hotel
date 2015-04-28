
package com.micros.webservices.og._4_3.hotelcommon;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RestaurantsTypeRestaurant complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RestaurantsTypeRestaurant">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RestaurantDescription" type="{http://webservices.micros.com/og/4.3/HotelCommon/}Paragraph" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="RelativePosition" type="{http://webservices.micros.com/og/4.3/HotelCommon/}Vector" minOccurs="0"/>
 *         &lt;element name="Cuisines" type="{http://webservices.micros.com/og/4.3/HotelCommon/}ArrayOfRestaurantsTypeRestaurantCuisine" minOccurs="0"/>
 *         &lt;element name="RestaurantContacts" type="{http://webservices.micros.com/og/4.3/HotelCommon/}ArrayOfAddress" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="RestaurantName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="MaxSeatingCapacity" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *       &lt;attribute name="MaxSingleParty" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *       &lt;attribute name="OfferBreakfast" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="OfferLunch" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="OfferDinner" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="OfferBrunch" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RestaurantsTypeRestaurant", propOrder = {
    "restaurantDescription",
    "relativePosition",
    "cuisines",
    "restaurantContacts"
})
public class RestaurantsTypeRestaurant {

    @XmlElement(name = "RestaurantDescription")
    protected List<Paragraph> restaurantDescription;
    @XmlElement(name = "RelativePosition")
    protected Vector relativePosition;
    @XmlElement(name = "Cuisines")
    protected ArrayOfRestaurantsTypeRestaurantCuisine cuisines;
    @XmlElement(name = "RestaurantContacts")
    protected ArrayOfAddress restaurantContacts;
    @XmlAttribute(name = "RestaurantName")
    protected String restaurantName;
    @XmlAttribute(name = "MaxSeatingCapacity")
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger maxSeatingCapacity;
    @XmlAttribute(name = "MaxSingleParty")
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger maxSingleParty;
    @XmlAttribute(name = "OfferBreakfast")
    protected Boolean offerBreakfast;
    @XmlAttribute(name = "OfferLunch")
    protected Boolean offerLunch;
    @XmlAttribute(name = "OfferDinner")
    protected Boolean offerDinner;
    @XmlAttribute(name = "OfferBrunch")
    protected Boolean offerBrunch;

    /**
     * Gets the value of the restaurantDescription property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the restaurantDescription property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRestaurantDescription().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Paragraph }
     * 
     * 
     */
    public List<Paragraph> getRestaurantDescription() {
        if (restaurantDescription == null) {
            restaurantDescription = new ArrayList<Paragraph>();
        }
        return this.restaurantDescription;
    }

    /**
     * Gets the value of the relativePosition property.
     * 
     * @return
     *     possible object is
     *     {@link Vector }
     *     
     */
    public Vector getRelativePosition() {
        return relativePosition;
    }

    /**
     * Sets the value of the relativePosition property.
     * 
     * @param value
     *     allowed object is
     *     {@link Vector }
     *     
     */
    public void setRelativePosition(Vector value) {
        this.relativePosition = value;
    }

    /**
     * Gets the value of the cuisines property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfRestaurantsTypeRestaurantCuisine }
     *     
     */
    public ArrayOfRestaurantsTypeRestaurantCuisine getCuisines() {
        return cuisines;
    }

    /**
     * Sets the value of the cuisines property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfRestaurantsTypeRestaurantCuisine }
     *     
     */
    public void setCuisines(ArrayOfRestaurantsTypeRestaurantCuisine value) {
        this.cuisines = value;
    }

    /**
     * Gets the value of the restaurantContacts property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfAddress }
     *     
     */
    public ArrayOfAddress getRestaurantContacts() {
        return restaurantContacts;
    }

    /**
     * Sets the value of the restaurantContacts property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfAddress }
     *     
     */
    public void setRestaurantContacts(ArrayOfAddress value) {
        this.restaurantContacts = value;
    }

    /**
     * Gets the value of the restaurantName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRestaurantName() {
        return restaurantName;
    }

    /**
     * Sets the value of the restaurantName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRestaurantName(String value) {
        this.restaurantName = value;
    }

    /**
     * Gets the value of the maxSeatingCapacity property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMaxSeatingCapacity() {
        return maxSeatingCapacity;
    }

    /**
     * Sets the value of the maxSeatingCapacity property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMaxSeatingCapacity(BigInteger value) {
        this.maxSeatingCapacity = value;
    }

    /**
     * Gets the value of the maxSingleParty property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMaxSingleParty() {
        return maxSingleParty;
    }

    /**
     * Sets the value of the maxSingleParty property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMaxSingleParty(BigInteger value) {
        this.maxSingleParty = value;
    }

    /**
     * Gets the value of the offerBreakfast property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isOfferBreakfast() {
        return offerBreakfast;
    }

    /**
     * Sets the value of the offerBreakfast property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setOfferBreakfast(Boolean value) {
        this.offerBreakfast = value;
    }

    /**
     * Gets the value of the offerLunch property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isOfferLunch() {
        return offerLunch;
    }

    /**
     * Sets the value of the offerLunch property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setOfferLunch(Boolean value) {
        this.offerLunch = value;
    }

    /**
     * Gets the value of the offerDinner property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isOfferDinner() {
        return offerDinner;
    }

    /**
     * Sets the value of the offerDinner property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setOfferDinner(Boolean value) {
        this.offerDinner = value;
    }

    /**
     * Gets the value of the offerBrunch property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isOfferBrunch() {
        return offerBrunch;
    }

    /**
     * Sets the value of the offerBrunch property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setOfferBrunch(Boolean value) {
        this.offerBrunch = value;
    }

}
