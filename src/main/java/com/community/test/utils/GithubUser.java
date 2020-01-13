package com.community.test.utils;

import lombok.Data;

/**
 * @program: TestPro
 * @author: WolfSky
 * @create: 2019-11-03 15:11
 **/
@Data

public class GithubUser {
    private String name;
    private  Long id;
    private String bio;

    private String avatar_url;
}
