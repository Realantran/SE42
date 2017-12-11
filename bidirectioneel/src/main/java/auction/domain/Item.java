package auction.domain;

import java.util.Objects;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import nl.fontys.util.Money;

@Entity
@NamedQueries({
    @NamedQuery(name = "Item.getAll", query = "select I from Item as I")
    ,
    @NamedQuery(name = "Item.count", query = "select count(I) from Item as I")
    ,
    @NamedQuery(name = "Item.findByDescription", query = "select I from Item as I where I.description = :description")
    ,
    @NamedQuery(name = "Item.findByID", query = "select I from Item as I where I.id = :id")

})
public class Item implements Comparable {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User seller;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "description",
                column = @Column(name = "category_description"))
    })
    private Category category;
    private String description;

    @OneToOne(cascade = CascadeType.PERSIST, mappedBy = "bidItem")
    private Bid highest;

    public Item(User seller, Category category, String description) {
        this.seller = seller;
        this.category = category;
        this.description = description;
        seller.addItem(this);
    }

    public Item() {

    }

    public Long getId() {
        return id;
    }

    public User getSeller() {
        return seller;
    }

    public Category getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public Bid getHighestBid() {
        return highest;
    }

    public Bid newBid(User buyer, Money amount) {
        if (highest != null && highest.getAmount().compareTo(amount) >= 0) {
            return null;
        }
        highest = new Bid(buyer, amount, this);
        return highest;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.seller);
        hash = 37 * hash + Objects.hashCode(this.category);
        hash = 37 * hash + Objects.hashCode(this.description);
        hash = 37 * hash + Objects.hashCode(this.highest);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Item other = (Item) obj;
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.seller, other.seller)) {
            return false;
        }
        if (!Objects.equals(this.category, other.category)) {
            return false;
        }
        if (!Objects.equals(this.highest, other.highest)) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Object o) {
        Item compareItem = (Item) o;
        return this.id.compareTo(compareItem.getId());
    }
}
