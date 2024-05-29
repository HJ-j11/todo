package com.example.todo.service;


import com.example.todo.dto.MemberDto;
import com.example.todo.entity.Member;
import com.example.todo.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberMapper memberMapper;
    private final ModelMapper modelMapper;

    public List<MemberDto> selectAllMembers() {
        List<Member> members  = memberMapper.selectAllMembers();
        return members.stream().map(member -> modelMapper.map(member, MemberDto.class)).toList();
    }

    public MemberDto getLastJoinedMember() {
        Member member = memberMapper.getLastJoinedMember();
        return modelMapper.map(member, MemberDto.class);
    }
}
