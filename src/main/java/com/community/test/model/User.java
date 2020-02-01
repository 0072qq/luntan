package com.community.test.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @program: TestPro
 * @author: WolfSky
 * @create: 2019-11-07 16:36
 **/
@Data
public class User {
    private Integer id;
    private String username;
    private String account_id;
    private String token;
    private Long gmt_create ;
    private Long gmt_modified;
    private String avator_url;//头像地址
}
