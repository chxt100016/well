
package com.wellassist.pay.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.wellassist.pay.client package. 
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

    private final static QName _B2BPayResponse_QNAME = new QName("http://service.pay.wellassist.com/", "b2bPayResponse");
    private final static QName _B2BPay_QNAME = new QName("http://service.pay.wellassist.com/", "b2bPay");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.wellassist.pay.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link B2BPayResponse }
     * 
     */
    public B2BPayResponse createB2BPayResponse() {
        return new B2BPayResponse();
    }

    /**
     * Create an instance of {@link B2BPay }
     * 
     */
    public B2BPay createB2BPay() {
        return new B2BPay();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link B2BPayResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.pay.wellassist.com/", name = "b2bPayResponse")
    public JAXBElement<B2BPayResponse> createB2BPayResponse(B2BPayResponse value) {
        return new JAXBElement<B2BPayResponse>(_B2BPayResponse_QNAME, B2BPayResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link B2BPay }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.pay.wellassist.com/", name = "b2bPay")
    public JAXBElement<B2BPay> createB2BPay(B2BPay value) {
        return new JAXBElement<B2BPay>(_B2BPay_QNAME, B2BPay.class, null, value);
    }

}
