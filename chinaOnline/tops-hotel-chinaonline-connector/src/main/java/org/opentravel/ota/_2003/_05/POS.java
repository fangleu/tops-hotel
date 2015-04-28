
package org.opentravel.ota._2003._05;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * <p>Java class for POS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="POS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Source" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="RequestorID" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="CompanyName" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;simpleContent>
 *                                   &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                                     &lt;attribute name="CompanyShortName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                   &lt;/extension>
 *                                 &lt;/simpleContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                           &lt;attribute name="ID" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *                           &lt;attribute name="Type" use="required" type="{http://www.w3.org/2001/XMLSchema}byte" />
 *                           &lt;attribute name="URL" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
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
@XmlType(name = "POS", propOrder = {
    "source"
})
public class POS {

    @XmlElement(name = "Source")
    protected POS.Source source;

    /**
     * Gets the value of the source property.
     * 
     * @return
     *     possible object is
     *     {@link POS.Source }
     *     
     */
    public POS.Source getSource() {
        return source;
    }

    /**
     * Sets the value of the source property.
     * 
     * @param value
     *     allowed object is
     *     {@link POS.Source }
     *     
     */
    public void setSource(POS.Source value) {
        this.source = value;
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
     *         &lt;element name="RequestorID" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="CompanyName" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;simpleContent>
     *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
     *                           &lt;attribute name="CompanyShortName" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                         &lt;/extension>
     *                       &lt;/simpleContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *                 &lt;attribute name="ID" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
     *                 &lt;attribute name="Type" use="required" type="{http://www.w3.org/2001/XMLSchema}byte" />
     *                 &lt;attribute name="URL" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
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
        "requestorID"
    })
    public static class Source {

        @XmlElement(name = "RequestorID")
        protected POS.Source.RequestorID requestorID;

        /**
         * Gets the value of the requestorID property.
         * 
         * @return
         *     possible object is
         *     {@link POS.Source.RequestorID }
         *     
         */
        public POS.Source.RequestorID getRequestorID() {
            return requestorID;
        }

        /**
         * Sets the value of the requestorID property.
         * 
         * @param value
         *     allowed object is
         *     {@link POS.Source.RequestorID }
         *     
         */
        public void setRequestorID(POS.Source.RequestorID value) {
            this.requestorID = value;
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
         *         &lt;element name="CompanyName" minOccurs="0">
         *           &lt;complexType>
         *             &lt;simpleContent>
         *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
         *                 &lt;attribute name="CompanyShortName" type="{http://www.w3.org/2001/XMLSchema}string" />
         *               &lt;/extension>
         *             &lt;/simpleContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *       &lt;/sequence>
         *       &lt;attribute name="ID" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
         *       &lt;attribute name="Type" use="required" type="{http://www.w3.org/2001/XMLSchema}byte" />
         *       &lt;attribute name="URL" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "companyName"
        })
        public static class RequestorID {

            @XmlElement(name = "CompanyName")
            protected POS.Source.RequestorID.CompanyName companyName;
            @XmlAttribute(name = "ID", required = true)
            protected int id;
            @XmlAttribute(name = "Type", required = true)
            protected byte type;
            @XmlAttribute(name = "URL")
            @XmlSchemaType(name = "anyURI")
            protected String url;

            /**
             * Gets the value of the companyName property.
             * 
             * @return
             *     possible object is
             *     {@link POS.Source.RequestorID.CompanyName }
             *     
             */
            public POS.Source.RequestorID.CompanyName getCompanyName() {
                return companyName;
            }

            /**
             * Sets the value of the companyName property.
             * 
             * @param value
             *     allowed object is
             *     {@link POS.Source.RequestorID.CompanyName }
             *     
             */
            public void setCompanyName(POS.Source.RequestorID.CompanyName value) {
                this.companyName = value;
            }

            /**
             * Gets the value of the id property.
             * 
             */
            public int getID() {
                return id;
            }

            /**
             * Sets the value of the id property.
             * 
             */
            public void setID(int value) {
                this.id = value;
            }

            /**
             * Gets the value of the type property.
             * 
             */
            public byte getType() {
                return type;
            }

            /**
             * Sets the value of the type property.
             * 
             */
            public void setType(byte value) {
                this.type = value;
            }

            /**
             * Gets the value of the url property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getURL() {
                return url;
            }

            /**
             * Sets the value of the url property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setURL(String value) {
                this.url = value;
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
             *       &lt;attribute name="CompanyShortName" type="{http://www.w3.org/2001/XMLSchema}string" />
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
            public static class CompanyName {

                @XmlValue
                protected String value;
                @XmlAttribute(name = "CompanyShortName")
                protected String companyShortName;

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
                 * Gets the value of the companyShortName property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getCompanyShortName() {
                    return companyShortName;
                }

                /**
                 * Sets the value of the companyShortName property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setCompanyShortName(String value) {
                    this.companyShortName = value;
                }

            }

        }

    }

}
