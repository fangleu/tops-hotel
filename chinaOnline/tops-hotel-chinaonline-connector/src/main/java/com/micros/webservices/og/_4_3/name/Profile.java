
package com.micros.webservices.og._4_3.name;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import com.micros.webservices.og._4_3.common.ArrayOfUniqueID;
import com.micros.webservices.og._4_3.common.ArrayOfUserDefinedValue;


/**
 * <p>Java class for Profile complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Profile">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ProfileIDs" type="{http://webservices.micros.com/og/4.3/Common/}ArrayOfUniqueID" minOccurs="0"/>
 *         &lt;choice>
 *           &lt;element name="Company" type="{http://webservices.micros.com/og/4.3/Name/}Company" minOccurs="0"/>
 *           &lt;element name="Customer" type="{http://webservices.micros.com/og/4.3/Name/}Customer" minOccurs="0"/>
 *         &lt;/choice>
 *         &lt;element name="CreditCards" type="{http://webservices.micros.com/og/4.3/Name/}ArrayOfNameCreditCard" minOccurs="0"/>
 *         &lt;element name="Addresses" type="{http://webservices.micros.com/og/4.3/Name/}ArrayOfNameAddress" minOccurs="0"/>
 *         &lt;element name="Blacklist" type="{http://webservices.micros.com/og/4.3/Name/}BlackList" minOccurs="0"/>
 *         &lt;element name="Phones" type="{http://webservices.micros.com/og/4.3/Name/}ArrayOfNamePhone" minOccurs="0"/>
 *         &lt;element name="Preferences" type="{http://webservices.micros.com/og/4.3/Name/}ArrayOfPreference" minOccurs="0"/>
 *         &lt;element name="EMails" type="{http://webservices.micros.com/og/4.3/Name/}ArrayOfNameEmail" minOccurs="0"/>
 *         &lt;element name="Memberships" type="{http://webservices.micros.com/og/4.3/Name/}ArrayOfNameMembership" minOccurs="0"/>
 *         &lt;element name="NegotiatedRates" type="{http://webservices.micros.com/og/4.3/Name/}ArrayOfNegotiatedRate" minOccurs="0"/>
 *         &lt;element name="Comments" type="{http://webservices.micros.com/og/4.3/Name/}ArrayOfComment" minOccurs="0"/>
 *         &lt;element name="UserDefinedValues" type="{http://webservices.micros.com/og/4.3/Common/}ArrayOfUserDefinedValue" minOccurs="0"/>
 *         &lt;element name="Privacy" type="{http://webservices.micros.com/og/4.3/Name/}ArrayOfPrivacyOptionType" minOccurs="0"/>
 *         &lt;element name="UserGroup" type="{http://webservices.micros.com/og/4.3/Name/}ProfileUserGroup" minOccurs="0"/>
 *         &lt;element name="StayHistory" type="{http://webservices.micros.com/og/4.3/Name/}StayHistoryData" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="nameType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="protected" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="languageCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="nationality" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="vipCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="taxExempt" type="{http://www.w3.org/2001/XMLSchema}boolean" />
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
@XmlType(name = "Profile", propOrder = {
    "profileIDs",
    "company",
    "customer",
    "creditCards",
    "addresses",
    "blacklist",
    "phones",
    "preferences",
    "eMails",
    "memberships",
    "negotiatedRates",
    "comments",
    "userDefinedValues",
    "privacy",
    "userGroup",
    "stayHistory"
})
public class Profile {

    @XmlElement(name = "ProfileIDs")
    protected ArrayOfUniqueID profileIDs;
    @XmlElement(name = "Company")
    protected Company company;
    @XmlElement(name = "Customer")
    protected Customer customer;
    @XmlElement(name = "CreditCards")
    protected ArrayOfNameCreditCard creditCards;
    @XmlElement(name = "Addresses")
    protected ArrayOfNameAddress addresses;
    @XmlElement(name = "Blacklist")
    protected BlackList blacklist;
    @XmlElement(name = "Phones")
    protected ArrayOfNamePhone phones;
    @XmlElement(name = "Preferences")
    protected ArrayOfPreference preferences;
    @XmlElement(name = "EMails")
    protected ArrayOfNameEmail eMails;
    @XmlElement(name = "Memberships")
    protected ArrayOfNameMembership memberships;
    @XmlElement(name = "NegotiatedRates")
    protected ArrayOfNegotiatedRate negotiatedRates;
    @XmlElement(name = "Comments")
    protected ArrayOfComment comments;
    @XmlElement(name = "UserDefinedValues")
    protected ArrayOfUserDefinedValue userDefinedValues;
    @XmlElement(name = "Privacy")
    protected ArrayOfPrivacyOptionType privacy;
    @XmlElement(name = "UserGroup")
    protected ProfileUserGroup userGroup;
    @XmlElement(name = "StayHistory")
    protected StayHistoryData stayHistory;
    @XmlAttribute(name = "nameType")
    protected String nameType;
    @XmlAttribute(name = "protected")
    protected Boolean _protected;
    @XmlAttribute(name = "languageCode")
    protected String languageCode;
    @XmlAttribute(name = "nationality")
    protected String nationality;
    @XmlAttribute(name = "vipCode")
    protected String vipCode;
    @XmlAttribute(name = "taxExempt")
    protected Boolean taxExempt;
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
     * Gets the value of the profileIDs property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfUniqueID }
     *     
     */
    public ArrayOfUniqueID getProfileIDs() {
        return profileIDs;
    }

    /**
     * Sets the value of the profileIDs property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfUniqueID }
     *     
     */
    public void setProfileIDs(ArrayOfUniqueID value) {
        this.profileIDs = value;
    }

    /**
     * Gets the value of the company property.
     * 
     * @return
     *     possible object is
     *     {@link Company }
     *     
     */
    public Company getCompany() {
        return company;
    }

    /**
     * Sets the value of the company property.
     * 
     * @param value
     *     allowed object is
     *     {@link Company }
     *     
     */
    public void setCompany(Company value) {
        this.company = value;
    }

    /**
     * Gets the value of the customer property.
     * 
     * @return
     *     possible object is
     *     {@link Customer }
     *     
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Sets the value of the customer property.
     * 
     * @param value
     *     allowed object is
     *     {@link Customer }
     *     
     */
    public void setCustomer(Customer value) {
        this.customer = value;
    }

    /**
     * Gets the value of the creditCards property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfNameCreditCard }
     *     
     */
    public ArrayOfNameCreditCard getCreditCards() {
        return creditCards;
    }

    /**
     * Sets the value of the creditCards property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfNameCreditCard }
     *     
     */
    public void setCreditCards(ArrayOfNameCreditCard value) {
        this.creditCards = value;
    }

    /**
     * Gets the value of the addresses property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfNameAddress }
     *     
     */
    public ArrayOfNameAddress getAddresses() {
        return addresses;
    }

    /**
     * Sets the value of the addresses property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfNameAddress }
     *     
     */
    public void setAddresses(ArrayOfNameAddress value) {
        this.addresses = value;
    }

    /**
     * Gets the value of the blacklist property.
     * 
     * @return
     *     possible object is
     *     {@link BlackList }
     *     
     */
    public BlackList getBlacklist() {
        return blacklist;
    }

    /**
     * Sets the value of the blacklist property.
     * 
     * @param value
     *     allowed object is
     *     {@link BlackList }
     *     
     */
    public void setBlacklist(BlackList value) {
        this.blacklist = value;
    }

    /**
     * Gets the value of the phones property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfNamePhone }
     *     
     */
    public ArrayOfNamePhone getPhones() {
        return phones;
    }

    /**
     * Sets the value of the phones property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfNamePhone }
     *     
     */
    public void setPhones(ArrayOfNamePhone value) {
        this.phones = value;
    }

    /**
     * Gets the value of the preferences property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfPreference }
     *     
     */
    public ArrayOfPreference getPreferences() {
        return preferences;
    }

    /**
     * Sets the value of the preferences property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfPreference }
     *     
     */
    public void setPreferences(ArrayOfPreference value) {
        this.preferences = value;
    }

    /**
     * Gets the value of the eMails property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfNameEmail }
     *     
     */
    public ArrayOfNameEmail getEMails() {
        return eMails;
    }

    /**
     * Sets the value of the eMails property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfNameEmail }
     *     
     */
    public void setEMails(ArrayOfNameEmail value) {
        this.eMails = value;
    }

    /**
     * Gets the value of the memberships property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfNameMembership }
     *     
     */
    public ArrayOfNameMembership getMemberships() {
        return memberships;
    }

    /**
     * Sets the value of the memberships property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfNameMembership }
     *     
     */
    public void setMemberships(ArrayOfNameMembership value) {
        this.memberships = value;
    }

    /**
     * Gets the value of the negotiatedRates property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfNegotiatedRate }
     *     
     */
    public ArrayOfNegotiatedRate getNegotiatedRates() {
        return negotiatedRates;
    }

    /**
     * Sets the value of the negotiatedRates property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfNegotiatedRate }
     *     
     */
    public void setNegotiatedRates(ArrayOfNegotiatedRate value) {
        this.negotiatedRates = value;
    }

    /**
     * Gets the value of the comments property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfComment }
     *     
     */
    public ArrayOfComment getComments() {
        return comments;
    }

    /**
     * Sets the value of the comments property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfComment }
     *     
     */
    public void setComments(ArrayOfComment value) {
        this.comments = value;
    }

    /**
     * Gets the value of the userDefinedValues property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfUserDefinedValue }
     *     
     */
    public ArrayOfUserDefinedValue getUserDefinedValues() {
        return userDefinedValues;
    }

    /**
     * Sets the value of the userDefinedValues property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfUserDefinedValue }
     *     
     */
    public void setUserDefinedValues(ArrayOfUserDefinedValue value) {
        this.userDefinedValues = value;
    }

    /**
     * Gets the value of the privacy property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfPrivacyOptionType }
     *     
     */
    public ArrayOfPrivacyOptionType getPrivacy() {
        return privacy;
    }

    /**
     * Sets the value of the privacy property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfPrivacyOptionType }
     *     
     */
    public void setPrivacy(ArrayOfPrivacyOptionType value) {
        this.privacy = value;
    }

    /**
     * Gets the value of the userGroup property.
     * 
     * @return
     *     possible object is
     *     {@link ProfileUserGroup }
     *     
     */
    public ProfileUserGroup getUserGroup() {
        return userGroup;
    }

    /**
     * Sets the value of the userGroup property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProfileUserGroup }
     *     
     */
    public void setUserGroup(ProfileUserGroup value) {
        this.userGroup = value;
    }

    /**
     * Gets the value of the stayHistory property.
     * 
     * @return
     *     possible object is
     *     {@link StayHistoryData }
     *     
     */
    public StayHistoryData getStayHistory() {
        return stayHistory;
    }

    /**
     * Sets the value of the stayHistory property.
     * 
     * @param value
     *     allowed object is
     *     {@link StayHistoryData }
     *     
     */
    public void setStayHistory(StayHistoryData value) {
        this.stayHistory = value;
    }

    /**
     * Gets the value of the nameType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNameType() {
        return nameType;
    }

    /**
     * Sets the value of the nameType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNameType(String value) {
        this.nameType = value;
    }

    /**
     * Gets the value of the protected property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isProtected() {
        return _protected;
    }

    /**
     * Sets the value of the protected property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setProtected(Boolean value) {
        this._protected = value;
    }

    /**
     * Gets the value of the languageCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLanguageCode() {
        return languageCode;
    }

    /**
     * Sets the value of the languageCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLanguageCode(String value) {
        this.languageCode = value;
    }

    /**
     * Gets the value of the nationality property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNationality() {
        return nationality;
    }

    /**
     * Sets the value of the nationality property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNationality(String value) {
        this.nationality = value;
    }

    /**
     * Gets the value of the vipCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVipCode() {
        return vipCode;
    }

    /**
     * Sets the value of the vipCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVipCode(String value) {
        this.vipCode = value;
    }

    /**
     * Gets the value of the taxExempt property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isTaxExempt() {
        return taxExempt;
    }

    /**
     * Sets the value of the taxExempt property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTaxExempt(Boolean value) {
        this.taxExempt = value;
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
