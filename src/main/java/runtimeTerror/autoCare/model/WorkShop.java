package runtimeTerror.autoCare.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

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
    String location;

    @OneToMany(mappedBy = "feeds")
    private List<WorkShopFeeds> feeds;




    public WorkShop() {
    }

    public WorkShop(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public WorkShop(String username, String password, String shopName, String category, String location) {
        this.username = username;
        this.password = password;
        this.shopName = shopName;
        this.category = category;
        this.location = location;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getId() {
        return id;
    }


}