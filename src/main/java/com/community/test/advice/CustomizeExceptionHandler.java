package com.community.test.advice;

import com.community.test.exception.CustomizeException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CustomizeExceptionHandler {
    @ExceptionHandler(Exception.class)
    ModelAndView handleException(HttpServletRequest request, Throwable ex, Model model){
        HttpStatus status = getStatus(request);

        if(ex instanceof CustomizeException){
            model.addAttribute("errorMsg",ex.getMessage());
        }else {
            model.addAttribute("errorMsg","已死亡");
        }
        System.out.println("草泥马");
        return new ModelAndView("error");
    }
    private HttpStatus getStatus(HttpServletRequest request){
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if(statusCode == null){
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }

}
