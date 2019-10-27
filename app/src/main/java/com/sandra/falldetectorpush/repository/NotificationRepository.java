package com.sandra.falldetectorpush.repository;

import com.sandra.falldetectorpush.model.Notification;

import io.realm.Realm;

public class NotificationRepository {

    public void saveNotification(Notification n){
        Realm.getDefaultInstance().executeTransaction(realm -> {
            realm.copyToRealm(n);
        });
    }

    public Notification[] getAllNotifications(){
        Object[] objects = Realm.getDefaultInstance().where(Notification.class).findAll().toArray();
        Notification[] notifications = Realm.getDefaultInstance().where(Notification.class).findAll().toArray(new Notification[objects.length]);
        return notifications;
    }
}
