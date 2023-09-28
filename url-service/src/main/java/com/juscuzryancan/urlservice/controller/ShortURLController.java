package com.juscuzryancan.urlservice.controller;

import com.juscuzryancan.urlservice.dto.ShortURLRequest;
import com.juscuzryancan.urlservice.service.URLShortenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class ShortURLController {

    private URLShortenerService shortenerService;

    public ShortURLController(URLShortenerService shortenerService) {
        this.shortenerService = shortenerService;
    }

    @GetMapping("/{shortURL}")
    public RedirectView redirect(@PathVariable("shortURL") String shortURL) {
        String URL = shortenerService.getOriginalLink();
        return new RedirectView(URL);
    }

    @PostMapping("/api/shorturl")
    public ResponseEntity<String> createShortURL(@RequestBody ShortURLRequest request) {
        try {
            String shortURL = shortenerService.createShortURL(request.getURL());
            return ResponseEntity.ok(shortURL);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
