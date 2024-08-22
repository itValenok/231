package web.DAO;

import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import java.util.List;

@Transactional

public interface UserDAO {
    void addUser(User user);

    List<User> getUsers();
    void updateUser(User user);
    void deleteUser(int id);
    User getUser(int id);
}
