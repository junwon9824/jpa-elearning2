package io.sample.learn.controller;

import io.sample.learn.dto.Filebuyrequest;
import io.sample.learn.dto.Boardsaverequest;
import io.sample.learn.service.SignService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.sample.learn.service.FileService;

@RestController
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;
    private final SignService signService;

    @PostMapping(value = "/user/save/file")
    public ResponseEntity<?> save(@RequestBody Boardsaverequest boardsaverequest) {
//        return new ResponseEntity<>(fileService.save(filesaverequest), HttpStatus.OK);

        String uploadImage = fileService.save(boardsaverequest);

        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);
    }


    @PostMapping(value = "/user/buy/file")
    public ResponseEntity<String> buy(@RequestBody Filebuyrequest filebuyrequest) {
        return new ResponseEntity<>(fileService.buy(filebuyrequest), HttpStatus.OK);
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


}
