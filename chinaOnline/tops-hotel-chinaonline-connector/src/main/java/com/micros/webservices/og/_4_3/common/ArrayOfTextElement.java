
package com.micros.webservices.og._4_3.common;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfTextElement complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfTextElement">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TextElement" type="{http://webservices.micros.com/og/4.3/Common/}TextElement" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfTextElement", propOrder = {
    "textElement"
})
public class ArrayOfTextElement {

    @XmlElement(name = "TextElement", nillable = true)
    protected List<TextElement> textElement;

    /**
     * Gets the value of the textElement property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the textElement property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTextElement().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TextElement }
     * 
     * 
     */
    public List<TextElement> getTextElement() {
        if (textElement == null) {
            textElement = new ArrayList<TextElement>();
        }
        return this.textElement;
    }

}
