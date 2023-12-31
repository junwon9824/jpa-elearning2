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
    private String[] filename;

    private Long price;
    private String owneremail;
    private LocalDateTime createdDate;

    private File[] file;

//    private  MultipartFile multipartFile;

    public File[] getFile() {

        File[] files = new File[filename.length];

        for (int i = 0; i < filename.length; i++) {
            files[i] = new File(filepath, filename[i]);
        }

        return files;

    }


    public static AllBoardsresponse from(Board board) {
        int size = board.getFilename().length;

        File[] getFile=new File[size];
        String[] filenames = new String[board.getFilename().length];
        filenames = board.getFilename();

        for (int i = 0; i < size; i++)
            getFile[i] = new File(board.getFilepath(), filenames[i]);

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
