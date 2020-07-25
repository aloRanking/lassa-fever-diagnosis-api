package com.aloranking.lfds.models;


import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "user_registration")
public class UserRegistration extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long user_id;


    @NotNull
    @Column(name = "first_name")
    private String first_name;

    @NotNull
    @Column(name = "last_name")
    private String last_name;



    @NotNull
    @Column(name = "email", unique = true)
    private String email;

    @NotNull
    @Column(name = "gender")
    private String gender;

    @NotNull
    @Column(name = "password")
    private String password;

    @Column(name = "phone_number")
    private String phone_number;

    @Column(name = "image")
    private String image;

    @Column(name = "residential_state")
    private String res_state;

    @Column(name = "home_address")
    private String res_home_address;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "role")
    private Role role;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRes_state() {
        return res_state;
    }

    public void setRes_state(String res_state) {
        this.res_state = res_state;
    }

    public String getRes_home_address() {
        return res_home_address;
    }

    public void setRes_home_address(String res_home_address) {
        this.res_home_address = res_home_address;
    }
}
