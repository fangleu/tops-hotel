
package com.micros.webservices.og._4_3.name;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import com.micros.webservices.og._4_3.common.DescriptiveText;


/**
 * <p>Java class for Preference complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Preference">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PreferenceDescription" type="{http://webservices.micros.com/og/4.3/Common/}DescriptiveText" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="resortCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="preferenceType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="otherPreferenceType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="preferenceValue" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="insertUser" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="insertDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="updateUser" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="updateDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="inactiveDate" type="{http://www.w3.org/2001/XMLSchema}date" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Preference", propOrder = {
    "preferenceDescription"
})
public class Preference {

    @XmlElement(name = "PreferenceDescription")
    protected DescriptiveText preferenceDescription;
    @XmlAttribute(name = "resortCode")
    protected String resortCode;
    @XmlAttribute(name = "preferenceType")
    protected String preferenceType;
    @XmlAttribute(name = "otherPreferenceType")
    protected String otherPreferenceType;
    @XmlAttribute(name = "preferenceValue")
    protected String preferenceValue;
    @XmlAttribute(name = "insertUser")
    protected String insertUser;
    @XmlAttribute(name = "insertDate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar insertDate;
    @XmlAttribute(name = "updateUser")
    protected String updateUser;
    @XmlAttribute(name = "updateDate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar updateDate;
    @XmlAttribute(name = "inactiveDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar inactiveDate;

    /**
     * Gets the value of the preferenceDescription property.
     * 
     * @return
     *     possible object is
     *     {@link DescriptiveText }
     *     
     */
    public DescriptiveText getPreferenceDescription() {
        return preferenceDescription;
    }

    /**
     * Sets the value of the preferenceDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link DescriptiveText }
     *     
     */
    public void setPreferenceDescription(DescriptiveText value) {
        this.preferenceDescription = value;
    }

    /**
     * Gets the value of the resortCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResortCode() {
        return resortCode;
    }

    /**
     * Sets the value of the resortCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResortCode(String value) {
        this.resortCode = value;
    }

    /**
     * Gets the value of the preferenceType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPreferenceType() {
        return preferenceType;
    }

    /**
     * Sets the value of the preferenceType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreferenceType(String value) {
        this.preferenceType = value;
    }

    /**
     * Gets the value of the otherPreferenceType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOtherPreferenceType() {
        return otherPreferenceType;
    }

    /**
     * Sets the value of the otherPreferenceType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOtherPreferenceType(String value) {
        this.otherPreferenceType = value;
    }

    /**
     * Gets the value of the preferenceValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPreferenceValue() {
        return preferenceValue;
    }

    /**
     * Sets the value of the preferenceValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreferenceValue(String value) {
        this.preferenceValue = value;
    }

    /**
     * Gets the value of the insertUser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInsertUser() {
        return insertUser;
    }

    /**
     * Sets the value of the insertUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInsertUser(String value) {
        this.insertUser = value;
    }

    /**
     * Gets the value of the insertDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getInsertDate() {
        return insertDate;
    }

    /**
     * Sets the value of the insertDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setInsertDate(XMLGregorianCalendar value) {
        this.insertDate = value;
    }

    /**
     * Gets the value of the updateUser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * Sets the value of the updateUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpdateUser(String value) {
        this.updateUser = value;
    }

    /**
     * Gets the value of the updateDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getUpdateDate() {
        return updateDate;
    }

    /**
     * Sets the value of the updateDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setUpdateDate(XMLGregorianCalendar value) {
        this.updateDate = value;
    }

    /**
     * Gets the value of the inactiveDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getInactiveDate() {
        return inactiveDate;
    }

    /**
     * Sets the value of the inactiveDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setInactiveDate(XMLGregorianCalendar value) {
        this.inactiveDate = value;
    }

}
