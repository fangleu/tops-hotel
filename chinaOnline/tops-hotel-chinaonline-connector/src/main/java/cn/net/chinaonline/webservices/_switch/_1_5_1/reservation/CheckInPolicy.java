
package cn.net.chinaonline.webservices._switch._1_5_1.reservation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CheckInPolicy complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CheckInPolicy">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="EarliestTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LatestTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CheckInPolicy", propOrder = {
    "earliestTime",
    "latestTime"
})
public class CheckInPolicy {

    @XmlElement(name = "EarliestTime")
    protected String earliestTime;
    @XmlElement(name = "LatestTime")
    protected String latestTime;

    /**
     * Gets the value of the earliestTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEarliestTime() {
        return earliestTime;
    }

    /**
     * Sets the value of the earliestTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEarliestTime(String value) {
        this.earliestTime = value;
    }

    /**
     * Gets the value of the latestTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLatestTime() {
        return latestTime;
    }

    /**
     * Sets the value of the latestTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLatestTime(String value) {
        this.latestTime = value;
    }

}
