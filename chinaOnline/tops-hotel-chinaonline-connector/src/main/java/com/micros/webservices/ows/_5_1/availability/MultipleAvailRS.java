
package com.micros.webservices.ows._5_1.availability;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.opentravel.ota._2003._05.ErrorsType;
import org.opentravel.ota._2003._05.MultipleAvailResponseSegments;
import org.opentravel.ota._2003._05.POS;
import org.opentravel.ota._2003._05.Success;


/**
 * <p>Java class for MultipleAvailRS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MultipleAvailRS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Success" type="{http://www.opentravel.org/OTA/2003/05}Success" minOccurs="0"/>
 *         &lt;element name="Errors" type="{http://www.opentravel.org/OTA/2003/05}ErrorsType" minOccurs="0"/>
 *         &lt;element name="POS" type="{http://www.opentravel.org/OTA/2003/05}POS" minOccurs="0"/>
 *         &lt;element name="MultipleAvailResponseSegments" type="{http://www.opentravel.org/OTA/2003/05}MultipleAvailResponseSegments" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Version" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MultipleAvailRS", propOrder = {
    "success",
    "errors",
    "pos",
    "multipleAvailResponseSegments"
})
public class MultipleAvailRS {

    @XmlElement(name = "Success")
    protected Success success;
    @XmlElement(name = "Errors")
    protected ErrorsType errors;
    @XmlElement(name = "POS")
    protected POS pos;
    @XmlElement(name = "MultipleAvailResponseSegments")
    protected MultipleAvailResponseSegments multipleAvailResponseSegments;
    @XmlAttribute(name = "Version", required = true)
    protected BigDecimal version;

    /**
     * Gets the value of the success property.
     * 
     * @return
     *     possible object is
     *     {@link Success }
     *     
     */
    public Success getSuccess() {
        return success;
    }

    /**
     * Sets the value of the success property.
     * 
     * @param value
     *     allowed object is
     *     {@link Success }
     *     
     */
    public void setSuccess(Success value) {
        this.success = value;
    }

    /**
     * Gets the value of the errors property.
     * 
     * @return
     *     possible object is
     *     {@link ErrorsType }
     *     
     */
    public ErrorsType getErrors() {
        return errors;
    }

    /**
     * Sets the value of the errors property.
     * 
     * @param value
     *     allowed object is
     *     {@link ErrorsType }
     *     
     */
    public void setErrors(ErrorsType value) {
        this.errors = value;
    }

    /**
     * Gets the value of the pos property.
     * 
     * @return
     *     possible object is
     *     {@link POS }
     *     
     */
    public POS getPOS() {
        return pos;
    }

    /**
     * Sets the value of the pos property.
     * 
     * @param value
     *     allowed object is
     *     {@link POS }
     *     
     */
    public void setPOS(POS value) {
        this.pos = value;
    }

    /**
     * Gets the value of the multipleAvailResponseSegments property.
     * 
     * @return
     *     possible object is
     *     {@link MultipleAvailResponseSegments }
     *     
     */
    public MultipleAvailResponseSegments getMultipleAvailResponseSegments() {
        return multipleAvailResponseSegments;
    }

    /**
     * Sets the value of the multipleAvailResponseSegments property.
     * 
     * @param value
     *     allowed object is
     *     {@link MultipleAvailResponseSegments }
     *     
     */
    public void setMultipleAvailResponseSegments(MultipleAvailResponseSegments value) {
        this.multipleAvailResponseSegments = value;
    }

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setVersion(BigDecimal value) {
        this.version = value;
    }

}
