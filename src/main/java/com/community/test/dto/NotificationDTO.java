package com.community.test.dto;

import com.community.test.model.User;
import lombok.Data;

@Data
public class NotificationDTO {
    private Integer id;

    private Long gmtCreate;

    private Integer status;

    private String outTitle;

    private Integer outid;

    private User notifier;

    private String type;

    private Integer questionId;
}
