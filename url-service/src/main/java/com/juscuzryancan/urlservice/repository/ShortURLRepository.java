package com.juscuzryancan.urlservice.repository;

import com.juscuzryancan.urlservice.model.ShortURL;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShortURLRepository extends JpaRepository<ShortURL, String> {
    ShortURL findByOriginalLink(String originalLink);
    ShortURL findByShortLink(String shortLink);
}
