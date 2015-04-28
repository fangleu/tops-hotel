
package cn.net.chinaonline.webservices._switch._1_5_1.reservation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CreateBookingRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CreateBookingRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://webservices.micros.com/ows/5.1/Reservation.wsdl}CreateBookingRequest">
 *       &lt;sequence>
 *         &lt;element name="CheckInPolicy" type="{http://webservices.chinaonline.net.cn/switch/1.5.1/Reservation.wsdl}CheckInPolicy" minOccurs="0"/>
 *         &lt;element name="ChannelUniqueResID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CreateBookingRequest", propOrder = {
    "checkInPolicy",
    "channelUniqueResID"
})
public class CreateBookingRequest
    extends com.micros.webservices.ows._5_1.reservation.CreateBookingRequest
{

    @XmlElement(name = "CheckInPolicy")
    protected CheckInPolicy checkInPolicy;
    @XmlElement(name = "ChannelUniqueResID")
    protected String channelUniqueResID;

    /**
     * Gets the value of the checkInPolicy property.
     * 
     * @return
     *     possible object is
     *     {@link CheckInPolicy }
     *     
     */
    public CheckInPolicy getCheckInPolicy() {
        return checkInPolicy;
    }

    /**
     * Sets the value of the checkInPolicy property.
     * 
     * @param value
     *     allowed object is
     *     {@link CheckInPolicy }
     *     
     */
    public void setCheckInPolicy(CheckInPolicy value) {
        this.checkInPolicy = value;
    }

    /**
     * Gets the value of the channelUniqueResID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChannelUniqueResID() {
        return channelUniqueResID;
    }

    /**
     * Sets the value of the channelUniqueResID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChannelUniqueResID(String value) {
        this.channelUniqueResID = value;
    }

}
