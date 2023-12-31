package io.sample.learn.dto;

 import io.sample.learn.entity.Buyfile;
 import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class filebuyrequest {

    private Long id;

    private String email;
    private String filetitle;




}
