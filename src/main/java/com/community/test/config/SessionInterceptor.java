package com.community.test.config;

import com.community.test.enums.NotificationStatusEnum;
import com.community.test.mapper.NotificationMapper;
import com.community.test.mapper.UserMapper;
import com.community.test.model.NotificationExample;
import com.community.test.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class SessionInterceptor implements HandlerInterceptor {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private NotificationMapper notificationMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        if(cookies==null){
            System.out.println("no cookie");
            //return "index";
        }
        else{
            for (Cookie cookie:
                    cookies) {
                if("token".equals(cookie.getName())){
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    if(user!=null){
                        request.getSession().setAttribute("user",user);
                        System.out.println(user);
                    }
                    break;
                }
            }
        }
        NotificationExample example = new NotificationExample();
        example.createCriteria().andStatusEqualTo(NotificationStatusEnum.UNREAD.getStatus());
        long l = notificationMapper.countByExample(example);
        request.getSession().setAttribute("count",l);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
