package com.kruger.pruebatecnica.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "user_information")
public class UserInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    @Column(name = "last_name")
    private String lastName;
    private String email;
    @Column(name = "birth_date")
    private Date birthDate;
    private String phone;
    private String address;
    private int dni;

    @OneToOne
    @JoinColumn(name = "id_vaccination")
    private Vaccination vaccination;

    private boolean delete = Boolean.FALSE;
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
}
