package com.juscuzryancan.redirectservice;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ShortURLRepository extends  JpaRepository<ShortURL, String>{
}