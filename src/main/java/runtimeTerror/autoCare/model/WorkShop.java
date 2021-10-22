package runtimeTerror.autoCare.model;

import javax.persistence.*;

@Entity
public class WorkShop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    String shopName;
    String category;
    String location;


    public WorkShop() {
    }


    public WorkShop(String shopName, String category, String location) {
        this.shopName = shopName;
        this.category = category;
        this.location = location;
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