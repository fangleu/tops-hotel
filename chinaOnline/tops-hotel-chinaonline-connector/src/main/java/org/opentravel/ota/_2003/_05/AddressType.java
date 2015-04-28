
package org.opentravel.ota._2003._05;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for AddressType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AddressType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AddressLine" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="BldgRoom" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                 &lt;attribute name="BldgNameIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="CityName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CountryName" type="{http://www.opentravel.org/OTA/2003/05}CountryNameType" minOccurs="0"/>
 *         &lt;element name="County" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PostalCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="StateProv" type="{http://www.opentravel.org/OTA/2003/05}StateProvType" minOccurs="0"/>
 *         &lt;element name="StreetNmbr" minOccurs="0">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.opentravel.org/OTA/2003/05>StreetNmbrType">
 *                 &lt;attribute name="RuralRouteNmbr" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="StreetDirection" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="StreetNmbrSuffix" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="FormattedInd" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="Language" type="{http://www.w3.org/2001/XMLSchema}language" />
 *       &lt;attribute name="Remark" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ShareMarketInd">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="Yes"/>
 *             &lt;enumeration value="No"/>
 *             &lt;enumeration value="Inherit"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="ShareSynchInd">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="Yes"/>
 *             &lt;enumeration value="No"/>
 *             &lt;enumeration value="Inherit"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="Type" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ValidInd" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AddressType", propOrder = {
    "addressLine",
    "bldgRoom",
    "cityName",
    "countryName",
    "county",
    "postalCode",
    "stateProv",
    "streetNmbr"
})
@XmlSeeAlso({
    AddressInfoType.class
})
public class AddressType {

    @XmlElement(name = "AddressLine")
    protected List<String> addressLine;
    @XmlElement(name = "BldgRoom")
    protected List<AddressType.BldgRoom> bldgRoom;
    @XmlElement(name = "CityName")
    protected String cityName;
    @XmlElement(name = "CountryName")
    protected CountryNameType countryName;
    @XmlElement(name = "County")
    protected String county;
    @XmlElement(name = "PostalCode")
    protected String postalCode;
    @XmlElement(name = "StateProv")
    protected StateProvType stateProv;
    @XmlElement(name = "StreetNmbr")
    protected AddressType.StreetNmbr streetNmbr;
    @XmlAttribute(name = "FormattedInd")
    protected Boolean formattedInd;
    @XmlAttribute(name = "Language")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "language")
    protected String language;
    @XmlAttribute(name = "Remark")
    protected String remark;
    @XmlAttribute(name = "ShareMarketInd")
    protected String shareMarketInd;
    @XmlAttribute(name = "ShareSynchInd")
    protected String shareSynchInd;
    @XmlAttribute(name = "Type")
    protected String type;
    @XmlAttribute(name = "ValidInd")
    protected Boolean validInd;

    /**
     * Gets the value of the addressLine property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the addressLine property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAddressLine().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getAddressLine() {
        if (addressLine == null) {
            addressLine = new ArrayList<String>();
        }
        return this.addressLine;
    }

    /**
     * Gets the value of the bldgRoom property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the bldgRoom property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBldgRoom().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AddressType.BldgRoom }
     * 
     * 
     */
    public List<AddressType.BldgRoom> getBldgRoom() {
        if (bldgRoom == null) {
            bldgRoom = new ArrayList<AddressType.BldgRoom>();
        }
        return this.bldgRoom;
    }

    /**
     * Gets the value of the cityName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * Sets the value of the cityName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCityName(String value) {
        this.cityName = value;
    }

    /**
     * Gets the value of the countryName property.
     * 
     * @return
     *     possible object is
     *     {@link CountryNameType }
     *     
     */
    public CountryNameType getCountryName() {
        return countryName;
    }

    /**
     * Sets the value of the countryName property.
     * 
     * @param value
     *     allowed object is
     *     {@link CountryNameType }
     *     
     */
    public void setCountryName(CountryNameType value) {
        this.countryName = value;
    }

    /**
     * Gets the value of the county property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCounty() {
        return county;
    }

    /**
     * Sets the value of the county property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCounty(String value) {
        this.county = value;
    }

    /**
     * Gets the value of the postalCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Sets the value of the postalCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostalCode(String value) {
        this.postalCode = value;
    }

    /**
     * Gets the value of the stateProv property.
     * 
     * @return
     *     possible object is
     *     {@link StateProvType }
     *     
     */
    public StateProvType getStateProv() {
        return stateProv;
    }

    /**
     * Sets the value of the stateProv property.
     * 
     * @param value
     *     allowed object is
     *     {@link StateProvType }
     *     
     */
    public void setStateProv(StateProvType value) {
        this.stateProv = value;
    }

    /**
     * Gets the value of the streetNmbr property.
     * 
     * @return
     *     possible object is
     *     {@link AddressType.StreetNmbr }
     *     
     */
    public AddressType.StreetNmbr getStreetNmbr() {
        return streetNmbr;
    }

    /**
     * Sets the value of the streetNmbr property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddressType.StreetNmbr }
     *     
     */
    public void setStreetNmbr(AddressType.StreetNmbr value) {
        this.streetNmbr = value;
    }

    /**
     * Gets the value of the formattedInd property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isFormattedInd() {
        return formattedInd;
    }

    /**
     * Sets the value of the formattedInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFormattedInd(Boolean value) {
        this.formattedInd = value;
    }

    /**
     * Gets the value of the language property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Sets the value of the language property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLanguage(String value) {
        this.language = value;
    }

    /**
     * Gets the value of the remark property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemark() {
        return remark;
    }

    /**
     * Sets the value of the remark property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemark(String value) {
        this.remark = value;
    }

    /**
     * Gets the value of the shareMarketInd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShareMarketInd() {
        return shareMarketInd;
    }

    /**
     * Sets the value of the shareMarketInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShareMarketInd(String value) {
        this.shareMarketInd = value;
    }

    /**
     * Gets the value of the shareSynchInd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShareSynchInd() {
        return shareSynchInd;
    }

    /**
     * Sets the value of the shareSynchInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShareSynchInd(String value) {
        this.shareSynchInd = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the validInd property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isValidInd() {
        return validInd;
    }

    /**
     * Sets the value of the validInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setValidInd(Boolean value) {
        this.validInd = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;simpleContent>
     *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
     *       &lt;attribute name="BldgNameIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" />
     *     &lt;/extension>
     *   &lt;/simpleContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "value"
    })
    public static class BldgRoom {

        @XmlValue
        protected String value;
        @XmlAttribute(name = "BldgNameIndicator")
        protected Boolean bldgNameIndicator;

        /**
         * Gets the value of the value property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setValue(String value) {
            this.value = value;
        }

        /**
         * Gets the value of the bldgNameIndicator property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isBldgNameIndicator() {
            return bldgNameIndicator;
        }

        /**
         * Sets the value of the bldgNameIndicator property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setBldgNameIndicator(Boolean value) {
            this.bldgNameIndicator = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;simpleContent>
     *     &lt;extension base="&lt;http://www.opentravel.org/OTA/2003/05>StreetNmbrType">
     *       &lt;attribute name="RuralRouteNmbr" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="StreetDirection" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="StreetNmbrSuffix" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/extension>
     *   &lt;/simpleContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class StreetNmbr
        extends StreetNmbrType
    {

        @XmlAttribute(name = "RuralRouteNmbr")
        protected String ruralRouteNmbr;
        @XmlAttribute(name = "StreetDirection")
        protected String streetDirection;
        @XmlAttribute(name = "StreetNmbrSuffix")
        protected String streetNmbrSuffix;

        /**
         * Gets the value of the ruralRouteNmbr property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getRuralRouteNmbr() {
            return ruralRouteNmbr;
        }

        /**
         * Sets the value of the ruralRouteNmbr property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setRuralRouteNmbr(String value) {
            this.ruralRouteNmbr = value;
        }

        /**
         * Gets the value of the streetDirection property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getStreetDirection() {
            return streetDirection;
        }

        /**
         * Sets the value of the streetDirection property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setStreetDirection(String value) {
            this.streetDirection = value;
        }

        /**
         * Gets the value of the streetNmbrSuffix property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getStreetNmbrSuffix() {
            return streetNmbrSuffix;
        }

        /**
         * Sets the value of the streetNmbrSuffix property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setStreetNmbrSuffix(String value) {
            this.streetNmbrSuffix = value;
        }

    }

}
