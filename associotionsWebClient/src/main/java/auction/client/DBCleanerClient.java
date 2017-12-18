/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auction.client;

/**
 *
 * @author AntonTran
 */
public class DBCleanerClient {
    
    public void clean(){      
        try {
            auction.webservice.DBCleanerService service = new auction.webservice.DBCleanerService();
            auction.webservice.DBCleaner port = service.getDBCleanerPort();
            port.clean();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
