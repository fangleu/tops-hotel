
package com.micros.webservices.og._4_3.hotelcommon;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ReservationComment complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ReservationComment">
 *   &lt;complexContent>
 *     &lt;extension base="{http://webservices.micros.com/og/4.3/HotelCommon/}Paragraph">
 *       &lt;attribute name="commentOriginatorCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="guestViewable" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReservationComment")
public class ReservationComment
    extends Paragraph
{

    @XmlAttribute(name = "commentOriginatorCode")
    protected String commentOriginatorCode;
    @XmlAttribute(name = "guestViewable")
    protected Boolean guestViewable;

    /**
     * Gets the value of the commentOriginatorCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCommentOriginatorCode() {
        return commentOriginatorCode;
    }

    /**
     * Sets the value of the commentOriginatorCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCommentOriginatorCode(String value) {
        this.commentOriginatorCode = value;
    }

    /**
     * Gets the value of the guestViewable property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isGuestViewable() {
        return guestViewable;
    }

    /**
     * Sets the value of the guestViewable property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setGuestViewable(Boolean value) {
        this.guestViewable = value;
    }

}
