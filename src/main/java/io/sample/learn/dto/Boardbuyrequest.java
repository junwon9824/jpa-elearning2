package io.sample.learn.dto;

 import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Boardbuyrequest {

    private Long id;

    private String email;
    private String filetext;




}
