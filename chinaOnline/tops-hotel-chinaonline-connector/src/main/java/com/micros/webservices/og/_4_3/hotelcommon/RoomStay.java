
package com.micros.webservices.og._4_3.hotelcommon;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.micros.webservices.og._4_3.common.Amount;
import com.micros.webservices.og._4_3.membership.MemberAwardInfo;


/**
 * <p>Java class for RoomStay complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RoomStay">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RatePlans" type="{http://webservices.micros.com/og/4.3/HotelCommon/}ArrayOfRatePlan" minOccurs="0"/>
 *         &lt;element name="RoomTypes" type="{http://webservices.micros.com/og/4.3/HotelCommon/}ArrayOfRoomType" minOccurs="0"/>
 *         &lt;element name="RoomRates" type="{http://webservices.micros.com/og/4.3/HotelCommon/}ArrayOfRoomRate" minOccurs="0"/>
 *         &lt;element name="GuestCounts" type="{http://webservices.micros.com/og/4.3/HotelCommon/}GuestCountList" minOccurs="0"/>
 *         &lt;element name="TimeSpan" type="{http://webservices.micros.com/og/4.3/HotelCommon/}TimeSpan" minOccurs="0"/>
 *         &lt;element name="Guarantee" type="{http://webservices.micros.com/og/4.3/HotelCommon/}Guarantee" minOccurs="0"/>
 *         &lt;element name="Payment" type="{http://webservices.micros.com/og/4.3/HotelCommon/}Payment" minOccurs="0"/>
 *         &lt;element name="CreditCardDeposit" type="{http://webservices.micros.com/og/4.3/HotelCommon/}CreditCardPayment" minOccurs="0"/>
 *         &lt;element name="CancelPenalties" type="{http://webservices.micros.com/og/4.3/HotelCommon/}ArrayOfCancelPenalty" minOccurs="0"/>
 *         &lt;element name="CancelTerm" type="{http://webservices.micros.com/og/4.3/HotelCommon/}CancelTerm" minOccurs="0"/>
 *         &lt;element name="HotelReference" type="{http://webservices.micros.com/og/4.3/HotelCommon/}HotelReference" minOccurs="0"/>
 *         &lt;element name="HotelContact" type="{http://webservices.micros.com/og/4.3/HotelCommon/}HotelContact" minOccurs="0"/>
 *         &lt;element name="Total" type="{http://webservices.micros.com/og/4.3/Common/}Amount" minOccurs="0"/>
 *         &lt;element name="ResGuestRPHs" type="{http://webservices.micros.com/og/4.3/HotelCommon/}ArrayOfResGuestRPH" minOccurs="0"/>
 *         &lt;element name="Comments" type="{http://webservices.micros.com/og/4.3/HotelCommon/}ArrayOfReservationComment" minOccurs="0"/>
 *         &lt;element name="SpecialRequests" type="{http://webservices.micros.com/og/4.3/HotelCommon/}ArrayOfSpecialRequest" minOccurs="0"/>
 *         &lt;element name="Packages" type="{http://webservices.micros.com/og/4.3/HotelCommon/}ArrayOfPackageElement" minOccurs="0"/>
 *         &lt;element name="HotelExtendedInformation" type="{http://webservices.micros.com/og/4.3/HotelCommon/}ExtendedHotelInfo" minOccurs="0"/>
 *         &lt;element name="DailyChargePoints" type="{http://webservices.micros.com/og/4.3/HotelCommon/}ArrayOfTimeSpanPoints" minOccurs="0"/>
 *         &lt;element name="MemberAwardInfo" type="{http://webservices.micros.com/og/4.3/Membership/}MemberAwardInfo" minOccurs="0"/>
 *         &lt;element name="ExpectedCharges" type="{http://webservices.micros.com/og/4.3/HotelCommon/}DailyChargeList" minOccurs="0"/>
 *         &lt;element name="GdsTotalPricing" type="{http://webservices.micros.com/og/4.3/HotelCommon/}GdsTotalPricing" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="isAlternate" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RoomStay", propOrder = {
    "ratePlans",
    "roomTypes",
    "roomRates",
    "guestCounts",
    "timeSpan",
    "guarantee",
    "payment",
    "creditCardDeposit",
    "cancelPenalties",
    "cancelTerm",
    "hotelReference",
    "hotelContact",
    "total",
    "resGuestRPHs",
    "comments",
    "specialRequests",
    "packages",
    "hotelExtendedInformation",
    "dailyChargePoints",
    "memberAwardInfo",
    "expectedCharges",
    "gdsTotalPricing"
})
public class RoomStay {

    @XmlElement(name = "RatePlans")
    protected ArrayOfRatePlan ratePlans;
    @XmlElement(name = "RoomTypes")
    protected ArrayOfRoomType roomTypes;
    @XmlElement(name = "RoomRates")
    protected ArrayOfRoomRate roomRates;
    @XmlElement(name = "GuestCounts")
    protected GuestCountList guestCounts;
    @XmlElement(name = "TimeSpan")
    protected TimeSpan timeSpan;
    @XmlElement(name = "Guarantee")
    protected Guarantee guarantee;
    @XmlElement(name = "Payment")
    protected Payment payment;
    @XmlElement(name = "CreditCardDeposit")
    protected CreditCardPayment creditCardDeposit;
    @XmlElement(name = "CancelPenalties")
    protected ArrayOfCancelPenalty cancelPenalties;
    @XmlElement(name = "CancelTerm")
    protected CancelTerm cancelTerm;
    @XmlElement(name = "HotelReference")
    protected HotelReference hotelReference;
    @XmlElement(name = "HotelContact")
    protected HotelContact hotelContact;
    @XmlElement(name = "Total")
    protected Amount total;
    @XmlElement(name = "ResGuestRPHs")
    protected ArrayOfResGuestRPH resGuestRPHs;
    @XmlElement(name = "Comments")
    protected ArrayOfReservationComment comments;
    @XmlElement(name = "SpecialRequests")
    protected ArrayOfSpecialRequest specialRequests;
    @XmlElement(name = "Packages")
    protected ArrayOfPackageElement packages;
    @XmlElement(name = "HotelExtendedInformation")
    protected ExtendedHotelInfo hotelExtendedInformation;
    @XmlElement(name = "DailyChargePoints")
    protected ArrayOfTimeSpanPoints dailyChargePoints;
    @XmlElement(name = "MemberAwardInfo")
    protected MemberAwardInfo memberAwardInfo;
    @XmlElement(name = "ExpectedCharges")
    protected DailyChargeList expectedCharges;
    @XmlElement(name = "GdsTotalPricing")
    protected GdsTotalPricing gdsTotalPricing;
    @XmlAttribute(name = "isAlternate")
    protected Boolean isAlternate;

    /**
     * Gets the value of the ratePlans property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfRatePlan }
     *     
     */
    public ArrayOfRatePlan getRatePlans() {
        return ratePlans;
    }

    /**
     * Sets the value of the ratePlans property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfRatePlan }
     *     
     */
    public void setRatePlans(ArrayOfRatePlan value) {
        this.ratePlans = value;
    }

    /**
     * Gets the value of the roomTypes property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfRoomType }
     *     
     */
    public ArrayOfRoomType getRoomTypes() {
        return roomTypes;
    }

    /**
     * Sets the value of the roomTypes property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfRoomType }
     *     
     */
    public void setRoomTypes(ArrayOfRoomType value) {
        this.roomTypes = value;
    }

    /**
     * Gets the value of the roomRates property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfRoomRate }
     *     
     */
    public ArrayOfRoomRate getRoomRates() {
        return roomRates;
    }

    /**
     * Sets the value of the roomRates property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfRoomRate }
     *     
     */
    public void setRoomRates(ArrayOfRoomRate value) {
        this.roomRates = value;
    }

    /**
     * Gets the value of the guestCounts property.
     * 
     * @return
     *     possible object is
     *     {@link GuestCountList }
     *     
     */
    public GuestCountList getGuestCounts() {
        return guestCounts;
    }

    /**
     * Sets the value of the guestCounts property.
     * 
     * @param value
     *     allowed object is
     *     {@link GuestCountList }
     *     
     */
    public void setGuestCounts(GuestCountList value) {
        this.guestCounts = value;
    }

    /**
     * Gets the value of the timeSpan property.
     * 
     * @return
     *     possible object is
     *     {@link TimeSpan }
     *     
     */
    public TimeSpan getTimeSpan() {
        return timeSpan;
    }

    /**
     * Sets the value of the timeSpan property.
     * 
     * @param value
     *     allowed object is
     *     {@link TimeSpan }
     *     
     */
    public void setTimeSpan(TimeSpan value) {
        this.timeSpan = value;
    }

    /**
     * Gets the value of the guarantee property.
     * 
     * @return
     *     possible object is
     *     {@link Guarantee }
     *     
     */
    public Guarantee getGuarantee() {
        return guarantee;
    }

    /**
     * Sets the value of the guarantee property.
     * 
     * @param value
     *     allowed object is
     *     {@link Guarantee }
     *     
     */
    public void setGuarantee(Guarantee value) {
        this.guarantee = value;
    }

    /**
     * Gets the value of the payment property.
     * 
     * @return
     *     possible object is
     *     {@link Payment }
     *     
     */
    public Payment getPayment() {
        return payment;
    }

    /**
     * Sets the value of the payment property.
     * 
     * @param value
     *     allowed object is
     *     {@link Payment }
     *     
     */
    public void setPayment(Payment value) {
        this.payment = value;
    }

    /**
     * Gets the value of the creditCardDeposit property.
     * 
     * @return
     *     possible object is
     *     {@link CreditCardPayment }
     *     
     */
    public CreditCardPayment getCreditCardDeposit() {
        return creditCardDeposit;
    }

    /**
     * Sets the value of the creditCardDeposit property.
     * 
     * @param value
     *     allowed object is
     *     {@link CreditCardPayment }
     *     
     */
    public void setCreditCardDeposit(CreditCardPayment value) {
        this.creditCardDeposit = value;
    }

    /**
     * Gets the value of the cancelPenalties property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfCancelPenalty }
     *     
     */
    public ArrayOfCancelPenalty getCancelPenalties() {
        return cancelPenalties;
    }

    /**
     * Sets the value of the cancelPenalties property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfCancelPenalty }
     *     
     */
    public void setCancelPenalties(ArrayOfCancelPenalty value) {
        this.cancelPenalties = value;
    }

    /**
     * Gets the value of the cancelTerm property.
     * 
     * @return
     *     possible object is
     *     {@link CancelTerm }
     *     
     */
    public CancelTerm getCancelTerm() {
        return cancelTerm;
    }

    /**
     * Sets the value of the cancelTerm property.
     * 
     * @param value
     *     allowed object is
     *     {@link CancelTerm }
     *     
     */
    public void setCancelTerm(CancelTerm value) {
        this.cancelTerm = value;
    }

    /**
     * Gets the value of the hotelReference property.
     * 
     * @return
     *     possible object is
     *     {@link HotelReference }
     *     
     */
    public HotelReference getHotelReference() {
        return hotelReference;
    }

    /**
     * Sets the value of the hotelReference property.
     * 
     * @param value
     *     allowed object is
     *     {@link HotelReference }
     *     
     */
    public void setHotelReference(HotelReference value) {
        this.hotelReference = value;
    }

    /**
     * Gets the value of the hotelContact property.
     * 
     * @return
     *     possible object is
     *     {@link HotelContact }
     *     
     */
    public HotelContact getHotelContact() {
        return hotelContact;
    }

    /**
     * Sets the value of the hotelContact property.
     * 
     * @param value
     *     allowed object is
     *     {@link HotelContact }
     *     
     */
    public void setHotelContact(HotelContact value) {
        this.hotelContact = value;
    }

    /**
     * Gets the value of the total property.
     * 
     * @return
     *     possible object is
     *     {@link Amount }
     *     
     */
    public Amount getTotal() {
        return total;
    }

    /**
     * Sets the value of the total property.
     * 
     * @param value
     *     allowed object is
     *     {@link Amount }
     *     
     */
    public void setTotal(Amount value) {
        this.total = value;
    }

    /**
     * Gets the value of the resGuestRPHs property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfResGuestRPH }
     *     
     */
    public ArrayOfResGuestRPH getResGuestRPHs() {
        return resGuestRPHs;
    }

    /**
     * Sets the value of the resGuestRPHs property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfResGuestRPH }
     *     
     */
    public void setResGuestRPHs(ArrayOfResGuestRPH value) {
        this.resGuestRPHs = value;
    }

    /**
     * Gets the value of the comments property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfReservationComment }
     *     
     */
    public ArrayOfReservationComment getComments() {
        return comments;
    }

    /**
     * Sets the value of the comments property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfReservationComment }
     *     
     */
    public void setComments(ArrayOfReservationComment value) {
        this.comments = value;
    }

    /**
     * Gets the value of the specialRequests property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfSpecialRequest }
     *     
     */
    public ArrayOfSpecialRequest getSpecialRequests() {
        return specialRequests;
    }

    /**
     * Sets the value of the specialRequests property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfSpecialRequest }
     *     
     */
    public void setSpecialRequests(ArrayOfSpecialRequest value) {
        this.specialRequests = value;
    }

    /**
     * Gets the value of the packages property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfPackageElement }
     *     
     */
    public ArrayOfPackageElement getPackages() {
        return packages;
    }

    /**
     * Sets the value of the packages property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfPackageElement }
     *     
     */
    public void setPackages(ArrayOfPackageElement value) {
        this.packages = value;
    }

    /**
     * Gets the value of the hotelExtendedInformation property.
     * 
     * @return
     *     possible object is
     *     {@link ExtendedHotelInfo }
     *     
     */
    public ExtendedHotelInfo getHotelExtendedInformation() {
        return hotelExtendedInformation;
    }

    /**
     * Sets the value of the hotelExtendedInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link ExtendedHotelInfo }
     *     
     */
    public void setHotelExtendedInformation(ExtendedHotelInfo value) {
        this.hotelExtendedInformation = value;
    }

    /**
     * Gets the value of the dailyChargePoints property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfTimeSpanPoints }
     *     
     */
    public ArrayOfTimeSpanPoints getDailyChargePoints() {
        return dailyChargePoints;
    }

    /**
     * Sets the value of the dailyChargePoints property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfTimeSpanPoints }
     *     
     */
    public void setDailyChargePoints(ArrayOfTimeSpanPoints value) {
        this.dailyChargePoints = value;
    }

    /**
     * Gets the value of the memberAwardInfo property.
     * 
     * @return
     *     possible object is
     *     {@link MemberAwardInfo }
     *     
     */
    public MemberAwardInfo getMemberAwardInfo() {
        return memberAwardInfo;
    }

    /**
     * Sets the value of the memberAwardInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link MemberAwardInfo }
     *     
     */
    public void setMemberAwardInfo(MemberAwardInfo value) {
        this.memberAwardInfo = value;
    }

    /**
     * Gets the value of the expectedCharges property.
     * 
     * @return
     *     possible object is
     *     {@link DailyChargeList }
     *     
     */
    public DailyChargeList getExpectedCharges() {
        return expectedCharges;
    }

    /**
     * Sets the value of the expectedCharges property.
     * 
     * @param value
     *     allowed object is
     *     {@link DailyChargeList }
     *     
     */
    public void setExpectedCharges(DailyChargeList value) {
        this.expectedCharges = value;
    }

    /**
     * Gets the value of the gdsTotalPricing property.
     * 
     * @return
     *     possible object is
     *     {@link GdsTotalPricing }
     *     
     */
    public GdsTotalPricing getGdsTotalPricing() {
        return gdsTotalPricing;
    }

    /**
     * Sets the value of the gdsTotalPricing property.
     * 
     * @param value
     *     allowed object is
     *     {@link GdsTotalPricing }
     *     
     */
    public void setGdsTotalPricing(GdsTotalPricing value) {
        this.gdsTotalPricing = value;
    }

    /**
     * Gets the value of the isAlternate property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsAlternate() {
        return isAlternate;
    }

    /**
     * Sets the value of the isAlternate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsAlternate(Boolean value) {
        this.isAlternate = value;
    }

}
