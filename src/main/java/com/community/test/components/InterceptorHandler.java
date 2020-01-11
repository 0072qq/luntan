package com.community.test.components;

import com.community.test.mapper.UserMapper;
import com.community.test.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: TestPro
 * @author: WolfSky
 * @create: 2019-11-28 08:44
 **/

public class InterceptorHandler implements HandlerInterceptor {
    private static final Logger log = LoggerFactory.getLogger(InterceptorHandler.class);

    @Autowired
    private UserMapper userMapper;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Boolean status = true;

        Cookie[] cookies = request.getCookies();
        if(cookies==null){
//            System.out.println("no cookie");
            log.warn("no user");
            status = false;
//            response.sendRedirect("/");
            response.sendRedirect("https://github.com/login/oauth/authorize?client_id=b94a1b3cb05dbc213906&redirect_uri=http://localhost:8880/callback&scope=user&state=1");
        }
        else{
            for (Cookie cookie:
                    cookies) {
                if("token".equals(cookie.getName())){
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    if(user!=null){
                        request.getSession().setAttribute("user",user);
//                        System.out.println(user);
                        log.info(user.toString());
                    }
                    break;
                }
            }
        }
        return status;
    }
}
