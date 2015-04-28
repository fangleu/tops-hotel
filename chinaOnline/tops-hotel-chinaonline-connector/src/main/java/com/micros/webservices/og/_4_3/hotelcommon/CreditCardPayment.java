
package com.micros.webservices.og._4_3.hotelcommon;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.micros.webservices.og._4_3.common.Address;
import com.micros.webservices.og._4_3.common.Amount;
import com.micros.webservices.og._4_3.common.CreditCard;


/**
 * <p>Java class for CreditCardPayment complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CreditCardPayment">
 *   &lt;complexContent>
 *     &lt;extension base="{http://webservices.micros.com/og/4.3/Common/}CreditCard">
 *       &lt;sequence>
 *         &lt;element name="CIDNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Address" type="{http://webservices.micros.com/og/4.3/Common/}Address" minOccurs="0"/>
 *         &lt;element name="IssueNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ApprovalCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DepositAmount" type="{http://webservices.micros.com/og/4.3/Common/}Amount" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CreditCardPayment", propOrder = {
    "cidNumber",
    "address",
    "issueNumber",
    "approvalCode",
    "depositAmount"
})
public class CreditCardPayment
    extends CreditCard
{

    @XmlElement(name = "CIDNumber")
    protected String cidNumber;
    @XmlElement(name = "Address")
    protected Address address;
    @XmlElement(name = "IssueNumber")
    protected String issueNumber;
    @XmlElement(name = "ApprovalCode")
    protected String approvalCode;
    @XmlElement(name = "DepositAmount")
    protected Amount depositAmount;

    /**
     * Gets the value of the cidNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCIDNumber() {
        return cidNumber;
    }

    /**
     * Sets the value of the cidNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCIDNumber(String value) {
        this.cidNumber = value;
    }

    /**
     * Gets the value of the address property.
     * 
     * @return
     *     possible object is
     *     {@link Address }
     *     
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Sets the value of the address property.
     * 
     * @param value
     *     allowed object is
     *     {@link Address }
     *     
     */
    public void setAddress(Address value) {
        this.address = value;
    }

    /**
     * Gets the value of the issueNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIssueNumber() {
        return issueNumber;
    }

    /**
     * Sets the value of the issueNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIssueNumber(String value) {
        this.issueNumber = value;
    }

    /**
     * Gets the value of the approvalCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApprovalCode() {
        return approvalCode;
    }

    /**
     * Sets the value of the approvalCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApprovalCode(String value) {
        this.approvalCode = value;
    }

    /**
     * Gets the value of the depositAmount property.
     * 
     * @return
     *     possible object is
     *     {@link Amount }
     *     
     */
    public Amount getDepositAmount() {
        return depositAmount;
    }

    /**
     * Sets the value of the depositAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Amount }
     *     
     */
    public void setDepositAmount(Amount value) {
        this.depositAmount = value;
    }

}
