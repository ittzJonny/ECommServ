package com.example.authenticationservice.Utils;

import com.example.authenticationservice.DTOs.BaseResponseDTO;
import org.springframework.http.ResponseEntity;

import java.lang.reflect.InvocationTargetException;

public class ExceptionUtil {


    // This method takes in the exception and the Class type to be returned as response dto
    // this method will wrap the exception message in ResponseMessage variable if return type is a child of BaseResponseDTO
    public static <T> T getExceptionResponse(Exception e, Class<T> tClass)
    {
        try {
            T t=tClass.getDeclaredConstructor().newInstance();
            if (t instanceof BaseResponseDTO)
            {
                ((BaseResponseDTO) t).setResponseMessage(e.getMessage());
            }
            return t;
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }

}
