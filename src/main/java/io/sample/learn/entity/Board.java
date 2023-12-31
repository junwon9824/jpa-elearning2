package io.sample.learn.entity;

import io.sample.learn.BaseTimeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType .IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String []filename;
    private Long price;
    private String filepath;
    private String owneremail;

    public String[] getFilenames() {

    }

    public void addcustomer(BuyBoard buyBoard) {
    }

    // 기타 필요한 필드들 추가

    // 생성자, Getter, Setter, 기타 메서드들

}
