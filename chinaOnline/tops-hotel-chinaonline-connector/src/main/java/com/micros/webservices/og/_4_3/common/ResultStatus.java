
package com.micros.webservices.og._4_3.common;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import com.micros.webservices.og._4_3.hotelcommon.GDSResultStatus;


/**
 * <p>Java class for ResultStatus complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResultStatus">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Text" type="{http://webservices.micros.com/og/4.3/Common/}ArrayOfText" minOccurs="0"/>
 *         &lt;element name="IDs" type="{http://webservices.micros.com/og/4.3/Common/}ArrayOfIDPair" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="resultStatusFlag" type="{http://webservices.micros.com/og/4.3/Common/}ResultStatusFlag" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResultStatus", propOrder = {
    "text",
    "iDs"
})
@XmlSeeAlso({
    GDSResultStatus.class
})
public class ResultStatus {

    @XmlElement(name = "Text")
    protected ArrayOfText text;
    @XmlElement(name = "IDs")
    protected ArrayOfIDPair iDs;
    @XmlAttribute(name = "resultStatusFlag")
    protected ResultStatusFlag resultStatusFlag;

    /**
     * Gets the value of the text property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfText }
     *     
     */
    public ArrayOfText getText() {
        return text;
    }

    /**
     * Sets the value of the text property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfText }
     *     
     */
    public void setText(ArrayOfText value) {
        this.text = value;
    }

    /**
     * Gets the value of the iDs property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfIDPair }
     *     
     */
    public ArrayOfIDPair getIDs() {
        return iDs;
    }

    /**
     * Sets the value of the iDs property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfIDPair }
     *     
     */
    public void setIDs(ArrayOfIDPair value) {
        this.iDs = value;
    }

    /**
     * Gets the value of the resultStatusFlag property.
     * 
     * @return
     *     possible object is
     *     {@link ResultStatusFlag }
     *     
     */
    public ResultStatusFlag getResultStatusFlag() {
        return resultStatusFlag;
    }

    /**
     * Sets the value of the resultStatusFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResultStatusFlag }
     *     
     */
    public void setResultStatusFlag(ResultStatusFlag value) {
        this.resultStatusFlag = value;
    }

}
