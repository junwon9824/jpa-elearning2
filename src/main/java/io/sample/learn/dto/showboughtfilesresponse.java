package io.sample.learn.dto;

import io.sample.learn.entity.Board;
import io.sample.learn.entity.BuyBoard;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor
public class showboughtfilesresponse {

    private Long id;

    private String title;

    private String description;

    private String filepath;
    private String[] filename;

    private Long price;
    private String owneremail;
    private LocalDateTime createdDate;

    private File[] file;

    public static Object from(BuyBoard buyBoard) {
        return showboughtfilesresponse.builder().build()
                . filepath(buyBoard.f)
    }

//    private  MultipartFile multipartFile;

    public File[] getFile() {

        File[] files = new File[filename.length];

        for (int i = 0; i < filename.length; i++) {
            files[i] = new File(filepath, filename[i]);
        }

        return files;

    }


}
