
package com.micros.webservices.og._4_3.reservation;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.micros.webservices.og._4_3.common.ArrayOfUniqueID;
import com.micros.webservices.og._4_3.common.UniqueID;
import com.micros.webservices.og._4_3.name.NameAddress;
import com.micros.webservices.og._4_3.name.NativeName;


/**
 * <p>Java class for BillHeader complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BillHeader">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Address" type="{http://webservices.micros.com/og/4.3/Name/}NameAddress" minOccurs="0"/>
 *         &lt;element name="Name" type="{http://webservices.micros.com/og/4.3/Name/}NativeName" minOccurs="0"/>
 *         &lt;element name="ProfileIDs" type="{http://webservices.micros.com/og/4.3/Common/}ArrayOfUniqueID" minOccurs="0"/>
 *         &lt;element name="BillNumber" type="{http://webservices.micros.com/og/4.3/Common/}UniqueID" minOccurs="0"/>
 *         &lt;element name="BillItems" type="{http://webservices.micros.com/og/4.3/Reservation/}BillItem" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="BillTaxes" type="{http://webservices.micros.com/og/4.3/Reservation/}BillTax" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="CreditCardSurcharges" type="{http://webservices.micros.com/og/4.3/Reservation/}CreditCardSurcharge" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BillHeader", propOrder = {
    "address",
    "name",
    "profileIDs",
    "billNumber",
    "billItems",
    "billTaxes",
    "creditCardSurcharges"
})
public class BillHeader {

    @XmlElement(name = "Address")
    protected NameAddress address;
    @XmlElement(name = "Name")
    protected NativeName name;
    @XmlElement(name = "ProfileIDs")
    protected ArrayOfUniqueID profileIDs;
    @XmlElement(name = "BillNumber")
    protected UniqueID billNumber;
    @XmlElement(name = "BillItems")
    protected List<BillItem> billItems;
    @XmlElement(name = "BillTaxes")
    protected List<BillTax> billTaxes;
    @XmlElement(name = "CreditCardSurcharges")
    protected List<CreditCardSurcharge> creditCardSurcharges;

    /**
     * Gets the value of the address property.
     * 
     * @return
     *     possible object is
     *     {@link NameAddress }
     *     
     */
    public NameAddress getAddress() {
        return address;
    }

    /**
     * Sets the value of the address property.
     * 
     * @param value
     *     allowed object is
     *     {@link NameAddress }
     *     
     */
    public void setAddress(NameAddress value) {
        this.address = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link NativeName }
     *     
     */
    public NativeName getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link NativeName }
     *     
     */
    public void setName(NativeName value) {
        this.name = value;
    }

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
     * Gets the value of the billNumber property.
     * 
     * @return
     *     possible object is
     *     {@link UniqueID }
     *     
     */
    public UniqueID getBillNumber() {
        return billNumber;
    }

    /**
     * Sets the value of the billNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link UniqueID }
     *     
     */
    public void setBillNumber(UniqueID value) {
        this.billNumber = value;
    }

    /**
     * Gets the value of the billItems property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the billItems property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBillItems().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BillItem }
     * 
     * 
     */
    public List<BillItem> getBillItems() {
        if (billItems == null) {
            billItems = new ArrayList<BillItem>();
        }
        return this.billItems;
    }

    /**
     * Gets the value of the billTaxes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the billTaxes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBillTaxes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BillTax }
     * 
     * 
     */
    public List<BillTax> getBillTaxes() {
        if (billTaxes == null) {
            billTaxes = new ArrayList<BillTax>();
        }
        return this.billTaxes;
    }

    /**
     * Gets the value of the creditCardSurcharges property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the creditCardSurcharges property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCreditCardSurcharges().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CreditCardSurcharge }
     * 
     * 
     */
    public List<CreditCardSurcharge> getCreditCardSurcharges() {
        if (creditCardSurcharges == null) {
            creditCardSurcharges = new ArrayList<CreditCardSurcharge>();
        }
        return this.creditCardSurcharges;
    }

}
