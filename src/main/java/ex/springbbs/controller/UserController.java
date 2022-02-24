package ex.springbbs.controller;

import ex.springbbs.dto.JwtDto;
import ex.springbbs.dto.MemberDto;
import ex.springbbs.dto.SignUpResponseDto;
import ex.springbbs.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final IUserService userService;

    @Autowired
    public UserController(IUserService IUserService) {
        this.userService = IUserService;
    }

    @PostMapping(path = "/signUp") // 회원가입
    public SignUpResponseDto signUp(@RequestBody @Validated MemberDto memberDto) {
        return userService.join(memberDto);
    }

    @PostMapping("/login") // 로그인
    public JwtDto login(@RequestBody @Validated MemberDto memberDto) {
        return userService.login(memberDto);
    }
}