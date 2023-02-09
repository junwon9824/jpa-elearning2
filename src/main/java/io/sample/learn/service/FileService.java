package io.sample.learn.service;

import io.sample.learn.dto.Filebuyrequest;
import io.sample.learn.dto.Filesaverequest;
import io.sample.learn.entity.Member;
import io.sample.learn.repository.BuyFilesRepository;
import io.sample.learn.repository.FileRepository;
import io.sample.learn.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import io.sample.learn.entity.*;
import org.slf4j.Logger;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class FileService {
    private final FileRepository fileRepository;
    private final MemberRepository memberRepository;
    private final BuyFilesRepository buyFilesRepository;

    @Transactional
    public String save(Filesaverequest filesaverequest) {

        Member member=memberRepository.findByemail( filesaverequest.getEmail());

        System.out.println("email owner"+filesaverequest.getEmail());
        System.out.println("meber info"+member.getNickname());

        fileRepository.save(File.builder()
                .description(filesaverequest.getDescription())
                .filepath(filesaverequest.getFilepath())
                .text(filesaverequest.getText())
                .price(filesaverequest.getPrice())
                .owneremail(member.getEmail())
                .build());


        return filesaverequest.getText() + " 성공적으로 저장 하였습니다.";


    }


    public String buy(Filebuyrequest filebuyrequest) {

        File file = fileRepository.findBytext(filebuyrequest.getFiletext());

        Member member = memberRepository.findByemail(filebuyrequest.getEmail());


        System.out.println("member money" + member.getPoint());
        System.out.println("file money" + file.getPrice());


        if (file.getPrice() > member.getPoint()) {
            throw new IllegalArgumentException("잔액이 부족합니다..");

        }


//        BuyFiles buytfiles=new BuyFiles(member,file);
        buyFilesRepository.save(BuyFiles.builder()
                .file(file)
                .member(member)

                .build());

        member.setPoint(member.getPoint() - file.getPrice());

//        member.setRoles(Collections.singletonList(Roles.builder().name("ROLE_USER").build()));
//
//        Member.setFile(Collections.singletonList(buytfiles.builder()
//                .file(file)
//                .member()
//                .build()
//        ));
//
//

        return member.getAccount() + " 님이" + file.getText() + " 을(를) 성공적으로 구매 하였습니다.";


    }


}
