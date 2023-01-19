package io.sample.learn.controller;

//import io.sample.learn.dto.userdto;

import io.sample.learn.dto.userSaveRequestdto;
import io.sample.learn.dto.userSaveResponsedto;
import io.sample.learn.dto.userUpdateRequestdto;
import io.sample.learn.entity.User;
import io.sample.learn.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor


@RestController
public class usercontroller {
    private final UserService userService;

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.findById(id);
    }

//
//    @GetMapping("/users")
//    public List<User> findall(){
//        return userService.findall();
//
//    }


//
//    @PostMapping("/user")
//    public Long save(@RequestParam String name, @RequestParam int age) {
//        return userService.save(name, age);
//    }
////
//    @PostMapping("/users")
//    public Long saves(User user) {
//        return userService.save2(user);
//    }

    @PostMapping("/user")
    public Long save(userSaveResponsedto dto) {
        return userService.save(dto);
    }

    @PutMapping("/user/{id}")
    public Long update(@PathVariable Long id, userUpdateRequestdto dto)
    {
        return userService.update(id,dto);

    }



}

