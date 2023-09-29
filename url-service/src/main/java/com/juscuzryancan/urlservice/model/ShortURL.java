package com.juscuzryancan.urlservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Entity
@Data
@Table(name = "shorturls")
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ShortURL {
    @Id
    @Column(length = 7)
    private String shortLink;
    private int expiration;
    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime createdAt;
    private String originalLink;
    private int clicks;
}
