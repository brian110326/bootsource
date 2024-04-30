package com.example.movie.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;

import com.example.movie.entity.Movie;
import com.example.movie.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // DELETE FROM REVIEW r WHERE r.movie_mno=1
    // deleteById 사용불가 : review_no로 삭제되기 때문 => 메소드 생성필요

    @Modifying
    @Query("delete from Review r where r.movie = :movie")
    void deleteByMovie(Movie movie);

    // movie 번호를 이용해 review 가져오기
    // 이 메소드 실행 시 join 구문으로 처리해줘
    @EntityGraph(attributePaths = { "member" }, type = EntityGraphType.FETCH)
    List<Review> findByMovie(Movie movie);
}
