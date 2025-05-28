package org.koreait.global.exceoption.script;

import org.koreait.global.exceoption.CommonException;
import org.springframework.http.HttpStatus;

public class AlertException extends CommonException {

    public AlertException(String message, HttpStatus status){
        super(message,status);
    }






}
