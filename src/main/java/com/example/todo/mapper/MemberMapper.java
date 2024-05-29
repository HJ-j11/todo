package com.example.todo.mapper;


import com.example.todo.entity.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {
    List<Member> selectAllMembers();
    Member getLastJoinedMember();
}
