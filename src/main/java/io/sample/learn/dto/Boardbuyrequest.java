package io.sample.learn.dto;

 import io.sample.learn.entity.BuyBoard;
 import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Boardbuyrequest {

    private Long id;

    private String email;
    private String filetitle;




}
