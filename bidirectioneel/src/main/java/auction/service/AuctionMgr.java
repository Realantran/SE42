package auction.service;

import auction.dao.ItemDAO;
import auction.dao.ItemDAOJPAImpl;
import auction.dao.UserDAO;
import auction.dao.UserDAOJPAImpl;
import nl.fontys.util.Money;
import auction.domain.Bid;
import auction.domain.Item;
import auction.domain.User;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AuctionMgr {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("dbi341211");

    /**
     * @param id
     * @return het item met deze id; als dit item niet bekend is wordt er null
     * geretourneerd
     */
    public Item getItem(Long id) {
        EntityManager em = emf.createEntityManager();
        ItemDAO itemDAO = new ItemDAOJPAImpl(em);
        return itemDAO.find(id);
    }

    /**
     * @param description
     * @return een lijst met items met @desciption. Eventueel lege lijst.
     */
    public List<Item> findItemByDescription(String description) {
        EntityManager em = emf.createEntityManager();
        ItemDAO itemDAO = new ItemDAOJPAImpl(em);
        return (List<Item>) itemDAO.findByDescription(description);
    }

    /**
     * @param item
     * @param buyer
     * @param amount
     * @return het nieuwe bod ter hoogte van amount op item door buyer, tenzij
     * amount niet hoger was dan het laatste bod, dan null
     */
    public Bid newBid(Item item, User buyer, Money amount) {
        EntityManager em = emf.createEntityManager();
        ItemDAO itemDAO = new ItemDAOJPAImpl(em);
        em.getTransaction().begin();
        Bid bid = item.newBid(buyer, amount);
        try {
          Item it =  itemDAO.edit(item);
            em.getTransaction().commit();
            return it.getHighestBid();

        } catch (Exception ex) {
            System.out.println(ex);
            em.getTransaction().rollback();
        }
        return null;
    }
}
