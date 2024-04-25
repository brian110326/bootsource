package com.example.movie.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.movie.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    // @Query("select m, avg(r.grade), count(distinct r) from Movie m left join
    // Review r on r.movie = m group by m")
    // oralce로 했기 때문에 오류 발생
    @Query("select m, min(mi), avg(r.grade), count(distinct r) from Movie m left join MovieImage mi on mi.movie = m " +
            "left join Review r on r.movie = m group by m")
    Page<Object[]> getListPage2(Pageable pageable);

    // 여러개가 나오는 native query로 할 때 Projection개념(강의자료참고) 필요(getListPage2와 나오는 형태가 다름)
    // oracle은 group by가 들어간 순간 getListPage2처럼 X
    @Query(value = "SELECT * FROM MOVIE m LEFT JOIN (SELECT r.MOVIE_MNO , COUNT(*) , AVG(r.GRADE) FROM REVIEW r " +
            "GROUP BY r.MOVIE_MNO) r1 ON m.MNO = r1.movie_mno " +
            "LEFT JOIN (SELECT mi2.* FROM MOVIE_IMAGE mi2 WHERE mi2.inum " +
            "IN (SELECT MIN(mi.inum) FROM MOVIE_IMAGE mi GROUP BY mi.movie_mno)) A ON m.MNO = A.movie_mno " +
            "ORDER BY MNO DESC", nativeQuery = true)
    Page<Object[]> getListPage(Pageable pageable);

}
