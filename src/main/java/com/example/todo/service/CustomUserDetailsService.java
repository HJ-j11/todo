package com.example.todo.service;


import com.example.todo.entity.Member;
import com.example.todo.entity.UserRole;
import com.example.todo.entity.error.CommonErrorCode;
import com.example.todo.entity.exception.RestApiException;
import com.example.todo.entity.jwt.CustomUserDetails;
import com.example.todo.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberMapper memberMapper;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> member = memberMapper.findMemberByUsername(username);
        if(member.isPresent()) {
            return createUserDetails(member.get());
        } else {
            throw new RestApiException(CommonErrorCode.USER_NOT_FOUND);
        }
    }
    private CustomUserDetails createUserDetails(Member member) {
        return new CustomUserDetails(member.getId(), member.getUsername(), member.getPassword(), member.getUserRole());
    }
}