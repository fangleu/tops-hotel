
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
 * <p>Java class for MultipleAvailRequestSegments complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MultipleAvailRequestSegments">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MultipleAvailRequestSegment" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Currency" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;attribute name="Code" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="HotelSearchCriteria" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="Criterion" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="HotelRef" minOccurs="0">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;attribute name="HotelCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                             &lt;/restriction>
 *                                           &lt;/complexContent>
 *                                         &lt;/complexType>
 *                                       &lt;/element>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="LengthOfStay" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;attribute name="Start" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *                           &lt;attribute name="End" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="RatePlans" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="RatePlan" maxOccurs="unbounded" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="Currency" minOccurs="0">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;attribute name="Code" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                             &lt;/restriction>
 *                                           &lt;/complexContent>
 *                                         &lt;/complexType>
 *                                       &lt;/element>
 *                                       &lt;element name="Rooms" type="{http://www.opentravel.org/OTA/2003/05}ArrayOfRoom" minOccurs="0"/>
 *                                     &lt;/sequence>
 *                                     &lt;attribute name="RatePlanCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                     &lt;attribute name="RatePlanType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="StayDateRange" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;attribute name="End" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
 *                           &lt;attribute name="Start" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *                 &lt;attribute name="AvailReqType" type="{http://www.w3.org/2001/XMLSchema}string" />
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
@XmlType(name = "MultipleAvailRequestSegments", propOrder = {
    "multipleAvailRequestSegment"
})
public class MultipleAvailRequestSegments {

    @XmlElement(name = "MultipleAvailRequestSegment")
    protected MultipleAvailRequestSegments.MultipleAvailRequestSegment multipleAvailRequestSegment;

    /**
     * Gets the value of the multipleAvailRequestSegment property.
     * 
     * @return
     *     possible object is
     *     {@link MultipleAvailRequestSegments.MultipleAvailRequestSegment }
     *     
     */
    public MultipleAvailRequestSegments.MultipleAvailRequestSegment getMultipleAvailRequestSegment() {
        return multipleAvailRequestSegment;
    }

    /**
     * Sets the value of the multipleAvailRequestSegment property.
     * 
     * @param value
     *     allowed object is
     *     {@link MultipleAvailRequestSegments.MultipleAvailRequestSegment }
     *     
     */
    public void setMultipleAvailRequestSegment(MultipleAvailRequestSegments.MultipleAvailRequestSegment value) {
        this.multipleAvailRequestSegment = value;
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
     *         &lt;element name="Currency" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;attribute name="Code" type="{http://www.w3.org/2001/XMLSchema}string" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="HotelSearchCriteria" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="Criterion" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="HotelRef" minOccurs="0">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;attribute name="HotelCode" type="{http://www.w3.org/2001/XMLSchema}string" />
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
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="LengthOfStay" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;attribute name="Start" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
     *                 &lt;attribute name="End" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="RatePlans" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="RatePlan" maxOccurs="unbounded" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="Currency" minOccurs="0">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;attribute name="Code" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                                   &lt;/restriction>
     *                                 &lt;/complexContent>
     *                               &lt;/complexType>
     *                             &lt;/element>
     *                             &lt;element name="Rooms" type="{http://www.opentravel.org/OTA/2003/05}ArrayOfRoom" minOccurs="0"/>
     *                           &lt;/sequence>
     *                           &lt;attribute name="RatePlanCode" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                           &lt;attribute name="RatePlanType" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="StayDateRange" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;attribute name="End" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
     *                 &lt;attribute name="Start" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *       &lt;attribute name="AvailReqType" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "currency",
        "hotelSearchCriteria",
        "lengthOfStay",
        "ratePlans",
        "stayDateRange"
    })
    public static class MultipleAvailRequestSegment {

        @XmlElement(name = "Currency")
        protected MultipleAvailRequestSegments.MultipleAvailRequestSegment.Currency currency;
        @XmlElement(name = "HotelSearchCriteria")
        protected MultipleAvailRequestSegments.MultipleAvailRequestSegment.HotelSearchCriteria hotelSearchCriteria;
        @XmlElement(name = "LengthOfStay")
        protected MultipleAvailRequestSegments.MultipleAvailRequestSegment.LengthOfStay lengthOfStay;
        @XmlElement(name = "RatePlans")
        protected MultipleAvailRequestSegments.MultipleAvailRequestSegment.RatePlans ratePlans;
        @XmlElement(name = "StayDateRange")
        protected MultipleAvailRequestSegments.MultipleAvailRequestSegment.StayDateRange stayDateRange;
        @XmlAttribute(name = "AvailReqType")
        protected String availReqType;

        /**
         * Gets the value of the currency property.
         * 
         * @return
         *     possible object is
         *     {@link MultipleAvailRequestSegments.MultipleAvailRequestSegment.Currency }
         *     
         */
        public MultipleAvailRequestSegments.MultipleAvailRequestSegment.Currency getCurrency() {
            return currency;
        }

        /**
         * Sets the value of the currency property.
         * 
         * @param value
         *     allowed object is
         *     {@link MultipleAvailRequestSegments.MultipleAvailRequestSegment.Currency }
         *     
         */
        public void setCurrency(MultipleAvailRequestSegments.MultipleAvailRequestSegment.Currency value) {
            this.currency = value;
        }

        /**
         * Gets the value of the hotelSearchCriteria property.
         * 
         * @return
         *     possible object is
         *     {@link MultipleAvailRequestSegments.MultipleAvailRequestSegment.HotelSearchCriteria }
         *     
         */
        public MultipleAvailRequestSegments.MultipleAvailRequestSegment.HotelSearchCriteria getHotelSearchCriteria() {
            return hotelSearchCriteria;
        }

        /**
         * Sets the value of the hotelSearchCriteria property.
         * 
         * @param value
         *     allowed object is
         *     {@link MultipleAvailRequestSegments.MultipleAvailRequestSegment.HotelSearchCriteria }
         *     
         */
        public void setHotelSearchCriteria(MultipleAvailRequestSegments.MultipleAvailRequestSegment.HotelSearchCriteria value) {
            this.hotelSearchCriteria = value;
        }

        /**
         * Gets the value of the lengthOfStay property.
         * 
         * @return
         *     possible object is
         *     {@link MultipleAvailRequestSegments.MultipleAvailRequestSegment.LengthOfStay }
         *     
         */
        public MultipleAvailRequestSegments.MultipleAvailRequestSegment.LengthOfStay getLengthOfStay() {
            return lengthOfStay;
        }

        /**
         * Sets the value of the lengthOfStay property.
         * 
         * @param value
         *     allowed object is
         *     {@link MultipleAvailRequestSegments.MultipleAvailRequestSegment.LengthOfStay }
         *     
         */
        public void setLengthOfStay(MultipleAvailRequestSegments.MultipleAvailRequestSegment.LengthOfStay value) {
            this.lengthOfStay = value;
        }

        /**
         * Gets the value of the ratePlans property.
         * 
         * @return
         *     possible object is
         *     {@link MultipleAvailRequestSegments.MultipleAvailRequestSegment.RatePlans }
         *     
         */
        public MultipleAvailRequestSegments.MultipleAvailRequestSegment.RatePlans getRatePlans() {
            return ratePlans;
        }

        /**
         * Sets the value of the ratePlans property.
         * 
         * @param value
         *     allowed object is
         *     {@link MultipleAvailRequestSegments.MultipleAvailRequestSegment.RatePlans }
         *     
         */
        public void setRatePlans(MultipleAvailRequestSegments.MultipleAvailRequestSegment.RatePlans value) {
            this.ratePlans = value;
        }

        /**
         * Gets the value of the stayDateRange property.
         * 
         * @return
         *     possible object is
         *     {@link MultipleAvailRequestSegments.MultipleAvailRequestSegment.StayDateRange }
         *     
         */
        public MultipleAvailRequestSegments.MultipleAvailRequestSegment.StayDateRange getStayDateRange() {
            return stayDateRange;
        }

        /**
         * Sets the value of the stayDateRange property.
         * 
         * @param value
         *     allowed object is
         *     {@link MultipleAvailRequestSegments.MultipleAvailRequestSegment.StayDateRange }
         *     
         */
        public void setStayDateRange(MultipleAvailRequestSegments.MultipleAvailRequestSegment.StayDateRange value) {
            this.stayDateRange = value;
        }

        /**
         * Gets the value of the availReqType property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAvailReqType() {
            return availReqType;
        }

        /**
         * Sets the value of the availReqType property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAvailReqType(String value) {
            this.availReqType = value;
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
         *       &lt;attribute name="Code" type="{http://www.w3.org/2001/XMLSchema}string" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class Currency {

            @XmlAttribute(name = "Code")
            protected String code;

            /**
             * Gets the value of the code property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCode() {
                return code;
            }

            /**
             * Sets the value of the code property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCode(String value) {
                this.code = value;
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
         *       &lt;sequence>
         *         &lt;element name="Criterion" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="HotelRef" minOccurs="0">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;attribute name="HotelCode" type="{http://www.w3.org/2001/XMLSchema}string" />
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
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "criterion"
        })
        public static class HotelSearchCriteria {

            @XmlElement(name = "Criterion")
            protected MultipleAvailRequestSegments.MultipleAvailRequestSegment.HotelSearchCriteria.Criterion criterion;

            /**
             * Gets the value of the criterion property.
             * 
             * @return
             *     possible object is
             *     {@link MultipleAvailRequestSegments.MultipleAvailRequestSegment.HotelSearchCriteria.Criterion }
             *     
             */
            public MultipleAvailRequestSegments.MultipleAvailRequestSegment.HotelSearchCriteria.Criterion getCriterion() {
                return criterion;
            }

            /**
             * Sets the value of the criterion property.
             * 
             * @param value
             *     allowed object is
             *     {@link MultipleAvailRequestSegments.MultipleAvailRequestSegment.HotelSearchCriteria.Criterion }
             *     
             */
            public void setCriterion(MultipleAvailRequestSegments.MultipleAvailRequestSegment.HotelSearchCriteria.Criterion value) {
                this.criterion = value;
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
             *         &lt;element name="HotelRef" minOccurs="0">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;attribute name="HotelCode" type="{http://www.w3.org/2001/XMLSchema}string" />
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
                "hotelRef"
            })
            public static class Criterion {

                @XmlElement(name = "HotelRef")
                protected MultipleAvailRequestSegments.MultipleAvailRequestSegment.HotelSearchCriteria.Criterion.HotelRef hotelRef;

                /**
                 * Gets the value of the hotelRef property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link MultipleAvailRequestSegments.MultipleAvailRequestSegment.HotelSearchCriteria.Criterion.HotelRef }
                 *     
                 */
                public MultipleAvailRequestSegments.MultipleAvailRequestSegment.HotelSearchCriteria.Criterion.HotelRef getHotelRef() {
                    return hotelRef;
                }

                /**
                 * Sets the value of the hotelRef property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link MultipleAvailRequestSegments.MultipleAvailRequestSegment.HotelSearchCriteria.Criterion.HotelRef }
                 *     
                 */
                public void setHotelRef(MultipleAvailRequestSegments.MultipleAvailRequestSegment.HotelSearchCriteria.Criterion.HotelRef value) {
                    this.hotelRef = value;
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
                 *       &lt;attribute name="HotelCode" type="{http://www.w3.org/2001/XMLSchema}string" />
                 *     &lt;/restriction>
                 *   &lt;/complexContent>
                 * &lt;/complexType>
                 * </pre>
                 * 
                 * 
                 */
                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "")
                public static class HotelRef {

                    @XmlAttribute(name = "HotelCode")
                    protected String hotelCode;

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

                }

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
         *       &lt;attribute name="Start" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
         *       &lt;attribute name="End" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class LengthOfStay {

            @XmlAttribute(name = "Start", required = true)
            protected int start;
            @XmlAttribute(name = "End", required = true)
            protected int end;

            /**
             * Gets the value of the start property.
             * 
             */
            public int getStart() {
                return start;
            }

            /**
             * Sets the value of the start property.
             * 
             */
            public void setStart(int value) {
                this.start = value;
            }

            /**
             * Gets the value of the end property.
             * 
             */
            public int getEnd() {
                return end;
            }

            /**
             * Sets the value of the end property.
             * 
             */
            public void setEnd(int value) {
                this.end = value;
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
         *       &lt;sequence>
         *         &lt;element name="RatePlan" maxOccurs="unbounded" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="Currency" minOccurs="0">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;attribute name="Code" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                         &lt;/restriction>
         *                       &lt;/complexContent>
         *                     &lt;/complexType>
         *                   &lt;/element>
         *                   &lt;element name="Rooms" type="{http://www.opentravel.org/OTA/2003/05}ArrayOfRoom" minOccurs="0"/>
         *                 &lt;/sequence>
         *                 &lt;attribute name="RatePlanCode" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                 &lt;attribute name="RatePlanType" type="{http://www.w3.org/2001/XMLSchema}string" />
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
            "ratePlan"
        })
        public static class RatePlans {

            @XmlElement(name = "RatePlan")
            protected List<MultipleAvailRequestSegments.MultipleAvailRequestSegment.RatePlans.RatePlan> ratePlan;

            /**
             * Gets the value of the ratePlan property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the ratePlan property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getRatePlan().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link MultipleAvailRequestSegments.MultipleAvailRequestSegment.RatePlans.RatePlan }
             * 
             * 
             */
            public List<MultipleAvailRequestSegments.MultipleAvailRequestSegment.RatePlans.RatePlan> getRatePlan() {
                if (ratePlan == null) {
                    ratePlan = new ArrayList<MultipleAvailRequestSegments.MultipleAvailRequestSegment.RatePlans.RatePlan>();
                }
                return this.ratePlan;
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
             *         &lt;element name="Currency" minOccurs="0">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;attribute name="Code" type="{http://www.w3.org/2001/XMLSchema}string" />
             *               &lt;/restriction>
             *             &lt;/complexContent>
             *           &lt;/complexType>
             *         &lt;/element>
             *         &lt;element name="Rooms" type="{http://www.opentravel.org/OTA/2003/05}ArrayOfRoom" minOccurs="0"/>
             *       &lt;/sequence>
             *       &lt;attribute name="RatePlanCode" type="{http://www.w3.org/2001/XMLSchema}string" />
             *       &lt;attribute name="RatePlanType" type="{http://www.w3.org/2001/XMLSchema}string" />
             *     &lt;/restriction>
             *   &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "currency",
                "rooms"
            })
            public static class RatePlan {

                @XmlElement(name = "Currency")
                protected MultipleAvailRequestSegments.MultipleAvailRequestSegment.RatePlans.RatePlan.Currency currency;
                @XmlElement(name = "Rooms")
                protected ArrayOfRoom rooms;
                @XmlAttribute(name = "RatePlanCode")
                protected String ratePlanCode;
                @XmlAttribute(name = "RatePlanType")
                protected String ratePlanType;

                /**
                 * Gets the value of the currency property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link MultipleAvailRequestSegments.MultipleAvailRequestSegment.RatePlans.RatePlan.Currency }
                 *     
                 */
                public MultipleAvailRequestSegments.MultipleAvailRequestSegment.RatePlans.RatePlan.Currency getCurrency() {
                    return currency;
                }

                /**
                 * Sets the value of the currency property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link MultipleAvailRequestSegments.MultipleAvailRequestSegment.RatePlans.RatePlan.Currency }
                 *     
                 */
                public void setCurrency(MultipleAvailRequestSegments.MultipleAvailRequestSegment.RatePlans.RatePlan.Currency value) {
                    this.currency = value;
                }

                /**
                 * Gets the value of the rooms property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link ArrayOfRoom }
                 *     
                 */
                public ArrayOfRoom getRooms() {
                    return rooms;
                }

                /**
                 * Sets the value of the rooms property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link ArrayOfRoom }
                 *     
                 */
                public void setRooms(ArrayOfRoom value) {
                    this.rooms = value;
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
                 * <p>Java class for anonymous complex type.
                 * 
                 * <p>The following schema fragment specifies the expected content contained within this class.
                 * 
                 * <pre>
                 * &lt;complexType>
                 *   &lt;complexContent>
                 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 *       &lt;attribute name="Code" type="{http://www.w3.org/2001/XMLSchema}string" />
                 *     &lt;/restriction>
                 *   &lt;/complexContent>
                 * &lt;/complexType>
                 * </pre>
                 * 
                 * 
                 */
                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "")
                public static class Currency {

                    @XmlAttribute(name = "Code")
                    protected String code;

                    /**
                     * Gets the value of the code property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getCode() {
                        return code;
                    }

                    /**
                     * Sets the value of the code property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setCode(String value) {
                        this.code = value;
                    }

                }

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
         *       &lt;attribute name="End" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
         *       &lt;attribute name="Start" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class StayDateRange {

            @XmlAttribute(name = "End", required = true)
            @XmlSchemaType(name = "date")
            protected XMLGregorianCalendar end;
            @XmlAttribute(name = "Start", required = true)
            @XmlSchemaType(name = "date")
            protected XMLGregorianCalendar start;

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

        }

    }

}
