/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auction.dao;

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
public class UserDAOJPAImpl implements UserDAO {

    private EntityManager em;

    public UserDAOJPAImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public int count() {
        Query q = em.createNamedQuery("User.count", User.class);
        int returnValue = ((Long) q.getSingleResult()).intValue();
        em.close();
        return returnValue;
    }

    @Override
    public void create(User user) {
        em.persist(user);
    }

    @Override
    public void edit(User user) {
        em.merge(user);
    }

    @Override
    public List<User> findAll() {
        Query q = em.createNamedQuery("User.getAll", User.class);
        List<User> returnUsers = new ArrayList(q.getResultList());
        em.close();
        return returnUsers;
    }

    @Override
    public User findByEmail(String email) {
        try {
            Query q = em.createNamedQuery("User.findByEmail", User.class);
            q.setParameter("email", email);
            User returnUser = (User) q.getSingleResult();
            em.close();
            return returnUser;
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void remove(User user) {
        em.remove(em.merge(user));
    }
}
