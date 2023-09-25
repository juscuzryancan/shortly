package com.juscuzryancan.readapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/read")
public class ReadController {

    @GetMapping("/")
    public String example() {
        return "Hello";
    }

}
