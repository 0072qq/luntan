package com.community.test.advice;

import com.alibaba.fastjson.JSON;
import com.community.test.dto.resultDTO;
import com.community.test.exception.CustomizeException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class CustomizeExceptionHandler {
    @ExceptionHandler(CustomizeException.class)
    Object handleException(HttpServletRequest request, Throwable ex, Model model,
                           HttpServletResponse response){


        String contentType = request.getContentType();
        if("application/json".equals(contentType)){
            resultDTO resultDTO = null;
            //返回json
            if(ex instanceof CustomizeException){
                resultDTO = resultDTO.errorOf((CustomizeException) ex);
            }else {
                resultDTO = resultDTO.errorOf(5000,"已死亡");
            }
            try {
                response.setContentType("application/json");
                response.setStatus(200);
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(JSON.toJSONString(resultDTO));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        else {
            //返回错误页
            HttpStatus status = getStatus(request);

            if(ex instanceof CustomizeException){
                model.addAttribute("errorMsg",ex.getMessage());
            }else {
                model.addAttribute("errorMsg","已死亡");
            }
            System.out.println("草泥马");
            return new ModelAndView("error");
        }

    }
    private HttpStatus getStatus(HttpServletRequest request){
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if(statusCode == null){
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }

}
