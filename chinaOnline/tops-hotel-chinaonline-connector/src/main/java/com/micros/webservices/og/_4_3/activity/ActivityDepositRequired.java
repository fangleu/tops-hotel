
package com.micros.webservices.og._4_3.activity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import com.micros.webservices.og._4_3.common.Amount;


/**
 * <p>Java class for ActivityDepositRequired complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ActivityDepositRequired">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://webservices.micros.com/og/4.3/Common/>Amount">
 *       &lt;attribute name="collectedBy" type="{http://webservices.micros.com/og/4.3/Activity/}ActivityDepositRequiredCollectedBy" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ActivityDepositRequired")
public class ActivityDepositRequired
    extends Amount
{

    @XmlAttribute(name = "collectedBy")
    protected ActivityDepositRequiredCollectedBy collectedBy;

    /**
     * Gets the value of the collectedBy property.
     * 
     * @return
     *     possible object is
     *     {@link ActivityDepositRequiredCollectedBy }
     *     
     */
    public ActivityDepositRequiredCollectedBy getCollectedBy() {
        return collectedBy;
    }

    /**
     * Sets the value of the collectedBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link ActivityDepositRequiredCollectedBy }
     *     
     */
    public void setCollectedBy(ActivityDepositRequiredCollectedBy value) {
        this.collectedBy = value;
    }

}
