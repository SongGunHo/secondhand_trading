package org.koreait.member.validators;

import lombok.RequiredArgsConstructor;
import org.koreait.global.repository.MemberRepositroy;
import org.koreait.global.validators.MobileValidator;
import org.koreait.global.validators.PasswordValidator;
import org.koreait.member.controller.RequestJoin;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Lazy
@Component// db 의존성 주입
@RequiredArgsConstructor
public class JoinValidator implements Validator , PasswordValidator, MobileValidator {

    private final MemberRepositroy repositroy;



    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(RequestJoin.class);
    }

    @Override
    public void validate(Object target, Errors errors) {

        if (errors.hasErrors()){
            return;
        }





        /**
         * 1. email 중복 여부
         * 2. 비밀 번호 복작성
         * 3. 비밀 번호 확인
         * 3. 휴대폰 번호 형식 검증
         *
         */
        RequestJoin form = (RequestJoin) target;
        String email  = form.getEmail();
        String password = form.getPassword();
        String confirmPassword = form.getConfirmPassword();
        String mobile = form.getMobile();
        //1. email 중복 여부

        if(repositroy.existsById(form.getEmail())){
            errors.rejectValue("email", "Duplicated");
        }

        //2. 비밀 번호 복작성
        if (!checkAlpha(password, false )|| !checkNumber(password)||!checkSpecialChars(password)){
            errors.rejectValue("password", "Complexity");

        }

        //3. 비밀 번호 확인
        if (!password.equals(confirmPassword)){
            errors.rejectValue("confirmPassword", "Misamtch");
        }

        //3. 휴대폰 번호 형식 검증
        if(!checkMobile(mobile)){
            errors.rejectValue("mobile", "Format");
        }


    }
}
