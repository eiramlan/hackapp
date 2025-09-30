# HackApp - SQL Injection Learning Lab

This repository contains a small Spring Boot application intentionally designed for classroom demonstration and hands-on exercises related to secure coding practices (specifically authentication and SQL injection awareness).

Important safety note
- This project is a local, isolated learning lab. Do not deploy it to public or production environments.
- The repository intentionally contains insecure code paths so students can learn how to find and fix issues. The goal is defensive: teach how to remediate vulnerabilities.

What this repo contains
- A simple Spring Boot app (Java 17, Spring Boot 3.x) with a minimal REST API in `src/main/java/com/example/hackapp`.
- A `User` entity, `UserRepository` and `UserController` demonstrating a basic register/login flow.
- `application.properties` is configured to use an in-memory H2 database and enable the H2 console for development.
- A backup/fixed example is included: `UserRepository.java.bak` (inspect this as the recommended fix during exercises).

Learning objectives
- Understand how ORMs and parameterized queries affect SQL injection risk.
- Identify insecure patterns (plain-text password storage, insufficient authentication control, unsafe console exposure).
- Implement safe fixes: password hashing (bcrypt), proper authentication (Spring Security), and limiting dev-only features.

Run locally (safe development steps)

1. Build the project

   ```bash
   ./mvnw clean package -DskipTests
   ```

2. Run the app

   ```bash
   ./mvnw spring-boot:run
   # or
   java -jar target/hackapp-0.0.1-SNAPSHOT.jar
   ```

3. Open the H2 console (dev only)

   - URL: http://localhost:8080/h2-console
   - JDBC URL: `jdbc:h2:mem:testdb`
   - User: `sa`
   - Password: `password`

What to inspect (guided exercise)
- `src/main/java/com/example/hackapp/UserController.java` — login and registration endpoints.
- `src/main/java/com/example/hackapp/UserRepository.java` — repository interface used by the controller.
- `src/main/resources/application.properties` — DB configuration and H2 console setting.

Suggested classroom exercises (no exploit instructions included)
1. Review the login flow and explain why the repository lookup uses a bind parameter (see Hibernate logs showing `?` placeholders) and why that prevents string-based SQL injection in this code path.
2. Identify why storing passwords in plaintext is dangerous. Implement password hashing using `BCryptPasswordEncoder` and update registration/login logic to use it.
3. Remove or restrict the H2 console for non-development profiles.
4. Audit the code for any direct/native SQL usage or string-concatenated queries; replace any such code with parameterized queries or JPA methods.
5. Optionally add Spring Security to protect endpoints and demonstrate the difference between ad-hoc checks and framework-managed authentication.

Further reading and safe practice resources
- OWASP Top 10 — https://owasp.org/www-project-top-ten/
- OWASP SQL Injection Cheat Sheet — https://cheatsheetseries.owasp.org/cheatsheets/SQL_Injection_Prevention_Cheat_Sheet.html
- OWASP Juice Shop / WebGoat — intentionally vulnerable apps for safe, isolated learning.

Class materials and instructor notes
- The instructor should not publish exploit payloads in shared or public course repos. For demo purposes, use controlled, in-class demonstrations and point students to purpose-built vulnerable labs when hands-on practice with attacks is required.

License & disclaimers
- This repository is for educational use only. The author and institution are not responsible for misuse.
