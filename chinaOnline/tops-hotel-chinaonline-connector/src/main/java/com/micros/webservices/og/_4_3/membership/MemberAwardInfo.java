
package com.micros.webservices.og._4_3.membership;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import com.micros.webservices.og._4_3.hotelcommon.TimeSpan;


/**
 * <p>Java class for MemberAwardInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MemberAwardInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="awardType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="membershipID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="pointsUsedForReservation" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="redemRateCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stayDate" type="{http://webservices.micros.com/og/4.3/HotelCommon/}TimeSpan" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MemberAwardInfo", propOrder = {
    "awardType",
    "membershipID",
    "pointsUsedForReservation",
    "redemRateCode",
    "stayDate"
})
public class MemberAwardInfo {

    protected String awardType;
    protected long membershipID;
    protected double pointsUsedForReservation;
    protected String redemRateCode;
    protected TimeSpan stayDate;

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
     * Gets the value of the membershipID property.
     * 
     */
    public long getMembershipID() {
        return membershipID;
    }

    /**
     * Sets the value of the membershipID property.
     * 
     */
    public void setMembershipID(long value) {
        this.membershipID = value;
    }

    /**
     * Gets the value of the pointsUsedForReservation property.
     * 
     */
    public double getPointsUsedForReservation() {
        return pointsUsedForReservation;
    }

    /**
     * Sets the value of the pointsUsedForReservation property.
     * 
     */
    public void setPointsUsedForReservation(double value) {
        this.pointsUsedForReservation = value;
    }

    /**
     * Gets the value of the redemRateCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRedemRateCode() {
        return redemRateCode;
    }

    /**
     * Sets the value of the redemRateCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRedemRateCode(String value) {
        this.redemRateCode = value;
    }

    /**
     * Gets the value of the stayDate property.
     * 
     * @return
     *     possible object is
     *     {@link TimeSpan }
     *     
     */
    public TimeSpan getStayDate() {
        return stayDate;
    }

    /**
     * Sets the value of the stayDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link TimeSpan }
     *     
     */
    public void setStayDate(TimeSpan value) {
        this.stayDate = value;
    }

}
