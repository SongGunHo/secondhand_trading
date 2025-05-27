package org.koreait.member.controller;

import lombok.Data;

@Data
public class RequestLogin {

    private String email;
    private String password;
    private boolean autoLogin;







}
