package app.server.user;

import java.util.Objects;
import java.util.Optional;

public class UserService {
    private final IUserRepo repo;
    public UserService(IUserRepo userRepo) {
        repo = userRepo;
    }

    public boolean userExists(String username) {
        return repo.FindByUsername(username).isPresent();
    }

    public void register(String username, String password) {
        if (userExists(username)) {
            throw new UserException("register new user error - user exists");
        }

        repo.Create(username, password);
    }

    public boolean isPasswordValid(String password) {
        return password != null && !password.isBlank();
    }

    public boolean checkPassword(String username, String password) {
        Optional<UserModel> user = repo.FindByUsername(username);

        if (user.isEmpty()) {
            throw new UserException("user does not exist");
        }

        return Objects.equals(password, user.get().getPassword());
    }
}
