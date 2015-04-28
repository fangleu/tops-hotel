
package com.micros.webservices.og._4_3.hotelcommon;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ResPayRouting complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResPayRouting">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="RoutingInstruction" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Owner" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Window" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="Room" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResPayRouting")
public class ResPayRouting {

    @XmlAttribute(name = "RoutingInstruction")
    protected String routingInstruction;
    @XmlAttribute(name = "Owner")
    protected String owner;
    @XmlAttribute(name = "Window", required = true)
    protected int window;
    @XmlAttribute(name = "Room")
    protected String room;

    /**
     * Gets the value of the routingInstruction property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoutingInstruction() {
        return routingInstruction;
    }

    /**
     * Sets the value of the routingInstruction property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoutingInstruction(String value) {
        this.routingInstruction = value;
    }

    /**
     * Gets the value of the owner property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Sets the value of the owner property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOwner(String value) {
        this.owner = value;
    }

    /**
     * Gets the value of the window property.
     * 
     */
    public int getWindow() {
        return window;
    }

    /**
     * Sets the value of the window property.
     * 
     */
    public void setWindow(int value) {
        this.window = value;
    }

    /**
     * Gets the value of the room property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoom() {
        return room;
    }

    /**
     * Sets the value of the room property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoom(String value) {
        this.room = value;
    }

}
