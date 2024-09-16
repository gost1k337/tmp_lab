package app.transport.message.storage;

import app.transport.message.Message;

public class RegisterPasswordRequest extends Message {
    private final String password;

    public RegisterPasswordRequest(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return String.format("RegisterPasswordRequest{password='%s'}", password);
    }
}
