package auction.service;

import auction.dao.ItemDAO;
import auction.dao.ItemDAOJPAImpl;
import auction.domain.Category;
import auction.domain.Furniture;
import auction.domain.Item;
import auction.domain.Painting;
import auction.domain.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SellerMgr {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("dbi341211");

    /**
     * @param seller
     * @param cat
     * @param description
     * @return het item aangeboden door seller, behorende tot de categorie cat
     * en met de beschrijving description
     */
    public Item offerItem(User seller, Category cat, String description) {
//        EntityManager em = emf.createEntityManager();
//        ItemDAO itemDAO = new ItemDAOJPAImpl(em);
//      Item item = new Item(seller, cat, description);
//        em.getTransaction().begin();
//        itemDAO.create(item);
//        em.getTransaction().commit();
//        return item;
        return null;
    }

    public Item offerFurniture(User seller, Category cat, String description, String material) {
        EntityManager em = emf.createEntityManager();
        ItemDAO itemDAO = new ItemDAOJPAImpl(em);
        Item item = new Furniture(material, seller, cat, description);
        em.getTransaction().begin();
        itemDAO.create(item);
        em.getTransaction().commit();
        return item;
    }

    public Item offerPainting(User seller, Category cat, String description, String title, String painter) {
        EntityManager em = emf.createEntityManager();
        ItemDAO itemDAO = new ItemDAOJPAImpl(em);
        Item item = new Painting(title, painter, seller, cat, description);
        em.getTransaction().begin();
        itemDAO.create(item);
        em.getTransaction().commit();
        return item;
    }

    /**
     * @param item
     * @return true als er nog niet geboden is op het item. Het item word
     * verwijderd. false als er al geboden was op het item.
     */
    public boolean revokeItem(Item item) {
        EntityManager em = emf.createEntityManager();
        ItemDAO itemDAO = new ItemDAOJPAImpl(em);
        em.getTransaction().begin();
        Item databaseItem = itemDAO.find(item.getId());
        if (databaseItem.getHighestBid() == null) {
            itemDAO.remove(databaseItem);
            em.getTransaction().commit();
            return true;
        }
        return false;
    }
}
