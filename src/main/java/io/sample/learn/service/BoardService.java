package io.sample.learn.service;

import io.sample.learn.dto.Boardbuyrequest;
import io.sample.learn.dto.Boardsaverequest;
import io.sample.learn.entity.Member;
import io.sample.learn.repository.BoardRepository;
import io.sample.learn.repository.BuyBoardRepository;
import io.sample.learn.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
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
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional

public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    private final BuyBoardRepository buyBoardRepository;

//
//    @Transactional
//    public String save(Filesaverequest filesaverequest) {
//
//        Member member = memberRepository.findByemail(filesaverequest.getEmail());
//
//        System.out.println("email owner" + filesaverequest.getEmail());
//        System.out.println("meber info" + member.getNickname());
//
//
//        File file = (File.builder()
//                .description(filesaverequest.getDescription())
//                .filepath(filesaverequest.getFilepath())
//                .title(filesaverequest.getTitle())
//                .price(filesaverequest.getPrice())
//                .owneremail(member.getEmail())
//                .build());
//
//        file.setimgs(Collections.singletonList(Image.builder().imagename(filesaverequest.getImgname()).build()));
//
//
//        fileRepository.save(file);
//        return "file uploaded successfully! filePath : " + filesaverequest.getTitle();
//
//
//    }

    public String write(Boardsaverequest boardsaverequest) throws Exception {

        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";

        UUID uuid = UUID.randomUUID();
        MultipartFile file = boardsaverequest.getMultipartFile();

        System.out.println("title" + boardsaverequest.getTitle());
        System.out.println("email owner" + boardsaverequest.getEmail());


        String fileName = uuid + "_" + file.getOriginalFilename();
        File saveFile = new File(projectPath, fileName);

        file.transferTo(saveFile);


        Member member = memberRepository.findByemail(boardsaverequest.getEmail());


        Board board = (Board.builder()
                .description(boardsaverequest.getDescription())
                .filepath(boardsaverequest.getFilepath())
                .title(boardsaverequest.getTitle())
                .price(boardsaverequest.getPrice())
                .owneremail(member.getEmail())
                .filename(fileName)
                .filepath("/files/" + fileName)
                .build());


        boardRepository.save(board);

        return "file uploaded successfully! filePath : " + boardsaverequest.getTitle();


    }

    public String buy(Boardbuyrequest boardbuyrequest) {

        Board board = boardRepository.findBytitle(boardbuyrequest.getFiletext());

        Member member = memberRepository.findByemail(boardbuyrequest.getEmail());


        System.out.println("member money" + member.getPoint());
        System.out.println("file money" + board.getPrice());


        if (board.getPrice() > member.getPoint()) {
            throw new IllegalArgumentException("잔액이 부족합니다..");

        }


        buyBoardRepository.save(BuyBoard.builder()
                .board(board)
                .member(member)

                .build());

        member.setPoint(member.getPoint() - board.getPrice());


        return member.getAccount() + " 님이" + board.getTitle() + " 을(를) 성공적으로 구매 하였습니다.";


    }


//
//
//    public Page<File> searchtext(String searchkeyword, Pageable pageable) {
//        Page<File> plist = fileRepository.findBytitleContaining(searchkeyword, pageable);
//
//        File file=plist.forEach( );
//
//        return Files.readAllBytes(new File(filePath).toPath());
//
//        return plist;
//
//    }

    public String   findimagename(String title) throws IOException {
         Board board=boardRepository.findBytitle(title);
        String imagename;
        imagename=board.getFilename();
        return  imagename;
    }


    public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
        Board board = boardRepository.findByfilename(fileName);

        String name = board.getFilename();
        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files\\"+name;

        System.out.println("download filePath: {}"+ projectPath);

        return Files.readAllBytes(new File(projectPath).toPath());
    }

}
