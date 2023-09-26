package com.juscuzryancan.redirectservice;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "shorturls")
public class ShortURL {
    @Id
    private String shortLink;
    private int expiration;
    private Date createdAt;
    private String pastePath;
    private String originalLink;
}
