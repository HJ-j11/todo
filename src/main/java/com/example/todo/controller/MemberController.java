package com.example.todo.controller;


import com.example.todo.dto.MemberDto;
import com.example.todo.dto.api.ApiResponse;
import com.example.todo.service.MemberServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberServiceImpl memberServiceImpl;

    @Operation(summary = "Member 목록 조회")
    @GetMapping("/members")
    public ApiResponse<List<MemberDto>> selectAllMembers() {
        List<MemberDto> members = memberServiceImpl.selectAllMembers();
        return ApiResponse.success(members);
    }

    @Operation(summary = "최근 가입 멤버 조회")
    @GetMapping("/members/recent")
    public ApiResponse<MemberDto> getLastJoinedMember() {
        MemberDto member = memberServiceImpl.getLastJoinedMember();
        return ApiResponse.success(member);
    }
}
