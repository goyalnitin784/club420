package com.phantom.model.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_details")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long userId;

    @Column(name = "type_of_user")
    private int userType;

    @Column(name = "usrname")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "profile_pic")
    private String profilePic;

    @Column(name = "phone")
    private String phoneNo;

    @Column(name = "email_address")
    private String email;

    @Column(name = "dob")
    private String dob;

    @Column(name = "is_age_above_21")
    private int isAgeAbove21;

    @Column(name = "created_on")
    private Date createdOn;

    @Column(name = "uuid")
    private String ssoToken;

    @Column(name = "last_updated_on")
    private Date lastUpdatedOn;

    @Column(name = "confirmed_on")
    private Date confirmedOn;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public int getIsAgeAbove21() {
        return isAgeAbove21;
    }

    public void setIsAgeAbove21(int isAgeAbove21) {
        this.isAgeAbove21 = isAgeAbove21;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getSsoToken() {
        return ssoToken;
    }

    public void setSsoToken(String ssoToken) {
        this.ssoToken = ssoToken;
    }

    public Date getLastUpdatedOn() {
        return lastUpdatedOn;
    }

    public void setLastUpdatedOn(Date lastUpdatedOn) {
        this.lastUpdatedOn = lastUpdatedOn;
    }

    public Date getConfirmedOn() {
        return confirmedOn;
    }

    public void setConfirmedOn(Date confirmedOn) {
        this.confirmedOn = confirmedOn;
    }
}