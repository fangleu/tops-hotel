
package org.opentravel.ota._2003._05;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for OTA_HotelCacheChangeRQCacheSearchCriteria complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OTA_HotelCacheChangeRQCacheSearchCriteria">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CacheSearchCriterion" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="GeographicalInfo" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="Address" type="{http://www.opentravel.org/OTA/2003/05}AddressType" minOccurs="0"/>
 *                             &lt;element name="Position" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;attribute name="Altitude" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                     &lt;attribute name="AltitudeUnitOfMeasureCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                     &lt;attribute name="Latitude" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                     &lt;attribute name="Longitude" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                     &lt;attribute name="PositionAccuracyCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="Radius" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;attribute name="Direction" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                     &lt;attribute name="Distance" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                     &lt;attribute name="DistanceMax" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                     &lt;attribute name="DistanceMeasure" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                     &lt;attribute name="UnitOfMeasureCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *                 &lt;attribute name="BrandCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="CacheEndDateTime" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="CacheStartDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *                 &lt;attribute name="ChainCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="HotelCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="InvBlockCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="PromotionCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="PromotionVendorCode">
 *                   &lt;simpleType>
 *                     &lt;list itemType="{http://www.w3.org/2001/XMLSchema}string" />
 *                   &lt;/simpleType>
 *                 &lt;/attribute>
 *                 &lt;attribute name="RatePlanCategory" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="RatePlanCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="RatePlanID" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="RatePlanQualifier" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *                 &lt;attribute name="RatePlanType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="RoomTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="AvailabilityInd" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="CacheFromTimestamp" use="required" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="MoreEchoDataToken" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="RatesInd" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="ResponseType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OTA_HotelCacheChangeRQCacheSearchCriteria", propOrder = {
    "cacheSearchCriterion"
})
public class OTAHotelCacheChangeRQCacheSearchCriteria {

    @XmlElement(name = "CacheSearchCriterion")
    protected List<OTAHotelCacheChangeRQCacheSearchCriteria.CacheSearchCriterion> cacheSearchCriterion;
    @XmlAttribute(name = "AvailabilityInd", required = true)
    protected boolean availabilityInd;
    @XmlAttribute(name = "CacheFromTimestamp", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar cacheFromTimestamp;
    @XmlAttribute(name = "MoreEchoDataToken")
    protected String moreEchoDataToken;
    @XmlAttribute(name = "RatesInd", required = true)
    protected boolean ratesInd;
    @XmlAttribute(name = "ResponseType")
    protected String responseType;

    /**
     * Gets the value of the cacheSearchCriterion property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cacheSearchCriterion property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCacheSearchCriterion().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OTAHotelCacheChangeRQCacheSearchCriteria.CacheSearchCriterion }
     * 
     * 
     */
    public List<OTAHotelCacheChangeRQCacheSearchCriteria.CacheSearchCriterion> getCacheSearchCriterion() {
        if (cacheSearchCriterion == null) {
            cacheSearchCriterion = new ArrayList<OTAHotelCacheChangeRQCacheSearchCriteria.CacheSearchCriterion>();
        }
        return this.cacheSearchCriterion;
    }

    /**
     * Gets the value of the availabilityInd property.
     * 
     */
    public boolean isAvailabilityInd() {
        return availabilityInd;
    }

    /**
     * Sets the value of the availabilityInd property.
     * 
     */
    public void setAvailabilityInd(boolean value) {
        this.availabilityInd = value;
    }

    /**
     * Gets the value of the cacheFromTimestamp property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCacheFromTimestamp() {
        return cacheFromTimestamp;
    }

    /**
     * Sets the value of the cacheFromTimestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCacheFromTimestamp(XMLGregorianCalendar value) {
        this.cacheFromTimestamp = value;
    }

    /**
     * Gets the value of the moreEchoDataToken property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMoreEchoDataToken() {
        return moreEchoDataToken;
    }

    /**
     * Sets the value of the moreEchoDataToken property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMoreEchoDataToken(String value) {
        this.moreEchoDataToken = value;
    }

    /**
     * Gets the value of the ratesInd property.
     * 
     */
    public boolean isRatesInd() {
        return ratesInd;
    }

    /**
     * Sets the value of the ratesInd property.
     * 
     */
    public void setRatesInd(boolean value) {
        this.ratesInd = value;
    }

    /**
     * Gets the value of the responseType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResponseType() {
        return responseType;
    }

    /**
     * Sets the value of the responseType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResponseType(String value) {
        this.responseType = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="GeographicalInfo" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="Address" type="{http://www.opentravel.org/OTA/2003/05}AddressType" minOccurs="0"/>
     *                   &lt;element name="Position" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;attribute name="Altitude" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                           &lt;attribute name="AltitudeUnitOfMeasureCode" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                           &lt;attribute name="Latitude" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                           &lt;attribute name="Longitude" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                           &lt;attribute name="PositionAccuracyCode" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="Radius" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;attribute name="Direction" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                           &lt;attribute name="Distance" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                           &lt;attribute name="DistanceMax" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                           &lt;attribute name="DistanceMeasure" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                           &lt;attribute name="UnitOfMeasureCode" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *       &lt;attribute name="BrandCode" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="CacheEndDateTime" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="CacheStartDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
     *       &lt;attribute name="ChainCode" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="HotelCode" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="InvBlockCode" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="PromotionCode" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="PromotionVendorCode">
     *         &lt;simpleType>
     *           &lt;list itemType="{http://www.w3.org/2001/XMLSchema}string" />
     *         &lt;/simpleType>
     *       &lt;/attribute>
     *       &lt;attribute name="RatePlanCategory" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="RatePlanCode" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="RatePlanID" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="RatePlanQualifier" type="{http://www.w3.org/2001/XMLSchema}boolean" />
     *       &lt;attribute name="RatePlanType" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="RoomTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "geographicalInfo"
    })
    public static class CacheSearchCriterion {

        @XmlElement(name = "GeographicalInfo")
        protected OTAHotelCacheChangeRQCacheSearchCriteria.CacheSearchCriterion.GeographicalInfo geographicalInfo;
        @XmlAttribute(name = "BrandCode")
        protected String brandCode;
        @XmlAttribute(name = "CacheEndDateTime")
        protected String cacheEndDateTime;
        @XmlAttribute(name = "CacheStartDateTime")
        @XmlSchemaType(name = "dateTime")
        protected XMLGregorianCalendar cacheStartDateTime;
        @XmlAttribute(name = "ChainCode")
        protected String chainCode;
        @XmlAttribute(name = "HotelCode")
        protected String hotelCode;
        @XmlAttribute(name = "InvBlockCode")
        protected String invBlockCode;
        @XmlAttribute(name = "PromotionCode")
        protected String promotionCode;
        @XmlAttribute(name = "PromotionVendorCode")
        protected List<String> promotionVendorCode;
        @XmlAttribute(name = "RatePlanCategory")
        protected String ratePlanCategory;
        @XmlAttribute(name = "RatePlanCode")
        protected String ratePlanCode;
        @XmlAttribute(name = "RatePlanID")
        protected String ratePlanID;
        @XmlAttribute(name = "RatePlanQualifier")
        protected Boolean ratePlanQualifier;
        @XmlAttribute(name = "RatePlanType")
        protected String ratePlanType;
        @XmlAttribute(name = "RoomTypeCode")
        protected String roomTypeCode;

        /**
         * Gets the value of the geographicalInfo property.
         * 
         * @return
         *     possible object is
         *     {@link OTAHotelCacheChangeRQCacheSearchCriteria.CacheSearchCriterion.GeographicalInfo }
         *     
         */
        public OTAHotelCacheChangeRQCacheSearchCriteria.CacheSearchCriterion.GeographicalInfo getGeographicalInfo() {
            return geographicalInfo;
        }

        /**
         * Sets the value of the geographicalInfo property.
         * 
         * @param value
         *     allowed object is
         *     {@link OTAHotelCacheChangeRQCacheSearchCriteria.CacheSearchCriterion.GeographicalInfo }
         *     
         */
        public void setGeographicalInfo(OTAHotelCacheChangeRQCacheSearchCriteria.CacheSearchCriterion.GeographicalInfo value) {
            this.geographicalInfo = value;
        }

        /**
         * Gets the value of the brandCode property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getBrandCode() {
            return brandCode;
        }

        /**
         * Sets the value of the brandCode property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setBrandCode(String value) {
            this.brandCode = value;
        }

        /**
         * Gets the value of the cacheEndDateTime property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCacheEndDateTime() {
            return cacheEndDateTime;
        }

        /**
         * Sets the value of the cacheEndDateTime property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCacheEndDateTime(String value) {
            this.cacheEndDateTime = value;
        }

        /**
         * Gets the value of the cacheStartDateTime property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getCacheStartDateTime() {
            return cacheStartDateTime;
        }

        /**
         * Sets the value of the cacheStartDateTime property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setCacheStartDateTime(XMLGregorianCalendar value) {
            this.cacheStartDateTime = value;
        }

        /**
         * Gets the value of the chainCode property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getChainCode() {
            return chainCode;
        }

        /**
         * Sets the value of the chainCode property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setChainCode(String value) {
            this.chainCode = value;
        }

        /**
         * Gets the value of the hotelCode property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getHotelCode() {
            return hotelCode;
        }

        /**
         * Sets the value of the hotelCode property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setHotelCode(String value) {
            this.hotelCode = value;
        }

        /**
         * Gets the value of the invBlockCode property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getInvBlockCode() {
            return invBlockCode;
        }

        /**
         * Sets the value of the invBlockCode property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setInvBlockCode(String value) {
            this.invBlockCode = value;
        }

        /**
         * Gets the value of the promotionCode property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPromotionCode() {
            return promotionCode;
        }

        /**
         * Sets the value of the promotionCode property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPromotionCode(String value) {
            this.promotionCode = value;
        }

        /**
         * Gets the value of the promotionVendorCode property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the promotionVendorCode property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getPromotionVendorCode().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getPromotionVendorCode() {
            if (promotionVendorCode == null) {
                promotionVendorCode = new ArrayList<String>();
            }
            return this.promotionVendorCode;
        }

        /**
         * Gets the value of the ratePlanCategory property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getRatePlanCategory() {
            return ratePlanCategory;
        }

        /**
         * Sets the value of the ratePlanCategory property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setRatePlanCategory(String value) {
            this.ratePlanCategory = value;
        }

        /**
         * Gets the value of the ratePlanCode property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getRatePlanCode() {
            return ratePlanCode;
        }

        /**
         * Sets the value of the ratePlanCode property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setRatePlanCode(String value) {
            this.ratePlanCode = value;
        }

        /**
         * Gets the value of the ratePlanID property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getRatePlanID() {
            return ratePlanID;
        }

        /**
         * Sets the value of the ratePlanID property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setRatePlanID(String value) {
            this.ratePlanID = value;
        }

        /**
         * Gets the value of the ratePlanQualifier property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isRatePlanQualifier() {
            return ratePlanQualifier;
        }

        /**
         * Sets the value of the ratePlanQualifier property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setRatePlanQualifier(Boolean value) {
            this.ratePlanQualifier = value;
        }

        /**
         * Gets the value of the ratePlanType property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getRatePlanType() {
            return ratePlanType;
        }

        /**
         * Sets the value of the ratePlanType property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setRatePlanType(String value) {
            this.ratePlanType = value;
        }

        /**
         * Gets the value of the roomTypeCode property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getRoomTypeCode() {
            return roomTypeCode;
        }

        /**
         * Sets the value of the roomTypeCode property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setRoomTypeCode(String value) {
            this.roomTypeCode = value;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="Address" type="{http://www.opentravel.org/OTA/2003/05}AddressType" minOccurs="0"/>
         *         &lt;element name="Position" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;attribute name="Altitude" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                 &lt;attribute name="AltitudeUnitOfMeasureCode" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                 &lt;attribute name="Latitude" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                 &lt;attribute name="Longitude" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                 &lt;attribute name="PositionAccuracyCode" type="{http://www.w3.org/2001/XMLSchema}string" />
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="Radius" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;attribute name="Direction" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                 &lt;attribute name="Distance" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                 &lt;attribute name="DistanceMax" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                 &lt;attribute name="DistanceMeasure" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                 &lt;attribute name="UnitOfMeasureCode" type="{http://www.w3.org/2001/XMLSchema}string" />
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "address",
            "position",
            "radius"
        })
        public static class GeographicalInfo {

            @XmlElement(name = "Address")
            protected AddressType address;
            @XmlElement(name = "Position")
            protected OTAHotelCacheChangeRQCacheSearchCriteria.CacheSearchCriterion.GeographicalInfo.Position position;
            @XmlElement(name = "Radius")
            protected OTAHotelCacheChangeRQCacheSearchCriteria.CacheSearchCriterion.GeographicalInfo.Radius radius;

            /**
             * Gets the value of the address property.
             * 
             * @return
             *     possible object is
             *     {@link AddressType }
             *     
             */
            public AddressType getAddress() {
                return address;
            }

            /**
             * Sets the value of the address property.
             * 
             * @param value
             *     allowed object is
             *     {@link AddressType }
             *     
             */
            public void setAddress(AddressType value) {
                this.address = value;
            }

            /**
             * Gets the value of the position property.
             * 
             * @return
             *     possible object is
             *     {@link OTAHotelCacheChangeRQCacheSearchCriteria.CacheSearchCriterion.GeographicalInfo.Position }
             *     
             */
            public OTAHotelCacheChangeRQCacheSearchCriteria.CacheSearchCriterion.GeographicalInfo.Position getPosition() {
                return position;
            }

            /**
             * Sets the value of the position property.
             * 
             * @param value
             *     allowed object is
             *     {@link OTAHotelCacheChangeRQCacheSearchCriteria.CacheSearchCriterion.GeographicalInfo.Position }
             *     
             */
            public void setPosition(OTAHotelCacheChangeRQCacheSearchCriteria.CacheSearchCriterion.GeographicalInfo.Position value) {
                this.position = value;
            }

            /**
             * Gets the value of the radius property.
             * 
             * @return
             *     possible object is
             *     {@link OTAHotelCacheChangeRQCacheSearchCriteria.CacheSearchCriterion.GeographicalInfo.Radius }
             *     
             */
            public OTAHotelCacheChangeRQCacheSearchCriteria.CacheSearchCriterion.GeographicalInfo.Radius getRadius() {
                return radius;
            }

            /**
             * Sets the value of the radius property.
             * 
             * @param value
             *     allowed object is
             *     {@link OTAHotelCacheChangeRQCacheSearchCriteria.CacheSearchCriterion.GeographicalInfo.Radius }
             *     
             */
            public void setRadius(OTAHotelCacheChangeRQCacheSearchCriteria.CacheSearchCriterion.GeographicalInfo.Radius value) {
                this.radius = value;
            }


            /**
             * <p>Java class for anonymous complex type.
             * 
             * <p>The following schema fragment specifies the expected content contained within this class.
             * 
             * <pre>
             * &lt;complexType>
             *   &lt;complexContent>
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *       &lt;attribute name="Altitude" type="{http://www.w3.org/2001/XMLSchema}string" />
             *       &lt;attribute name="AltitudeUnitOfMeasureCode" type="{http://www.w3.org/2001/XMLSchema}string" />
             *       &lt;attribute name="Latitude" type="{http://www.w3.org/2001/XMLSchema}string" />
             *       &lt;attribute name="Longitude" type="{http://www.w3.org/2001/XMLSchema}string" />
             *       &lt;attribute name="PositionAccuracyCode" type="{http://www.w3.org/2001/XMLSchema}string" />
             *     &lt;/restriction>
             *   &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class Position {

                @XmlAttribute(name = "Altitude")
                protected String altitude;
                @XmlAttribute(name = "AltitudeUnitOfMeasureCode")
                protected String altitudeUnitOfMeasureCode;
                @XmlAttribute(name = "Latitude")
                protected String latitude;
                @XmlAttribute(name = "Longitude")
                protected String longitude;
                @XmlAttribute(name = "PositionAccuracyCode")
                protected String positionAccuracyCode;

                /**
                 * Gets the value of the altitude property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getAltitude() {
                    return altitude;
                }

                /**
                 * Sets the value of the altitude property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setAltitude(String value) {
                    this.altitude = value;
                }

                /**
                 * Gets the value of the altitudeUnitOfMeasureCode property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getAltitudeUnitOfMeasureCode() {
                    return altitudeUnitOfMeasureCode;
                }

                /**
                 * Sets the value of the altitudeUnitOfMeasureCode property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setAltitudeUnitOfMeasureCode(String value) {
                    this.altitudeUnitOfMeasureCode = value;
                }

                /**
                 * Gets the value of the latitude property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getLatitude() {
                    return latitude;
                }

                /**
                 * Sets the value of the latitude property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setLatitude(String value) {
                    this.latitude = value;
                }

                /**
                 * Gets the value of the longitude property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getLongitude() {
                    return longitude;
                }

                /**
                 * Sets the value of the longitude property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setLongitude(String value) {
                    this.longitude = value;
                }

                /**
                 * Gets the value of the positionAccuracyCode property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getPositionAccuracyCode() {
                    return positionAccuracyCode;
                }

                /**
                 * Sets the value of the positionAccuracyCode property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setPositionAccuracyCode(String value) {
                    this.positionAccuracyCode = value;
                }

            }


            /**
             * <p>Java class for anonymous complex type.
             * 
             * <p>The following schema fragment specifies the expected content contained within this class.
             * 
             * <pre>
             * &lt;complexType>
             *   &lt;complexContent>
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *       &lt;attribute name="Direction" type="{http://www.w3.org/2001/XMLSchema}string" />
             *       &lt;attribute name="Distance" type="{http://www.w3.org/2001/XMLSchema}string" />
             *       &lt;attribute name="DistanceMax" type="{http://www.w3.org/2001/XMLSchema}string" />
             *       &lt;attribute name="DistanceMeasure" type="{http://www.w3.org/2001/XMLSchema}string" />
             *       &lt;attribute name="UnitOfMeasureCode" type="{http://www.w3.org/2001/XMLSchema}string" />
             *     &lt;/restriction>
             *   &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class Radius {

                @XmlAttribute(name = "Direction")
                protected String direction;
                @XmlAttribute(name = "Distance")
                protected String distance;
                @XmlAttribute(name = "DistanceMax")
                protected String distanceMax;
                @XmlAttribute(name = "DistanceMeasure")
                protected String distanceMeasure;
                @XmlAttribute(name = "UnitOfMeasureCode")
                protected String unitOfMeasureCode;

                /**
                 * Gets the value of the direction property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getDirection() {
                    return direction;
                }

                /**
                 * Sets the value of the direction property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setDirection(String value) {
                    this.direction = value;
                }

                /**
                 * Gets the value of the distance property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getDistance() {
                    return distance;
                }

                /**
                 * Sets the value of the distance property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setDistance(String value) {
                    this.distance = value;
                }

                /**
                 * Gets the value of the distanceMax property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getDistanceMax() {
                    return distanceMax;
                }

                /**
                 * Sets the value of the distanceMax property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setDistanceMax(String value) {
                    this.distanceMax = value;
                }

                /**
                 * Gets the value of the distanceMeasure property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getDistanceMeasure() {
                    return distanceMeasure;
                }

                /**
                 * Sets the value of the distanceMeasure property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setDistanceMeasure(String value) {
                    this.distanceMeasure = value;
                }

                /**
                 * Gets the value of the unitOfMeasureCode property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getUnitOfMeasureCode() {
                    return unitOfMeasureCode;
                }

                /**
                 * Sets the value of the unitOfMeasureCode property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setUnitOfMeasureCode(String value) {
                    this.unitOfMeasureCode = value;
                }

            }

        }

    }

}
