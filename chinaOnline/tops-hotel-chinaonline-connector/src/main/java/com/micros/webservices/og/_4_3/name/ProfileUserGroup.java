
package com.micros.webservices.og._4_3.name;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ProfileUserGroup complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProfileUserGroup">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="groupType" use="required" type="{http://webservices.micros.com/og/4.3/Name/}UserGroupType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProfileUserGroup")
public class ProfileUserGroup {

    @XmlAttribute(name = "groupType", required = true)
    protected UserGroupType groupType;

    /**
     * Gets the value of the groupType property.
     * 
     * @return
     *     possible object is
     *     {@link UserGroupType }
     *     
     */
    public UserGroupType getGroupType() {
        return groupType;
    }

    /**
     * Sets the value of the groupType property.
     * 
     * @param value
     *     allowed object is
     *     {@link UserGroupType }
     *     
     */
    public void setGroupType(UserGroupType value) {
        this.groupType = value;
    }

}
