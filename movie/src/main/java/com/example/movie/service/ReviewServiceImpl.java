package com.example.movie.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.movie.dto.ReviewDto;
import com.example.movie.entity.Movie;
import com.example.movie.entity.Review;
import com.example.movie.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Override
    public List<ReviewDto> getListOfMovie(Long mno) {

        Movie movie = Movie.builder().mno(mno).build();

        List<Review> reviews = reviewRepository.findByMovie(movie);

        // List<Review> => List<ReviewDto>
        return reviews.stream().map(review -> entityToDto(review)).collect(Collectors.toList());
    }

    @Override
    public Long addReview(ReviewDto reviewDto) {
        // dto -> entity
        Review review = dtoToEntity(reviewDto);

        return reviewRepository.save(review).getReviewNo();
    }

    @Override
    public void removeReview(Long reviewNo) {
        reviewRepository.deleteById(reviewNo);
    }

    @Override
    public ReviewDto getReview(Long reviewNo) {
        Review review = reviewRepository.findById(reviewNo).get();

        return entityToDto(review);
    }

}
