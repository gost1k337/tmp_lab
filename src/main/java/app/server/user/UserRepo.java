package app.server.user;

import app.IO;
import app.postgres.PostgresConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;

public class UserRepo implements IUserRepo {
    private final PostgresConnection postgresConnection;
    public UserRepo(PostgresConnection connection) {
        this.postgresConnection = connection;
    }

    @Override
    public void Create(String username, String password) {
        String sql = "INSERT INTO users (username, password) VALUES (?, ?) RETURNING id, created_at";
        try (Connection connection = postgresConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                long generatedId = rs.getLong("id");
                LocalDateTime createdAt = rs.getObject("created_at", LocalDateTime.class);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public Optional<UserModel> FindByID(Integer id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        try (Connection connection = postgresConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                UserModel user = new UserModel(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getObject("created_at", LocalDateTime.class)
                );
                return Optional.of(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public Optional<UserModel> FindByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (Connection connection = postgresConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                UserModel user = new UserModel(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getObject("created_at", LocalDateTime.class)
                );
                return Optional.of(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
