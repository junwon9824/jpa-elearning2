package io.sample.learn.entity;

import io.sample.learn.BaseTimeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String account;

    private String password;

    private String nickname;

    private String name;
    private Long point;

    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "members", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Builder.Default
    private List<Roles> roles = new ArrayList<>();


    @OneToMany(mappedBy = "member",cascade = CascadeType.ALL)
    private List<BuyBoard> boughtfiles = new ArrayList<>();



    private String[] madefiles ;


    public void setRoles(List<Roles> role) {
        this.roles = role;
        role.forEach(o -> o.setMember(this));
    }

//
//    public void setFile(List<BuyBoard> boughtfiles) {
//        this.boughtfiles = boughtfiles;
//    }


    public void setPassword(String pwd) {
        this.password = pwd;
    }

}