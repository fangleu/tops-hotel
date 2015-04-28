
package com.micros.webservices.og._4_3.common;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for UserDefinedValue complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UserDefinedValue">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;element name="CharacterValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *           &lt;element name="NumericValue" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *           &lt;element name="DateValue" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *       &lt;attribute name="valueName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UserDefinedValue", propOrder = {
    "characterValue",
    "numericValue",
    "dateValue"
})
public class UserDefinedValue {

    @XmlElement(name = "CharacterValue")
    protected String characterValue;
    @XmlElement(name = "NumericValue")
    protected Double numericValue;
    @XmlElement(name = "DateValue")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateValue;
    @XmlAttribute(name = "valueName")
    protected String valueName;

    /**
     * Gets the value of the characterValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCharacterValue() {
        return characterValue;
    }

    /**
     * Sets the value of the characterValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCharacterValue(String value) {
        this.characterValue = value;
    }

    /**
     * Gets the value of the numericValue property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getNumericValue() {
        return numericValue;
    }

    /**
     * Sets the value of the numericValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setNumericValue(Double value) {
        this.numericValue = value;
    }

    /**
     * Gets the value of the dateValue property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateValue() {
        return dateValue;
    }

    /**
     * Sets the value of the dateValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateValue(XMLGregorianCalendar value) {
        this.dateValue = value;
    }

    /**
     * Gets the value of the valueName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValueName() {
        return valueName;
    }

    /**
     * Sets the value of the valueName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValueName(String value) {
        this.valueName = value;
    }

}
