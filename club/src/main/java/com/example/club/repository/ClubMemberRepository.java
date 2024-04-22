package com.example.club.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.club.entity.ClubMember;

public interface ClubMemberRepository extends JpaRepository<ClubMember, String> {

}
