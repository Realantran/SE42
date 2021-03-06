
package auction.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.8
 * Generated source version: 2.2
 * 
 */
@WebService(name = "DBCleaner", targetNamespace = "http://webservice.auction/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface DBCleaner {


    /**
     * 
     */
    @WebMethod
    @RequestWrapper(localName = "clean", targetNamespace = "http://webservice.auction/", className = "auction.webservice.Clean")
    @ResponseWrapper(localName = "cleanResponse", targetNamespace = "http://webservice.auction/", className = "auction.webservice.CleanResponse")
    @Action(input = "http://webservice.auction/DBCleaner/cleanRequest", output = "http://webservice.auction/DBCleaner/cleanResponse")
    public void clean();

}
