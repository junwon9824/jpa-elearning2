package io.sample.learn.controller;

 import io.sample.learn.dto.Allfilesresponse;
 import io.sample.learn.dto.Filesaverequest;
 import io.sample.learn.dto.addpointrequest;
 import io.sample.learn.service.UserService;

import lombok.RequiredArgsConstructor;
 import org.springframework.http.HttpStatus;
 import org.springframework.http.ResponseEntity;
 import org.springframework.security.core.parameters.P;
 import org.springframework.security.crypto.password.PasswordEncoder;
 import org.springframework.web.bind.annotation.*;

 import java.util.Collections;
 import java.util.*;
import org.springframework.ui.Model;

@RequiredArgsConstructor


@RestController
public class usercontroller {

    private final UserService userService;

    @GetMapping(value = "/user/boughtfiles")
    public ResponseEntity<List< Filesaverequest> > showboughtfiles(@RequestParam String email)
    {
        return new ResponseEntity<>( userService.showboughtfiles(email),HttpStatus.OK);
    }

    @PutMapping(value = "/user/addpoint")
    public ResponseEntity<String> addpoint(@RequestBody addpointrequest addpointrequest2){
        return new ResponseEntity<>(userService.addpoint(addpointrequest2), HttpStatus.OK);
    }

    @GetMapping( value = "/user/showallfiles")
    public ResponseEntity<List<Allfilesresponse>> showallfiles(){
        return new ResponseEntity<>(userService.showallfiles(),HttpStatus.OK);

    }


}

