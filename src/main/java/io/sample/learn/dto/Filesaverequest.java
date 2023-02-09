package io.sample.learn.dto;

 import io.sample.learn.entity.File;
 import io.sample.learn.entity.Member;
 import lombok.*;

@Builder
@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor
public class Filesaverequest {

    private Long id;

    private String text;

    private String description;

    private String filepath;

    private Long price;
    private String email;

    public Filesaverequest(File file)
    {
        this.id=file.getId();
        this.text=file.getText();
        this.description=file.getDescription();
        this.filepath=file.getFilepath();
        this.price=file.getPrice();
        this.email= file.getOwneremail();
    }

    public static Filesaverequest toFilesaverequest (File file)
    {
        return new Filesaverequest(file);
    }
}
