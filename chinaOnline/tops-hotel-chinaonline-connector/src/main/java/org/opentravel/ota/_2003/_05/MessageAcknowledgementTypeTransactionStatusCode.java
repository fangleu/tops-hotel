
package org.opentravel.ota._2003._05;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MessageAcknowledgementTypeTransactionStatusCode.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="MessageAcknowledgementTypeTransactionStatusCode">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Start"/>
 *     &lt;enumeration value="End"/>
 *     &lt;enumeration value="Rollback"/>
 *     &lt;enumeration value="InSeries"/>
 *     &lt;enumeration value="Continuation"/>
 *     &lt;enumeration value="Subsequent"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "MessageAcknowledgementTypeTransactionStatusCode")
@XmlEnum
public enum MessageAcknowledgementTypeTransactionStatusCode {

    @XmlEnumValue("Start")
    START("Start"),
    @XmlEnumValue("End")
    END("End"),
    @XmlEnumValue("Rollback")
    ROLLBACK("Rollback"),
    @XmlEnumValue("InSeries")
    IN_SERIES("InSeries"),
    @XmlEnumValue("Continuation")
    CONTINUATION("Continuation"),
    @XmlEnumValue("Subsequent")
    SUBSEQUENT("Subsequent");
    private final String value;

    MessageAcknowledgementTypeTransactionStatusCode(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static MessageAcknowledgementTypeTransactionStatusCode fromValue(String v) {
        for (MessageAcknowledgementTypeTransactionStatusCode c: MessageAcknowledgementTypeTransactionStatusCode.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
