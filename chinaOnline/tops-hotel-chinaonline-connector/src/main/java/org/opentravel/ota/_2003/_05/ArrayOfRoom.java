
package org.opentravel.ota._2003._05;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfRoom complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfRoom">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Room" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="RoomTypes" type="{http://www.opentravel.org/OTA/2003/05}ArrayOfRoomType" minOccurs="0"/>
 *                   &lt;element name="AmountData" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="AmountItem" type="{http://www.opentravel.org/OTA/2003/05}ArrayOfAmounts" minOccurs="0"/>
 *                             &lt;element name="AmountMasks" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="AmountMask" minOccurs="0">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;attribute name="Position" use="required" type="{http://www.w3.org/2001/XMLSchema}byte" />
 *                                               &lt;attribute name="Value" use="required" type="{http://www.w3.org/2001/XMLSchema}short" />
 *                                             &lt;/restriction>
 *                                           &lt;/complexContent>
 *                                         &lt;/complexType>
 *                                       &lt;/element>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="RoomStatuses" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="RoomStatus" type="{http://www.opentravel.org/OTA/2003/05}ArrayOfAvailStatus" minOccurs="0"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfRoom", propOrder = {
    "room"
})
public class ArrayOfRoom {

    @XmlElement(name = "Room", nillable = true)
    protected List<ArrayOfRoom.Room> room;

    /**
     * Gets the value of the room property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the room property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRoom().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ArrayOfRoom.Room }
     * 
     * 
     */
    public List<ArrayOfRoom.Room> getRoom() {
        if (room == null) {
            room = new ArrayList<ArrayOfRoom.Room>();
        }
        return this.room;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="RoomTypes" type="{http://www.opentravel.org/OTA/2003/05}ArrayOfRoomType" minOccurs="0"/>
     *         &lt;element name="AmountData" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="AmountItem" type="{http://www.opentravel.org/OTA/2003/05}ArrayOfAmounts" minOccurs="0"/>
     *                   &lt;element name="AmountMasks" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="AmountMask" minOccurs="0">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;attribute name="Position" use="required" type="{http://www.w3.org/2001/XMLSchema}byte" />
     *                                     &lt;attribute name="Value" use="required" type="{http://www.w3.org/2001/XMLSchema}short" />
     *                                   &lt;/restriction>
     *                                 &lt;/complexContent>
     *                               &lt;/complexType>
     *                             &lt;/element>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="RoomStatuses" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="RoomStatus" type="{http://www.opentravel.org/OTA/2003/05}ArrayOfAvailStatus" minOccurs="0"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "roomTypes",
        "amountData",
        "roomStatuses"
    })
    public static class Room {

        @XmlElement(name = "RoomTypes")
        protected ArrayOfRoomType roomTypes;
        @XmlElement(name = "AmountData")
        protected ArrayOfRoom.Room.AmountData amountData;
        @XmlElement(name = "RoomStatuses")
        protected ArrayOfRoom.Room.RoomStatuses roomStatuses;

        /**
         * Gets the value of the roomTypes property.
         * 
         * @return
         *     possible object is
         *     {@link ArrayOfRoomType }
         *     
         */
        public ArrayOfRoomType getRoomTypes() {
            return roomTypes;
        }

        /**
         * Sets the value of the roomTypes property.
         * 
         * @param value
         *     allowed object is
         *     {@link ArrayOfRoomType }
         *     
         */
        public void setRoomTypes(ArrayOfRoomType value) {
            this.roomTypes = value;
        }

        /**
         * Gets the value of the amountData property.
         * 
         * @return
         *     possible object is
         *     {@link ArrayOfRoom.Room.AmountData }
         *     
         */
        public ArrayOfRoom.Room.AmountData getAmountData() {
            return amountData;
        }

        /**
         * Sets the value of the amountData property.
         * 
         * @param value
         *     allowed object is
         *     {@link ArrayOfRoom.Room.AmountData }
         *     
         */
        public void setAmountData(ArrayOfRoom.Room.AmountData value) {
            this.amountData = value;
        }

        /**
         * Gets the value of the roomStatuses property.
         * 
         * @return
         *     possible object is
         *     {@link ArrayOfRoom.Room.RoomStatuses }
         *     
         */
        public ArrayOfRoom.Room.RoomStatuses getRoomStatuses() {
            return roomStatuses;
        }

        /**
         * Sets the value of the roomStatuses property.
         * 
         * @param value
         *     allowed object is
         *     {@link ArrayOfRoom.Room.RoomStatuses }
         *     
         */
        public void setRoomStatuses(ArrayOfRoom.Room.RoomStatuses value) {
            this.roomStatuses = value;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="AmountItem" type="{http://www.opentravel.org/OTA/2003/05}ArrayOfAmounts" minOccurs="0"/>
         *         &lt;element name="AmountMasks" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="AmountMask" minOccurs="0">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;attribute name="Position" use="required" type="{http://www.w3.org/2001/XMLSchema}byte" />
         *                           &lt;attribute name="Value" use="required" type="{http://www.w3.org/2001/XMLSchema}short" />
         *                         &lt;/restriction>
         *                       &lt;/complexContent>
         *                     &lt;/complexType>
         *                   &lt;/element>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "amountItem",
            "amountMasks"
        })
        public static class AmountData {

            @XmlElement(name = "AmountItem")
            protected ArrayOfAmounts amountItem;
            @XmlElement(name = "AmountMasks")
            protected ArrayOfRoom.Room.AmountData.AmountMasks amountMasks;

            /**
             * Gets the value of the amountItem property.
             * 
             * @return
             *     possible object is
             *     {@link ArrayOfAmounts }
             *     
             */
            public ArrayOfAmounts getAmountItem() {
                return amountItem;
            }

            /**
             * Sets the value of the amountItem property.
             * 
             * @param value
             *     allowed object is
             *     {@link ArrayOfAmounts }
             *     
             */
            public void setAmountItem(ArrayOfAmounts value) {
                this.amountItem = value;
            }

            /**
             * Gets the value of the amountMasks property.
             * 
             * @return
             *     possible object is
             *     {@link ArrayOfRoom.Room.AmountData.AmountMasks }
             *     
             */
            public ArrayOfRoom.Room.AmountData.AmountMasks getAmountMasks() {
                return amountMasks;
            }

            /**
             * Sets the value of the amountMasks property.
             * 
             * @param value
             *     allowed object is
             *     {@link ArrayOfRoom.Room.AmountData.AmountMasks }
             *     
             */
            public void setAmountMasks(ArrayOfRoom.Room.AmountData.AmountMasks value) {
                this.amountMasks = value;
            }


            /**
             * <p>Java class for anonymous complex type.
             * 
             * <p>The following schema fragment specifies the expected content contained within this class.
             * 
             * <pre>
             * &lt;complexType>
             *   &lt;complexContent>
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *       &lt;sequence>
             *         &lt;element name="AmountMask" minOccurs="0">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;attribute name="Position" use="required" type="{http://www.w3.org/2001/XMLSchema}byte" />
             *                 &lt;attribute name="Value" use="required" type="{http://www.w3.org/2001/XMLSchema}short" />
             *               &lt;/restriction>
             *             &lt;/complexContent>
             *           &lt;/complexType>
             *         &lt;/element>
             *       &lt;/sequence>
             *     &lt;/restriction>
             *   &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "amountMask"
            })
            public static class AmountMasks {

                @XmlElement(name = "AmountMask")
                protected ArrayOfRoom.Room.AmountData.AmountMasks.AmountMask amountMask;

                /**
                 * Gets the value of the amountMask property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link ArrayOfRoom.Room.AmountData.AmountMasks.AmountMask }
                 *     
                 */
                public ArrayOfRoom.Room.AmountData.AmountMasks.AmountMask getAmountMask() {
                    return amountMask;
                }

                /**
                 * Sets the value of the amountMask property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link ArrayOfRoom.Room.AmountData.AmountMasks.AmountMask }
                 *     
                 */
                public void setAmountMask(ArrayOfRoom.Room.AmountData.AmountMasks.AmountMask value) {
                    this.amountMask = value;
                }


                /**
                 * <p>Java class for anonymous complex type.
                 * 
                 * <p>The following schema fragment specifies the expected content contained within this class.
                 * 
                 * <pre>
                 * &lt;complexType>
                 *   &lt;complexContent>
                 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 *       &lt;attribute name="Position" use="required" type="{http://www.w3.org/2001/XMLSchema}byte" />
                 *       &lt;attribute name="Value" use="required" type="{http://www.w3.org/2001/XMLSchema}short" />
                 *     &lt;/restriction>
                 *   &lt;/complexContent>
                 * &lt;/complexType>
                 * </pre>
                 * 
                 * 
                 */
                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "")
                public static class AmountMask {

                    @XmlAttribute(name = "Position", required = true)
                    protected byte position;
                    @XmlAttribute(name = "Value", required = true)
                    protected short value;

                    /**
                     * Gets the value of the position property.
                     * 
                     */
                    public byte getPosition() {
                        return position;
                    }

                    /**
                     * Sets the value of the position property.
                     * 
                     */
                    public void setPosition(byte value) {
                        this.position = value;
                    }

                    /**
                     * Gets the value of the value property.
                     * 
                     */
                    public short getValue() {
                        return value;
                    }

                    /**
                     * Sets the value of the value property.
                     * 
                     */
                    public void setValue(short value) {
                        this.value = value;
                    }

                }

            }

        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="RoomStatus" type="{http://www.opentravel.org/OTA/2003/05}ArrayOfAvailStatus" minOccurs="0"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "roomStatus"
        })
        public static class RoomStatuses {

            @XmlElement(name = "RoomStatus")
            protected ArrayOfAvailStatus roomStatus;

            /**
             * Gets the value of the roomStatus property.
             * 
             * @return
             *     possible object is
             *     {@link ArrayOfAvailStatus }
             *     
             */
            public ArrayOfAvailStatus getRoomStatus() {
                return roomStatus;
            }

            /**
             * Sets the value of the roomStatus property.
             * 
             * @param value
             *     allowed object is
             *     {@link ArrayOfAvailStatus }
             *     
             */
            public void setRoomStatus(ArrayOfAvailStatus value) {
                this.roomStatus = value;
            }

        }

    }

}
