package app.server.message;

import app.server.user.IUserRepo;
import app.server.user.UserModel;

import java.util.List;
import java.util.Optional;

public class MessageService {
    private final IMessageRepo repo;
    private final IUserRepo userRepo;
    public MessageService(IMessageRepo repo, IUserRepo userRepo) {
        this.repo = repo;
        this.userRepo = userRepo;
    }

    public void Create(String username, String text) {
        Optional<UserModel> user = userRepo.FindByUsername(username);
        if (user.isPresent()) {
            MessageModel message = new MessageModel(0, user.get().getId(), text, null);
            repo.Create(message);
        }
    }

    public List<MessageModel> FindLastMessages() {
        return repo.FindLastMessages();
    }
}
