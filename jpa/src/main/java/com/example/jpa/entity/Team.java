package com.example.jpa.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = { "members" }) // ToString 생성 시 클래스 내 모든 property가 기준, exclude는 property 제외
@Builder
@Entity
public class Team {

    @Column(name = "team_id")
    @Id
    private String id;

    @Column(name = "team_name")
    private String name;

    // 한팀에 여러명이 들어올 수 있으니 List로

    // 주체가 누구인지 알려주기
    // ManyToOne이 주인
    @Builder.Default
    @OneToMany(mappedBy = "team", fetch = FetchType.EAGER)
    private List<TeamMember> members = new ArrayList<>();
}
