package com.kruger.pruebatecnica.auth.model.entity;


import com.kruger.pruebatecnica.model.entity.UserInformation;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;


@Entity
@Getter
@Setter
@Table(name = "user", schema = "public")
@Where(clause = "delete=false")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;
    private String password;
    private boolean delete = Boolean.FALSE;

    @OneToOne
    @JoinColumn(name = "id_user_information")
    private UserInformation userInformation;

    @NotNull
    @ManyToMany
    @JoinTable(name = "user_rol", schema = "public",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_rol"))
    private Set<Rol> rols = new HashSet<>();


    @Column(name = "deleted_date")
    private Date deletedDate;
    @Column(name = "deleted_by")
    private String deletedBy;
    @Column(name = "created_date")
    private Date createdDate;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "updated_date")
    private Date updatedDate;
    @Column(name = "updated_by")
    private String updatedBy;


    @PrePersist
    public void prePersist() {
        try {
            this.createdBy = SecurityContextHolder.getContext().getAuthentication().getName();
        }catch (NullPointerException e){
            this.createdBy = "";
        }
        this.createdDate = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        try {
            this.updatedBy = SecurityContextHolder.getContext().getAuthentication().getName();
        }catch (NullPointerException e){
            this.updatedBy = "";
        }
        this.updatedDate = new Date();
    }

    @PreRemove
    public void preRemove() {
        try {
            this.deletedBy = SecurityContextHolder.getContext().getAuthentication().getName();
        }catch (NullPointerException e){
            this.deletedBy = "";
        }
        this.deletedDate = new Date();
    }

    public void addRol(Rol rol){
        this.rols.add(rol);
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        System.out.println("llega aqui");
        System.out.println(rols.toString());
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for(Rol rol: rols){
            System.out.println(rol.getRolName());
            authorities.add(new SimpleGrantedAuthority(rol.getRolName().toString()));
        }
        return authorities;
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
}
