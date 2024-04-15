package com.example.guestbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.guestbook.entity.GuestBook;

public interface GuestBookRepository extends JpaRepository<GuestBook, Long> {

}
