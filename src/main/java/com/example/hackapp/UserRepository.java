package com.example.hackapp;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * VULNERABLE demo implementation.
 * Use for in-class SQL injection demo only (local machine).
 */
@Repository
public class UserRepository {

    private final JdbcTemplate jdbc;

    public UserRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    // Vulnerable: concatenates user input into SQL (DO NOT USE IN PROD)
    public User findByUsername(String username, String password) {
        // IMPORTANT: intentionally vulnerable string concatenation (demo only)
        String sql = "SELECT id, username, password FROM \"user\" WHERE username = '" + username
                   + "' AND password = '" + password + "'";
        return jdbc.query(sql, rs -> {
            if (rs.next()) {
                User u = new User();
                u.setId(rs.getLong("id"));
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                return u;
            }
            return null;
        });
    }

    // Simple registration helper (stores plaintext password)
    public void saveUserPlain(String username, String plainPassword) {
        jdbc.update("INSERT INTO users(username, password) VALUES (?, ?)", username, plainPassword);
    }
}
