package com.VenkateshManvi.MoEngage.Model;



import jakarta.persistence.*;


import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(
        name = "responsecode",
        uniqueConstraints = @UniqueConstraint(columnNames = {"name", "user_id"})
)




public class ResponseCodeList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private LocalDateTime createdAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<ResponseCodeEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<ResponseCodeEntry> entries) {
        this.entries = entries;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "responseCodeList", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ResponseCodeEntry> entries;

    public ResponseCodeList() {
    }

    public ResponseCodeList(String name, LocalDateTime createdAt, User user, List<ResponseCodeEntry> entries) {
        this.name = name;
        this.createdAt = createdAt;
        this.user = user;
        this.entries = entries;
    }

}

