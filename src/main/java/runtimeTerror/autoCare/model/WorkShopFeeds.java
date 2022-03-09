package runtimeTerror.autoCare.model;


import javax.persistence.*;
import java.util.Date;
import java.util.concurrent.TimeUnit;


@Entity
public class WorkShopFeeds {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    String feeds;
    String image;
    long timeNow = new Date().getTime();

    @ManyToOne
    @JoinColumn(name = "workshop_id")
    private WorkShop workShop;


    public WorkShopFeeds() {
    }

    public WorkShopFeeds(String feeds, String image, WorkShop workShop) {
        this.feeds = feeds;
        this.image = image;
        this.workShop = workShop;
    }

    public WorkShopFeeds(String feeds, String image) {
        this.feeds = feeds;
        this.image = image;
    }

    public String getFeeds() {
        return feeds;
    }

    public void setFeeds(String feeds) {
        this.feeds = feeds;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTimeNow() {

        String time = "";

        long defTime = new Date().getTime() - timeNow;
        if (defTime < 1000 * 60) {
            time = TimeUnit.MILLISECONDS.toSeconds(defTime) + " seconds ago";
        } else if (defTime < 1000 * 60 * 60) {
            time = TimeUnit.MILLISECONDS.toMinutes(defTime) + " minutes ago";
        } else if (defTime < 1000 * 60 * 60 * 60) {
            time = TimeUnit.MILLISECONDS.toHours(defTime) + " hours ago";
        } else if (defTime < (long) 1000 * 60 * 60 * 60 * 24) {
            time = TimeUnit.MILLISECONDS.toDays(defTime) + " days ago";
        }

        return time;
    }

    public void setTimeNow(long timeNow) {
        this.timeNow = timeNow;
    }

    public WorkShop getWorkShop() {
        return workShop;
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

}
