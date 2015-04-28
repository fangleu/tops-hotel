
package org.opentravel.ota._2003._05;

import java.math.BigDecimal;
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
 * <p>Java class for ArrayOfAmounts complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfAmounts">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Amounts" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Amount" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;attribute name="LOS" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="BaseRate" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *                           &lt;attribute name="TotalRate" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *                 &lt;attribute name="Date" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
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
@XmlType(name = "ArrayOfAmounts", propOrder = {
    "amounts"
})
public class ArrayOfAmounts {

    @XmlElement(name = "Amounts")
    protected List<ArrayOfAmounts.Amounts> amounts;

    /**
     * Gets the value of the amounts property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the amounts property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAmounts().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ArrayOfAmounts.Amounts }
     * 
     * 
     */
    public List<ArrayOfAmounts.Amounts> getAmounts() {
        if (amounts == null) {
            amounts = new ArrayList<ArrayOfAmounts.Amounts>();
        }
        return this.amounts;
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
     *         &lt;element name="Amount" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;attribute name="LOS" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="BaseRate" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" />
     *                 &lt;attribute name="TotalRate" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *       &lt;attribute name="Date" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "amount"
    })
    public static class Amounts {

        @XmlElement(name = "Amount")
        protected List<ArrayOfAmounts.Amounts.Amount> amount;
        @XmlAttribute(name = "Date", required = true)
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar date;

        /**
         * Gets the value of the amount property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the amount property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getAmount().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ArrayOfAmounts.Amounts.Amount }
         * 
         * 
         */
        public List<ArrayOfAmounts.Amounts.Amount> getAmount() {
            if (amount == null) {
                amount = new ArrayList<ArrayOfAmounts.Amounts.Amount>();
            }
            return this.amount;
        }

        /**
         * Gets the value of the date property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getDate() {
            return date;
        }

        /**
         * Sets the value of the date property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setDate(XMLGregorianCalendar value) {
            this.date = value;
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
         *       &lt;attribute name="LOS" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="BaseRate" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" />
         *       &lt;attribute name="TotalRate" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class Amount {

            @XmlAttribute(name = "LOS")
            protected String los;
            @XmlAttribute(name = "BaseRate", required = true)
            protected BigDecimal baseRate;
            @XmlAttribute(name = "TotalRate", required = true)
            protected BigDecimal totalRate;

            /**
             * Gets the value of the los property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getLOS() {
                return los;
            }

            /**
             * Sets the value of the los property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setLOS(String value) {
                this.los = value;
            }

            /**
             * Gets the value of the baseRate property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getBaseRate() {
                return baseRate;
            }

            /**
             * Sets the value of the baseRate property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setBaseRate(BigDecimal value) {
                this.baseRate = value;
            }

            /**
             * Gets the value of the totalRate property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getTotalRate() {
                return totalRate;
            }

            /**
             * Sets the value of the totalRate property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setTotalRate(BigDecimal value) {
                this.totalRate = value;
            }

        }

    }

}
