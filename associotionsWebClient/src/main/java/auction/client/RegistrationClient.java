/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auction.client;

import auction.webservice.User;
import java.util.List;

/**
 *
 * @author AntonTran
 */
public class RegistrationClient {

    public User registerUser(String email) {
        auction.webservice.RegistrationService service = new auction.webservice.RegistrationService();
        auction.webservice.Registration port = service.getRegistrationPort();
        return port.registerUser(email);
    }

    public User getUser(String email) {
        auction.webservice.RegistrationService service = new auction.webservice.RegistrationService();
        auction.webservice.Registration port = service.getRegistrationPort();
        return port.getUser(email);
    }
    
    public boolean compareUser(User user1, User user2) {
        return user1.getEmail().equals(user2.getEmail());
    }
    
    public List<User> getUsers(){
            auction.webservice.RegistrationService service = new auction.webservice.RegistrationService();
            auction.webservice.Registration port = service.getRegistrationPort();
            return port.getUsers();
    }
}
