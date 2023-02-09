package io.sample.learn.dto;

import io.sample.learn.entity.File;
import io.sample.learn.entity.Member;
import lombok.*;

@Builder
@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor
public class Allfilesresponse {

    private Long id;

    private String text;

    private String description;

    private String filepath;

    private Long price;
    private String owneremail;
//
//    public Allfilesresponse(File file)
//    {
//        this.id=file.getId();
//        this.text=file.getText();
//        this.description=file.getDescription();
//        this.filepath=file.getFilepath();
//        this.price=file.getPrice();
//    }

    public static Allfilesresponse from (File file)
    {
        return   Allfilesresponse.builder()
                .id(file.getId())
                .text(file.getText())
                .description(file.getDescription())
                .filepath(file.getFilepath())
                .price(file.getPrice())
                .owneremail(file.getOwneremail())
                .build();

    }
}
