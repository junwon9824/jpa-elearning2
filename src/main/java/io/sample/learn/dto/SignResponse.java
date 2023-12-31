package io.sample.learn.dto;

import io.sample.learn.entity.Roles;
import io.sample.learn.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignResponse {

    private Long id;

    private String account;

    private String nickname;

    private String name;

    private String email;

    private List<Roles> roles = new ArrayList<>();

//    private String token;
    private Long point;

    private String[] madefiles;

    public SignResponse(Member member) {
        this.id = member.getId();
        this.account = member.getAccount();
        this.nickname = member.getNickname();
        this.name = member.getName();
        this.email = member.getEmail();
        this.roles = member.getRoles();
        this.point=member.getPoint();
        this.madefiles=member.getMadefiles();
    }
}
