package service;

import model.User;

import java.util.List;

public interface UserService {
    long save(User user);

    User get(long id);

    List<User> list();

    void update(long id, User user);

    void delete(long id);
}