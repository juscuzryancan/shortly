package com.juscuzryancan.redirectservice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/")
public class ReadController {

    @GetMapping("/{shortLink}")
    public RedirectView getRedirect(@RequestParam String shortLink) {
//        if (/*shortlink is not found */) {
//            //throw not found and return 404
//            return ResponseEntity.notFound().build();
//        }

        return new RedirectView("https://www.google.com");
    }

    @ExceptionHandler(ShortURLNotFoundException.class)
    ResponseEntity<String> handleShortURLNotFoundException(ShortURLNotFoundException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

}
