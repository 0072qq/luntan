package com.community.test.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @program: TestPro
 * @author: WolfSky
 * @create: 2019-11-07 16:36
 **/
@Getter
@Setter
public class User {
    private Integer id;
    private String username;
    private String account_id;
    private String token;
    private Long gmt_create ;
    private Long gmt_modified;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", account_id='" + account_id + '\'' +
                ", token='" + token + '\'' +
                ", gmt_create=" + gmt_create +
                ", gmt_modified=" + gmt_modified +
                '}';
    }
}
