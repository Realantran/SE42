/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auction.dao;

import auction.domain.Item;
import auction.domain.User;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author AntonTran
 */
public class ItemDAOJPAImpl implements ItemDAO {

    private EntityManager em;

    public ItemDAOJPAImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public int count() {
        Query q = em.createNamedQuery("Item.count", Item.class);
        int returnValue = ((Long) q.getSingleResult()).intValue();
        em.close();
        return returnValue;
    }

    @Override
    public void create(Item item) {
        em.persist(item);
    }

    @Override
    public void edit(Item item) {
        em.merge(item);
    }

    @Override
    public Item find(Long id) {
        try {
            Query q = em.createNamedQuery("Item.findByID", Item.class);
            q.setParameter("id", id);
            Item returnItem = (Item) q.getSingleResult();
            em.close();
            return returnItem;
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Item> findAll() {
        Query q = em.createNamedQuery("Item.getAll", Item.class);
        List<Item> returnItems = new ArrayList(q.getResultList());
        em.close();
        return returnItems;
    }

    @Override
    public List<Item> findByDescription(String description) {
        List<Item> returnItems;
        try {
            Query q = em.createNamedQuery("Item.findByDescription", Item.class);
            q.setParameter("description", description);
            returnItems = new ArrayList<>(q.getResultList());
            em.close();
        } catch (NoResultException e) {
            return null;
        }
        return returnItems;
    }

    @Override
    public void remove(Item item) {
        em.remove(item);
    }
}
