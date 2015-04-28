
package com.micros.webservices.ows._5_1.availability;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import com.micros.webservices.ows._5_1.areaavailability.AreaAvailabilityResponse;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.micros.webservices.ows._5_1.availability package. 
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

    private final static QName _FetchCalendarResponse_QNAME = new QName("http://webservices.micros.com/ows/5.1/Availability.wsdl", "FetchCalendarResponse");
    private final static QName _AvailabilityResponse_QNAME = new QName("http://webservices.micros.com/ows/5.1/Availability.wsdl", "AvailabilityResponse");
    private final static QName _FetchCalendarRequest_QNAME = new QName("http://webservices.micros.com/ows/5.1/Availability.wsdl", "FetchCalendarRequest");
    private final static QName _MultipleAvailRQ_QNAME = new QName("http://webservices.micros.com/ows/5.1/Availability.wsdl", "MultipleAvailRQ");
    private final static QName _MultipleAvailRS_QNAME = new QName("http://webservices.micros.com/ows/5.1/Availability.wsdl", "MultipleAvailRS");
    private final static QName _AreaAvailabilityRequest_QNAME = new QName("http://webservices.micros.com/ows/5.1/Availability.wsdl", "AreaAvailabilityRequest");
    private final static QName _AvailabilityRequest_QNAME = new QName("http://webservices.micros.com/ows/5.1/Availability.wsdl", "AvailabilityRequest");
    private final static QName _AreaAvailabilityResponse_QNAME = new QName("http://webservices.micros.com/ows/5.1/Availability.wsdl", "AreaAvailabilityResponse");
    private final static QName _AvailabilityCacheRequest_QNAME = new QName("http://webservices.micros.com/ows/5.1/Availability.wsdl", "AvailabilityCacheRequest");
    private final static QName _HotelCacheChangeRQ_QNAME = new QName("http://webservices.micros.com/ows/5.1/Availability.wsdl", "HotelCacheChangeRQ");
    private final static QName _AvailabilityCacheResponse_QNAME = new QName("http://webservices.micros.com/ows/5.1/Availability.wsdl", "AvailabilityCacheResponse");
    private final static QName _HotelCacheChangeRS_QNAME = new QName("http://webservices.micros.com/ows/5.1/Availability.wsdl", "HotelCacheChangeRS");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.micros.webservices.ows._5_1.availability
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AvailabilityCacheResponse }
     * 
     */
    public AvailabilityCacheResponse createAvailabilityCacheResponse() {
        return new AvailabilityCacheResponse();
    }

    /**
     * Create an instance of {@link AvailabilityRequest }
     * 
     */
    public AvailabilityRequest createAvailabilityRequest() {
        return new AvailabilityRequest();
    }

    /**
     * Create an instance of {@link MultipleAvailRQ }
     * 
     */
    public MultipleAvailRQ createMultipleAvailRQ() {
        return new MultipleAvailRQ();
    }

    /**
     * Create an instance of {@link ArrayOfDailyInventory }
     * 
     */
    public ArrayOfDailyInventory createArrayOfDailyInventory() {
        return new ArrayOfDailyInventory();
    }

    /**
     * Create an instance of {@link MultipleAvailRS }
     * 
     */
    public MultipleAvailRS createMultipleAvailRS() {
        return new MultipleAvailRS();
    }

    /**
     * Create an instance of {@link HotelCacheChangeRS }
     * 
     */
    public HotelCacheChangeRS createHotelCacheChangeRS() {
        return new HotelCacheChangeRS();
    }

    /**
     * Create an instance of {@link AvailabilityResponse }
     * 
     */
    public AvailabilityResponse createAvailabilityResponse() {
        return new AvailabilityResponse();
    }

    /**
     * Create an instance of {@link HotelCacheChangeRQ }
     * 
     */
    public HotelCacheChangeRQ createHotelCacheChangeRQ() {
        return new HotelCacheChangeRQ();
    }

    /**
     * Create an instance of {@link AreaAvailabilityRequest }
     * 
     */
    public AreaAvailabilityRequest createAreaAvailabilityRequest() {
        return new AreaAvailabilityRequest();
    }

    /**
     * Create an instance of {@link FetchCalendarResponse }
     * 
     */
    public FetchCalendarResponse createFetchCalendarResponse() {
        return new FetchCalendarResponse();
    }

    /**
     * Create an instance of {@link ArrayOfSourceType }
     * 
     */
    public ArrayOfSourceType createArrayOfSourceType() {
        return new ArrayOfSourceType();
    }

    /**
     * Create an instance of {@link FetchCalendarRequest }
     * 
     */
    public FetchCalendarRequest createFetchCalendarRequest() {
        return new FetchCalendarRequest();
    }

    /**
     * Create an instance of {@link AvailabilityCacheRequest }
     * 
     */
    public AvailabilityCacheRequest createAvailabilityCacheRequest() {
        return new AvailabilityCacheRequest();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FetchCalendarResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.micros.com/ows/5.1/Availability.wsdl", name = "FetchCalendarResponse")
    public JAXBElement<FetchCalendarResponse> createFetchCalendarResponse(FetchCalendarResponse value) {
        return new JAXBElement<FetchCalendarResponse>(_FetchCalendarResponse_QNAME, FetchCalendarResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AvailabilityResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.micros.com/ows/5.1/Availability.wsdl", name = "AvailabilityResponse")
    public JAXBElement<AvailabilityResponse> createAvailabilityResponse(AvailabilityResponse value) {
        return new JAXBElement<AvailabilityResponse>(_AvailabilityResponse_QNAME, AvailabilityResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FetchCalendarRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.micros.com/ows/5.1/Availability.wsdl", name = "FetchCalendarRequest")
    public JAXBElement<FetchCalendarRequest> createFetchCalendarRequest(FetchCalendarRequest value) {
        return new JAXBElement<FetchCalendarRequest>(_FetchCalendarRequest_QNAME, FetchCalendarRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MultipleAvailRQ }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.micros.com/ows/5.1/Availability.wsdl", name = "MultipleAvailRQ")
    public JAXBElement<MultipleAvailRQ> createMultipleAvailRQ(MultipleAvailRQ value) {
        return new JAXBElement<MultipleAvailRQ>(_MultipleAvailRQ_QNAME, MultipleAvailRQ.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MultipleAvailRS }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.micros.com/ows/5.1/Availability.wsdl", name = "MultipleAvailRS")
    public JAXBElement<MultipleAvailRS> createMultipleAvailRS(MultipleAvailRS value) {
        return new JAXBElement<MultipleAvailRS>(_MultipleAvailRS_QNAME, MultipleAvailRS.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AreaAvailabilityRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.micros.com/ows/5.1/Availability.wsdl", name = "AreaAvailabilityRequest")
    public JAXBElement<AreaAvailabilityRequest> createAreaAvailabilityRequest(AreaAvailabilityRequest value) {
        return new JAXBElement<AreaAvailabilityRequest>(_AreaAvailabilityRequest_QNAME, AreaAvailabilityRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AvailabilityRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.micros.com/ows/5.1/Availability.wsdl", name = "AvailabilityRequest")
    public JAXBElement<AvailabilityRequest> createAvailabilityRequest(AvailabilityRequest value) {
        return new JAXBElement<AvailabilityRequest>(_AvailabilityRequest_QNAME, AvailabilityRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AreaAvailabilityResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.micros.com/ows/5.1/Availability.wsdl", name = "AreaAvailabilityResponse")
    public JAXBElement<AreaAvailabilityResponse> createAreaAvailabilityResponse(AreaAvailabilityResponse value) {
        return new JAXBElement<AreaAvailabilityResponse>(_AreaAvailabilityResponse_QNAME, AreaAvailabilityResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AvailabilityCacheRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.micros.com/ows/5.1/Availability.wsdl", name = "AvailabilityCacheRequest")
    public JAXBElement<AvailabilityCacheRequest> createAvailabilityCacheRequest(AvailabilityCacheRequest value) {
        return new JAXBElement<AvailabilityCacheRequest>(_AvailabilityCacheRequest_QNAME, AvailabilityCacheRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HotelCacheChangeRQ }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.micros.com/ows/5.1/Availability.wsdl", name = "HotelCacheChangeRQ")
    public JAXBElement<HotelCacheChangeRQ> createHotelCacheChangeRQ(HotelCacheChangeRQ value) {
        return new JAXBElement<HotelCacheChangeRQ>(_HotelCacheChangeRQ_QNAME, HotelCacheChangeRQ.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AvailabilityCacheResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.micros.com/ows/5.1/Availability.wsdl", name = "AvailabilityCacheResponse")
    public JAXBElement<AvailabilityCacheResponse> createAvailabilityCacheResponse(AvailabilityCacheResponse value) {
        return new JAXBElement<AvailabilityCacheResponse>(_AvailabilityCacheResponse_QNAME, AvailabilityCacheResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HotelCacheChangeRS }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.micros.com/ows/5.1/Availability.wsdl", name = "HotelCacheChangeRS")
    public JAXBElement<HotelCacheChangeRS> createHotelCacheChangeRS(HotelCacheChangeRS value) {
        return new JAXBElement<HotelCacheChangeRS>(_HotelCacheChangeRS_QNAME, HotelCacheChangeRS.class, null, value);
    }

}
