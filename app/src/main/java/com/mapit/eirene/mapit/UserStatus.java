package com.mapit.eirene.mapit;

public class UserStatus {
    public String userStatus;
    public Double userLatitude;
    public Double userLongitude;

    public UserStatus(){

    }

    public UserStatus(String userStatus){
        this.userStatus = userStatus;
    }

    public UserStatus(Double userLatitude, Double userLongitude){
        this.userLatitude = userLatitude;
        this.userLongitude = userLongitude;
    }

    public UserStatus(Double userLatitude, Double userLongitude, String userStatus){
        this.userLatitude = userLatitude;
        this.userLongitude = userLongitude;
        this.userStatus = userStatus;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
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
}

