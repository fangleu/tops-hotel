
package com.micros.webservices.og._4_3.hotelcommon;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AmenityAmenityType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="AmenityAmenityType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Property"/>
 *     &lt;enumeration value="Room"/>
 *     &lt;enumeration value="Both"/>
 *     &lt;enumeration value="Nearby"/>
 *     &lt;enumeration value="Exists"/>
 *     &lt;enumeration value="Other"/>
 *     &lt;enumeration value="Parking"/>
 *     &lt;enumeration value="WheelChairAccess"/>
 *     &lt;enumeration value="Gymnasium"/>
 *     &lt;enumeration value="ConferenceRoom"/>
 *     &lt;enumeration value="BusinessCentre"/>
 *     &lt;enumeration value="Pets"/>
 *     &lt;enumeration value="RoomService"/>
 *     &lt;enumeration value="Restaurant"/>
 *     &lt;enumeration value="SwimingPool"/>
 *     &lt;enumeration value="Internet"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "AmenityAmenityType")
@XmlEnum
public enum AmenityAmenityType {

    @XmlEnumValue("Property")
    PROPERTY("Property"),
    @XmlEnumValue("Room")
    ROOM("Room"),
    @XmlEnumValue("Both")
    BOTH("Both"),
    @XmlEnumValue("Nearby")
    NEARBY("Nearby"),
    @XmlEnumValue("Exists")
    EXISTS("Exists"),
    @XmlEnumValue("Other")
    OTHER("Other"),
    @XmlEnumValue("Parking")
    PARKING("Parking"),
    @XmlEnumValue("WheelChairAccess")
    WHEEL_CHAIR_ACCESS("WheelChairAccess"),
    @XmlEnumValue("Gymnasium")
    GYMNASIUM("Gymnasium"),
    @XmlEnumValue("ConferenceRoom")
    CONFERENCE_ROOM("ConferenceRoom"),
    @XmlEnumValue("BusinessCentre")
    BUSINESS_CENTRE("BusinessCentre"),
    @XmlEnumValue("Pets")
    PETS("Pets"),
    @XmlEnumValue("RoomService")
    ROOM_SERVICE("RoomService"),
    @XmlEnumValue("Restaurant")
    RESTAURANT("Restaurant"),
    @XmlEnumValue("SwimingPool")
    SWIMING_POOL("SwimingPool"),
    @XmlEnumValue("Internet")
    INTERNET("Internet");
    private final String value;

    AmenityAmenityType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AmenityAmenityType fromValue(String v) {
        for (AmenityAmenityType c: AmenityAmenityType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
