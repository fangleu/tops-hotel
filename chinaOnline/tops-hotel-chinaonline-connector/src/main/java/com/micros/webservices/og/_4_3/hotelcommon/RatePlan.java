
package com.micros.webservices.og._4_3.hotelcommon;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for RatePlan complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RatePlan">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RatePlanDescription" type="{http://webservices.micros.com/og/4.3/HotelCommon/}Paragraph" minOccurs="0"/>
 *         &lt;element name="RatePlanShortDescription" type="{http://webservices.micros.com/og/4.3/HotelCommon/}Paragraph" minOccurs="0"/>
 *         &lt;element name="Commission" type="{http://webservices.micros.com/og/4.3/HotelCommon/}Commission" minOccurs="0"/>
 *         &lt;element name="AdditionalDetails" type="{http://webservices.micros.com/og/4.3/HotelCommon/}ArrayOfAdditionalDetail" minOccurs="0"/>
 *         &lt;element name="CancellationDateTime" type="{http://webservices.micros.com/og/4.3/HotelCommon/}CancelDateTime" minOccurs="0"/>
 *         &lt;element name="DepositRequired" type="{http://webservices.micros.com/og/4.3/HotelCommon/}DepositRequirement" minOccurs="0"/>
 *         &lt;element name="GdsFlags" type="{http://webservices.micros.com/og/4.3/HotelCommon/}GdsFlags" minOccurs="0"/>
 *         &lt;element name="CancelPolicy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="ratePlanCategory" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ratePlanCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="promotionCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="qualifyingIdType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="qualifyingIdValue" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="effectiveDate" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="expirationDate" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="hold" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="mandatoryDeposit" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="suppressRate" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="ratePlanName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="awardType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="redemRate" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RatePlan", propOrder = {
    "ratePlanDescription",
    "ratePlanShortDescription",
    "commission",
    "additionalDetails",
    "cancellationDateTime",
    "depositRequired",
    "gdsFlags",
    "cancelPolicy"
})
@XmlSeeAlso({
    RatePlanRoomTypes.class
})
public class RatePlan {

    @XmlElement(name = "RatePlanDescription")
    protected Paragraph ratePlanDescription;
    @XmlElement(name = "RatePlanShortDescription")
    protected Paragraph ratePlanShortDescription;
    @XmlElement(name = "Commission")
    protected Commission commission;
    @XmlElement(name = "AdditionalDetails")
    protected ArrayOfAdditionalDetail additionalDetails;
    @XmlElement(name = "CancellationDateTime")
    protected CancelDateTime cancellationDateTime;
    @XmlElement(name = "DepositRequired")
    protected DepositRequirement depositRequired;
    @XmlElement(name = "GdsFlags")
    protected GdsFlags gdsFlags;
    @XmlElement(name = "CancelPolicy")
    protected String cancelPolicy;
    @XmlAttribute(name = "ratePlanCategory")
    protected String ratePlanCategory;
    @XmlAttribute(name = "ratePlanCode")
    protected String ratePlanCode;
    @XmlAttribute(name = "promotionCode")
    protected String promotionCode;
    @XmlAttribute(name = "qualifyingIdType")
    protected String qualifyingIdType;
    @XmlAttribute(name = "qualifyingIdValue")
    protected String qualifyingIdValue;
    @XmlAttribute(name = "effectiveDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar effectiveDate;
    @XmlAttribute(name = "expirationDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar expirationDate;
    @XmlAttribute(name = "hold")
    protected Boolean hold;
    @XmlAttribute(name = "mandatoryDeposit")
    protected Boolean mandatoryDeposit;
    @XmlAttribute(name = "suppressRate")
    protected Boolean suppressRate;
    @XmlAttribute(name = "ratePlanName")
    protected String ratePlanName;
    @XmlAttribute(name = "awardType")
    protected String awardType;
    @XmlAttribute(name = "redemRate")
    protected Boolean redemRate;

    /**
     * Gets the value of the ratePlanDescription property.
     * 
     * @return
     *     possible object is
     *     {@link Paragraph }
     *     
     */
    public Paragraph getRatePlanDescription() {
        return ratePlanDescription;
    }

    /**
     * Sets the value of the ratePlanDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link Paragraph }
     *     
     */
    public void setRatePlanDescription(Paragraph value) {
        this.ratePlanDescription = value;
    }

    /**
     * Gets the value of the ratePlanShortDescription property.
     * 
     * @return
     *     possible object is
     *     {@link Paragraph }
     *     
     */
    public Paragraph getRatePlanShortDescription() {
        return ratePlanShortDescription;
    }

    /**
     * Sets the value of the ratePlanShortDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link Paragraph }
     *     
     */
    public void setRatePlanShortDescription(Paragraph value) {
        this.ratePlanShortDescription = value;
    }

    /**
     * Gets the value of the commission property.
     * 
     * @return
     *     possible object is
     *     {@link Commission }
     *     
     */
    public Commission getCommission() {
        return commission;
    }

    /**
     * Sets the value of the commission property.
     * 
     * @param value
     *     allowed object is
     *     {@link Commission }
     *     
     */
    public void setCommission(Commission value) {
        this.commission = value;
    }

    /**
     * Gets the value of the additionalDetails property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfAdditionalDetail }
     *     
     */
    public ArrayOfAdditionalDetail getAdditionalDetails() {
        return additionalDetails;
    }

    /**
     * Sets the value of the additionalDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfAdditionalDetail }
     *     
     */
    public void setAdditionalDetails(ArrayOfAdditionalDetail value) {
        this.additionalDetails = value;
    }

    /**
     * Gets the value of the cancellationDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link CancelDateTime }
     *     
     */
    public CancelDateTime getCancellationDateTime() {
        return cancellationDateTime;
    }

    /**
     * Sets the value of the cancellationDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link CancelDateTime }
     *     
     */
    public void setCancellationDateTime(CancelDateTime value) {
        this.cancellationDateTime = value;
    }

    /**
     * Gets the value of the depositRequired property.
     * 
     * @return
     *     possible object is
     *     {@link DepositRequirement }
     *     
     */
    public DepositRequirement getDepositRequired() {
        return depositRequired;
    }

    /**
     * Sets the value of the depositRequired property.
     * 
     * @param value
     *     allowed object is
     *     {@link DepositRequirement }
     *     
     */
    public void setDepositRequired(DepositRequirement value) {
        this.depositRequired = value;
    }

    /**
     * Gets the value of the gdsFlags property.
     * 
     * @return
     *     possible object is
     *     {@link GdsFlags }
     *     
     */
    public GdsFlags getGdsFlags() {
        return gdsFlags;
    }

    /**
     * Sets the value of the gdsFlags property.
     * 
     * @param value
     *     allowed object is
     *     {@link GdsFlags }
     *     
     */
    public void setGdsFlags(GdsFlags value) {
        this.gdsFlags = value;
    }

    /**
     * Gets the value of the cancelPolicy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCancelPolicy() {
        return cancelPolicy;
    }

    /**
     * Sets the value of the cancelPolicy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCancelPolicy(String value) {
        this.cancelPolicy = value;
    }

    /**
     * Gets the value of the ratePlanCategory property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRatePlanCategory() {
        return ratePlanCategory;
    }

    /**
     * Sets the value of the ratePlanCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRatePlanCategory(String value) {
        this.ratePlanCategory = value;
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
     * Gets the value of the promotionCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPromotionCode() {
        return promotionCode;
    }

    /**
     * Sets the value of the promotionCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPromotionCode(String value) {
        this.promotionCode = value;
    }

    /**
     * Gets the value of the qualifyingIdType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQualifyingIdType() {
        return qualifyingIdType;
    }

    /**
     * Sets the value of the qualifyingIdType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQualifyingIdType(String value) {
        this.qualifyingIdType = value;
    }

    /**
     * Gets the value of the qualifyingIdValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQualifyingIdValue() {
        return qualifyingIdValue;
    }

    /**
     * Sets the value of the qualifyingIdValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQualifyingIdValue(String value) {
        this.qualifyingIdValue = value;
    }

    /**
     * Gets the value of the effectiveDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEffectiveDate() {
        return effectiveDate;
    }

    /**
     * Sets the value of the effectiveDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEffectiveDate(XMLGregorianCalendar value) {
        this.effectiveDate = value;
    }

    /**
     * Gets the value of the expirationDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getExpirationDate() {
        return expirationDate;
    }

    /**
     * Sets the value of the expirationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setExpirationDate(XMLGregorianCalendar value) {
        this.expirationDate = value;
    }

    /**
     * Gets the value of the hold property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isHold() {
        return hold;
    }

    /**
     * Sets the value of the hold property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setHold(Boolean value) {
        this.hold = value;
    }

    /**
     * Gets the value of the mandatoryDeposit property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isMandatoryDeposit() {
        return mandatoryDeposit;
    }

    /**
     * Sets the value of the mandatoryDeposit property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMandatoryDeposit(Boolean value) {
        this.mandatoryDeposit = value;
    }

    /**
     * Gets the value of the suppressRate property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSuppressRate() {
        return suppressRate;
    }

    /**
     * Sets the value of the suppressRate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSuppressRate(Boolean value) {
        this.suppressRate = value;
    }

    /**
     * Gets the value of the ratePlanName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRatePlanName() {
        return ratePlanName;
    }

    /**
     * Sets the value of the ratePlanName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRatePlanName(String value) {
        this.ratePlanName = value;
    }

    /**
     * Gets the value of the awardType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAwardType() {
        return awardType;
    }

    /**
     * Sets the value of the awardType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAwardType(String value) {
        this.awardType = value;
    }

    /**
     * Gets the value of the redemRate property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isRedemRate() {
        return redemRate;
    }

    /**
     * Sets the value of the redemRate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRedemRate(Boolean value) {
        this.redemRate = value;
    }

}
