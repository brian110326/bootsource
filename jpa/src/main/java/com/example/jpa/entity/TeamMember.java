package com.example.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
public class TeamMember {

    @Id
    @Column(name = "member_id")
    private String id;

    private String username;

    // sql 코드 외래키 제약조건
    // Many(TeamMember), One(Team)
    @ManyToOne
    private Team team;

}
