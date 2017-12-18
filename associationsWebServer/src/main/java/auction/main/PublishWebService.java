/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auction.main;

import javax.xml.ws.Endpoint;
import auction.webservice.Auction;
import auction.webservice.DBCleaner;
import auction.webservice.Registration;

/**
 *
 * @author AntonTran
 */
public class PublishWebService {
    
    
    private static final String url1 = "http://localhost:8100/Registration";
    private static final String url2 = "http://localhost:8100/Auction";
    private static final String url3 = "http://localhost:8100/DBCleaner";

    public static void main(String[] args) {
        Endpoint.publish(url1, new Registration());
        Endpoint.publish(url2, new Auction());
        Endpoint.publish(url3, new DBCleaner());
    }
}
