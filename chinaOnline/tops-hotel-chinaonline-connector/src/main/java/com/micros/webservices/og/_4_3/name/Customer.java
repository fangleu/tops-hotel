
package com.micros.webservices.og._4_3.name;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import com.micros.webservices.og._4_3.common.ArrayOfGovernmentID;
import com.micros.webservices.og._4_3.common.Gender;
import com.micros.webservices.og._4_3.common.PersonName;


/**
 * <p>Java class for Customer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Customer">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PersonName" type="{http://webservices.micros.com/og/4.3/Common/}PersonName" minOccurs="0"/>
 *         &lt;element name="NativeName" type="{http://webservices.micros.com/og/4.3/Name/}NativeName" minOccurs="0"/>
 *         &lt;element name="BusinessTitle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GovernmentIDList" type="{http://webservices.micros.com/og/4.3/Common/}ArrayOfGovernmentID" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="gender" type="{http://webservices.micros.com/og/4.3/Common/}Gender" />
 *       &lt;attribute name="birthDate" type="{http://www.w3.org/2001/XMLSchema}date" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Customer", propOrder = {
    "personName",
    "nativeName",
    "businessTitle",
    "governmentIDList"
})
public class Customer {

    @XmlElement(name = "PersonName")
    protected PersonName personName;
    @XmlElement(name = "NativeName")
    protected NativeName nativeName;
    @XmlElement(name = "BusinessTitle")
    protected String businessTitle;
    @XmlElement(name = "GovernmentIDList")
    protected ArrayOfGovernmentID governmentIDList;
    @XmlAttribute(name = "gender")
    protected Gender gender;
    @XmlAttribute(name = "birthDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar birthDate;

    /**
     * Gets the value of the personName property.
     * 
     * @return
     *     possible object is
     *     {@link PersonName }
     *     
     */
    public PersonName getPersonName() {
        return personName;
    }

    /**
     * Sets the value of the personName property.
     * 
     * @param value
     *     allowed object is
     *     {@link PersonName }
     *     
     */
    public void setPersonName(PersonName value) {
        this.personName = value;
    }

    /**
     * Gets the value of the nativeName property.
     * 
     * @return
     *     possible object is
     *     {@link NativeName }
     *     
     */
    public NativeName getNativeName() {
        return nativeName;
    }

    /**
     * Sets the value of the nativeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link NativeName }
     *     
     */
    public void setNativeName(NativeName value) {
        this.nativeName = value;
    }

    /**
     * Gets the value of the businessTitle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusinessTitle() {
        return businessTitle;
    }

    /**
     * Sets the value of the businessTitle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusinessTitle(String value) {
        this.businessTitle = value;
    }

    /**
     * Gets the value of the governmentIDList property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfGovernmentID }
     *     
     */
    public ArrayOfGovernmentID getGovernmentIDList() {
        return governmentIDList;
    }

    /**
     * Sets the value of the governmentIDList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfGovernmentID }
     *     
     */
    public void setGovernmentIDList(ArrayOfGovernmentID value) {
        this.governmentIDList = value;
    }

    /**
     * Gets the value of the gender property.
     * 
     * @return
     *     possible object is
     *     {@link Gender }
     *     
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * Sets the value of the gender property.
     * 
     * @param value
     *     allowed object is
     *     {@link Gender }
     *     
     */
    public void setGender(Gender value) {
        this.gender = value;
    }

    /**
     * Gets the value of the birthDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getBirthDate() {
        return birthDate;
    }

    /**
     * Sets the value of the birthDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setBirthDate(XMLGregorianCalendar value) {
        this.birthDate = value;
    }

}
