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
```json
{
  "shortCode": "yeBDAZ",
  "originalUrl": "https://www.wikipedia.org"
}
