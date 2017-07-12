package com.example.bean;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by yangbin on 2017/6/23.
 */
@Entity
@Table(name = "t_app_user")
public class JpaUser implements Serializable {

    private static final long serialVersionUID = -1L;


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long userId;

    @Column(name="user_kind",nullable = false)
    private String userKind;

    @Column(name="user_status",nullable = false)
    private Integer userStatus;

    public JpaUser(){
    }

    public JpaUser(Long userId){

        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserKind() {
        return userKind;
    }

    public void setUserKind(String userKind) {
        this.userKind = userKind;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }
}
