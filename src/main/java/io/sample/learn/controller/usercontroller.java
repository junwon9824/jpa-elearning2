package io.sample.learn.controller;

import io.sample.learn.dto.AllBoardsresponse;
import io.sample.learn.dto.Boardsaverequest;
import io.sample.learn.dto.addpointrequest;
import io.sample.learn.dto.showboughtfilesresponse;
import io.sample.learn.service.BoardService;
import io.sample.learn.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequiredArgsConstructor


@RestController
public class usercontroller {

    private final UserService userService;

    private final BoardService boardService;

    @GetMapping(value = "/user/boughtfiles")
    public ResponseEntity<List<showboughtfilesresponse>> showboughtfiles(@RequestParam String email) {
        return new ResponseEntity<>(userService.showboughtfiles(email), HttpStatus.OK);
    }

    @PutMapping(value = "/user/addpoint")
    public ResponseEntity<String> addpoint(@RequestBody addpointrequest addpointrequest2) {
        return new ResponseEntity<>(userService.addpoint(addpointrequest2), HttpStatus.OK);
    }
    @GetMapping(value = "/user/showallfiles")
    public ResponseEntity<List<AllBoardsresponse>> showallfiles() {
        return new ResponseEntity<>(userService.showallfiles(), HttpStatus.OK);

    }

}

