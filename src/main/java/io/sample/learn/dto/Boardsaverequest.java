package io.sample.learn.dto;

import io.sample.learn.entity.File;
 import io.sample.learn.entity.Buyfile;
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
public class filesaverequest {

    private Long id;

    private String title;

    private String description;

    private String filepath;

    private Long price;
    private String email;

    private List<Buyfile> members = new ArrayList<>();

    private MultipartFile [] multipartFile;
    public filesaverequest(File file) {
        this.id = file.getId();
        this.title = file.getTitle();
        this.description = file.getDescription();
        this.filepath = file.getFilepath();
        this.price = file.getPrice();
        this.email = file.getOwneremail();
        this.members=file.getCustomers();
    }

    public static filesaverequest toFilesaverequest(File file ) {
        return new filesaverequest(file );
    }

    public File toEntity() {
        return File.builder()
                .title(this.title)
                .owneremail(this.email)
                .price(this.price)
                .description(this.description)
                .customers(this.members)
                .build();
    }



}
