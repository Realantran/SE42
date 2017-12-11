package auction.domain;

import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import nl.fontys.util.FontysTime;
import nl.fontys.util.Money;

@Entity
public class Bid {

    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    @OneToOne
    private FontysTime time;

    @ManyToOne
    private User buyer;

    @Embedded
    @OneToOne
    private Money amount;

    @OneToOne
    @JoinColumn(nullable = false)
    private Item bidItem;

    public Bid(User buyer, Money amount, Item bidItem) {
        this.buyer = buyer;
        this.amount = amount;
        this.bidItem = bidItem;
    }

    public Bid() {

    }

    public Item getItemBid() {
        return this.bidItem;
    }

    public void setItemBid(Item bidItem) {
        this.bidItem = bidItem;
    }

    public FontysTime getTime() {
        return time;
    }

    public User getBuyer() {
        return buyer;
    }

    public Money getAmount() {
        return amount;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.time);
        hash = 97 * hash + Objects.hashCode(this.buyer);
        hash = 97 * hash + Objects.hashCode(this.amount);
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
        final Bid other = (Bid) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.time, other.time)) {
            return false;
        }
        if (!Objects.equals(this.buyer, other.buyer)) {
            return false;
        }
        if (!Objects.equals(this.amount, other.amount)) {
            return false;
        }
        return true;
    }

}
