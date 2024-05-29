package com.example.todo.controller;


import com.example.todo.dto.MemberDto;
import com.example.todo.dto.api.ApiResponse;
import com.example.todo.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @Operation(summary = "Member 목록 조회")
    @GetMapping("/members")
    public ApiResponse<List<MemberDto>> selectAllMembers() {
        List<MemberDto> members = memberService.selectAllMembers();
        return ApiResponse.success(members);
    }

    @Operation(summary = "최근 가입 멤버 조회")
    @GetMapping("/members/recent")
    public ApiResponse<MemberDto> getLastJoinedMember() {
        MemberDto member = memberService.getLastJoinedMember();
        return ApiResponse.success(member);
    }
}
