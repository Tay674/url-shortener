package com.example.demo;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.net.URI;
import java.util.Map;
import java.util.Optional;

@RestController
public class UrlController {

    private final UrlMappingRepository repository;

    public UrlController(UrlMappingRepository repository) {
        this.repository = repository;
    }

    @RequestMapping("/api/shorten")
    public Map<String, String> shorten(@RequestParam String url) {
        String code = generateShortCode();
        UrlMapping mapping = new UrlMapping(url, code);
        repository.save(mapping);
        return Map.of("shortCode", code, "originalUrl", url);
    }

    @GetMapping("/{code:[a-zA-Z0-9]{6}}")
    public ResponseEntity<Void> redirect(@PathVariable String code) {
        Optional<UrlMapping> optionalMapping = repository.findByShortCode(code);
        
        if (optionalMapping.isEmpty()) {
            return ResponseEntity.notFound().build(); 
        }
        
        UrlMapping mapping = optionalMapping.get();
        
        // NEU: Klick hochzählen und in der Datenbank speichern
        mapping.setClickCount(mapping.getClickCount() + 1);
        repository.save(mapping);
        
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(mapping.getOriginalUrl()))
                .build();
    }

    // NEU: Ein Endpunkt für die Statistik
    @GetMapping("/api/stats/{code}")
    public ResponseEntity<?> getStats(@PathVariable String code) {
        Optional<UrlMapping> optionalMapping = repository.findByShortCode(code);
        
        if (optionalMapping.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        UrlMapping mapping = optionalMapping.get();
        
        return ResponseEntity.ok(Map.of(
            "shortCode", mapping.getShortCode(),
            "originalUrl", mapping.getOriginalUrl(),
            "clickCount", mapping.getClickCount()
        ));
    }

    private String generateShortCode() {
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder shortCode = new StringBuilder();
        java.util.Random random = new java.util.Random();
        for (int i = 0; i < 6; i++) {
            int index = random.nextInt(characters.length());
            shortCode.append(characters.charAt(index));
        }
        return shortCode.toString();
    }
}