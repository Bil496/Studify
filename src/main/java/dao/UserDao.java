package dao;

import model.User;

import java.util.List;

public interface UserDao {
    long save(User user);

    User get(long id);

    List<User> list();

    void update(long id, User user);

    void delete(long id);

    // Boolean login(long id, User user);
}
