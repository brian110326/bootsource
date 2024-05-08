package com.example.movie.task;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.movie.dto.MovieImageDto;
import com.example.movie.entity.MovieImage;
import com.example.movie.repository.MovieImageRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
@RequiredArgsConstructor
public class FileCheckTask {

    private final MovieImageRepository movieImageRepository;

    @Value("${com.example.upload.path}")
    private String uploadPath;

    // 전일자 폴더 구하기
    private String getFolderYesterday() {
        // 어제날짜 추출
        LocalDate yesterday = LocalDate.now().minusDays(1);

        // 문자열 형태로 변환
        String strDay = yesterday.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        // 2024-05-07 ==> 2024\05\07 String 형태
        // windows : \, linux : /
        return strDay.replace("-", File.separator);
    }

    // cron : 초 분 시 일 월 요일 연도
    @Scheduled(cron = "0 * * * * *")
    public void checkFiles() {
        log.info("file check task 시작....");

        // 데이터베이스 상 어제날짜 이미지 파일 리스트 가져오기
        List<MovieImage> oldImages = movieImageRepository.getOldMovieImages();

        // oldImages ==> dto로
        List<MovieImageDto> movieImageDtos = oldImages.stream().map(movieImage -> {
            return MovieImageDto.builder().inum(movieImage.getInum()).uuid(movieImage.getUuid())
                    .imgName(movieImage.getImgName())
                    .path(movieImage.getPath())
                    .build();
        }).collect(Collectors.toList());

        // dto 내용 수집 ==> 이미지 파일 1개당 c:\\upload\\2024\\05\\07\\uuid_파일명
        List<Path> fileListPaths = movieImageDtos.stream()
                .map(dto -> Paths.get(uploadPath, dto.getImageURL(), dto.getUuid() + "_" + dto.getImgName()))
                .collect(Collectors.toList());

        // 썸네일 이미지 파일명
        movieImageDtos.stream()
                .map(dto -> Paths.get(uploadPath, dto.getImageURL(), "s_" + dto.getUuid() + "_" + dto.getImgName()))
                .forEach(p -> fileListPaths.add(p));

        // for (Path path : fileListPaths) {
        // log.info(path);
        // }

        // 어제날짜 폴더 구하기
        File targetDir = Paths.get(uploadPath, getFolderYesterday()).toFile();

        // targetDir 안의 파일과 DB 파일 목록 비교 후 일치하지 않는 파일 목록 생성
        File[] removFiles = targetDir.listFiles(f -> fileListPaths.contains(f.toPath()) == false);

        if (removFiles != null) {
            for (File file : removFiles) {
                file.delete();
            }
        }
    }

}
