
package com.micros.webservices.og._4_3.name;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfNameMembership complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfNameMembership">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="NameMembership" type="{http://webservices.micros.com/og/4.3/Name/}NameMembership" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfNameMembership", propOrder = {
    "nameMembership"
})
public class ArrayOfNameMembership {

    @XmlElement(name = "NameMembership")
    protected List<NameMembership> nameMembership;

    /**
     * Gets the value of the nameMembership property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the nameMembership property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNameMembership().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NameMembership }
     * 
     * 
     */
    public List<NameMembership> getNameMembership() {
        if (nameMembership == null) {
            nameMembership = new ArrayList<NameMembership>();
        }
        return this.nameMembership;
    }

}
