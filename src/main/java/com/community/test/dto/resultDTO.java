package com.community.test.dto;

import com.community.test.exception.CustomizeException;
import lombok.Data;

@Data
public class resultDTO {
    private int code;//
    private String message;

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
}
