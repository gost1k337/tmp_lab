package app.postgres;

import app.IO;
import app.Settings;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresConnection {
    private DataSource dataSource;

    public PostgresConnection() {
        this.dataSource = DataSourceConfig.getDataSource();
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

//
//    public PostgresConnection() {
//        try {
//            this.connection = DriverManager.getConnection(
//                    Settings.url,
//                    Settings.username,
//                    Settings.password
//            );
//            io.println("Connected to postgres db");
//            io.println(this.connection.isClosed());
//        } catch (SQLException e) {
//            io.println("Error while connecting to db");
//            e.printStackTrace();
//        }
//    }
//
//    public Connection getConnection() {
//        return connection;
//    }
//
//    public void closeConnection() {
//        try {
//            if (connection != null && !connection.isClosed()) {
//                connection.close();
//                io.println("Connection closed");
//            }
//        } catch (SQLException e) {
//            io.println("Error on connection closing");
//            e.printStackTrace();
//        }
//    }
}
