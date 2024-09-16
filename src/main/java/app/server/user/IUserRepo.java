package app.server.user;

import java.util.Optional;

public interface IUserRepo {
    void Create(String username, String password);

    Optional<UserModel> FindByID(Integer userId);
    Optional<UserModel> FindByUsername(String username);
}
