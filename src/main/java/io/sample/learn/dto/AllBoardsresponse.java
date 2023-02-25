package io.sample.learn.dto;

import io.sample.learn.entity.Board;
import lombok.*;

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

    private Long price;
    private String owneremail;
    private LocalDateTime createdDate;

//
//    public Allfilesresponse(File file)
//    {
//        this.id=file.getId();
//        this.text=file.getText();
//        this.description=file.getDescription();
//        this.filepath=file.getFilepath();
//        this.price=file.getPrice();
//    }

    public static Allfilesresponse from(Board board) {
        return Allfilesresponse.builder()
                .id(board.getId())
                .title(board.getTitle())
                .description(board.getDescription())
                .filepath(board.getFilepath())
                .price(board.getPrice())
                .owneremail(board.getOwneremail())
                .createdDate(board.getCreatedDate())
                .build();

    }
}
