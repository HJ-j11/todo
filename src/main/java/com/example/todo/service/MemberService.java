package com.example.todo.service;

import com.example.todo.dto.MemberDto;
import com.example.todo.dto.SignUpDto;
import com.example.todo.entity.Member;
import com.example.todo.entity.jwt.jwtResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface MemberService {
    public List<MemberDto> selectAllMembers();

    public MemberDto getLastJoinedMember();

    public jwtResponse signIn(String username, String password);

    public void signUp(SignUpDto signUpDto);
}
