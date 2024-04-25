package com.example.movie.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@ToString(exclude = "movie")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieImage {

    @Id
    @SequenceGenerator(name = "movie_img_seq_gen", sequenceName = "movie_img_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movie_img_seq_gen")
    private Long inum;

    private String uuid;

    private String imgName;

    private String path;

    // 1개의 영화에 여러개의 영화이미지
    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie;
}
