package com.example.todo.controller;

import com.example.todo.dto.LoginDto;
import com.example.todo.dto.SignUpDto;
import com.example.todo.dto.api.ApiResponse;
import com.example.todo.entity.jwt.JwtResponse;
import com.example.todo.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.server.Cookie;
import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/auth")
public class LoginController {
    private final MemberService memberService;

    @Operation(summary = "로그인")
    @PostMapping("/sign-in")
    public ApiResponse<JwtResponse> signIn(@RequestBody LoginDto loginDto, HttpServletResponse response) {
        String username = loginDto.getUsername();
        String password = loginDto.getPassword();

        JwtResponse jwtResponse = memberService.signIn(username, password);
        log.info("Member Info ={}", jwtResponse);

        ResponseCookie cookie = ResponseCookie.from("refreshToken", jwtResponse.getRefreshToken())
                        .maxAge(24 * 60 * 60)
                        .path("/")
                        .secure(true)
                        .sameSite("None")
                        .httpOnly(true)
                        .build();

        response.setHeader("Set-Cookie", cookie.toString());
        return ApiResponse.success(jwtResponse);
    }

    @Operation(summary = "회원 가입")
    @PostMapping("/sign-up")
    public ApiResponse<Void> signUp(@RequestBody SignUpDto signUpDto) {
        memberService.signUp(signUpDto);
        return ApiResponse.successWithoutContent();
    }
}