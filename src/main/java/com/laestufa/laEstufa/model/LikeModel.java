package com.laestufa.laEstufa.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "likemodel")
public class LikeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer userid;
    private Integer postid;
    private Date creation_time;

    public LikeModel(Integer userid, Integer postid, Date creation_time) {
        this.userid = userid;
        this.postid = postid;
        this.creation_time = creation_time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getPostid() {
        return postid;
    }

    public void setPostid(Integer postid) {
        this.postid = postid;
    }

    public Date getCreation_time() {
        return creation_time;
    }

    public void setCreation_time(Date creation_time) {
        this.creation_time = creation_time;
    }

    @Override
    public String toString() {
        return "LikeModel{" +
                "id=" + id +
                ", userid=" + userid +
                ", postid=" + postid +
                ", creation_time=" + creation_time +
                '}';
    }
}
