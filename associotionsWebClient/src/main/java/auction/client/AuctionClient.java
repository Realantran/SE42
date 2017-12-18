/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auction.client;

import java.util.List;
import auction.webservice.Bid;
import auction.webservice.Category;
import auction.webservice.Item;
import auction.webservice.Money;
import auction.webservice.User;
import java.util.Objects;

/**
 *
 * @author AntonTran
 */
public class AuctionClient {

    public Item getItem(Long id) {
        auction.webservice.AuctionService service = new auction.webservice.AuctionService();
        auction.webservice.Auction port = service.getAuctionPort();
        return port.getItem(id);
    }

    public List<Item> findItemByDescription(String description) {
        auction.webservice.AuctionService service = new auction.webservice.AuctionService();
        auction.webservice.Auction port = service.getAuctionPort();
        return port.findItemByDescription(description);
    }

    public Bid newBid(Item item, User buyer, Money amount) {
        auction.webservice.AuctionService service = new auction.webservice.AuctionService();
        auction.webservice.Auction port = service.getAuctionPort();
        return port.newBid(item, buyer, amount);
    }

    public Item offerItem(User seller, Category cat, String description) {

        auction.webservice.AuctionService service = new auction.webservice.AuctionService();
        auction.webservice.Auction port = service.getAuctionPort();
        return port.offerItem(seller, cat, description);

    }

    public boolean revokeItem(Item item) {
        auction.webservice.AuctionService service = new auction.webservice.AuctionService();
        auction.webservice.Auction port = service.getAuctionPort();
        return port.revokeItem(item);
    }

}
