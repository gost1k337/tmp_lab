package app.server.message;

import app.postgres.PostgresConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MessageRepo implements IMessageRepo {
    private final PostgresConnection postgresConnection;

    public MessageRepo(PostgresConnection connection) {
        this.postgresConnection = connection;
    }

    @Override
    public List<MessageModel> FindLastMessages() {
        List<MessageModel> messages = new ArrayList<>();
        String sql = "SELECT * FROM messages WHERE sent_at >= NOW() - INTERVAL '5 minutes'";
        try (Connection connection = postgresConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                MessageModel message = new MessageModel(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getString("text"),
                        rs.getObject("sent_at", LocalDateTime.class)
                );
                messages.add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messages;
    }

    @Override
    public void Create(MessageModel message) {
        String sql = "INSERT INTO messages (user_id, text) VALUES (?, ?) RETURNING id, sent_at";
        try (Connection connection = postgresConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setObject(1, message.getUserId(), java.sql.Types.INTEGER);
            stmt.setString(2, message.getText());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
