package runtimeTerror.autoCare.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import runtimeTerror.autoCare.dto.UserRegistrationDto;
import runtimeTerror.autoCare.model.blog.Review;
import runtimeTerror.autoCare.model.blog.WorkshopReview;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private  String fullname;
    private boolean isVerified=false;
    @Column(unique = true)
    private  String username;

    private  String email;
    private  String phone;
    private String password;

    @OneToMany (mappedBy = "user")
    private List <Review> reviews;


    @OneToMany(mappedBy = "user")
    private List <WorkshopReview> workshopReview;


    public User() {
    }

    public User(UserRegistrationDto userRegistrationDto) {
        this.fullname = userRegistrationDto.getFullname();
        this.username = userRegistrationDto.getUserName();
        this.email = userRegistrationDto.getEmail();
        this.phone = userRegistrationDto.getPhone();
        this.password = userRegistrationDto.getPassword();

    }

    public User(String fullname, String username, String email, String phone, String password, Role role) {
        this.fullname = fullname;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.role = role;
    }


    public User(String fullname, String username, String email ) {
        this.fullname = fullname;
        this.username = username;
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }



    public List <Review> getReview() {
        return reviews;
    }

    public void setReview(List <Review> reviews) {
        this.reviews = reviews;
    }


    public List <WorkshopReview> getWorkshopReview() {
        return  workshopReview;
    }

    public void setWorkshopReview(List <WorkshopReview> workshopReview) {
        this.workshopReview =  workshopReview;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
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


    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.getName()));
        System.out.println("-------------------------------------------------------------------------");
        System.out.println(role.getName());
        return authorities;
    }



    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id", referencedColumnName = "id" )
    private Role role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fullname='" + fullname + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}