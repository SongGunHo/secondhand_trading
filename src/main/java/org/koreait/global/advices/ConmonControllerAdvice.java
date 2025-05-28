package org.koreait.global.advices;

import jakarta.servlet.http.HttpServletRequest;
import org.koreait.global.exceoption.CommonException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice("org.koreait")
public class ConmonControllerAdvice {

    @ExceptionHandler(Exception.class) // 모든 예외를 할 수 있다
    public ModelAndView errorHandler(Exception e, HttpServletRequest request){

        Map<String ,String> data = new HashMap<>();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR; // 500error
        String message = e.getMessage();

        if(e instanceof CommonException commonException){
            status = commonException.getStatus();
            if(commonException.isErrorCode()){ // 메세지 코드로 메세지룰 가져와야 하는 경우

            }
        }
        data.put("status", status.toString());
        data.put("message", message);
        data.put("path", request.getRequestURI());
        data.put("method", request.getMethod());


        ModelAndView mv = new ModelAndView();
        mv.setStatus(status);
        mv.addAllObjects(data);
        mv.setViewName("error/common");

        e.printStackTrace();
        return mv;




    }




}
