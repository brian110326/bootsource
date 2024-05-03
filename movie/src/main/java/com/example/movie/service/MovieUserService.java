package com.example.movie.service;

import com.example.movie.dto.MemberDto;
import com.example.movie.dto.PasswordChangeDto;
import com.example.movie.entity.Member;

public interface MovieUserService {
    // 회원가입
    String register(MemberDto insertDto) throws IllegalStateException;

    // 닉네임 수정
    void nickNameUpdate(MemberDto upMemberDto);

    // 비밀번호 수정
    void passwordUpdate(PasswordChangeDto pDto) throws IllegalStateException;

    // dto => entity
    public default Member dtoToEntity(MemberDto memberDto) {
        Member member = Member.builder().mid(memberDto.getMid()).email(memberDto.getEmail())
                .password(memberDto.getPassword())
                .nickname(memberDto.getNickname()).role(memberDto.getMemberRole())
                .build();

        return member;
    }

    // entity => dto
    public default MemberDto entityToDto(Member member) {
        MemberDto memberDto = MemberDto.builder().mid(member.getMid()).email(member.getEmail())
                .password(member.getPassword()).nickname(member.getNickname()).memberRole(member.getRole())
                .build();

        return memberDto;
    }
}
