package service;

import dao.UserDao;
import model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImp implements UserService {
    UserDao userDao;

    @Transactional
    public long save(User user) {
        return userDao.save(user);
    }

    public User get(long id) {
        return userDao.get(id);
    }

    public List<User> list() {
        return userDao.list();
    }

    @Transactional
    public void update(long id, User user) {
        userDao.update(id, user);

    }

    @Transactional
    public void delete(long id) {
        userDao.delete(id);
    }
}
