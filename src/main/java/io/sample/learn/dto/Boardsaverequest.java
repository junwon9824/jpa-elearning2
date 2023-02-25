package io.sample.learn.dto;

import io.sample.learn.entity.Board;
import io.sample.learn.entity.BuyBoard;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.lang.management.MemoryUsage;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor
public class Boardsaverequest {

    private Long id;

    private String title;

    private String description;

    private String filepath;

    private Long price;
    private String email;

    private List<BuyBoard> members = new ArrayList<>();

    private MultipartFile [] multipartFile;
    public Boardsaverequest(Board file) {
        this.id = file.getId();
        this.title = file.getTitle();
        this.description = file.getDescription();
        this.filepath = file.getFilepath();
        this.price = file.getPrice();
        this.email = file.getOwneremail();
        this.members=file.getMembers();
    }

    public static Boardsaverequest toFilesaverequest(Board file ) {
        return new Boardsaverequest(file );
    }

    public Board toEntity() {
        return Board.builder()
                .title(this.title)
                .owneremail(this.email)
                .price(this.price)
                .description(this.description)
                .members(this.members)
                .build();
    }



}
