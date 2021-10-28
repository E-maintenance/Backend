package runtimeTerror.autoCare.model;

import javax.persistence.*;

@Entity
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
    private String cardnumber;
    private String date;
    private  String firstname;
    private  String secondename;
    private  String email;
    private  String phonnumber;
    private  String address;
    private  String nationalityID;
    private  String DOB;

    public Rent(String name, String cardnumber, String date, String firstname, String secondename, String email,
                String phonnumber, String address, String nationalityID, String DOB) {
        this.name = name;
        this.cardnumber = cardnumber;
        this.date = date;
        this.firstname = firstname;
        this.secondename = secondename;
        this.email = email;
        this.phonnumber = phonnumber;
        this.address = address;
        this.nationalityID = nationalityID;
        this.DOB = DOB;
    }

    public Rent() {
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSecondename() {
        return secondename;
    }

    public void setSecondename(String secondename) {
        this.secondename = secondename;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonnumber() {
        return phonnumber;
    }

    public void setPhonnumber(String phonnumber) {
        this.phonnumber = phonnumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNationalityID() {
        return nationalityID;
    }

    public void setNationalityID(String nationalityID) {
        this.nationalityID = nationalityID;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(String cardnumber) {
        this.cardnumber = cardnumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

