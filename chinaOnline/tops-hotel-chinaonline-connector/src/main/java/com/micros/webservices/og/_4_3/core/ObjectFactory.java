
package com.micros.webservices.og._4_3.core;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.micros.webservices.og._4_3.core package. 
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

    private final static QName _OGHeader_QNAME = new QName("http://webservices.micros.com/og/4.3/Core/", "OGHeader");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.micros.webservices.og._4_3.core
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ArrayOfEndPoint }
     * 
     */
    public ArrayOfEndPoint createArrayOfEndPoint() {
        return new ArrayOfEndPoint();
    }

    /**
     * Create an instance of {@link OGHeaderAuthentication }
     * 
     */
    public OGHeaderAuthentication createOGHeaderAuthentication() {
        return new OGHeaderAuthentication();
    }

    /**
     * Create an instance of {@link EndPoint }
     * 
     */
    public EndPoint createEndPoint() {
        return new EndPoint();
    }

    /**
     * Create an instance of {@link SystemID }
     * 
     */
    public SystemID createSystemID() {
        return new SystemID();
    }

    /**
     * Create an instance of {@link OGHeader }
     * 
     */
    public OGHeader createOGHeader() {
        return new OGHeader();
    }

    /**
     * Create an instance of {@link OGHeaderAuthenticationLicence }
     * 
     */
    public OGHeaderAuthenticationLicence createOGHeaderAuthenticationLicence() {
        return new OGHeaderAuthenticationLicence();
    }

    /**
     * Create an instance of {@link OGHeaderAuthenticationUserCredentials }
     * 
     */
    public OGHeaderAuthenticationUserCredentials createOGHeaderAuthenticationUserCredentials() {
        return new OGHeaderAuthenticationUserCredentials();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OGHeader }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.micros.com/og/4.3/Core/", name = "OGHeader")
    public JAXBElement<OGHeader> createOGHeader(OGHeader value) {
        return new JAXBElement<OGHeader>(_OGHeader_QNAME, OGHeader.class, null, value);
    }

}
