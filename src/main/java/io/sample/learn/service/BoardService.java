package io.sample.learn.service;

import io.sample.learn.dto.Filebuyrequest;
import io.sample.learn.dto.Boardsaverequest;
import io.sample.learn.entity.Member;
import io.sample.learn.repository.BoardRepository;
import io.sample.learn.repository.BuyFilesRepository;
 import io.sample.learn.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import io.sample.learn.entity.*;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Transactional

public class FileService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    private final BuyFilesRepository buyFilesRepository;

    @Value("${file.dir}")//application.yml 파일에 있는 file.dir의 내용을 가져옴
    private String fileDir;

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

    public void write(Boardsaverequest boardsaverequest, MultipartFile multipartFile) {

        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";

        Member member = memberRepository.findByemail(boardsaverequest.getEmail());

        System.out.println("email owner" + boardsaverequest.getEmail());
        System.out.println("meber info" + member.getNickname());


        Board board = (Board.builder()
                .description(boardsaverequest.getDescription())
                .filepath(boardsaverequest.getFilepath())
                .title(boardsaverequest.getTitle())
                .price(boardsaverequest.getPrice())
                .owneremail(member.getEmail())
                .build());


        boardRepository.save(board);

        return "file uploaded successfully! filePath : " + boardsaverequest.getTitle();


    }

    public String buy(Filebuyrequest filebuyrequest) {

        Board board = boardRepository.findBytitle(filebuyrequest.getFiletext());

        Member member = memberRepository.findByemail(filebuyrequest.getEmail());


        System.out.println("member money" + member.getPoint());
        System.out.println("file money" + board.getPrice());


        if (board.getPrice() > member.getPoint()) {
            throw new IllegalArgumentException("잔액이 부족합니다..");

        }


        buyFilesRepository.save(BuyFiles.builder()
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


}
