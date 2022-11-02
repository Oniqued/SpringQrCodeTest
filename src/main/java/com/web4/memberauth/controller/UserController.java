package com.web4.memberauth.controller;

import com.web4.memberauth.domain.QRcode;
import com.web4.memberauth.domain.User;
import com.web4.memberauth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/sign-up")
    public String signUp(){
        return "signUp";
    }

    @Autowired
    UserService userService;

    //회원가입창 유저 정보가 DB에 저장
    @RequestMapping(value = "/sign-up", method = RequestMethod.POST)
    public String signUp(User user){
        userService.join(user);
        return "signUpSuccess";
    }

    //로그인창에 입력한 정보 = userInfo
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(User user, Model model){ //<<Model은 html의 특정 칸에 데이터 넣어주는 역할
        System.out.println("user = " + user);

        User userInfoTry = userService.loginUser(user.getId(), user.getPassword());
        //사용자 정보 불일치
        //만약 db에 두 값이 일치하는 내용이 없으면
        if (userInfoTry == null) {
            model.addAttribute("loginMessage", "아이디 혹은 비밀번호가 일치하지 않습니다!");
            return "login";
        }
        //로그인 성공시
        model.addAttribute("name", userInfoTry.getName());
        return "main";
    }

    @GetMapping("/coupon")
    public String couponTab(){
        return "coupon";
    }

    @RequestMapping(value = "/buyCoupon", method = RequestMethod.POST)
    public String buyCoupon(QRcode qrCode){
        return "coupon";
    }
}
