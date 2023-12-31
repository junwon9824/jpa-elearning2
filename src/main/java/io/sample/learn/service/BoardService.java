package io.sample.learn.service;

import io.sample.learn.dto.AllBoardsresponse;
import io.sample.learn.dto.Boardbuyrequest;
import io.sample.learn.dto.Boardsaverequest;
import io.sample.learn.entity.Member;
import io.sample.learn.repository.BoardRepository;
import io.sample.learn.repository.BuyBoardRepository;
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

public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    private final BuyBoardRepository buyBoardRepository;

    public String write(Boardsaverequest boardsaverequest) throws Exception {

        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";

        UUID uuid = UUID.randomUUID();
        MultipartFile[] file = boardsaverequest.getMultipartFile();

        System.out.println("title" + boardsaverequest.getTitle());
        System.out.println("email owner" + boardsaverequest.getEmail());
        String[] fileName = new String[file.length];
        File[] saveFile = new File[file.length];

        for (int i = 0; i < file.length; i++) {
            fileName[i] = uuid + "_" + file[i].getOriginalFilename();
            saveFile[i] = new File(projectPath, fileName[i]);

            file[i].transferTo(saveFile[i]);

        }


        Member member = memberRepository.findByemail(boardsaverequest.getEmail());

        member.setMadefiles(fileName);

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

        return "file uploaded successfully! filetitle : " + boardsaverequest.getTitle();


    }

    public String buy(Boardbuyrequest boardbuyrequest) {

        Board board = boardRepository.findBytitle(boardbuyrequest.getFiletitle());

        Member member = memberRepository.findByemail(boardbuyrequest.getEmail());


        if (member.getEmail().equals(board.getOwneremail())) {
            return "you can't buy your own file";

        } else {


            System.out.println("member money" + member.getPoint());
            System.out.println("file money" + board.getPrice());


            if (board.getPrice() > member.getPoint()) {
                throw new IllegalArgumentException("not enough money..");

            }

            BuyBoard buyBoard = BuyBoard.builder()
                    .board(board)
                    .member(member)

                    .build();

            Optional<BuyBoard> tmp = buyBoardRepository.findBymemberAndBoard(member, board); ///////////////////////



            if (tmp.isEmpty()) {


                buyBoardRepository.save(buyBoard);

                member.setPoint(member.getPoint() - board.getPrice());

                board.addcustomer(buyBoard);

                return member.getAccount() + " 님이" + board.getTitle() + " 을(를) 성공적으로 구매 하였습니다.";

            }

            return "you already bought this file";

        }

    }


    public List<AllBoardsresponse> searchtext(String searchkeyword, Pageable pageable) {
        List<AllBoardsresponse> plist = boardRepository.findBytitleContaining(searchkeyword).stream()
                .map(board -> AllBoardsresponse.from(board))
                .collect(Collectors.toList());


//
//       return boardRepository.findBytitleContaining(searchkeyword, pageable).stream()
//                .map(board -> AllBoardsresponse.from(board))
//                .collect(Collectors.toList());

        return plist;

    }

    public byte[] downloadFromFileSystem(String title, String filename) throws IOException {
        Board board = boardRepository.findBytitle(title);

        String[] name = board.getFilenames();
        String res = null;

        for (int i = 0; i < name.length; i++) {
            if (filename.equals(name[i])) {
                res = name[i];
            }
        }

        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files\\" + res;

        System.out.println("download filePath: {}" + projectPath);

        return Files.readAllBytes(new File(projectPath).toPath());
    }

    public String  deleteboard(String title ){
        Board board=boardRepository.findBytitle(title);

        boardRepository.delete(board);

        return board.getTitle()+ " has been deleted";
    }

}
