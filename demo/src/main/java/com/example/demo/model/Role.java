package com.example.demo.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> setUser;

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    public Role(int id, String name, Set<User> setUser) {
        this.id = id;
        this.name = name;
        this.setUser = setUser;
    }

    public Role(String name, Set<User> setUser) {
        this.name = name;
        this.setUser = setUser;
    }

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

    public Set<User> getUserSet() {
        return setUser;
    }

    public void setUserSet(Set<User> userSet) {
        this.setUser = setUser;
    }

    @Override
    public String getAuthority() {
        return getName();
    }

    @Override
    public String toString() {
        return name;
    }
}
