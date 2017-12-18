package auction.service;

import auction.client.DBCleanerClient;
import auction.client.RegistrationClient;
import auction.webservice.User;
import java.util.List;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class JPARegistrationMgrTest {

    private RegistrationClient registrationClient;
    private DBCleanerClient dbCleanerClient;

    @Before
    public void setUp() throws Exception {
        registrationClient = new RegistrationClient();
        dbCleanerClient = new DBCleanerClient();
        dbCleanerClient.clean();
    }

    @Test
    public void registerUser() {
        User user1 = registrationClient.registerUser("xxx1@yyy");
        assertTrue(user1.getEmail().equals("xxx1@yyy"));
        User user2 = registrationClient.registerUser("xxx2@yyy2");
        assertTrue(user2.getEmail().equals("xxx2@yyy2"));
        User user2bis = registrationClient.registerUser("xxx2@yyy2");
        assertTrue(registrationClient.compareUser(user2bis, user2));
        //geen @ in het adres
        assertNull(registrationClient.registerUser("abc"));
        
        
    }

    @Test
    public void getUser() {
        User user1 = registrationClient.registerUser("xxx5@yyy5");
        User userGet = registrationClient.getUser("xxx5@yyy5");
        assertTrue(registrationClient.compareUser(userGet, user1));
        assertNull(registrationClient.getUser("aaa4@bb5"));
        registrationClient.registerUser("abc");
        assertNull(registrationClient.getUser("abc"));
    }

    @Test
    public void getUsers() {
        List<User> users = registrationClient.getUsers();
        assertEquals(0, users.size());

        User user1 = registrationClient.registerUser("xxx8@yyy");
        users = registrationClient.getUsers();
        assertEquals(1, users.size());
        assertTrue(registrationClient.compareUser(users.get(0), user1));

        User user2 = registrationClient.registerUser("xxx9@yyy");
        users = registrationClient.getUsers();
        assertEquals(2, users.size());

        registrationClient.registerUser("abc");
        //geen nieuwe user toegevoegd, dus gedrag hetzelfde als hiervoor
        users = registrationClient.getUsers();
        assertEquals(2, users.size());
    }
}
