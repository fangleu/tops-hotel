
package com.micros.webservices.og._4_3.hotelcommon;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ChargeList complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ChargeList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Charges" type="{http://webservices.micros.com/og/4.3/HotelCommon/}Charge" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="TotalCharges" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="TotalTaxAndFee" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="decimals" type="{http://www.w3.org/2001/XMLSchema}short" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ChargeList", propOrder = {
    "charges"
})
public class ChargeList {

    @XmlElement(name = "Charges")
    protected List<Charge> charges;
    @XmlAttribute(name = "TotalCharges", required = true)
    protected double totalCharges;
    @XmlAttribute(name = "TotalTaxAndFee")
    protected Double totalTaxAndFee;
    @XmlAttribute(name = "decimals")
    protected Short decimals;

    /**
     * Gets the value of the charges property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the charges property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCharges().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Charge }
     * 
     * 
     */
    public List<Charge> getCharges() {
        if (charges == null) {
            charges = new ArrayList<Charge>();
        }
        return this.charges;
    }

    /**
     * Gets the value of the totalCharges property.
     * 
     */
    public double getTotalCharges() {
        return totalCharges;
    }

    /**
     * Sets the value of the totalCharges property.
     * 
     */
    public void setTotalCharges(double value) {
        this.totalCharges = value;
    }

    /**
     * Gets the value of the totalTaxAndFee property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getTotalTaxAndFee() {
        return totalTaxAndFee;
    }

    /**
     * Sets the value of the totalTaxAndFee property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setTotalTaxAndFee(Double value) {
        this.totalTaxAndFee = value;
    }

    /**
     * Gets the value of the decimals property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getDecimals() {
        return decimals;
    }

    /**
     * Sets the value of the decimals property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setDecimals(Short value) {
        this.decimals = value;
    }

}
