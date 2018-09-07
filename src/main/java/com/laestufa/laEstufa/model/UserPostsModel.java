package com.laestufa.laEstufa.model;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_posts")
@NamedStoredProcedureQuery(
        name = "getAllUserPost",
        procedureName = "getAllUserPost"
)
public class UserPostsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String Contents;
    private String AuthorId;
    private Date Date;
    private boolean HasImage;

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

    public boolean isHasImage() {
        return HasImage;
    }

    public void setHasImage(boolean hasImage) {
        HasImage = hasImage;
    }
}
