package com.community.test.enums;

public enum NotificationEnum {

    REPlY_QUESTION(1,"回复问题"),
    REPLY_COMMENT(2,"回复评论")
    ;

    private int status;
    private String name;

    public int getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    NotificationEnum(int status, String name) {
        this.status = status;
        this.name = name;
    }
}
