package com.laestufa.laEstufa.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "usermodel")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String Name;
    private String LastName;
    private int Active;
    private String Login;
    private String Email;
    private Date BirthDay;
    private String Password;
    private String Avatar;
    private String Description;
    private Boolean IsPrivate;
    private Boolean EnableMat;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns =
    @JoinColumn(name = "id"), inverseJoinColumns =
    @JoinColumn(name = "role_id"))
    private Set<RoleModel> roles;

    public UserModel() {
    }

    public UserModel(UserModel userModel) {
        this.Name = userModel.Name;
        this.LastName = userModel.LastName;
        this.Login = userModel.Login;
        this.Active = userModel.Active;
        this.Email = userModel.Email;
        this.BirthDay = userModel.BirthDay;
        this.Password = userModel.Password;
        this.roles = userModel.roles;
        this.Avatar = userModel.Avatar;
        this.Description = userModel.Description;
        this.IsPrivate = userModel.IsPrivate;
        this.EnableMat = userModel.EnableMat;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        Login = login;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public Date getBirthDay() {
        return BirthDay;
    }

    public void setBirthDay(Date birthDay) {
        BirthDay = birthDay;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getActive() {
        return Active;
    }

    public void setActive(int active) {
        Active = active;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public Set<RoleModel> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleModel> roles) {
        this.roles = roles;
    }

    public String getAvatar() {
        return Avatar;
    }

    public void setAvatar(String avatar) {
        Avatar = avatar;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Boolean getPrivate() {
        return IsPrivate;
    }

    public void setPrivate(Boolean aPrivate) {
        IsPrivate = aPrivate;
    }

    public Boolean getEnableMat() {
        return EnableMat;
    }

    public void setEnableMat(Boolean enableMat) {
        EnableMat = enableMat;
    }
}
