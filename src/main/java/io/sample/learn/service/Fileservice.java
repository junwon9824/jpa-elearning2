package io.sample.learn.service;

import io.sample.learn.dto.Allfilesresponse;
import io.sample.learn.dto.filebuyrequest;
import io.sample.learn.dto.filesaverequest;
import io.sample.learn.entity.Member;
import io.sample.learn.repository.BuyFileRepository;
import io.sample.learn.repository.FileRepository;
import io.sample.learn.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import io.sample.learn.entity.*;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional

public class Fileservice {
    private final FileRepository fileRepository;
    private final MemberRepository memberRepository;
    private final BuyFileRepository buyfileRepository;

    public String write(filesaverequest filesaverequest) throws Exception {

        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";

        UUID uuid = UUID.randomUUID();
        MultipartFile[] file = filesaverequest.getMultipartFile();

        System.out.println("title" + filesaverequest.getTitle());
        System.out.println("email owner" + filesaverequest.getEmail());

        String[] fileName = new String[file.length];
        File[] saveFile = new File[file.length];

        for (int i = 0; i < file.length; i++) {
            fileName[i] = uuid + "_" + file[i].getOriginalFilename();
            saveFile[i] = new File(projectPath, fileName[i]);

            file[i].transferTo(saveFile[i]);
        }


        Member member = memberRepository.findByemail(filesaverequest.getEmail());

        member.setMadefiles(fileName);

       io.sample.learn.entity.File file2 = (io.sample.learn.entity.File.builder()
                .description(filesaverequest.getDescription())
                .filepath(filesaverequest.getFilepath())
                .title(filesaverequest.getTitle())
                .price(filesaverequest.getPrice())
                .owneremail(member.getEmail())
                .filename(fileName)
                .filepath("/files/" + fileName)
                .build());

        fileRepository.save(file2);

        return "file uploaded successfully! filetitle : " + filesaverequest.getTitle();
    }

    public String buy(filebuyrequest filebuyrequest) {

       io.sample.learn.entity.File file = fileRepository.findBytitle(filebuyrequest.getFiletitle());

        Member member = memberRepository.findByemail(filebuyrequest.getEmail());


        if (member.getEmail().equals(file.getOwneremail())) {
            return "you can't buy your own file";
        } else {

            System.out.println("member money" + member.getPoint());
            System.out.println("file money" + file.getPrice());

            if (file.getPrice() > member.getPoint()) {
                throw new IllegalArgumentException("not enough money..");
            }

            Buyfile buyfile = Buyfile.builder()
                    .file(file)
                    .member(member)
                    .build();

            Optional<Buyfile> tmp = buyfileRepository.findByMemberAndFile(member, file); ///////////////////////

            if (tmp.isEmpty()) {
                buyfileRepository.save(buyfile);
                member.setPoint(member.getPoint() - file.getPrice());
                return member.getAccount() + " 님이" + file.getTitle() + " 을(를) 성공적으로 구매 하였습니다.";
            }

            return "you already bought this file";
        }
    }


    public List<Allfilesresponse> searchtext(String searchkeyword, Pageable pageable) {
        List<Allfilesresponse> plist = fileRepository.findBytitleContaining(searchkeyword).stream()
                .map(file -> Allfilesresponse.from(file))
                .collect(Collectors.toList());
//
//       return fileRepository.findBytitleContaining(searchkeyword, pageable).stream()
//                .map(file -> Allfilesresponse.from(file))
//                .collect(Collectors.toList());
        return plist;
    }

//
//    public byte[] downloadFromFileSystem(String title, String filename) throws IOException {
//        io.sample.learn.entity.File file = fileRepository.findBytitle(title);
//
//        String[] name = file.getFilenames();
//        String res = null;
//
//        for (int i = 0; i < name.length; i++) {
//            if (filename.equals(name[i])) {
//                res = name[i];
//            }
//        }
//
//        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files\\" + res;
//
//        System.out.println("download filePath: {}" + projectPath);
//
//        return Files.readAllBytes(new File(projectPath).toPath());
//    }

    public String  deletefile(String title ){
        io.sample.learn.entity.File file=fileRepository.findBytitle(title);

        fileRepository.delete(file);

        return file.getTitle()+ " has been deleted";
    }

}
