package com.juscuzryancan.urlservice.service;

import com.juscuzryancan.urlservice.model.ShortURL;
import com.juscuzryancan.urlservice.repository.ShortURLRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import java.security.SecureRandom;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Service
@Slf4j
public class URLShortenerService {
    private ShortURLRepository repository;
    private MessageDigest md = MessageDigest.getInstance("MD5");

    public URLShortenerService(ShortURLRepository repository) throws NoSuchAlgorithmException {
        this.repository = repository;
    }

    public String getOriginalLink() {
        return "https://google.com";
    }

    public String createShortURL(String URL) throws NoSuchAlgorithmException {
        byte[] messageDigest =  md.digest(URL.getBytes());
        byte[] salt = new byte[16];
        SecureRandom.getInstanceStrong().nextBytes(salt);
        md.update(salt);
        String shortLink = Base64.getEncoder().encodeToString(messageDigest);
        ShortURL shortURL = ShortURL.builder()
                .shortLink(shortLink)
                .originalLink(URL)
                .build();
        repository.save(shortURL);
        log.info("Short URL Created -- OriginalURL: {}", URL);
        return shortLink;
    }
}
