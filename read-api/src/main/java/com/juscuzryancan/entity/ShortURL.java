package com.juscuzryancan.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@Table(name = "shorturls")
@NoArgsConstructor
@AllArgsConstructor
public class ShortURL {
    @Id
    @Column(name = "shortlink")
    private String shortlink;
    @Column(name = "expiration_length_in_minutes")
    private int expiration;
    @Column
    private Date createdAt;
    @Column(name = "paste_path")
    private String pastePath;
}
