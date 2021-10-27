package runtimeTerror.autoCare.model;

import javax.persistence.*;

@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private  String status;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "workShop")
    private WorkShop workShop;

    public Appointment() {

    }

    public Appointment(String status, User user, WorkShop workShop) {

        this.status = status;
        this.user = user;
        this.workShop = workShop;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public WorkShop getWorkShop() {
        return workShop;
    }

    public void setWorkShop(WorkShop workShop) {
        this.workShop = workShop;
    }

    @Override
    public String toString() {
        return "appointment{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", user=" + user +
                ", workShop=" + workShop.toString() +
                '}';
    }
}
