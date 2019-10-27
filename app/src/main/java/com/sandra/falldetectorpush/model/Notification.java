package com.sandra.falldetectorpush.model;

import io.realm.RealmObject;

public class Notification extends RealmObject {

    String description;
    String date;

    public Notification(){

    }

    public Notification(String description, String date) {
        this.description = description;
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
