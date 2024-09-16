package app.server.message;

import java.util.List;

public interface IMessageRepo {
    List<MessageModel> FindLastMessages();
    void Create(MessageModel message);
}
