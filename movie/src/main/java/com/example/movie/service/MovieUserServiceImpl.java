package com.example.movie.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.movie.dto.AuthMemberDto;
import com.example.movie.dto.MemberDto;
import com.example.movie.dto.PasswordChangeDto;
import com.example.movie.entity.Member;
import com.example.movie.repository.MemberRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class MovieUserServiceImpl implements UserDetailsService, MovieUserService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("로그인 요청 {}", username);
        // security에서 사용하는 로그인 메소드
        // User로 return 혹은 User를 구현한 CustomUser로 return

        // Optional<Member> result = memberRepository.findById(null);

        // if (result.isPresent()) {
        // Member member = result.get();
        // }

        // return
        // User.builder().username(member.getEmail()).password(member.getPassword())
        // .roles(member.getMemberRole().toString()).build();

        // 로그인 시 email, password : findById로는 mid로 해야함

        Optional<Member> result = memberRepository.findByEmail(username);

        if (!result.isPresent()) {
            throw new UsernameNotFoundException("Check Email");
        }

        Member member = result.get();

        // entity => dto

        return new AuthMemberDto(entityToDto(member));

    }

    @Override
    public String register(MemberDto insertDto) throws IllegalStateException {

        throw new UnsupportedOperationException("Unimplemented method 'register'");
    }

    @Override
    @Transactional
    public void nickNameUpdate(MemberDto upMemberDto) {

        // select 결과에 따라 insert or update
        // Member member = dtoToEntity(upMemberDto);

        // memberRepository.save(member);

        // save 사용안할 때
        memberRepository.updateNickName(upMemberDto.getNickname(), upMemberDto.getEmail());
    }

    @Override
    public void passwordUpdate(PasswordChangeDto pDto) throws IllegalStateException {

        // 현재 이메일과 비밀번호가 일치하는지 여부
        // select => 결과 있다면 => update
        Member member = memberRepository.findByEmail(pDto.getEmail()).get();

        // 비밀번호는 암호화 된 상태
        if (!passwordEncoder.matches(pDto.getCurrentPassword(), member.getPassword())) {
            throw new IllegalStateException("현재 비밀번호가 일치하지 않습니다");
        } else {
            member.setPassword(passwordEncoder.encode(pDto.getNewPassword()));
            memberRepository.save(member);
        }
    }

}
