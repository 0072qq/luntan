package com.community.test.enums;

public enum NotificationStatusEnum {

    UNREAD(1),
    READ(2)
    ;
    private int status;

    public int getStatus() {
        return status;
    }

    NotificationStatusEnum(int status) {
        this.status = status;
    }
}
