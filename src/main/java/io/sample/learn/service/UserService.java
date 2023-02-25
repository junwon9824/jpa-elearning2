package io.sample.learn.service;

//import io.sample.learn.dto.userdto;

//import io.sample.learn.entity.Role;

import io.sample.learn.dto.AllBoardsresponse;
import io.sample.learn.dto.Boardsaverequest;
import io.sample.learn.dto.addpointrequest;
import io.sample.learn.entity.Board;
import io.sample.learn.entity.BuyBoard;
import io.sample.learn.entity.Member;
//import io.sample.learn.repository.RoleRepository;
import io.sample.learn.repository.BoardRepository;
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
    private final BoardRepository boardRepository;

    public List<Boardsaverequest> showboughtfiles(String email) {
        Member member = memberRepository.findByemail(email);

        List<BuyBoard> list = member.getFiles();

        return list.stream()
                .map(buyFiles -> Boardsaverequest.toFilesaverequest(buyFiles.getBoard()))
                .collect(Collectors.toList());


    }

    public String addpoint(addpointrequest addpointrequest2) {
        Member member = memberRepository.findByemail(addpointrequest2.getEmail());

        member.setPoint(member.getPoint() + addpointrequest2.getPoint());

        return member.getAccount() + "님의 잔액은" + member.getPoint() + " 입니다";
    }

    public List<AllBoardsresponse> showallfiles() {
        return boardRepository.findAll().stream()
                .map(file -> {
                    AllBoardsresponse allBoardsresponse = AllBoardsresponse.from(file);
                    File getFile = allBoardsresponse.getFile();
                    allBoardsresponse.setFile(getFile);

                    return allBoardsresponse;
                })
                .collect(Collectors.toList());

    }


}