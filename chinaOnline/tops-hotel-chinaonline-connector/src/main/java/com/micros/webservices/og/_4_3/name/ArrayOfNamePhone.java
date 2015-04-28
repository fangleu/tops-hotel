
package com.micros.webservices.og._4_3.name;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfNamePhone complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfNamePhone">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="NamePhone" type="{http://webservices.micros.com/og/4.3/Name/}NamePhone" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfNamePhone", propOrder = {
    "namePhone"
})
public class ArrayOfNamePhone {

    @XmlElement(name = "NamePhone")
    protected List<NamePhone> namePhone;

    /**
     * Gets the value of the namePhone property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the namePhone property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNamePhone().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NamePhone }
     * 
     * 
     */
    public List<NamePhone> getNamePhone() {
        if (namePhone == null) {
            namePhone = new ArrayList<NamePhone>();
        }
        return this.namePhone;
    }

}
