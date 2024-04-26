package com.example.movie.service;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.movie.dto.MovieDto;
import com.example.movie.dto.PageRequestDto;
import com.example.movie.dto.PageResultDto;
import com.example.movie.entity.Movie;
import com.example.movie.entity.MovieImage;
import com.example.movie.repository.MovieImageRepository;
import com.example.movie.repository.MovieRepository;
import com.example.movie.repository.ReviewRepository;
import com.example.movie.repository.total.MovieImageReviewRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    // 사용할것들만 작성(RequiredArgsConstructor)
    private final MovieRepository movieRepository;
    private final MovieImageRepository movieImageRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public PageResultDto<MovieDto, Object[]> getList(PageRequestDto pageRequestDto) {

        Pageable pageable = pageRequestDto.getPageable(Sort.by("mno").descending());

        Page<Object[]> result = movieImageRepository.getTotalList(pageable);

        // [Movie(mno=100, title=Movie100), MovieImage(inum=292,
        // uuid=2d8c6c28-38bb-4568-9a22-fd130f9c6f35, imgName=img0.jpg, path=null), 1,
        // 2.0]]

        Function<Object[], MovieDto> function = (en -> entityToDto((Movie) en[0],
                (List<MovieImage>) Arrays.asList((MovieImage) en[1]),
                (Long) en[2],
                (Double) en[3]));

        return new PageResultDto<>(result, function);
    }

}
