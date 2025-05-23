package com.VenkateshManvi.MoEngage.Model;



import jakarta.persistence.*;


import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;
    private String email;
    private String password;
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }



    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ResponseCodeList> responseCodeLists;

    public User() {
    }

    public User(String username, String email, String password,String role, List<ResponseCodeList> responseCodeLists) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.responseCodeLists = responseCodeLists;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<ResponseCodeList> getResponseCodeLists() {
        return responseCodeLists;
    }

    public void setResponseCodeLists(List<ResponseCodeList> responseCodeLists) {
        this.responseCodeLists = responseCodeLists;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", rol='" + role + '\'' +
                ", responseCodeLists=" + responseCodeLists +
                '}';
    }
}

