package io.sample.learn.controller;


import io.sample.learn.dto.AllBoardsresponse;
import io.sample.learn.dto.Boardbuyrequest;
import io.sample.learn.dto.Boardsaverequest;
import io.sample.learn.entity.Board;
import io.sample.learn.service.SignService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import io.sample.learn.service.BoardService;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final SignService signService;

    @PostMapping(value = "/user/save/file")
//    public ResponseEntity<String> save( Boardsaverequest boardsaverequest , MultipartFile multipartFile) throws Exception {
    public ResponseEntity<String> save(@ModelAttribute Boardsaverequest boardsaverequest) throws Exception {
        return new ResponseEntity<>(boardService.write(boardsaverequest), HttpStatus.OK);


    }


    @PutMapping(value = "/user/buy/board")
    public ResponseEntity<String> buy(@RequestBody Boardbuyrequest boardbuyrequest) {
        return new ResponseEntity<>(boardService.buy(boardbuyrequest), HttpStatus.OK);
    }


    @GetMapping(value = "/user/searchfiles")
    public ResponseEntity<List<AllBoardsresponse>> searchfiles(@PageableDefault(sort = "id",
            direction = Sort.Direction.DESC) Pageable pageable, @RequestParam String searchkeyword) {

        List<AllBoardsresponse> searchlist = null;

        if (searchkeyword == null)
            System.out.println("plese put keyword");

        else
            searchlist = boardService.searchtext(searchkeyword, pageable);


        return new ResponseEntity<>(searchlist, HttpStatus.OK);
    }

    @DeleteMapping(value = "user/deleteboard")
    public ResponseEntity<String> deleteboard(@RequestParam String title) {
       return new ResponseEntity<>( boardService.deleteboard(title),HttpStatus.OK) ;


    }


    String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";


    @GetMapping("/user/fileimg")
//    public ResponseEntity<?> downloadImageToFileSystem(@PathVariable("fileName") String fileName) throws IOException {
    public ResponseEntity<?> downloadImageToFileSystem(@RequestParam String title, @RequestParam String filename) throws IOException {
        byte[] downloadImage = boardService.downloadFromFileSystem(title, filename);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(downloadImage);
    }

    @GetMapping("/user/filevid")
    public ResponseEntity<?> downloadvidToFileSystem(@RequestParam String title, @RequestParam String filename) throws IOException {
        byte[] downloadImage = boardService.downloadFromFileSystem(title, filename);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("video/mp4"))
                .body(downloadImage);
    }

}
