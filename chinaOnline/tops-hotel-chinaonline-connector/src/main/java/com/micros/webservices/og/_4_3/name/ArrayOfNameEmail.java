
package com.micros.webservices.og._4_3.name;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfNameEmail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfNameEmail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="NameEmail" type="{http://webservices.micros.com/og/4.3/Name/}NameEmail" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfNameEmail", propOrder = {
    "nameEmail"
})
public class ArrayOfNameEmail {

    @XmlElement(name = "NameEmail")
    protected List<NameEmail> nameEmail;

    /**
     * Gets the value of the nameEmail property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the nameEmail property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNameEmail().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NameEmail }
     * 
     * 
     */
    public List<NameEmail> getNameEmail() {
        if (nameEmail == null) {
            nameEmail = new ArrayList<NameEmail>();
        }
        return this.nameEmail;
    }

}
