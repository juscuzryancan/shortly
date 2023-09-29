package com.juscuzryancan.urlservice.service;

import com.juscuzryancan.urlservice.model.ShortURL;
import com.juscuzryancan.urlservice.repository.ShortURLRepository;
import io.seruco.encoding.base62.Base62;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
@Service
@Slf4j
public class URLShortenerService {
    private final ShortURLRepository repository;
    private final MessageDigest md = MessageDigest.getInstance("MD5");

    public URLShortenerService(ShortURLRepository repository) throws NoSuchAlgorithmException {
        this.repository = repository;
    }

    public String getOriginalLink(String shortLink) {
        ShortURL shortURL = repository.findByShortLink(shortLink);
        return shortURL.getOriginalLink();
    }

    /*
    TODO:
        add expiration check
     */
    public String createShortURL(String originalLink) throws NoSuchAlgorithmException {
        ShortURL url = repository.findByOriginalLink(originalLink);
        if (url != null) {
            log.info("Found Existing shortURL: {}", url.getOriginalLink());
            return url.getShortLink();
        }
        String shortLink = encode(originalLink);
        ShortURL shortURL = ShortURL.builder()
                .expiration(30)
                .createdAt(OffsetDateTime.now(ZoneOffset.UTC))
                .shortLink(shortLink)
                .originalLink(originalLink)
                .build();
        repository.save(shortURL);
        log.info("Short URL Created -- OriginalURL: {}", originalLink);
        return shortLink;
    }

    private String encode(String originalLink) throws NoSuchAlgorithmException {
        byte[] messageDigest =  md.digest(originalLink.getBytes());
        String shortLink;
        ShortURL dupe;
        do  {
            byte[] salt = new byte[16];
            SecureRandom.getInstanceStrong().nextBytes(salt);
            shortLink = new String(
                    Base62.createInstance().encode(messageDigest),
                    StandardCharsets.UTF_8
            ).substring(0, 7);
            dupe = repository.findByShortLink(shortLink);
        } while (dupe != null);
        return shortLink;
    }
}
