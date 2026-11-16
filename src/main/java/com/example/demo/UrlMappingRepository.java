package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UrlMappingRepository extends JpaRepository<UrlMapping, Long> {
    // Spring generiert die SQL-Abfrage dafür automatisch!
    Optional<UrlMapping> findByShortCode(String shortCode);
}