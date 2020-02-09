package com.community.test.dto;

import com.community.test.model.User;
import lombok.Data;

@Data
public class CommentTranDTO {
    private Long id;

    private Long parentId;

    private Integer type;

    private Integer commentator;

    private Long gmtCreate;

    private Long gmtModified;

    private Long likeCount;

    private String desc;

    private User user;

    @Override
    public String toString() {
        return "CommentTranDTO{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", type=" + type +
                ", commentator=" + commentator +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                ", likeCount=" + likeCount +
                ", desc='" + desc + '\'' +
                ", user=" + user +
                '}';
    }
}
