package app;

public interface Settings {
    String HOST = "localhost";
    int PORT = 5566;
    int NOTIFICATION_PORT = 5567;
    boolean DEBUG_ON = false;

    String url = "jdbc:postgresql://localhost:5432/chats_db";
    String username = "postgres";
    String password = "postgres";
    int poolSize = 10;
}
