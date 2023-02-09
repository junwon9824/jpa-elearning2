package io.sample.learn.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.support.MergedBeanDefinitionPostProcessor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor @Builder
@AllArgsConstructor
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    private String text;

    private String description;

    private Long price;

    private String filepath;



    private String owneremail;


    @OneToMany(mappedBy = "file")
    private List<BuyFiles> members = new ArrayList<>();



    public void setMembers(List<BuyFiles> members) {
        this.members = members;
        members.forEach(o -> o.setFile(this));
    }


}
