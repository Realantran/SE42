/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auction.webservice;

import java.sql.SQLException;
import javax.jws.WebService;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import util.DatabaseCleaner;

/**
 *
 * @author AntonTran
 */
@WebService
public class DBCleaner {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("dbi341211");
    private DatabaseCleaner dc;

    public void clean() {
        dc = new DatabaseCleaner(emf.createEntityManager());
        try {
            dc.clean();
            System.out.println("Test");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
}
