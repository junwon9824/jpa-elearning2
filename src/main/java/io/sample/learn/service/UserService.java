package io.sample.learn.service;

//import io.sample.learn.dto.userdto;

//import io.sample.learn.entity.Role;

import io.sample.learn.dto.Allfilesresponse;
import io.sample.learn.dto.Filesaverequest;
import io.sample.learn.dto.addpointrequest;
import io.sample.learn.dto.alluserresponse;
import io.sample.learn.entity.BuyFiles;
import io.sample.learn.entity.File;
import io.sample.learn.entity.Member;
//import io.sample.learn.repository.RoleRepository;
import io.sample.learn.repository.FileRepository;
import io.sample.learn.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.*;


import java.util.*;
import java.util.stream.Collectors;



@Service
@Transactional

@RequiredArgsConstructor
public class UserService {
    private final MemberRepository memberRepository;
    private final FileRepository fileRepository;

    public List<Filesaverequest> showboughtfiles(String email) {
        Member member = memberRepository.findByemail(email);

        List<BuyFiles> list = member.getFiles();

        return list.stream()
                .map(buyFiles -> Filesaverequest.toFilesaverequest(buyFiles.getFile()))
                .collect(Collectors.toList());


    }

    public String  addpoint(addpointrequest addpointrequest2){
        Member member=memberRepository.findByemail(addpointrequest2.getEmail());

        member.setPoint(member.getPoint()+addpointrequest2.getPoint());

        return member.getAccount()+"님의 잔액은" +member.getPoint()+" 입니다";
    }

    public List<Allfilesresponse> showallfiles()
    {
        return fileRepository.findAll().stream()
                .map(file->Allfilesresponse.from(file))
                .collect(Collectors.toList());


    }
}