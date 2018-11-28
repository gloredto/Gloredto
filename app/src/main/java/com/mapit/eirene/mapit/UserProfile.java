package com.mapit.eirene.mapit;

public class UserProfile {
    public String userEmail;
    public String userName;
    public String userBornWhere;
    public String userBornWhen;
    public String userAge;
    public String userStatus;
    public Double userLatitude;
    public Double userLongitude;

    public UserProfile(){

    }

    public UserProfile(Double userLatitude, Double userLongitude, String userStatus){
        this.userLatitude = userLatitude;
        this.userLongitude = userLongitude;
        this.userStatus = userStatus;
    }

    public Double getUserLatitude() {
        return userLatitude;
    }

    public void setUserLatitude(Double userLatitude) {
        this.userLatitude = userLatitude;
    }

    public Double getUserLongitude() {
        return userLongitude;
    }

    public void setUserLongitude(Double userLongitude) {
        this.userLongitude = userLongitude;
    }

    public String getUserStatus() { return userStatus; }

    public void  setUserStatus(String userStatus) { this.userStatus = userStatus; }

    public UserProfile(String userEmail, String userName, String userBornWhere, String userBornWhen, String userAge) {
        this.userEmail = userEmail;
        this.userName = userName;
        this.userBornWhere = userBornWhere;
        this.userBornWhen = userBornWhen;
        this.userAge = userAge;
    }

    public UserProfile(String userName, String userBornWhere, String userBornWhen, String userAge) {
        this.userName = userName;
        this.userBornWhere = userBornWhere;
        this.userBornWhen = userBornWhen;
        this.userAge = userAge;
    }

    public UserProfile(String userEmail, String userName) {
        this.userEmail = userEmail;
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserBornWhere() {
        return userBornWhere;
    }

    public void setUserBornWhere(String userBornWhere) {
        this.userBornWhere = userBornWhere;
    }

    public String getUserBornWhen() {
        return userBornWhen;
    }

    public void setUserBornWhen(String userBornWhen) {
        this.userBornWhen = userBornWhen;
    }

    public String getUserAge() {
        return userAge;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }
}


