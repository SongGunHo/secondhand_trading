package org.koreait.global.exceoption;

import org.springframework.http.HttpStatus;

public class NotFoundException extends CommonException{
    public NotFoundException(){
        this("NotFound");
        setErrorCode(true);
    }

    public NotFoundException(String message){
        super(message, HttpStatus.NOT_FOUND);
        setErrorCode(true); // 메세지 파일의 코드 이다
    }


}
