package com.community.test.dto;

import com.community.test.model.User;
import lombok.Data;

@Data
public class QuestionDTO {

    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Long gmt_create;
    private Long gmt_modify;
    private Integer creater;
    private Integer comment_count;
    private Integer like_count;
    private Integer view_count;

    private User user;

}
