package com.example.club.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ClubAuthMemberDto extends User {

    // db에서 인증된 정보를 담을 객체

    private String email;
    private String password;
    private String name;
    private boolean fromSocial;

    public ClubAuthMemberDto(String username, String password, boolean fromSocial,
            Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);

        this.email = username;
        this.fromSocial = fromSocial;
        this.password = password;
    }

}
