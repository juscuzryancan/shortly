package com.juscuzryancan.redirectservice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/api/v1/")
public class RedirectController {

    @GetMapping("/{shortLink}")
    public RedirectView getRedirect(@PathVariable("shortLink") String shortLink) {
        return new RedirectView("https://www.google.com");
    }

    @ExceptionHandler(ShortURLNotFoundException.class)
    ResponseEntity<String> handleShortURLNotFoundException(ShortURLNotFoundException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

}
