package io.sample.learn.controller;


import io.sample.learn.dto.Boardbuyrequest;
import io.sample.learn.dto.Boardsaverequest;
import io.sample.learn.service.SignService;
import lombok.RequiredArgsConstructor;
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


    @PostMapping(value = "/user/buy/file")
    public ResponseEntity<String> buy(@RequestBody Boardbuyrequest boardbuyrequest) {
        return new ResponseEntity<>(boardService.buy(boardbuyrequest), HttpStatus.OK);
    }


//
//    @GetMapping(value = "/user/searchfiles")
//    public ResponseEntity<Page<File>> searchfiles(@PageableDefault(sort = "id",
//            direction = Sort.Direction.DESC) Pageable pageable, @RequestParam String searchkeyword) {
//
//        Page<File> searchlist = null;
//
//        if (searchkeyword == null)
//            System.out.println("plese put keyword");
//
//        else
////            searchlist = fileService.searchtext(searchkeyword, pageable);
//
//        byte[] downloadImage =  fileService.searchtext(searchkeyword, pageable);
//
//        return ResponseEntity.status(HttpStatus.OK)
//                .contentType(MediaType.valueOf("image/png"))
//                .body(downloadImage);
//
////        return new ResponseEntity<>(searchlist, HttpStatus.OK);
//    }

    String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";


    @GetMapping("/user/fileSystem/{fileName}")
    public ResponseEntity<?> downloadImageToFileSystem(@PathVariable("fileName") String fileName) throws IOException {
        byte[] downloadImage = boardService.downloadImageFromFileSystem(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(downloadImage);
    }

}
