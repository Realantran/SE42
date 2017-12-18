package auction.domain;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@NamedQueries({
    @NamedQuery(name = "User.getAll", query = "select u from User as u"),
    @NamedQuery(name = "User.count", query = "select count(u) from User as u"),
    @NamedQuery(name = "User.findByEmail", query = "select u from User as u where u.email = :email")
})

public class User {

    @Id
    private String email;

    public User(String email) {
        this.email = email;
    }
    
    public User(){
        
    }

    public String getEmail() {
        return email;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.email);
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
        final User other = (User) obj;
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return true;
    }

    

   
    
}
