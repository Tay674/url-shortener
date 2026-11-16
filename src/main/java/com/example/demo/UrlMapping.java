package com.example.demo;

import jakarta.persistence.*;

@Entity
public class UrlMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 2048)
    private String originalUrl;

    @Column(unique = true, nullable = false)
    private String shortCode;

    // NEU: Der Klick-Zähler
    private Long clickCount = 0L;

    public UrlMapping() {}

    public UrlMapping(String originalUrl, String shortCode) {
        this.originalUrl = originalUrl;
        this.shortCode = shortCode;
        this.clickCount = 0L;
    }

    // Getter und Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getOriginalUrl() { return originalUrl; }
    public void setOriginalUrl(String originalUrl) { this.originalUrl = originalUrl; }
    public String getShortCode() { return shortCode; }
    public void setShortCode(String shortCode) { this.shortCode = shortCode; }
    public Long getClickCount() { return clickCount; }
    public void setClickCount(Long clickCount) { this.clickCount = clickCount; }
}