
package com.micros.webservices.og._4_3.reservation;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import com.micros.webservices.og._4_3.common.ArrayOfUniqueID;
import com.micros.webservices.og._4_3.common.ArrayOfUserDefinedValue;
import com.micros.webservices.og._4_3.common.History;
import com.micros.webservices.og._4_3.hotelcommon.ArrayOfAccompanyGuest;
import com.micros.webservices.og._4_3.hotelcommon.ArrayOfRoomStay;
import com.micros.webservices.og._4_3.hotelcommon.RequstBookingReason;
import com.micros.webservices.og._4_3.hotelcommon.ResPayMethod;
import com.micros.webservices.og._4_3.hotelcommon.ResPayRouting;
import com.micros.webservices.og._4_3.membership.ECertificate;
import com.micros.webservices.og._4_3.name.ArrayOfPreference;


/**
 * <p>Java class for HotelReservation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="HotelReservation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="UniqueIDList" type="{http://webservices.micros.com/og/4.3/Common/}ArrayOfUniqueID" minOccurs="0"/>
 *         &lt;element name="RoomStays" type="{http://webservices.micros.com/og/4.3/HotelCommon/}ArrayOfRoomStay" minOccurs="0"/>
 *         &lt;element name="ResGuests" type="{http://webservices.micros.com/og/4.3/Reservation/}ArrayOfResGuest" minOccurs="0"/>
 *         &lt;element name="WrittenConfInst" type="{http://webservices.micros.com/og/4.3/Reservation/}WrittenConfInst" minOccurs="0"/>
 *         &lt;element name="ReservationHistory" type="{http://webservices.micros.com/og/4.3/Common/}History" minOccurs="0"/>
 *         &lt;element name="UserDefinedValues" type="{http://webservices.micros.com/og/4.3/Common/}ArrayOfUserDefinedValue" minOccurs="0"/>
 *         &lt;element name="Invoice" type="{http://webservices.micros.com/og/4.3/Reservation/}BillHeader" minOccurs="0"/>
 *         &lt;element name="Preferences" type="{http://webservices.micros.com/og/4.3/Name/}ArrayOfPreference" minOccurs="0"/>
 *         &lt;element name="Alerts" type="{http://webservices.micros.com/og/4.3/Reservation/}Alert" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="PayRoutings" type="{http://webservices.micros.com/og/4.3/HotelCommon/}ResPayRouting" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="PayMethods" type="{http://webservices.micros.com/og/4.3/HotelCommon/}ResPayMethod" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="AccompanyGuests" type="{http://webservices.micros.com/og/4.3/HotelCommon/}ArrayOfAccompanyGuest" minOccurs="0"/>
 *         &lt;element name="ECertificate" type="{http://webservices.micros.com/og/4.3/Membership/}ECertificate" minOccurs="0"/>
 *         &lt;element name="onRequestBookingReason" type="{http://webservices.micros.com/og/4.3/HotelCommon/}RequstBookingReason" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="reservationAction" type="{http://webservices.micros.com/og/4.3/Reservation/}ReservationActionType" />
 *       &lt;attribute name="reservationStatus" type="{http://webservices.micros.com/og/4.3/Reservation/}ReservationStatusType" />
 *       &lt;attribute name="SessionAction" use="required" type="{http://webservices.micros.com/og/4.3/Reservation/}SessionActionType" />
 *       &lt;attribute name="marketSegment" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="sourceCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="originCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="authorizer" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="compRoutingFlag" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="compRoutingAuthorizer" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="onBehalfFlag" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="redemReservationFlag" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="insertUser" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="insertDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="updateUser" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="updateDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="inactiveDate" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="walkIn" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="noPost" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="remoteCo" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="group" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="attachPreferenceProfileField" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="allowPreRegisteration" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="preRegisterFlag" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="keyExpirationDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="totalCreditCardSurcharges" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="DoNotMoveRoom" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HotelReservation", propOrder = {
    "uniqueIDList",
    "roomStays",
    "resGuests",
    "writtenConfInst",
    "reservationHistory",
    "userDefinedValues",
    "invoice",
    "preferences",
    "alerts",
    "payRoutings",
    "payMethods",
    "accompanyGuests",
    "eCertificate",
    "onRequestBookingReason"
})
@XmlSeeAlso({
    HotelReservationListHotelReservation.class
})
public class HotelReservation {

    @XmlElement(name = "UniqueIDList")
    protected ArrayOfUniqueID uniqueIDList;
    @XmlElement(name = "RoomStays")
    protected ArrayOfRoomStay roomStays;
    @XmlElement(name = "ResGuests")
    protected ArrayOfResGuest resGuests;
    @XmlElement(name = "WrittenConfInst")
    protected WrittenConfInst writtenConfInst;
    @XmlElement(name = "ReservationHistory")
    protected History reservationHistory;
    @XmlElement(name = "UserDefinedValues")
    protected ArrayOfUserDefinedValue userDefinedValues;
    @XmlElement(name = "Invoice")
    protected BillHeader invoice;
    @XmlElement(name = "Preferences")
    protected ArrayOfPreference preferences;
    @XmlElement(name = "Alerts")
    protected List<Alert> alerts;
    @XmlElement(name = "PayRoutings")
    protected List<ResPayRouting> payRoutings;
    @XmlElement(name = "PayMethods")
    protected List<ResPayMethod> payMethods;
    @XmlElement(name = "AccompanyGuests")
    protected ArrayOfAccompanyGuest accompanyGuests;
    @XmlElement(name = "ECertificate")
    protected ECertificate eCertificate;
    protected RequstBookingReason onRequestBookingReason;
    @XmlAttribute(name = "reservationAction")
    protected ReservationActionType reservationAction;
    @XmlAttribute(name = "reservationStatus")
    protected ReservationStatusType reservationStatus;
    @XmlAttribute(name = "SessionAction", required = true)
    protected SessionActionType sessionAction;
    @XmlAttribute(name = "marketSegment")
    protected String marketSegment;
    @XmlAttribute(name = "sourceCode")
    protected String sourceCode;
    @XmlAttribute(name = "originCode")
    protected String originCode;
    @XmlAttribute(name = "authorizer")
    protected String authorizer;
    @XmlAttribute(name = "compRoutingFlag")
    protected String compRoutingFlag;
    @XmlAttribute(name = "compRoutingAuthorizer")
    protected String compRoutingAuthorizer;
    @XmlAttribute(name = "onBehalfFlag")
    protected Boolean onBehalfFlag;
    @XmlAttribute(name = "redemReservationFlag")
    protected Boolean redemReservationFlag;
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
    @XmlAttribute(name = "walkIn")
    protected Boolean walkIn;
    @XmlAttribute(name = "noPost")
    protected Boolean noPost;
    @XmlAttribute(name = "remoteCo")
    protected Boolean remoteCo;
    @XmlAttribute(name = "group")
    protected String group;
    @XmlAttribute(name = "attachPreferenceProfileField")
    protected Boolean attachPreferenceProfileField;
    @XmlAttribute(name = "allowPreRegisteration")
    protected Boolean allowPreRegisteration;
    @XmlAttribute(name = "preRegisterFlag")
    protected Boolean preRegisterFlag;
    @XmlAttribute(name = "keyExpirationDate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar keyExpirationDate;
    @XmlAttribute(name = "totalCreditCardSurcharges")
    protected Double totalCreditCardSurcharges;
    @XmlAttribute(name = "DoNotMoveRoom")
    protected Boolean doNotMoveRoom;

    /**
     * Gets the value of the uniqueIDList property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfUniqueID }
     *     
     */
    public ArrayOfUniqueID getUniqueIDList() {
        return uniqueIDList;
    }

    /**
     * Sets the value of the uniqueIDList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfUniqueID }
     *     
     */
    public void setUniqueIDList(ArrayOfUniqueID value) {
        this.uniqueIDList = value;
    }

    /**
     * Gets the value of the roomStays property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfRoomStay }
     *     
     */
    public ArrayOfRoomStay getRoomStays() {
        return roomStays;
    }

    /**
     * Sets the value of the roomStays property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfRoomStay }
     *     
     */
    public void setRoomStays(ArrayOfRoomStay value) {
        this.roomStays = value;
    }

    /**
     * Gets the value of the resGuests property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfResGuest }
     *     
     */
    public ArrayOfResGuest getResGuests() {
        return resGuests;
    }

    /**
     * Sets the value of the resGuests property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfResGuest }
     *     
     */
    public void setResGuests(ArrayOfResGuest value) {
        this.resGuests = value;
    }

    /**
     * Gets the value of the writtenConfInst property.
     * 
     * @return
     *     possible object is
     *     {@link WrittenConfInst }
     *     
     */
    public WrittenConfInst getWrittenConfInst() {
        return writtenConfInst;
    }

    /**
     * Sets the value of the writtenConfInst property.
     * 
     * @param value
     *     allowed object is
     *     {@link WrittenConfInst }
     *     
     */
    public void setWrittenConfInst(WrittenConfInst value) {
        this.writtenConfInst = value;
    }

    /**
     * Gets the value of the reservationHistory property.
     * 
     * @return
     *     possible object is
     *     {@link History }
     *     
     */
    public History getReservationHistory() {
        return reservationHistory;
    }

    /**
     * Sets the value of the reservationHistory property.
     * 
     * @param value
     *     allowed object is
     *     {@link History }
     *     
     */
    public void setReservationHistory(History value) {
        this.reservationHistory = value;
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
     * Gets the value of the invoice property.
     * 
     * @return
     *     possible object is
     *     {@link BillHeader }
     *     
     */
    public BillHeader getInvoice() {
        return invoice;
    }

    /**
     * Sets the value of the invoice property.
     * 
     * @param value
     *     allowed object is
     *     {@link BillHeader }
     *     
     */
    public void setInvoice(BillHeader value) {
        this.invoice = value;
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
     * Gets the value of the alerts property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the alerts property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAlerts().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Alert }
     * 
     * 
     */
    public List<Alert> getAlerts() {
        if (alerts == null) {
            alerts = new ArrayList<Alert>();
        }
        return this.alerts;
    }

    /**
     * Gets the value of the payRoutings property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the payRoutings property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPayRoutings().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ResPayRouting }
     * 
     * 
     */
    public List<ResPayRouting> getPayRoutings() {
        if (payRoutings == null) {
            payRoutings = new ArrayList<ResPayRouting>();
        }
        return this.payRoutings;
    }

    /**
     * Gets the value of the payMethods property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the payMethods property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPayMethods().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ResPayMethod }
     * 
     * 
     */
    public List<ResPayMethod> getPayMethods() {
        if (payMethods == null) {
            payMethods = new ArrayList<ResPayMethod>();
        }
        return this.payMethods;
    }

    /**
     * Gets the value of the accompanyGuests property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfAccompanyGuest }
     *     
     */
    public ArrayOfAccompanyGuest getAccompanyGuests() {
        return accompanyGuests;
    }

    /**
     * Sets the value of the accompanyGuests property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfAccompanyGuest }
     *     
     */
    public void setAccompanyGuests(ArrayOfAccompanyGuest value) {
        this.accompanyGuests = value;
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
     * Gets the value of the onRequestBookingReason property.
     * 
     * @return
     *     possible object is
     *     {@link RequstBookingReason }
     *     
     */
    public RequstBookingReason getOnRequestBookingReason() {
        return onRequestBookingReason;
    }

    /**
     * Sets the value of the onRequestBookingReason property.
     * 
     * @param value
     *     allowed object is
     *     {@link RequstBookingReason }
     *     
     */
    public void setOnRequestBookingReason(RequstBookingReason value) {
        this.onRequestBookingReason = value;
    }

    /**
     * Gets the value of the reservationAction property.
     * 
     * @return
     *     possible object is
     *     {@link ReservationActionType }
     *     
     */
    public ReservationActionType getReservationAction() {
        return reservationAction;
    }

    /**
     * Sets the value of the reservationAction property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReservationActionType }
     *     
     */
    public void setReservationAction(ReservationActionType value) {
        this.reservationAction = value;
    }

    /**
     * Gets the value of the reservationStatus property.
     * 
     * @return
     *     possible object is
     *     {@link ReservationStatusType }
     *     
     */
    public ReservationStatusType getReservationStatus() {
        return reservationStatus;
    }

    /**
     * Sets the value of the reservationStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReservationStatusType }
     *     
     */
    public void setReservationStatus(ReservationStatusType value) {
        this.reservationStatus = value;
    }

    /**
     * Gets the value of the sessionAction property.
     * 
     * @return
     *     possible object is
     *     {@link SessionActionType }
     *     
     */
    public SessionActionType getSessionAction() {
        return sessionAction;
    }

    /**
     * Sets the value of the sessionAction property.
     * 
     * @param value
     *     allowed object is
     *     {@link SessionActionType }
     *     
     */
    public void setSessionAction(SessionActionType value) {
        this.sessionAction = value;
    }

    /**
     * Gets the value of the marketSegment property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMarketSegment() {
        return marketSegment;
    }

    /**
     * Sets the value of the marketSegment property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMarketSegment(String value) {
        this.marketSegment = value;
    }

    /**
     * Gets the value of the sourceCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceCode() {
        return sourceCode;
    }

    /**
     * Sets the value of the sourceCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceCode(String value) {
        this.sourceCode = value;
    }

    /**
     * Gets the value of the originCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOriginCode() {
        return originCode;
    }

    /**
     * Sets the value of the originCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOriginCode(String value) {
        this.originCode = value;
    }

    /**
     * Gets the value of the authorizer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthorizer() {
        return authorizer;
    }

    /**
     * Sets the value of the authorizer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthorizer(String value) {
        this.authorizer = value;
    }

    /**
     * Gets the value of the compRoutingFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompRoutingFlag() {
        return compRoutingFlag;
    }

    /**
     * Sets the value of the compRoutingFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompRoutingFlag(String value) {
        this.compRoutingFlag = value;
    }

    /**
     * Gets the value of the compRoutingAuthorizer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompRoutingAuthorizer() {
        return compRoutingAuthorizer;
    }

    /**
     * Sets the value of the compRoutingAuthorizer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompRoutingAuthorizer(String value) {
        this.compRoutingAuthorizer = value;
    }

    /**
     * Gets the value of the onBehalfFlag property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isOnBehalfFlag() {
        return onBehalfFlag;
    }

    /**
     * Sets the value of the onBehalfFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setOnBehalfFlag(Boolean value) {
        this.onBehalfFlag = value;
    }

    /**
     * Gets the value of the redemReservationFlag property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isRedemReservationFlag() {
        return redemReservationFlag;
    }

    /**
     * Sets the value of the redemReservationFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRedemReservationFlag(Boolean value) {
        this.redemReservationFlag = value;
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

    /**
     * Gets the value of the walkIn property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isWalkIn() {
        return walkIn;
    }

    /**
     * Sets the value of the walkIn property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setWalkIn(Boolean value) {
        this.walkIn = value;
    }

    /**
     * Gets the value of the noPost property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isNoPost() {
        return noPost;
    }

    /**
     * Sets the value of the noPost property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setNoPost(Boolean value) {
        this.noPost = value;
    }

    /**
     * Gets the value of the remoteCo property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isRemoteCo() {
        return remoteCo;
    }

    /**
     * Sets the value of the remoteCo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRemoteCo(Boolean value) {
        this.remoteCo = value;
    }

    /**
     * Gets the value of the group property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGroup() {
        return group;
    }

    /**
     * Sets the value of the group property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGroup(String value) {
        this.group = value;
    }

    /**
     * Gets the value of the attachPreferenceProfileField property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAttachPreferenceProfileField() {
        return attachPreferenceProfileField;
    }

    /**
     * Sets the value of the attachPreferenceProfileField property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAttachPreferenceProfileField(Boolean value) {
        this.attachPreferenceProfileField = value;
    }

    /**
     * Gets the value of the allowPreRegisteration property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAllowPreRegisteration() {
        return allowPreRegisteration;
    }

    /**
     * Sets the value of the allowPreRegisteration property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAllowPreRegisteration(Boolean value) {
        this.allowPreRegisteration = value;
    }

    /**
     * Gets the value of the preRegisterFlag property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPreRegisterFlag() {
        return preRegisterFlag;
    }

    /**
     * Sets the value of the preRegisterFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPreRegisterFlag(Boolean value) {
        this.preRegisterFlag = value;
    }

    /**
     * Gets the value of the keyExpirationDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getKeyExpirationDate() {
        return keyExpirationDate;
    }

    /**
     * Sets the value of the keyExpirationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setKeyExpirationDate(XMLGregorianCalendar value) {
        this.keyExpirationDate = value;
    }

    /**
     * Gets the value of the totalCreditCardSurcharges property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getTotalCreditCardSurcharges() {
        return totalCreditCardSurcharges;
    }

    /**
     * Sets the value of the totalCreditCardSurcharges property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setTotalCreditCardSurcharges(Double value) {
        this.totalCreditCardSurcharges = value;
    }

    /**
     * Gets the value of the doNotMoveRoom property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDoNotMoveRoom() {
        return doNotMoveRoom;
    }

    /**
     * Sets the value of the doNotMoveRoom property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDoNotMoveRoom(Boolean value) {
        this.doNotMoveRoom = value;
    }

}
