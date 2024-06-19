package com.example.todo.mapper;


import com.example.todo.entity.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MemberMapper {
    List<Member> selectAllMembers();
    Member getLastJoinedMember();
    Member findMemberById(long id);
    Optional<Member> findMemberByUsername(String username);
    int createMember(Member member);
}
