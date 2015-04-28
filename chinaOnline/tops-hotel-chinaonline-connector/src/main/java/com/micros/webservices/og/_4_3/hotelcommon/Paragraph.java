
package com.micros.webservices.og._4_3.hotelcommon;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import com.micros.webservices.og._4_3.common.Text;


/**
 * <p>Java class for Paragraph complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Paragraph">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice maxOccurs="unbounded" minOccurs="0">
 *           &lt;element name="Image" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *           &lt;element name="URL" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
 *           &lt;element name="Text" type="{http://webservices.micros.com/og/4.3/Common/}Text" minOccurs="0"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *       &lt;attribute name="Name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ParagraphNumber" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Paragraph", propOrder = {
    "imageOrURLOrText"
})
@XmlSeeAlso({
    SpecialRequest.class,
    ReservationComment.class
})
public class Paragraph {

    @XmlElementRefs({
        @XmlElementRef(name = "URL", namespace = "http://webservices.micros.com/og/4.3/HotelCommon/", type = JAXBElement.class),
        @XmlElementRef(name = "Text", namespace = "http://webservices.micros.com/og/4.3/HotelCommon/", type = JAXBElement.class),
        @XmlElementRef(name = "Image", namespace = "http://webservices.micros.com/og/4.3/HotelCommon/", type = JAXBElement.class)
    })
    protected List<JAXBElement<?>> imageOrURLOrText;
    @XmlAttribute(name = "Name")
    protected String name;
    @XmlAttribute(name = "ParagraphNumber")
    protected String paragraphNumber;

    /**
     * Gets the value of the imageOrURLOrText property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the imageOrURLOrText property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getImageOrURLOrText().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link Text }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * 
     */
    public List<JAXBElement<?>> getImageOrURLOrText() {
        if (imageOrURLOrText == null) {
            imageOrURLOrText = new ArrayList<JAXBElement<?>>();
        }
        return this.imageOrURLOrText;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the paragraphNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParagraphNumber() {
        return paragraphNumber;
    }

    /**
     * Sets the value of the paragraphNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParagraphNumber(String value) {
        this.paragraphNumber = value;
    }

}
