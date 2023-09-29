package com.juscuzryancan.urlservice.controller;

import com.juscuzryancan.urlservice.dto.ShortURLRequest;
import com.juscuzryancan.urlservice.service.URLShortenerService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/api/v1")
@EnableCaching
public class ShortURLController {

    private URLShortenerService shortenerService;

    public ShortURLController(URLShortenerService shortenerService) {
        this.shortenerService = shortenerService;
    }

    @GetMapping("/shortURL/{shortURL}")
    @Cacheable(key = "#shortURL", value = "shortURL")
    public RedirectView redirect(@PathVariable("shortURL") String shortURL) {
        String URL = shortenerService.getOriginalLink(shortURL);
        return new RedirectView(URL);
    }

    @PostMapping("/data/shorten")
    public ResponseEntity<String> createShortURL(@RequestBody ShortURLRequest request) throws NoSuchAlgorithmException {
        String shortURL = shortenerService.createShortURL(request.getURL());
        return ResponseEntity.ok(shortURL);
    }
}
