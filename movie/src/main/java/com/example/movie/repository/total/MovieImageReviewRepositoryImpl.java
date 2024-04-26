package com.example.movie.repository.total;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.example.movie.entity.Movie;
import com.example.movie.entity.MovieImage;
import com.example.movie.entity.QMovie;
import com.example.movie.entity.QMovieImage;
import com.example.movie.entity.QReview;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class MovieImageReviewRepositoryImpl extends QuerydslRepositorySupport implements MovieImageReviewRepository {

    public MovieImageReviewRepositoryImpl() {
        super(MovieImage.class);
    }

    @Override
    public Page<Object[]> getTotalList(Pageable pageable) {
        log.info("==================== querydsl getTotalList ====================");

        // Q클래스 가져오기
        QMovieImage movieImage = QMovieImage.movieImage;
        QMovie movie = QMovie.movie;
        QReview review = QReview.review;

        // 기준이 되는 클래스 from
        // SELECT m.MNO ,m.CREATED_DATE ,m.TITLE , mi2.IMG_NAME , mi2."PATH" ,mi2.UUID,
        // (SELECT COUNT(DISTINCT r.review_no) FROM REVIEW r WHERE r.movie_mno = m.MNO)
        // AS review_cnt,
        // (SELECT AVG(r.GRADE) FROM REVIEW r WHERE r.movie_mno = m.MNO) AS grade_avg
        // FROM MOVIE_IMAGE mi2 LEFT JOIN MOVIE m ON mi2.MOVIE_MNO = m.MNO
        // WHERE mi2.INUM IN (SELECT MIN(mi.INUM)
        // FROM MOVIE_IMAGE mi
        // GROUP BY mi.MOVIE_MNO)

        JPQLQuery<MovieImage> query = from(movieImage);
        query.leftJoin(movie).on(movieImage.movie.eq(movie));

        // 서브 쿼리는 from절 안에 사용못함(select절 혹은 where절에 넣기)
        JPQLQuery<Tuple> tuple = query.select(movie, movieImage,
                JPAExpressions.select(review.countDistinct()).from(review).where(review.movie.eq(movieImage.movie)),
                JPAExpressions.select(review.grade.avg().round()).from(review)
                        .where(review.movie.eq(movieImage.movie)))
                .where(movieImage.inum
                        .in(JPAExpressions.select(movieImage.inum.min()).from(movieImage).groupBy(movieImage.movie)));
        // .orderBy(movie.mno.desc());

        // 페이지 나누기
        // sort 지정
        // 정렬기준이 1개가 아니라 기준이 계속 변경될 때 대비용
        Sort sort = pageable.getSort();
        sort.stream().forEach(order -> {
            Order direction = order.isAscending() ? Order.ASC : Order.DESC;
            String prop = order.getProperty();

            // 어떤 클래스를 기준으로 sort할것인지
            PathBuilder<Movie> orderByExpression = new PathBuilder<>(Movie.class, "movie");
            tuple.orderBy(new OrderSpecifier(direction, orderByExpression.get(prop)));
        });

        // 페이지 처리
        tuple.offset(pageable.getOffset());
        tuple.limit(pageable.getPageSize());

        List<Tuple> result = tuple.fetch();

        long count = tuple.fetchCount();

        return new PageImpl<>(result.stream().map(t -> t.toArray()).collect(Collectors.toList()), pageable, count);
    }

}
