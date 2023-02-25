package io.sample.learn.dto;

import io.sample.learn.entity.BuyFiles;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor
public class  Filesaverequest {

    private Long id;

    private String title;

    private String description;

    private String filepath;

    private Long price;
    private String email;

    private List<BuyFiles> members = new ArrayList<>();


    public Filesaverequest(File file) {
        this.id = file.getId();
        this.title = file.getTitle();
        this.description = file.getDescription();
        this.filepath = file.getFilepath();
        this.price = file.getPrice();
        this.email = file.getOwneremail();
        this.members=file.getMembers();
    }

    public static Filesaverequest toFilesaverequest(File file ) {
        return new Filesaverequest(file );
    }

    public File toEntity() {
        return File.builder()
                .title(this.title)
                .owneremail(this.email)
                .price(this.price)
                .description(this.description)
                .members(this.members)
                .build();
    }



}
