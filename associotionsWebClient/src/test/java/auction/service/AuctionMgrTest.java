package auction.service;

import auction.client.AuctionClient;
import auction.client.DBCleanerClient;
import auction.client.RegistrationClient;
import auction.webservice.Bid;
import auction.webservice.Category;
import auction.webservice.Item;
import auction.webservice.Money;
import auction.webservice.User;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

public class AuctionMgrTest {

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

    @Test
    public void getItem() {

        String email = "xx2@nl";
        String omsch = "omsch";

        User seller1 = registrationClient.registerUser(email);
        Category cat = new Category();
        cat.setDescription("cat2");
        Item item1 = auctionClient.offerItem(seller1, cat, omsch);
        Item item2 = auctionClient.getItem(item1.getId());
        assertEquals(omsch, item2.getDescription());
        assertEquals(email, item2.getSeller().getEmail());
    }

    @Test
    public void findItemByDescription() {
        String email3 = "xx3@nl";
        String omsch = "omsch";
        String email4 = "xx4@nl";
        String omsch2 = "omsch2";

        User seller3 = registrationClient.registerUser(email3);
        User seller4 = registrationClient.registerUser(email4);
        Category cat = new Category();
        cat.setDescription("cat3");
        Item item1 = auctionClient.offerItem(seller3, cat, omsch);
        Item item2 = auctionClient.offerItem(seller4, cat, omsch);

        ArrayList<Item> res = (ArrayList<Item>) auctionClient.findItemByDescription(omsch2);
        assertEquals(0, res.size());

        res = (ArrayList<Item>) auctionClient.findItemByDescription(omsch);
        assertEquals(2, res.size());

    }

    @Test
    public void newBid() {

        String email = "ss2@nl";
        String emailb = "bb@nl";
        String emailb2 = "bb2@nl";
        String omsch = "omsch_bb";

        User seller = registrationClient.registerUser(email);
        User buyer = registrationClient.registerUser(emailb);
        User buyer2 = registrationClient.registerUser(emailb2);
        // eerste bod
        Category cat = new Category();
        cat.setDescription("cat9");
        Item item1 = auctionClient.offerItem(seller, cat, omsch);

        Money testMoney1 = new Money();
        testMoney1.setCents(10);
        testMoney1.setCurrency("eur");

        Bid new1 = auctionClient.newBid(item1, buyer, testMoney1);
        System.out.println(new1.getBuyer().getEmail());
        assertEquals(emailb, new1.getBuyer().getEmail());

        Money testMoney2 = new Money();
        testMoney2.setCents(9);
        testMoney2.setCurrency("eur");
        item1 = auctionClient.getItem(item1.getId());

        // lager bod
        Bid new2 = auctionClient.newBid(item1, buyer2, testMoney2);
        assertNull(new2);

        Money testMoney3 = new Money();
        testMoney3.setCents(11);
        testMoney3.setCurrency("eur");
        item1 = auctionClient.getItem(item1.getId());
        
        // hoger bod
        Bid new3 = auctionClient.newBid(item1, buyer2, testMoney3);
        assertEquals(emailb2, new3.getBuyer().getEmail());
    }
}
