
package com.micros.webservices.og._4_3.availability;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RatePlanCandidate complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RatePlanCandidate">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="ratePlanCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="promotionCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="qualifyingIdType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="qualifyingIdValue" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="awardType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RatePlanCandidate")
public class RatePlanCandidate {

    @XmlAttribute(name = "ratePlanCode")
    protected String ratePlanCode;
    @XmlAttribute(name = "promotionCode")
    protected String promotionCode;
    @XmlAttribute(name = "qualifyingIdType")
    protected String qualifyingIdType;
    @XmlAttribute(name = "qualifyingIdValue")
    protected String qualifyingIdValue;
    @XmlAttribute(name = "awardType")
    protected String awardType;

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

}
