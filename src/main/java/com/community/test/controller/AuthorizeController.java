package com.community.test.controller;

import com.community.test.dto.accessTokenDTO;
import com.community.test.utils.GithubProvider;
import com.community.test.utils.GithubUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @program: TestPro
 * @author: WolfSky
 * @create: 2019-11-03 11:40
 **/
@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider provider;

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;

    @GetMapping("/callback")
    public String callback(@RequestParam(name ="code")String code,
                           @RequestParam(name = "state")String state){

        accessTokenDTO accessTokenDTO = new accessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        String accessToken = provider.getAccessToken(accessTokenDTO);
        GithubUser user = provider.getUser(accessToken);
        System.out.println(user);
        return "index";
    }
}
