
package org.opentravel.ota._2003._05;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MultipleAvailResponseSegments complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MultipleAvailResponseSegments">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MultipleAvailResponseSegment" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
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
 *                   &lt;element name="RatePlans" type="{http://www.opentravel.org/OTA/2003/05}ArrayOfRatePlan" minOccurs="0"/>
 *                 &lt;/sequence>
 *                 &lt;attribute name="AvailResponseType" type="{http://www.w3.org/2001/XMLSchema}string" />
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
@XmlType(name = "MultipleAvailResponseSegments", propOrder = {
    "multipleAvailResponseSegment"
})
public class MultipleAvailResponseSegments {

    @XmlElement(name = "MultipleAvailResponseSegment")
    protected MultipleAvailResponseSegments.MultipleAvailResponseSegment multipleAvailResponseSegment;

    /**
     * Gets the value of the multipleAvailResponseSegment property.
     * 
     * @return
     *     possible object is
     *     {@link MultipleAvailResponseSegments.MultipleAvailResponseSegment }
     *     
     */
    public MultipleAvailResponseSegments.MultipleAvailResponseSegment getMultipleAvailResponseSegment() {
        return multipleAvailResponseSegment;
    }

    /**
     * Sets the value of the multipleAvailResponseSegment property.
     * 
     * @param value
     *     allowed object is
     *     {@link MultipleAvailResponseSegments.MultipleAvailResponseSegment }
     *     
     */
    public void setMultipleAvailResponseSegment(MultipleAvailResponseSegments.MultipleAvailResponseSegment value) {
        this.multipleAvailResponseSegment = value;
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
     *         &lt;element name="RatePlans" type="{http://www.opentravel.org/OTA/2003/05}ArrayOfRatePlan" minOccurs="0"/>
     *       &lt;/sequence>
     *       &lt;attribute name="AvailResponseType" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="HotelCode" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "lengthOfStay",
        "ratePlans"
    })
    public static class MultipleAvailResponseSegment {

        @XmlElement(name = "LengthOfStay")
        protected MultipleAvailResponseSegments.MultipleAvailResponseSegment.LengthOfStay lengthOfStay;
        @XmlElement(name = "RatePlans")
        protected ArrayOfRatePlan ratePlans;
        @XmlAttribute(name = "AvailResponseType")
        protected String availResponseType;
        @XmlAttribute(name = "HotelCode")
        protected String hotelCode;

        /**
         * Gets the value of the lengthOfStay property.
         * 
         * @return
         *     possible object is
         *     {@link MultipleAvailResponseSegments.MultipleAvailResponseSegment.LengthOfStay }
         *     
         */
        public MultipleAvailResponseSegments.MultipleAvailResponseSegment.LengthOfStay getLengthOfStay() {
            return lengthOfStay;
        }

        /**
         * Sets the value of the lengthOfStay property.
         * 
         * @param value
         *     allowed object is
         *     {@link MultipleAvailResponseSegments.MultipleAvailResponseSegment.LengthOfStay }
         *     
         */
        public void setLengthOfStay(MultipleAvailResponseSegments.MultipleAvailResponseSegment.LengthOfStay value) {
            this.lengthOfStay = value;
        }

        /**
         * Gets the value of the ratePlans property.
         * 
         * @return
         *     possible object is
         *     {@link ArrayOfRatePlan }
         *     
         */
        public ArrayOfRatePlan getRatePlans() {
            return ratePlans;
        }

        /**
         * Sets the value of the ratePlans property.
         * 
         * @param value
         *     allowed object is
         *     {@link ArrayOfRatePlan }
         *     
         */
        public void setRatePlans(ArrayOfRatePlan value) {
            this.ratePlans = value;
        }

        /**
         * Gets the value of the availResponseType property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAvailResponseType() {
            return availResponseType;
        }

        /**
         * Sets the value of the availResponseType property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAvailResponseType(String value) {
            this.availResponseType = value;
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

    }

}
