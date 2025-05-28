package org.koreait.member.services;

import lombok.RequiredArgsConstructor;
import org.koreait.global.repository.MemberRepositroy;
import org.koreait.member.controller.RequestJoin;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.lang.reflect.Member;

@Lazy
@Service
@RequiredArgsConstructor
public class JoinService {


    private final PasswordEncoder encoder;
    private final MemberRepositroy repositroy;
    private final ModelMapper modelMapper;


    public void Process(RequestJoin form){
        /**
         *
         * 1. 비밀 번호 BCrypt 해시화
         * 2. 휴재전화 번호 통일 화
         *  숫자만 남기고 다 제거 한다
         * 3.. DB 에 영구 저장
         *
         */

        String hash = encoder.encode(form.getPassword());
        String mobile = form.getMobile();
        if (StringUtils.hasText(mobile)){
            mobile = mobile.replaceAll("\\D", "");

        }



        Member member = modelMapper.map(form, Member.class);









    }
}
