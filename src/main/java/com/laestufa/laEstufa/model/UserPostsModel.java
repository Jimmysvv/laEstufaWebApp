package com.laestufa.laEstufa.model;


import javax.persistence.*;
import java.sql.Blob;
import java.util.Date;

@Entity
@Table(name = "user_posts")
public class UserPostsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String Contents;
    private String AuthorId;
    private Date Date;
    private String Image;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContents() {
        return Contents;
    }

    public void setContents(String contents) {
        Contents = contents;
    }

    public String getAuthorId() {
        return AuthorId;
    }

    public void setAuthorId(String authorId) {
        AuthorId = authorId;
    }

    public java.util.Date getDate() {
        return Date;
    }

    public void setDate(java.util.Date date) {
        Date = date;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
