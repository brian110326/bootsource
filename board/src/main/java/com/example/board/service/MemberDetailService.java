package com.example.board.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.board.dto.MemberAuthDto;
import com.example.board.dto.MemberDto;
import com.example.board.entity.Member;
import com.example.board.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Service
public class MemberDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // id == email == username
        Optional<Member> result = memberRepository.findById(username);

        if (!result.isPresent())
            throw new UsernameNotFoundException("이메일을 확인해 주세요");

        Member member = result.get();

        // entity => dto
        // 시큐리티 로그인 ==> 회원 정보 + 허가와 관련된 정보(사이트를 접근 여부)
        MemberDto memberDto = MemberDto.builder()
                .email(member.getEmail())
                .name(member.getName())
                .password(member.getPassword())
                .memberRole(member.getMemberRole())
                .build();

        return new MemberAuthDto(memberDto);
    }

}
