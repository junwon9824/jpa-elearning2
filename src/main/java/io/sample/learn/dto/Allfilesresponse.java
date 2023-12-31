package io.sample.learn.dto;

 import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor
public class Allfilesresponse {

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


    public static Allfilesresponse from(io.sample.learn.entity.File file) {
        int size = file.getFilename().length;

        File[] getFile=new File[size];
        String[] filenames = new String[file.getFilename().length];
        filenames = file.getFilename();

        for (int i = 0; i < size; i++)
            getFile[i] = new File(file.getFilepath(), filenames[i]);

        return Allfilesresponse.builder()
                .id(file.getId())
                .title(file.getTitle())
                .description(file.getDescription())
                .filepath(file.getFilepath())
                .price(file.getPrice())
                .owneremail(file.getOwneremail())
                .createdDate(file.getCreatedDate())
                .filename(file.getFilename())
                .build();

    }
}
