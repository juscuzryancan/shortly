package com.juscuzryancan.urlservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Data
@Table(name = "shorturls")
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ShortURL {
    @Id
    private String shortLink;
    private int expiration;
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp createdAt;
    private String originalLink;
}
