package io.sample.learn.service;

//import io.sample.learn.dto.userdto;

//import io.sample.learn.entity.Role;

import io.sample.learn.dto.*;
import io.sample.learn.dto.filesaverequest;
import io.sample.learn.dto.addpointrequest;
import io.sample.learn.entity.file;
import io.sample.learn.entity.Buyfile;
import io.sample.learn.entity.Member;
//import io.sample.learn.repository.RoleRepository;
import io.sample.learn.repository.fileRepository;
import io.sample.learn.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;


@Service
@Transactional

@RequiredArgsConstructor
public class UserService {
    private final MemberRepository memberRepository;
    private final fileRepository fileRepository;

    public List<showboughtfilesresponse> showboughtfiles(String email) {
        Member member = memberRepository.findByemail(email);

        List<Buyfile> list = member.getBoughtfiles();

        return list.stream()
                .map(buyfile -> showboughtfilesresponse.from( buyfile))
                .collect(Collectors.toList());


    }

    public String addpoint(addpointrequest addpointrequest2) {
        Member member = memberRepository.findByemail(addpointrequest2.getEmail());

        member.setPoint(member.getPoint() + addpointrequest2.getPoint());

        return member.getAccount() + "님의 잔액은" + member.getPoint() + " 입니다";
    }

    public List<Allfilesresponse> showallfiles() {
        return fileRepository.findAll().stream()
                .map(file -> {
                    Allfilesresponse allfilesresponse = Allfilesresponse.from(file);
                    File[] getFile = allfilesresponse.getFile();
                    allfilesresponse.setFile(getFile);

                    return allfilesresponse;
                })
                .collect(Collectors.toList());

    }


}