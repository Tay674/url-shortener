# URL Shortener API

Ein voll funktionsfähiger URL-Shortener, entwickelt mit Java, Spring Boot und einer H2-Datenbank. Das Projekt ist vollständig dockerisiert und enthält ein modernes Web-Frontend.

## 🚀 Features
- **URL Kürzung:** Lange URLs werden in zufällige 6-stellige Base62-Codes umgewandelt.
- **Weiterleitung:** Aufruf des kurzen Links leitet per HTTP 302 auf die Original-URL weiter.
- **Persistenz:** Die Daten werden in einer H2-Datenbank gespeichert und überleben Server-Neustarts.
- **Klick-Statistik:** Jeder Aufruf eines kurzen Links wird gezählt und kann über eine API abgerufen werden.
- **Frontend:** Eine einfache, aber moderne HTML/JS-Oberfläche zur einfachen Nutzung.
- **Docker-Support:** Die Anwendung kann mit einem einzigen Befehl als Container gestartet werden.

## 🛠 Tech-Stack
- **Backend:** Java 21, Spring Boot (Web, Data JPA)
- **Datenbank:** H2 (File-based)
- **Frontend:** HTML5, CSS3, JavaScript (Vanilla)
- **Deployment:** Docker

## 📖 API-Endpunkte

### 1. URL kürzen
`GET /api/shorten?url={deine_lange_url}`

**Beispiel:**
`GET http://localhost:8080/api/shorten?url=https://www.wikipedia.org`

**Antwort:**
    {
      "shortCode": "yeBDAZ",
      "originalUrl": "https://www.wikipedia.org"
    }

### 2. Weiterleitung
`GET /{shortCode}`

Ruft man diese URL auf, wird man mit einem HTTP 302 Status auf die ursprüngliche URL weitergeleitet.

### 3. Statistik abrufen
`GET /api/stats/{shortCode}`

**Antwort:**
    {
      "shortCode": "yeBDAZ",
      "originalUrl": "https://www.wikipedia.org",
      "clickCount": 3
    }

## 💻 Lokale Ausführung

### Voraussetzungen
- Java 21 oder höher
- Maven
- Docker (optional)

### Starten mit Maven (Eclipse)
1. Projekt importieren.
2. `Run As -> Maven install` ausführen.
3. `Run As -> Spring Boot App` starten.
4. Öffne `http://localhost:8080` im Browser.

### Starten mit Docker
    # Image bauen
    docker build -t urlshortener-app .

    # Container starten (mit Volume für die Datenbank)
    docker run -d -p 8080:8080 -v "C:\Users\DEIN NAME\Projekt\urlshortener\data:/app/data" --name mein-shortener urlshortener-app

---
*Entwickelt von [Tay674](https://github.com/Tay674)*
