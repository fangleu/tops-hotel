
package com.micros.webservices.og._4_3.availability;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.micros.webservices.og._4_3.hotelcommon.AvailRequestType;
import com.micros.webservices.og._4_3.hotelcommon.MinMaxRate;
import com.micros.webservices.og._4_3.hotelcommon.TimeSpan;
import com.micros.webservices.og._4_3.membership.ECertificate;


/**
 * <p>Java class for AvailRequestSegment complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AvailRequestSegment">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="StayDateRange" type="{http://webservices.micros.com/og/4.3/HotelCommon/}TimeSpan" minOccurs="0"/>
 *         &lt;element name="RateRange" type="{http://webservices.micros.com/og/4.3/HotelCommon/}MinMaxRate" minOccurs="0"/>
 *         &lt;element name="RatePlanCandidates" type="{http://webservices.micros.com/og/4.3/Availability/}ArrayOfRatePlanCandidate" minOccurs="0"/>
 *         &lt;element name="RoomStayCandidates" type="{http://webservices.micros.com/og/4.3/Availability/}ArrayOfRoomStayCandidate" minOccurs="0"/>
 *         &lt;element name="HotelSearchCriteria" type="{http://webservices.micros.com/og/4.3/Availability/}ArrayOfHotelSearchCriterion" minOccurs="0"/>
 *         &lt;element name="CitySearchCriteria" type="{http://webservices.micros.com/og/4.3/Availability/}ArrayOfCitySearchCriterion" minOccurs="0"/>
 *         &lt;element name="ChildAges" type="{http://webservices.micros.com/og/4.3/Availability/}ArrayOfChildAge" minOccurs="0"/>
 *         &lt;element name="AlternateDates" type="{http://webservices.micros.com/og/4.3/Availability/}ArrayOfAlternateDate" minOccurs="0"/>
 *         &lt;element name="GdsTotalPrice" type="{http://webservices.micros.com/og/4.3/Availability/}GdsTotalPrice" minOccurs="0"/>
 *         &lt;element name="PointsDateRange" type="{http://webservices.micros.com/og/4.3/HotelCommon/}TimeSpan" minOccurs="0"/>
 *         &lt;element name="ECertificate" type="{http://webservices.micros.com/og/4.3/Membership/}ECertificate" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="availReqType" use="required" type="{http://webservices.micros.com/og/4.3/HotelCommon/}AvailRequestType" />
 *       &lt;attribute name="moreDataEchoToken" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="numberOfRooms" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="roomOccupancy" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="totalNumberOfGuests" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="numberOfChildren" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="childBucket1" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="childBucket2" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="childBucket3" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="membershipId" type="{http://www.w3.org/2001/XMLSchema}long" />
 *       &lt;attribute name="membershipIdSpecified" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="membershipType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="membershipLevel" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="pointsBelow" type="{http://www.w3.org/2001/XMLSchema}long" />
 *       &lt;attribute name="pointsAbove" type="{http://www.w3.org/2001/XMLSchema}long" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AvailRequestSegment", propOrder = {
    "stayDateRange",
    "rateRange",
    "ratePlanCandidates",
    "roomStayCandidates",
    "hotelSearchCriteria",
    "citySearchCriteria",
    "childAges",
    "alternateDates",
    "gdsTotalPrice",
    "pointsDateRange",
    "eCertificate"
})
public class AvailRequestSegment {

    @XmlElement(name = "StayDateRange")
    protected TimeSpan stayDateRange;
    @XmlElement(name = "RateRange")
    protected MinMaxRate rateRange;
    @XmlElement(name = "RatePlanCandidates")
    protected ArrayOfRatePlanCandidate ratePlanCandidates;
    @XmlElement(name = "RoomStayCandidates")
    protected ArrayOfRoomStayCandidate roomStayCandidates;
    @XmlElement(name = "HotelSearchCriteria")
    protected ArrayOfHotelSearchCriterion hotelSearchCriteria;
    @XmlElement(name = "CitySearchCriteria")
    protected ArrayOfCitySearchCriterion citySearchCriteria;
    @XmlElement(name = "ChildAges")
    protected ArrayOfChildAge childAges;
    @XmlElement(name = "AlternateDates")
    protected ArrayOfAlternateDate alternateDates;
    @XmlElement(name = "GdsTotalPrice")
    protected GdsTotalPrice gdsTotalPrice;
    @XmlElement(name = "PointsDateRange")
    protected TimeSpan pointsDateRange;
    @XmlElement(name = "ECertificate")
    protected ECertificate eCertificate;
    @XmlAttribute(name = "availReqType", required = true)
    protected AvailRequestType availReqType;
    @XmlAttribute(name = "moreDataEchoToken")
    protected String moreDataEchoToken;
    @XmlAttribute(name = "numberOfRooms")
    protected Integer numberOfRooms;
    @XmlAttribute(name = "roomOccupancy")
    protected Integer roomOccupancy;
    @XmlAttribute(name = "totalNumberOfGuests")
    protected Integer totalNumberOfGuests;
    @XmlAttribute(name = "numberOfChildren")
    protected Integer numberOfChildren;
    @XmlAttribute(name = "childBucket1")
    protected Integer childBucket1;
    @XmlAttribute(name = "childBucket2")
    protected Integer childBucket2;
    @XmlAttribute(name = "childBucket3")
    protected Integer childBucket3;
    @XmlAttribute(name = "membershipId")
    protected Long membershipId;
    @XmlAttribute(name = "membershipIdSpecified", required = true)
    protected boolean membershipIdSpecified;
    @XmlAttribute(name = "membershipType")
    protected String membershipType;
    @XmlAttribute(name = "membershipLevel")
    protected String membershipLevel;
    @XmlAttribute(name = "pointsBelow")
    protected Long pointsBelow;
    @XmlAttribute(name = "pointsAbove")
    protected Long pointsAbove;

    /**
     * Gets the value of the stayDateRange property.
     * 
     * @return
     *     possible object is
     *     {@link TimeSpan }
     *     
     */
    public TimeSpan getStayDateRange() {
        return stayDateRange;
    }

    /**
     * Sets the value of the stayDateRange property.
     * 
     * @param value
     *     allowed object is
     *     {@link TimeSpan }
     *     
     */
    public void setStayDateRange(TimeSpan value) {
        this.stayDateRange = value;
    }

    /**
     * Gets the value of the rateRange property.
     * 
     * @return
     *     possible object is
     *     {@link MinMaxRate }
     *     
     */
    public MinMaxRate getRateRange() {
        return rateRange;
    }

    /**
     * Sets the value of the rateRange property.
     * 
     * @param value
     *     allowed object is
     *     {@link MinMaxRate }
     *     
     */
    public void setRateRange(MinMaxRate value) {
        this.rateRange = value;
    }

    /**
     * Gets the value of the ratePlanCandidates property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfRatePlanCandidate }
     *     
     */
    public ArrayOfRatePlanCandidate getRatePlanCandidates() {
        return ratePlanCandidates;
    }

    /**
     * Sets the value of the ratePlanCandidates property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfRatePlanCandidate }
     *     
     */
    public void setRatePlanCandidates(ArrayOfRatePlanCandidate value) {
        this.ratePlanCandidates = value;
    }

    /**
     * Gets the value of the roomStayCandidates property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfRoomStayCandidate }
     *     
     */
    public ArrayOfRoomStayCandidate getRoomStayCandidates() {
        return roomStayCandidates;
    }

    /**
     * Sets the value of the roomStayCandidates property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfRoomStayCandidate }
     *     
     */
    public void setRoomStayCandidates(ArrayOfRoomStayCandidate value) {
        this.roomStayCandidates = value;
    }

    /**
     * Gets the value of the hotelSearchCriteria property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfHotelSearchCriterion }
     *     
     */
    public ArrayOfHotelSearchCriterion getHotelSearchCriteria() {
        return hotelSearchCriteria;
    }

    /**
     * Sets the value of the hotelSearchCriteria property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfHotelSearchCriterion }
     *     
     */
    public void setHotelSearchCriteria(ArrayOfHotelSearchCriterion value) {
        this.hotelSearchCriteria = value;
    }

    /**
     * Gets the value of the citySearchCriteria property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfCitySearchCriterion }
     *     
     */
    public ArrayOfCitySearchCriterion getCitySearchCriteria() {
        return citySearchCriteria;
    }

    /**
     * Sets the value of the citySearchCriteria property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfCitySearchCriterion }
     *     
     */
    public void setCitySearchCriteria(ArrayOfCitySearchCriterion value) {
        this.citySearchCriteria = value;
    }

    /**
     * Gets the value of the childAges property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfChildAge }
     *     
     */
    public ArrayOfChildAge getChildAges() {
        return childAges;
    }

    /**
     * Sets the value of the childAges property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfChildAge }
     *     
     */
    public void setChildAges(ArrayOfChildAge value) {
        this.childAges = value;
    }

    /**
     * Gets the value of the alternateDates property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfAlternateDate }
     *     
     */
    public ArrayOfAlternateDate getAlternateDates() {
        return alternateDates;
    }

    /**
     * Sets the value of the alternateDates property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfAlternateDate }
     *     
     */
    public void setAlternateDates(ArrayOfAlternateDate value) {
        this.alternateDates = value;
    }

    /**
     * Gets the value of the gdsTotalPrice property.
     * 
     * @return
     *     possible object is
     *     {@link GdsTotalPrice }
     *     
     */
    public GdsTotalPrice getGdsTotalPrice() {
        return gdsTotalPrice;
    }

    /**
     * Sets the value of the gdsTotalPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link GdsTotalPrice }
     *     
     */
    public void setGdsTotalPrice(GdsTotalPrice value) {
        this.gdsTotalPrice = value;
    }

    /**
     * Gets the value of the pointsDateRange property.
     * 
     * @return
     *     possible object is
     *     {@link TimeSpan }
     *     
     */
    public TimeSpan getPointsDateRange() {
        return pointsDateRange;
    }

    /**
     * Sets the value of the pointsDateRange property.
     * 
     * @param value
     *     allowed object is
     *     {@link TimeSpan }
     *     
     */
    public void setPointsDateRange(TimeSpan value) {
        this.pointsDateRange = value;
    }

    /**
     * Gets the value of the eCertificate property.
     * 
     * @return
     *     possible object is
     *     {@link ECertificate }
     *     
     */
    public ECertificate getECertificate() {
        return eCertificate;
    }

    /**
     * Sets the value of the eCertificate property.
     * 
     * @param value
     *     allowed object is
     *     {@link ECertificate }
     *     
     */
    public void setECertificate(ECertificate value) {
        this.eCertificate = value;
    }

    /**
     * Gets the value of the availReqType property.
     * 
     * @return
     *     possible object is
     *     {@link AvailRequestType }
     *     
     */
    public AvailRequestType getAvailReqType() {
        return availReqType;
    }

    /**
     * Sets the value of the availReqType property.
     * 
     * @param value
     *     allowed object is
     *     {@link AvailRequestType }
     *     
     */
    public void setAvailReqType(AvailRequestType value) {
        this.availReqType = value;
    }

    /**
     * Gets the value of the moreDataEchoToken property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMoreDataEchoToken() {
        return moreDataEchoToken;
    }

    /**
     * Sets the value of the moreDataEchoToken property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMoreDataEchoToken(String value) {
        this.moreDataEchoToken = value;
    }

    /**
     * Gets the value of the numberOfRooms property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumberOfRooms() {
        return numberOfRooms;
    }

    /**
     * Sets the value of the numberOfRooms property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumberOfRooms(Integer value) {
        this.numberOfRooms = value;
    }

    /**
     * Gets the value of the roomOccupancy property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRoomOccupancy() {
        return roomOccupancy;
    }

    /**
     * Sets the value of the roomOccupancy property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRoomOccupancy(Integer value) {
        this.roomOccupancy = value;
    }

    /**
     * Gets the value of the totalNumberOfGuests property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTotalNumberOfGuests() {
        return totalNumberOfGuests;
    }

    /**
     * Sets the value of the totalNumberOfGuests property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTotalNumberOfGuests(Integer value) {
        this.totalNumberOfGuests = value;
    }

    /**
     * Gets the value of the numberOfChildren property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumberOfChildren() {
        return numberOfChildren;
    }

    /**
     * Sets the value of the numberOfChildren property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumberOfChildren(Integer value) {
        this.numberOfChildren = value;
    }

    /**
     * Gets the value of the childBucket1 property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getChildBucket1() {
        return childBucket1;
    }

    /**
     * Sets the value of the childBucket1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setChildBucket1(Integer value) {
        this.childBucket1 = value;
    }

    /**
     * Gets the value of the childBucket2 property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getChildBucket2() {
        return childBucket2;
    }

    /**
     * Sets the value of the childBucket2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setChildBucket2(Integer value) {
        this.childBucket2 = value;
    }

    /**
     * Gets the value of the childBucket3 property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getChildBucket3() {
        return childBucket3;
    }

    /**
     * Sets the value of the childBucket3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setChildBucket3(Integer value) {
        this.childBucket3 = value;
    }

    /**
     * Gets the value of the membershipId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getMembershipId() {
        return membershipId;
    }

    /**
     * Sets the value of the membershipId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setMembershipId(Long value) {
        this.membershipId = value;
    }

    /**
     * Gets the value of the membershipIdSpecified property.
     * 
     */
    public boolean isMembershipIdSpecified() {
        return membershipIdSpecified;
    }

    /**
     * Sets the value of the membershipIdSpecified property.
     * 
     */
    public void setMembershipIdSpecified(boolean value) {
        this.membershipIdSpecified = value;
    }

    /**
     * Gets the value of the membershipType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMembershipType() {
        return membershipType;
    }

    /**
     * Sets the value of the membershipType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMembershipType(String value) {
        this.membershipType = value;
    }

    /**
     * Gets the value of the membershipLevel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMembershipLevel() {
        return membershipLevel;
    }

    /**
     * Sets the value of the membershipLevel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMembershipLevel(String value) {
        this.membershipLevel = value;
    }

    /**
     * Gets the value of the pointsBelow property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getPointsBelow() {
        return pointsBelow;
    }

    /**
     * Sets the value of the pointsBelow property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setPointsBelow(Long value) {
        this.pointsBelow = value;
    }

    /**
     * Gets the value of the pointsAbove property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getPointsAbove() {
        return pointsAbove;
    }

    /**
     * Sets the value of the pointsAbove property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setPointsAbove(Long value) {
        this.pointsAbove = value;
    }

}
