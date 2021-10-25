package runtimeTerror.autoCare.model;

import javax.persistence.*;

@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String lat;
    private String lon;


    @OneToOne(mappedBy = "location")
    private WorkShop workShop;

    public WorkShop getWorkShop() {
        return workShop;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", lat='" + lat + '\'' +
                ", lon='" + lon + '\'' +
                ", workShop=" + workShop +
                '}';
    }

    public void setWorkShop(WorkShop workShop) {
        this.workShop = workShop;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Location(String lat, String lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public Location() {
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }
}
