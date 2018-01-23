package service;

import java.util.List;

import model.User;

public interface UserService {
    long save(User user);

    User get(long id);

    List<User> list();

    void update(long id, User user);

    void delete(long id);
}