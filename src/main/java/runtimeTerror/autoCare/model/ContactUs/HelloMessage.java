package runtimeTerror.autoCare.model.ContactUs;

import javax.persistence.*;

@Entity
public class HelloMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;

    public HelloMessage(String name) {
        this.name = name;
    }
    public HelloMessage() {

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
