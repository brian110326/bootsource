package com.example.movie.dto;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDateTime;

import com.example.movie.entity.Movie;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MovieImageDto {
    private Long inum;

    private String uuid;

    private String imgName;

    private String path;

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;

    public String getImageURL() {
        String fullPath = "";

        try {
            // 한글이 있을 수 있으니 encoding 작업 필요
            fullPath = URLEncoder.encode(path + "/" + uuid + "_" + imgName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return fullPath;
    }

    public String getThumbImageURL() {
        String thumbFullPath = "";

        try {
            // 한글이 있을 수 있으니 encoding 작업 필요
            thumbFullPath = URLEncoder.encode(path + "/" + "s_" + uuid + "_" + imgName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return thumbFullPath;
    }
}
