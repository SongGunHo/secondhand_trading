package org.koreait.global.libs;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.koreait.global.advices.ConmonControllerAdvice;
import org.koreait.member.services.JoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Locale;

@Component
@RequiredArgsConstructor
public class Utils {
    private final HttpServletRequest request;
    private final MessageSource messageSource;
    private final LocaleResolver resolver;
    private final JoinService service;
    private final ConmonControllerAdvice conmonControllerAdvice;


    public int version(){
        return 1;
    }
    public String keywords(){
        return "";

    }

    /**
     * 휴대폰 장비 인지  pc 인지
     *
     *
     * @return
     */
    public boolean isMobile(){
        String us = request.getHeader("User-Agent");
        String pattern = ".*(iPhone|iPod|iPad|BlackBerry|Android|Windows CE|LG|MOT|SAMSUNG|SonyEricsson).*";

        return StringUtils.hasText(us) && us.matches(pattern);
    }

    /**
     * mobile front  템플릿 분리
     *
     * @param path
     * @return
     */
    public String tpl(String path){
        String prefix = isMobile() ? "mobile": "front";

        return String.format("%s/%s", prefix, path);
    }


    public String getMessage(String code){
        Locale locale  = resolver.resolveLocale(request);

        return messageSource.getMessage(code, null, locale);

    }






}
