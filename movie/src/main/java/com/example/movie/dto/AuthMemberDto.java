package com.example.movie.dto;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AuthMemberDto extends User {

    private MemberDto memberDto;

    public AuthMemberDto(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);

    }

    public AuthMemberDto(MemberDto memberDto) {
        // 모든 생성자는 부모생성자 호출
        super(memberDto.getEmail(), memberDto.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_" + memberDto.getMemberRole())));

        this.memberDto = memberDto;
    }

}
