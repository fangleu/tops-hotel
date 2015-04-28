
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
 * <p>Java class for CacheType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CacheType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="OtherInfo" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
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
 *         &lt;element name="TimeSpan" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="ChangeDateMask" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="Start" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
 *                 &lt;attribute name="End" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="BrandCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ChainCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="HotelCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CacheType", propOrder = {
    "otherInfo",
    "timeSpan"
})
public class CacheType {

    @XmlElement(name = "OtherInfo")
    protected CacheType.OtherInfo otherInfo;
    @XmlElement(name = "TimeSpan")
    protected CacheType.TimeSpan timeSpan;
    @XmlAttribute(name = "BrandCode")
    protected String brandCode;
    @XmlAttribute(name = "ChainCode")
    protected String chainCode;
    @XmlAttribute(name = "HotelCode")
    protected String hotelCode;

    /**
     * Gets the value of the otherInfo property.
     * 
     * @return
     *     possible object is
     *     {@link CacheType.OtherInfo }
     *     
     */
    public CacheType.OtherInfo getOtherInfo() {
        return otherInfo;
    }

    /**
     * Sets the value of the otherInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CacheType.OtherInfo }
     *     
     */
    public void setOtherInfo(CacheType.OtherInfo value) {
        this.otherInfo = value;
    }

    /**
     * Gets the value of the timeSpan property.
     * 
     * @return
     *     possible object is
     *     {@link CacheType.TimeSpan }
     *     
     */
    public CacheType.TimeSpan getTimeSpan() {
        return timeSpan;
    }

    /**
     * Sets the value of the timeSpan property.
     * 
     * @param value
     *     allowed object is
     *     {@link CacheType.TimeSpan }
     *     
     */
    public void setTimeSpan(CacheType.TimeSpan value) {
        this.timeSpan = value;
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
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
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
    @XmlType(name = "")
    public static class OtherInfo {

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
     *       &lt;attribute name="ChangeDateMask" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="Start" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
     *       &lt;attribute name="End" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class TimeSpan {

        @XmlAttribute(name = "ChangeDateMask")
        protected String changeDateMask;
        @XmlAttribute(name = "Start", required = true)
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar start;
        @XmlAttribute(name = "End", required = true)
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar end;

        /**
         * Gets the value of the changeDateMask property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getChangeDateMask() {
            return changeDateMask;
        }

        /**
         * Sets the value of the changeDateMask property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setChangeDateMask(String value) {
            this.changeDateMask = value;
        }

        /**
         * Gets the value of the start property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getStart() {
            return start;
        }

        /**
         * Sets the value of the start property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setStart(XMLGregorianCalendar value) {
            this.start = value;
        }

        /**
         * Gets the value of the end property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getEnd() {
            return end;
        }

        /**
         * Sets the value of the end property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setEnd(XMLGregorianCalendar value) {
            this.end = value;
        }

    }

}
