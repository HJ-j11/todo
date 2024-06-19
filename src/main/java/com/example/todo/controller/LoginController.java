package com.example.todo.controller;

import com.example.todo.dto.LoginDto;
import com.example.todo.dto.SignUpDto;
import com.example.todo.dto.api.ApiResponse;
import com.example.todo.entity.jwt.jwtResponse;
import com.example.todo.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class LoginController {
    private final MemberService memberService;

    @Operation(summary = "로그인")
    @PostMapping("/sign-in")
    public ApiResponse<jwtResponse> signIn(@RequestBody LoginDto loginDto) {
        String username = loginDto.getUsername();
        String password = loginDto.getPassword();
        jwtResponse jwtResponse = memberService.signIn(username, password);
        return ApiResponse.success(jwtResponse);
    }

    @Operation(summary = "회원 가입")
    @PostMapping("/sign-up")
    public ApiResponse<Void> signUp(@RequestBody SignUpDto signUpDto) {
        memberService.signUp(signUpDto);
        return ApiResponse.successWithoutContent();
    }
}