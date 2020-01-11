package com.community.test.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @program: TestPro
 * @author: WolfSky
 * @create: 2019-11-26 20:02
 **/
@Getter
@Setter
public class Question {

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
}
