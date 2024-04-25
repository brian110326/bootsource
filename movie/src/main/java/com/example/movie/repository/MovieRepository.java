package com.example.movie.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.movie.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query(value = "SELECT m.MNO,m.CREATED_DATE,m.TITLE,MI2.IMG_NAME,mi2.PATH,mi2.UUID, " +
            "(SELECT COUNT(DISTINCT r.review_no) FROM REVIEW r	WHERE r.MOVIE_MNO = m.MNO) AS review_cnt, " +
            "(SELECT AVG(r.GRADE) FROM REVIEW r	WHERE r.MOVIE_MNO = m.MNO) AS grade_avg " +
            "FROM MOVIE_IMAGE mi2 LEFT JOIN MOVIE m ON MI2.MOVIE_MNO = M.MNO " +
            "WHERE mi2.INUM IN (SELECT	min(mi.INUM) FROM MOVIE_IMAGE mi " +
            "GROUP BY mi.MOVIE_MNO) ORDER BY MNO DESC", nativeQuery = true)
    Page<Object[]> getListPage(Pageable pageable);
}
