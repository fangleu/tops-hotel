
package com.micros.webservices.ows._5_1.reservation;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.micros.webservices.ows._5_1.reservation package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _CreateBookingRequest_QNAME = new QName("http://webservices.micros.com/ows/5.1/Reservation.wsdl", "CreateBookingRequest");
    private final static QName _CancelBookingResponse_QNAME = new QName("http://webservices.micros.com/ows/5.1/Reservation.wsdl", "CancelBookingResponse");
    private final static QName _FetchBookingStatusResponse_QNAME = new QName("http://webservices.micros.com/ows/5.1/Reservation.wsdl", "FetchBookingStatusResponse");
    private final static QName _CreateBookingResponse_QNAME = new QName("http://webservices.micros.com/ows/5.1/Reservation.wsdl", "CreateBookingResponse");
    private final static QName _CancelBookingRequest_QNAME = new QName("http://webservices.micros.com/ows/5.1/Reservation.wsdl", "CancelBookingRequest");
    private final static QName _ModifyBookingRequest_QNAME = new QName("http://webservices.micros.com/ows/5.1/Reservation.wsdl", "ModifyBookingRequest");
    private final static QName _FetchBookingStatusRequest_QNAME = new QName("http://webservices.micros.com/ows/5.1/Reservation.wsdl", "FetchBookingStatusRequest");
    private final static QName _ModifyBookingResponse_QNAME = new QName("http://webservices.micros.com/ows/5.1/Reservation.wsdl", "ModifyBookingResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.micros.webservices.ows._5_1.reservation
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link com.micros.webservices.ows._5_1.reservation.CreateBookingRequest }
     * 
     */
    public com.micros.webservices.ows._5_1.reservation.CreateBookingRequest createCreateBookingRequest() {
        return new com.micros.webservices.ows._5_1.reservation.CreateBookingRequest();
    }

    /**
     * Create an instance of {@link FetchBookingStatusResponse }
     * 
     */
    public FetchBookingStatusResponse createFetchBookingStatusResponse() {
        return new FetchBookingStatusResponse();
    }

    /**
     * Create an instance of {@link CancelBookingResponse }
     * 
     */
    public CancelBookingResponse createCancelBookingResponse() {
        return new CancelBookingResponse();
    }

    /**
     * Create an instance of {@link CreateBookingResponse }
     * 
     */
    public CreateBookingResponse createCreateBookingResponse() {
        return new CreateBookingResponse();
    }

    /**
     * Create an instance of {@link ModifyBookingResponse }
     * 
     */
    public ModifyBookingResponse createModifyBookingResponse() {
        return new ModifyBookingResponse();
    }

    /**
     * Create an instance of {@link com.micros.webservices.ows._5_1.reservation.ModifyBookingRequest }
     * 
     */
    public com.micros.webservices.ows._5_1.reservation.ModifyBookingRequest createModifyBookingRequest() {
        return new com.micros.webservices.ows._5_1.reservation.ModifyBookingRequest();
    }

    /**
     * Create an instance of {@link CancelBookingRequest }
     * 
     */
    public CancelBookingRequest createCancelBookingRequest() {
        return new CancelBookingRequest();
    }

    /**
     * Create an instance of {@link FetchBookingStatusRequest }
     * 
     */
    public FetchBookingStatusRequest createFetchBookingStatusRequest() {
        return new FetchBookingStatusRequest();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link cn.net.chinaonline.webservices._switch._1_5_1.reservation.CreateBookingRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.micros.com/ows/5.1/Reservation.wsdl", name = "CreateBookingRequest")
    public JAXBElement<cn.net.chinaonline.webservices._switch._1_5_1.reservation.CreateBookingRequest> createCreateBookingRequest(cn.net.chinaonline.webservices._switch._1_5_1.reservation.CreateBookingRequest value) {
        return new JAXBElement<cn.net.chinaonline.webservices._switch._1_5_1.reservation.CreateBookingRequest>(_CreateBookingRequest_QNAME, cn.net.chinaonline.webservices._switch._1_5_1.reservation.CreateBookingRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CancelBookingResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.micros.com/ows/5.1/Reservation.wsdl", name = "CancelBookingResponse")
    public JAXBElement<CancelBookingResponse> createCancelBookingResponse(CancelBookingResponse value) {
        return new JAXBElement<CancelBookingResponse>(_CancelBookingResponse_QNAME, CancelBookingResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FetchBookingStatusResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.micros.com/ows/5.1/Reservation.wsdl", name = "FetchBookingStatusResponse")
    public JAXBElement<FetchBookingStatusResponse> createFetchBookingStatusResponse(FetchBookingStatusResponse value) {
        return new JAXBElement<FetchBookingStatusResponse>(_FetchBookingStatusResponse_QNAME, FetchBookingStatusResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateBookingResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.micros.com/ows/5.1/Reservation.wsdl", name = "CreateBookingResponse")
    public JAXBElement<CreateBookingResponse> createCreateBookingResponse(CreateBookingResponse value) {
        return new JAXBElement<CreateBookingResponse>(_CreateBookingResponse_QNAME, CreateBookingResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CancelBookingRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.micros.com/ows/5.1/Reservation.wsdl", name = "CancelBookingRequest")
    public JAXBElement<CancelBookingRequest> createCancelBookingRequest(CancelBookingRequest value) {
        return new JAXBElement<CancelBookingRequest>(_CancelBookingRequest_QNAME, CancelBookingRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link cn.net.chinaonline.webservices._switch._1_5_1.reservation.ModifyBookingRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.micros.com/ows/5.1/Reservation.wsdl", name = "ModifyBookingRequest")
    public JAXBElement<cn.net.chinaonline.webservices._switch._1_5_1.reservation.ModifyBookingRequest> createModifyBookingRequest(cn.net.chinaonline.webservices._switch._1_5_1.reservation.ModifyBookingRequest value) {
        return new JAXBElement<cn.net.chinaonline.webservices._switch._1_5_1.reservation.ModifyBookingRequest>(_ModifyBookingRequest_QNAME, cn.net.chinaonline.webservices._switch._1_5_1.reservation.ModifyBookingRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FetchBookingStatusRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.micros.com/ows/5.1/Reservation.wsdl", name = "FetchBookingStatusRequest")
    public JAXBElement<FetchBookingStatusRequest> createFetchBookingStatusRequest(FetchBookingStatusRequest value) {
        return new JAXBElement<FetchBookingStatusRequest>(_FetchBookingStatusRequest_QNAME, FetchBookingStatusRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModifyBookingResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.micros.com/ows/5.1/Reservation.wsdl", name = "ModifyBookingResponse")
    public JAXBElement<ModifyBookingResponse> createModifyBookingResponse(ModifyBookingResponse value) {
        return new JAXBElement<ModifyBookingResponse>(_ModifyBookingResponse_QNAME, ModifyBookingResponse.class, null, value);
    }

}
