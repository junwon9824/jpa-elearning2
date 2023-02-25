package io.sample.learn.dto;

import io.sample.learn.entity.Board;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor
public class AllBoardsresponse {

    private Long id;

    private String title;

    private String description;

    private String filepath;
    private String filename;

    private Long price;
    private String owneremail;
    private LocalDateTime createdDate;

    private  File file;
    private  MultipartFile multipartFile;

    public File getFile() {
        return new File(filepath, filename);
    }


    public static AllBoardsresponse from(Board board) {
        File getFile= new File(board.getFilepath(),board.getFilename());

        return AllBoardsresponse.builder()
                .id(board.getId())
                .title(board.getTitle())
                .description(board.getDescription())
                .filepath(board.getFilepath())
                .price(board.getPrice())
                .owneremail(board.getOwneremail())
                .createdDate(board.getCreatedDate())
                .filename(board.getFilename())
                 .build();

    }
}
