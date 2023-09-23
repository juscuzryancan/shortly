package com.juscuzryancan.dao;

import com.juscuzryancan.entity.ShortURL;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShortURLRepository extends  JpaRepository<ShortURL, String>{
}