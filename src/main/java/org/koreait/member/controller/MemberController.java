package org.koreait.member.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.koreait.global.exceoption.UnAuthorizdException;
import org.koreait.global.libs.Utils;
import org.koreait.member.controller.RequestJoin;
import org.koreait.member.controller.RequestLogin;
import org.koreait.member.validators.JoinValidator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final Utils utils;
    private final JoinValidator joinValidator;

    @ModelAttribute("addCss")
    public List<String> addCss() {
        return List.of("member/style");
    }

    // 회원가입 양식
    @GetMapping("/join")
    public String join(@ModelAttribute RequestJoin form, Model model) {
        commonProcess("join", model);

        return utils.tpl("member/join");
    }

    // 회원가입 처리
    @PostMapping("/join")
    @ResponseStatus(HttpStatus.CREATED)
    public String joinPs(@Valid RequestJoin form, Errors errors, Model model) {
        commonProcess("join", model);

        joinValidator.validate(form, errors);

        if (errors.hasErrors()) {
            return utils.tpl("member/join");
        }

        // 회원가입 성공시
        return "redirect:/member/login";
    }

    @GetMapping("/login")
    public String login(@ModelAttribute RequestLogin form, Model model) {
        commonProcess("login", model);
        boolean result  = false;

        if(!result){
            throw new ArithmeticException("", HttpStatus.BAD_REQUEST)
        }

        return utils.tpl("member/login");
    }

    /**
     * 현재 컨트롤러의 공통 처리 부분
     *
     * @param mode
     * @param model
     */
    private void commonProcess(String mode, Model model) {
        mode = StringUtils.hasText(mode) ? mode : "join";
        String pageTitle = "";
        List<String> addCommonScript = new ArrayList<>();
        List<String> addScript = new ArrayList<>();

        if (mode.equals("join")) { // 회원 가입 공통 처리
            addCommonScript.add("fileManager");
            addScript.add("member/join");
            pageTitle = "회원가입";

        } else if (mode.equals("login")) { // 로그인 공통 처리
            pageTitle = "로그인";
        }

        model.addAttribute("addCommonScript", addCommonScript);
        model.addAttribute("addScript", addScript);
        model.addAttribute("pageTitle", pageTitle);
    }
}