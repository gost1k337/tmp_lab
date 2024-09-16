package app.server.user;

import java.time.LocalDateTime;

public class UserModel {
    private int id;
    private String username;
    private String password;
    private LocalDateTime createdAt;

    public UserModel(int id, String username, String password, LocalDateTime createdAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", username=" + username +
                ", createdAt=" + createdAt +
                '}';
    }
}
