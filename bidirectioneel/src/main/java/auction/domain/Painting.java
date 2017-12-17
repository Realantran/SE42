/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auction.domain;

import javax.persistence.Entity;

/**
 *
 * @author Mitch Kuijpers
 */
@Entity
public class Painting extends Item {
    
    private String title;
    
    private String painter;

    public Painting(String title, String painter, Long id, User seller, Category category, String description, Bid highest) {
        super(id, seller, category, description, highest);
        this.title = title;
        this.painter = painter;
    }

   

    public Painting() {
    }

    public Painting(String title, String painter, User seller, Category category, String description) {
        super(seller, category, description);
        this.title = title;
        this.painter = painter;
    }

    
    
    

    /**
     * Get the value of painter
     *
     * @return the value of painter
     */
    public String getPainter() {
        return painter;
    }

    /**
     * Set the value of painter
     *
     * @param painter new value of painter
     */
    public void setPainter(String painter) {
        this.painter = painter;
    }


    /**
     * Get the value of title
     *
     * @return the value of title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the value of title
     *
     * @param title new value of title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    
}
