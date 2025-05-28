package org.koreait.global.exceoption;


import org.springframework.http.HttpStatus;

public class UnAuthorizdException extends CommonException {
    public UnAuthorizdException(){
        this("UnAuthorizd");
        setErrorCode(true);
    }
    public UnAuthorizdException(String message){
        super(message, HttpStatus.UNAUTHORIZED);
    }
}
