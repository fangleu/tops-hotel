
package com.micros.webservices.og._4_3.hotelcommon;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for Voucher complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Voucher">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="voucherNumber" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="voucherIssuedBy" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="voucherValidDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Voucher")
public class Voucher {

    @XmlAttribute(name = "voucherNumber")
    protected String voucherNumber;
    @XmlAttribute(name = "voucherIssuedBy")
    protected String voucherIssuedBy;
    @XmlAttribute(name = "voucherValidDate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar voucherValidDate;

    /**
     * Gets the value of the voucherNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVoucherNumber() {
        return voucherNumber;
    }

    /**
     * Sets the value of the voucherNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVoucherNumber(String value) {
        this.voucherNumber = value;
    }

    /**
     * Gets the value of the voucherIssuedBy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVoucherIssuedBy() {
        return voucherIssuedBy;
    }

    /**
     * Sets the value of the voucherIssuedBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVoucherIssuedBy(String value) {
        this.voucherIssuedBy = value;
    }

    /**
     * Gets the value of the voucherValidDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getVoucherValidDate() {
        return voucherValidDate;
    }

    /**
     * Sets the value of the voucherValidDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setVoucherValidDate(XMLGregorianCalendar value) {
        this.voucherValidDate = value;
    }

}
