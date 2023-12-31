package io.sample.learn.entity;

import io.sample.learn.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class File extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType .IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String []filename;
    private Long price;
    private String filepath;
    private String owneremail;

    @OneToMany(mappedBy = "file")
    private List<Buyfile> customers;


    public void addcustomer(Buyfile buyfile) {

    }

    // 기타 필요한 필드들 추가

    // 생성자, Getter, Setter, 기타 메서드들

}
