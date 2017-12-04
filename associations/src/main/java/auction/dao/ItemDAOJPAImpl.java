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
        return ((Long) q.getSingleResult()).intValue();
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
            return (Item) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Item> findAll() {
        Query q = em.createNamedQuery("Item.getAll", Item.class);
        return q.getResultList();
    }

    @Override
    public List<Item> findByDescription(String description) {
        try {
            Query q = em.createNamedQuery("Item.findByDescription", Item.class);
            q.setParameter("description", description);
            return new ArrayList<>(q.getResultList());
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void remove(Item item) {
        em.remove(item);
    }
}
