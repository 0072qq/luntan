package com.community.test.dto;

import com.community.test.exception.CustomizeException;
import lombok.Data;

@Data
public class resultDTO<T> {
    private int code;//
    private String message;
    private T data;

    public static resultDTO errorOf(int code,String message){
        resultDTO resultDTO = new resultDTO();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
        return resultDTO;
    }

    public static resultDTO errorOf(CustomizeException ex) {
        resultDTO resultDTO = new resultDTO();
        resultDTO.setCode(ex.getCode());
        resultDTO.setMessage(ex.getMessage());
        return resultDTO;
    }

    public static resultDTO okOf(Object e) {
        resultDTO resultDTO = new resultDTO();
        resultDTO.setCode(2000);
        resultDTO.setMessage("请求成功");
        resultDTO.setData(e);
        return resultDTO;
    }

}
