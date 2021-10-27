package runtimeTerror.autoCare.model.blog;


import runtimeTerror.autoCare.model.User;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class WorkshopReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String body;

    private Timestamp createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public WorkshopReview() {
    }

    public WorkshopReview(String body, Timestamp createdAt, User user) {
        this.body = body;
        this.createdAt = createdAt;
        this.user = user;
    }

    public WorkshopReview(String body, Timestamp createdAt) {
        this.body = body;
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
    }
}
