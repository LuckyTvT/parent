
package com.itheima.bos.service.server;

import org.springframework.stereotype.Service;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "CrmService", targetNamespace = "http://server.itheima.com/")
@XmlSeeAlso({

})

public interface CrmService {


    /**
     * 
     * @param arg0
     * @return
     *     returns com.itheima.bos.service.server.Customer
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "save", targetNamespace = "http://server.itheima.com/", className = "com.itheima.bos.service.server.Save")
    @ResponseWrapper(localName = "saveResponse", targetNamespace = "http://server.itheima.com/", className = "com.itheima.bos.service.server.SaveResponse")
    public Customer save(
        @WebParam(name = "arg0", targetNamespace = "")
        Customer arg0);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "active", targetNamespace = "http://server.itheima.com/", className = "com.itheima.bos.service.server.Active")
    @ResponseWrapper(localName = "activeResponse", targetNamespace = "http://server.itheima.com/", className = "com.itheima.bos.service.server.ActiveResponse")
    public boolean active(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "login", targetNamespace = "http://server.itheima.com/", className = "com.itheima.bos.service.server.Login")
    @ResponseWrapper(localName = "loginResponse", targetNamespace = "http://server.itheima.com/", className = "com.itheima.bos.service.server.LoginResponse")
    public boolean login(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1);

    /**
     * 
     * @param arg0
     * @return
     *     returns com.itheima.bos.service.server.Customer
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "findByAddress", targetNamespace = "http://server.itheima.com/", className = "com.itheima.bos.service.server.FindByAddress")
    @ResponseWrapper(localName = "findByAddressResponse", targetNamespace = "http://server.itheima.com/", className = "com.itheima.bos.service.server.FindByAddressResponse")
    public Customer findByAddress(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

    /**
     * 
     * @param arg1
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "associate", targetNamespace = "http://server.itheima.com/", className = "com.itheima.bos.service.server.Associate")
    @ResponseWrapper(localName = "associateResponse", targetNamespace = "http://server.itheima.com/", className = "com.itheima.bos.service.server.AssociateResponse")
    public void associate(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1);

    /**
     * 
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "noassociate", targetNamespace = "http://server.itheima.com/", className = "com.itheima.bos.service.server.Noassociate")
    @ResponseWrapper(localName = "noassociateResponse", targetNamespace = "http://server.itheima.com/", className = "com.itheima.bos.service.server.NoassociateResponse")
    public void noassociate(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns java.util.List<com.itheima.bos.service.server.Customer>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "findByFixedAreaId", targetNamespace = "http://server.itheima.com/", className = "com.itheima.bos.service.server.FindByFixedAreaId")
    @ResponseWrapper(localName = "findByFixedAreaIdResponse", targetNamespace = "http://server.itheima.com/", className = "com.itheima.bos.service.server.FindByFixedAreaIdResponse")
    public List<Customer> findByFixedAreaId(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

    /**
     * 
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "sendEmail", targetNamespace = "http://server.itheima.com/", className = "com.itheima.bos.service.server.SendEmail")
    @ResponseWrapper(localName = "sendEmailResponse", targetNamespace = "http://server.itheima.com/", className = "com.itheima.bos.service.server.SendEmailResponse")
    public void sendEmail(
        @WebParam(name = "arg0", targetNamespace = "")
        Customer arg0);

    /**
     * 
     * @return
     *     returns java.util.List<com.itheima.bos.service.server.Customer>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "findByFixedAreaIdIsNull", targetNamespace = "http://server.itheima.com/", className = "com.itheima.bos.service.server.FindByFixedAreaIdIsNull")
    @ResponseWrapper(localName = "findByFixedAreaIdIsNullResponse", targetNamespace = "http://server.itheima.com/", className = "com.itheima.bos.service.server.FindByFixedAreaIdIsNullResponse")
    public List<Customer> findByFixedAreaIdIsNull();

    /**
     * 
     * @param arg0
     * @return
     *     returns com.itheima.bos.service.server.Customer
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "findByUsername", targetNamespace = "http://server.itheima.com/", className = "com.itheima.bos.service.server.FindByUsername")
    @ResponseWrapper(localName = "findByUsernameResponse", targetNamespace = "http://server.itheima.com/", className = "com.itheima.bos.service.server.FindByUsernameResponse")
    public Customer findByUsername(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns com.itheima.bos.service.server.Customer
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "findByTelephone", targetNamespace = "http://server.itheima.com/", className = "com.itheima.bos.service.server.FindByTelephone")
    @ResponseWrapper(localName = "findByTelephoneResponse", targetNamespace = "http://server.itheima.com/", className = "com.itheima.bos.service.server.FindByTelephoneResponse")
    public Customer findByTelephone(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

}
