package io.sample.learn.controller;


import io.sample.learn.dto.Allfilesresponse;
import io.sample.learn.dto.filebuyrequest;
import io.sample.learn.dto.filesaverequest;
 import io.sample.learn.service.Fileservice;
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
 import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


@RestController
@RequiredArgsConstructor
public class fileController {
    private final Fileservice fileService;
    private final SignService signService;

    @PostMapping(value = "/user/save/file")
//    public ResponseEntity<String> save( filesaverequest filesaverequest , MultipartFile multipartFile) throws Exception {
    public ResponseEntity<String> save(@ModelAttribute filesaverequest filesaverequest) throws Exception {
        return new ResponseEntity<>(fileService.write(filesaverequest), HttpStatus.OK);


    }


    @PutMapping(value = "/user/buy/file")
    public ResponseEntity<String> buy(@RequestBody filebuyrequest filebuyrequest) {
        return new ResponseEntity<>(fileService.buy(filebuyrequest), HttpStatus.OK);
    }


    @GetMapping(value = "/user/searchfiles")
    public ResponseEntity<List<Allfilesresponse>> searchfiles(@PageableDefault(sort = "id",
            direction = Sort.Direction.DESC) Pageable pageable, @RequestParam String searchkeyword) {

        List<Allfilesresponse> searchlist = null;

        if (searchkeyword == null)
            System.out.println("plese put keyword");

        else
            searchlist = fileService.searchtext(searchkeyword, pageable);


        return new ResponseEntity<>(searchlist, HttpStatus.OK);
    }

    @DeleteMapping(value = "user/deletefile")
    public ResponseEntity<String> deletefile(@RequestParam String title) {
       return new ResponseEntity<>( fileService.deletefile(title),HttpStatus.OK) ;


    }


    String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";

//
//    @GetMapping("/user/fileimg")
////    public ResponseEntity<?> downloadImageToFileSystem(@PathVariable("fileName") String fileName) throws IOException {
//    public ResponseEntity<?> downloadImageToFileSystem(@RequestParam String title, @RequestParam String filename) throws IOException {
//        byte[] downloadImage = fileService.downloadFromFileSystem(title, filename);
//        return ResponseEntity.status(HttpStatus.OK)
//                .contentType(MediaType.valueOf("image/png"))
//                .body(downloadImage);
//    }
//
//    @GetMapping("/user/filevid")
//    public ResponseEntity<?> downloadvidToFileSystem(@RequestParam String title, @RequestParam String filename) throws IOException {
//        byte[] downloadImage = fileService.downloadFromFileSystem(title, filename);
//        return ResponseEntity.status(HttpStatus.OK)
//                .contentType(MediaType.valueOf("video/mp4"))
//                .body(downloadImage);
//    }

}
