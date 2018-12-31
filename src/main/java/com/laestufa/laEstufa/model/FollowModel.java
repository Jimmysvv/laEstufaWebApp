package com.laestufa.laEstufa.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "followers")
public class FollowModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer follower;
    private Integer following;
    private Date date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFollower() {
        return follower;
    }

    public void setFollower(Integer follower) {
        this.follower = follower;
    }

    public Integer getFollowing() {
        return following;
    }

    public void setFollowing(Integer following) {
        this.following = following;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
