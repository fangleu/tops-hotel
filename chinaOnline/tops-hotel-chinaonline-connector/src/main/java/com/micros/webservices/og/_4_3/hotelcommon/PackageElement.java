
package com.micros.webservices.og._4_3.hotelcommon;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import com.micros.webservices.og._4_3.common.Amount;
import com.micros.webservices.og._4_3.common.DescriptiveText;


/**
 * <p>Java class for PackageElement complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PackageElement">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Amount" type="{http://webservices.micros.com/og/4.3/Common/}Amount" minOccurs="0"/>
 *         &lt;element name="Description" type="{http://webservices.micros.com/og/4.3/Common/}DescriptiveText" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="PackageDetails" type="{http://webservices.micros.com/og/4.3/HotelCommon/}PackageDetailCharges" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="StartDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="EndDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="packageCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="calculationRule" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="postingRhythm" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="quantity" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="includedInRate" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="addRateSeprateLine" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="addRateCombinedLine" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="startTime" type="{http://www.w3.org/2001/XMLSchema}time" />
 *       &lt;attribute name="endTime" type="{http://www.w3.org/2001/XMLSchema}time" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PackageElement", propOrder = {
    "amount",
    "description",
    "packageDetails",
    "startDate",
    "endDate"
})
public class PackageElement {

    @XmlElement(name = "Amount")
    protected Amount amount;
    @XmlElement(name = "Description")
    protected List<DescriptiveText> description;
    @XmlElement(name = "PackageDetails")
    protected List<PackageDetailCharges> packageDetails;
    @XmlElement(name = "StartDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar startDate;
    @XmlElement(name = "EndDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar endDate;
    @XmlAttribute(name = "packageCode")
    protected String packageCode;
    @XmlAttribute(name = "calculationRule")
    protected String calculationRule;
    @XmlAttribute(name = "postingRhythm")
    protected String postingRhythm;
    @XmlAttribute(name = "quantity")
    protected Integer quantity;
    @XmlAttribute(name = "includedInRate")
    protected Boolean includedInRate;
    @XmlAttribute(name = "addRateSeprateLine")
    protected Boolean addRateSeprateLine;
    @XmlAttribute(name = "addRateCombinedLine")
    protected Boolean addRateCombinedLine;
    @XmlAttribute(name = "startTime")
    @XmlSchemaType(name = "time")
    protected XMLGregorianCalendar startTime;
    @XmlAttribute(name = "endTime")
    @XmlSchemaType(name = "time")
    protected XMLGregorianCalendar endTime;

    /**
     * Gets the value of the amount property.
     * 
     * @return
     *     possible object is
     *     {@link Amount }
     *     
     */
    public Amount getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Amount }
     *     
     */
    public void setAmount(Amount value) {
        this.amount = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the description property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDescription().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DescriptiveText }
     * 
     * 
     */
    public List<DescriptiveText> getDescription() {
        if (description == null) {
            description = new ArrayList<DescriptiveText>();
        }
        return this.description;
    }

    /**
     * Gets the value of the packageDetails property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the packageDetails property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPackageDetails().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PackageDetailCharges }
     * 
     * 
     */
    public List<PackageDetailCharges> getPackageDetails() {
        if (packageDetails == null) {
            packageDetails = new ArrayList<PackageDetailCharges>();
        }
        return this.packageDetails;
    }

    /**
     * Gets the value of the startDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStartDate() {
        return startDate;
    }

    /**
     * Sets the value of the startDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStartDate(XMLGregorianCalendar value) {
        this.startDate = value;
    }

    /**
     * Gets the value of the endDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEndDate() {
        return endDate;
    }

    /**
     * Sets the value of the endDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEndDate(XMLGregorianCalendar value) {
        this.endDate = value;
    }

    /**
     * Gets the value of the packageCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPackageCode() {
        return packageCode;
    }

    /**
     * Sets the value of the packageCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPackageCode(String value) {
        this.packageCode = value;
    }

    /**
     * Gets the value of the calculationRule property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCalculationRule() {
        return calculationRule;
    }

    /**
     * Sets the value of the calculationRule property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCalculationRule(String value) {
        this.calculationRule = value;
    }

    /**
     * Gets the value of the postingRhythm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostingRhythm() {
        return postingRhythm;
    }

    /**
     * Sets the value of the postingRhythm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostingRhythm(String value) {
        this.postingRhythm = value;
    }

    /**
     * Gets the value of the quantity property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * Sets the value of the quantity property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setQuantity(Integer value) {
        this.quantity = value;
    }

    /**
     * Gets the value of the includedInRate property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIncludedInRate() {
        return includedInRate;
    }

    /**
     * Sets the value of the includedInRate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIncludedInRate(Boolean value) {
        this.includedInRate = value;
    }

    /**
     * Gets the value of the addRateSeprateLine property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAddRateSeprateLine() {
        return addRateSeprateLine;
    }

    /**
     * Sets the value of the addRateSeprateLine property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAddRateSeprateLine(Boolean value) {
        this.addRateSeprateLine = value;
    }

    /**
     * Gets the value of the addRateCombinedLine property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAddRateCombinedLine() {
        return addRateCombinedLine;
    }

    /**
     * Sets the value of the addRateCombinedLine property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAddRateCombinedLine(Boolean value) {
        this.addRateCombinedLine = value;
    }

    /**
     * Gets the value of the startTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStartTime() {
        return startTime;
    }

    /**
     * Sets the value of the startTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStartTime(XMLGregorianCalendar value) {
        this.startTime = value;
    }

    /**
     * Gets the value of the endTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEndTime() {
        return endTime;
    }

    /**
     * Sets the value of the endTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEndTime(XMLGregorianCalendar value) {
        this.endTime = value;
    }

}
