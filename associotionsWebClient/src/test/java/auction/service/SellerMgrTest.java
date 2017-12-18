package auction.service;

import auction.client.AuctionClient;
import auction.client.DBCleanerClient;
import auction.client.RegistrationClient;
import auction.webservice.Category;
import auction.webservice.Item;
import auction.webservice.Money;
import auction.webservice.User;
import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;





public class SellerMgrTest {

    private AuctionClient auctionClient;
    private RegistrationClient registrationClient;
    private DBCleanerClient dbCleanerClient;

    @Before
    public void setUp() throws Exception {
        auctionClient = new AuctionClient();
        registrationClient = new RegistrationClient();
        dbCleanerClient = new DBCleanerClient();
        dbCleanerClient.clean();
    }

    /**
     * Test of offerItem method, of class SellerMgr.
     */
    @Test
    public void testOfferItem() {
        String omsch = "omsch";

        User user1 = registrationClient.registerUser("xx@nl");
        Category cat = new Category();
        cat.setDescription("cat1");
        Item item1 = auctionClient.offerItem(user1, cat, omsch);
        assertEquals(omsch, item1.getDescription());
        assertNotNull(item1.getId());
    }

    /**
     * Test of revokeItem method, of class SellerMgr.
     */
    @Test
    public void testRevokeItem() {
        String omsch = "omsch";
        String omsch2 = "omsch2";
        
    
        User seller = registrationClient.registerUser("sel@nl");
        User buyer = registrationClient.registerUser("buy@nl");
        Category cat = new Category();
        cat.setDescription("cat1");
        
            // revoke before bidding
        Item item1 = auctionClient.offerItem(seller, cat, omsch);
        boolean res = auctionClient.revokeItem(item1);
        assertTrue(res);
        int count = auctionClient.findItemByDescription(omsch).size();
        assertEquals(0, count);
        
            // revoke after bid has been made
        Item item2 = auctionClient.offerItem(seller, cat, omsch2);
        Money testMoney1 = new Money();
        testMoney1.setCents(100);
        testMoney1.setCurrency("Euro");
        auctionClient.newBid(item2, buyer, testMoney1);
        boolean res2 = auctionClient.revokeItem(item2);
        assertFalse(res2);
        int count2 = auctionClient.findItemByDescription(omsch2).size();
        assertEquals(1, count2);
        
        
        
        
    }

}
