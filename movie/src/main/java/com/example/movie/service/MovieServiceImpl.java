package com.example.movie.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.movie.dto.MovieDto;
import com.example.movie.dto.MovieImageDto;
import com.example.movie.dto.PageRequestDto;
import com.example.movie.dto.PageResultDto;
import com.example.movie.entity.Movie;
import com.example.movie.entity.MovieImage;
import com.example.movie.repository.MovieImageRepository;
import com.example.movie.repository.MovieRepository;
import com.example.movie.repository.ReviewRepository;
import com.example.movie.repository.total.MovieImageReviewRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    // 사용할것들만 작성(RequiredArgsConstructor)
    private final MovieRepository movieRepository;
    // MovieImageReviewRepository 넣었다가 오류 : 상속하기 때문에 굳이 2개를 다 넣을 필요 X
    // MovieImageRepository가 MovieImageReviewRepository의 기능을 다 가지므로
    // MovieImageRepository만 넣기
    private final MovieImageRepository movieImageRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public PageResultDto<MovieDto, Object[]> getList(PageRequestDto pageRequestDto) {

        Page<Object[]> result = movieImageRepository.getTotalList(pageRequestDto.getType(), pageRequestDto.getKeyword(),
                pageRequestDto.getPageable(Sort.by("mno").descending()));

        // [Movie(mno=100, title=Movie100), MovieImage(inum=292,
        // uuid=2d8c6c28-38bb-4568-9a22-fd130f9c6f35, imgName=img0.jpg, path=null), 1,
        // 2.0]]

        Function<Object[], MovieDto> function = (en -> entityToDto((Movie) en[0],
                (List<MovieImage>) Arrays.asList((MovieImage) en[1]),
                (Long) en[2],
                (Double) en[3]));

        return new PageResultDto<>(result, function);
    }

    @Override
    public MovieDto getRow(Long mno) {
        List<Object[]> result = movieImageRepository.getMovieRow(mno);

        // [Movie(mno=3, title=Movie3), MovieImage(inum=11,
        // uuid=ebbc2bce-90d2-4287-b8bb-9e6da85f02b2, imgName=img3.jpg, path=null), 3,
        // 3.0] ==> result[0] 총 4개있음
        Movie movie = (Movie) result.get(0)[0];
        Long reviewCnt = (Long) result.get(0)[2];
        Double avg = (Double) result.get(0)[3];

        // result의 size만큼 반복
        List<MovieImage> movieImages = new ArrayList<>();
        result.forEach(arr -> {
            MovieImage movieImage = (MovieImage) arr[1];
            movieImages.add(movieImage);
        });

        // result.stream().map(en -> (MovieImage)en[1]).collect(Collectors.toList());

        return entityToDto(movie, movieImages, reviewCnt, avg);

    }

    @Override
    @Transactional
    public void movieRemove(Long mno) {
        Movie movie = Movie.builder().mno(mno).build();
        movieImageRepository.deleteByMovie(movie);
        reviewRepository.deleteByMovie(movie);
        movieRepository.delete(movie);
    }

    @Override
    @Transactional
    public Long movieInsert(MovieDto movieDto) {
        // 영화정보 : title => Movie entity
        // 영화 이미지 : MovieImage entity

        // dto ==> entity
        Map<String, Object> entityMap = dtoToEntity(movieDto);

        // movie 삽입
        Movie movie = (Movie) entityMap.get("movie");
        movieRepository.save(movie);

        // movieImage 삽입
        List<MovieImage> movieImages = (List<MovieImage>) entityMap.get("imgList");
        movieImages.forEach(image -> movieImageRepository.save(image));

        return movie.getMno();
    }

    @Override
    @Transactional
    public Long movieUpdate(MovieDto movieDto) {

        // dto ==> entity
        Map<String, Object> entityMap = dtoToEntity(movieDto);

        // movie 기존 이미지 제거
        Movie movie = (Movie) entityMap.get("movie");
        movieImageRepository.deleteByMovie(movie);

        // movieImage 삽입
        List<MovieImage> movieImages = (List<MovieImage>) entityMap.get("imgList");
        movieImages.forEach(image -> movieImageRepository.save(image));

        return movie.getMno();
    }

}
