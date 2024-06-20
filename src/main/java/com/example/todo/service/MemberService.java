package com.example.todo.service;


import com.example.todo.dto.MemberDto;
import com.example.todo.dto.SignUpDto;
import com.example.todo.entity.Member;
import com.example.todo.entity.jwt.JwtResponse;
import com.example.todo.mapper.MemberMapper;
import com.example.todo.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberMapper memberMapper;
    private final ModelMapper modelMapper;
    private final JwtUtils jwtUtils;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final PasswordEncoder passwordEncoder;

    public List<MemberDto> selectAllMembers() {
        List<Member> members  = memberMapper.selectAllMembers();
        return members.stream().map(member -> modelMapper.map(member, MemberDto.class)).toList();
    }

    public MemberDto getLastJoinedMember() {
        Member member = memberMapper.getLastJoinedMember();
        return modelMapper.map(member, MemberDto.class);
    }

    public JwtResponse signIn(String username, String password) {
        /*
         * 1. Login ID/PW 를 기반으로 Authentication 객체 생성
         * 이때 authentication 는 인증 여부를 확인하는 authenticated 값이 false
         * */
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);

        /*
         * 2. 실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분
         * authenticate 매서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드가 실행
         * */
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        return jwtUtils.generateTokenByUserDetails(authentication);
    }

    public void signUp(SignUpDto signUpDto) {
        Member member = Member.builder()
                .name(signUpDto.getName())
                .username(signUpDto.getUsername())
                .password(passwordEncoder.encode(signUpDto.getPassword()))
                .build();

        memberMapper.createMember(member);
    }
}
