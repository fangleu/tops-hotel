
package com.micros.webservices.og._4_3.name;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfNameCreditCard complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfNameCreditCard">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="NameCreditCard" type="{http://webservices.micros.com/og/4.3/Name/}NameCreditCard" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfNameCreditCard", propOrder = {
    "nameCreditCard"
})
public class ArrayOfNameCreditCard {

    @XmlElement(name = "NameCreditCard")
    protected List<NameCreditCard> nameCreditCard;

    /**
     * Gets the value of the nameCreditCard property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the nameCreditCard property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNameCreditCard().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NameCreditCard }
     * 
     * 
     */
    public List<NameCreditCard> getNameCreditCard() {
        if (nameCreditCard == null) {
            nameCreditCard = new ArrayList<NameCreditCard>();
        }
        return this.nameCreditCard;
    }

}
