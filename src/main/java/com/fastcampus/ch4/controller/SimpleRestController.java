package com.fastcampus.ch4.controller;

import com.fastcampus.ch4.domain.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

//@RestController : @ResponseBody 생략 가능
@Controller
public class SimpleRestController {
    @GetMapping("/ajax")
    public String ajax() {
        return "ajax"; // ajax.jsp 뷰이름
    }

    @PostMapping("/send")
    @ResponseBody
    public Person test(@RequestBody Person p) {
        System.out.println("p = " + p);
        p.setName("ABC");
        p.setAge(p.getAge() + 10);

        return p;
    }

//    @PostMapping("/send")
////    @ResponseBody
//    public Person test2(@RequestBody Person p) {
//        System.out.println("p = " + p);
//        p.setName("ABC");
//        p.setAge(p.getAge() + 10);
//
//        return p;
//    }
}