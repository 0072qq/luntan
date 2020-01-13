package com.community.test.controller;

import com.community.test.dto.accessTokenDTO;
import com.community.test.mapper.UserMapper;
import com.community.test.model.User;
import com.community.test.utils.GithubProvider;
import com.community.test.utils.GithubUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * @program: TestPro
 * @author: WolfSky
 * @create: 2019-11-03 11:40
 **/
@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider provider;
    @Autowired
    private UserMapper userMapper;

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;

    @GetMapping("/callback")
    public String callback(@RequestParam(name ="code")String code,
                           @RequestParam(name = "state")String state,
                           HttpServletRequest request,
                           HttpServletResponse response){

        accessTokenDTO accessTokenDTO = new accessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        String accessToken = provider.getAccessToken(accessTokenDTO);
        GithubUser user = provider.getUser(accessToken);
        System.out.println(user);

        if(user!=null){
            User u = new User();

            String token = UUID.randomUUID().toString();
            u.setToken(token);
            u.setUsername(user.getName());
            u.setAccount_id(String.valueOf(user.getId()));
            u.setGmt_create(System.currentTimeMillis());
            u.setGmt_modified(u.getGmt_create());
            u.setAvator_url(user.getAvatar_url());
            System.out.println("--------------------------------");
            userMapper.insertUser(u);
            //存入cookie session
           // request.getSession().setAttribute("user",u);
            response.addCookie(new Cookie("token",token));
            request.getSession().setAttribute("user",userMapper.findByToken(token));
            return "forward:/publish";
        }else {
            //重新登录
            return "redirect:/";
        }
    }
}
