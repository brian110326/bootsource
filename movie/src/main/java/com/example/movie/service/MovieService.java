package com.example.movie.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.movie.dto.MovieDto;
import com.example.movie.dto.MovieImageDto;
import com.example.movie.dto.PageRequestDto;
import com.example.movie.dto.PageResultDto;
import com.example.movie.entity.Movie;
import com.example.movie.entity.MovieImage;

public interface MovieService {

    PageResultDto<MovieDto, Object[]> getList(PageRequestDto pageRequestDto);

    MovieDto getRow(Long mno);

    void movieRemove(Long mno);

    Long movieInsert(MovieDto movieDto);

    Long movieUpdate(MovieDto movieDto);

    // list로 보여줄 항목(test파일)
    // [Movie(mno=100, title=Movie100), MovieImage(inum=292,
    // uuid=2d8c6c28-38bb-4568-9a22-fd130f9c6f35, imgName=img0.jpg, path=null), 1,
    // 2.0]]

    // entity => dto
    public default MovieDto entityToDto(Movie movie, List<MovieImage> movieImages, Long reviewCnt, Double avg) {
        MovieDto movieDto = MovieDto.builder().mno(movie.getMno())
                .title(movie.getTitle())
                .createdDate(movie.getCreatedDate())
                .lastModifiedDate(movie.getLastModifiedDate())
                .avg(avg != null ? avg : 0.0d)
                .reviewCnt(reviewCnt).build();

        // 영화 상세 조회 => 이미지를 모두 보여줄 계획
        List<MovieImageDto> movieImageDtos = movieImages.stream().map(movieImage -> {
            return MovieImageDto.builder().inum(movieImage.getInum()).uuid(movieImage.getUuid())
                    .imgName(movieImage.getImgName())
                    .path(movieImage.getPath())
                    .build();
        }).collect(Collectors.toList());

        movieDto.setMovieImageDtos(movieImageDtos);

        return movieDto;
    }

    // dto => entity
    public default Map<String, Object> dtoToEntity(MovieDto dto) {

        // Movie Entity 생성
        Movie movie = Movie.builder().mno(dto.getMno()).title(dto.getTitle()).build();

        // 생성된 Movie Entity를 Map에 담기
        Map<String, Object> entityMap = new HashMap<>();
        entityMap.put("movie", movie);

        // List<MovieImageDto> => List<MovieImage> 변환
        List<MovieImageDto> movieImageDtos = dto.getMovieImageDtos();
        List<MovieImage> movieImages = new ArrayList<>();

        if (movieImageDtos != null && movieImageDtos.size() > 0) {
            for (MovieImageDto mDto : movieImageDtos) {
                MovieImage movieImage = MovieImage.builder().movie(movie).imgName(mDto.getImgName())
                        .uuid(mDto.getUuid())
                        .path(mDto.getPath()).build();

                movieImages.add(movieImage);
            }
        }

        // if (movieImageDtos != null && movieImageDtos.size() > 0) {
        // List<MovieImage> movieImages = movieImageDtos.stream().map(mDto -> {
        // MovieImage movieImage =
        // MovieImage.builder().imgName(mDto.getImgName()).uuid(mDto.getUuid())
        // .path(mDto.getPath()).build();
        // return movieImage;
        // }).collect(Collectors.toList());

        // }

        // 변환이 끝난 entity list를 Map에 담기
        entityMap.put("imgList", movieImages);

        return entityMap;
    }
}
