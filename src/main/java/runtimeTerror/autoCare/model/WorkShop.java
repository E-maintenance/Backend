package runtimeTerror.autoCare.model;

import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class WorkShop implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(unique = true)
    private String username;


    String password;
    String shopName;
    String category;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id")
   private Location location ;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "WorkShop{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", shopName='" + shopName + '\'' +
                ", category='" + category + '\'' +
                ", location=" + location +
                ", feeds=" + feeds +
                '}';
    }



    @OneToMany(mappedBy = "feeds")
    private List<WorkShopFeeds> feeds;


    public WorkShop() {
    }

    public WorkShop(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public WorkShop(String username, String password, String shopName, String category) {
        this.username = username;
        this.password = password;
        this.shopName = shopName;
        this.category = category;

    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    public List<WorkShopFeeds> getFeeds() {
        return feeds;
    }

    public void setFeeds(List<WorkShopFeeds> feeds) {
        this.feeds = feeds;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }



    public Long getId() {
        return id;
    }


}