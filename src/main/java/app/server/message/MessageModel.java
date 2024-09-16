package app.server.message;

import java.time.LocalDateTime;

public class MessageModel {
    private int id;
    private int userId;
    private String text;
    private LocalDateTime sentAt;

    public MessageModel(int id, int userId, String text, LocalDateTime sentAt) {
        this.id = id;
        this.userId = userId;
        this.text = text;
        this.sentAt = sentAt;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", userId=" + userId +
                ", text='" + text + '\'' +
                ", sentAt=" + sentAt +
                '}';
    }
}
