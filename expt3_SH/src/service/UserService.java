package service;

import dao.UserDao;
import model.User;
import org.hibernate.Query;

import java.util.List;

/**
 * Created by dd on 2016/5/12.
 */
public class UserService {
    static UserDao userDao = new UserDao();

    public User findUser(String username) {
        String hql = "from User as u where u.username=?";
        Query query = userDao.getSession().createQuery(hql);
        query.setParameter(0, username);
        List<User> list = query.list();
        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }

    public User findUser(int id) {
        return (User) userDao.findById("model.User", id);
    }

    public List<User> findUsers(String property, Object value) {
        try {
            String hql = "from User as u where u." + property + "=?";
            Query query = userDao.getSession().createQuery(hql);
            query.setParameter(0, value);
            return query.list();
        } catch (Exception e) {
            return null;
        }
    }

    public List<User> findAllUser() {
        try {
            String hql = "from User";
            Query query = userDao.getSession().createQuery(hql);
            return query.list();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean add(User user) {
        User u = findUser(user.getUsername());
        if (u == null) {
            userDao.add(user);
            return true;
        }
        return false;
    }

    public boolean delete(User user) {
        User u = findUser(user.getId());
        if (u != null) {
            userDao.delete(user);
            return true;
        }
        return false;
    }

    public boolean update(User user) {
        String preName = findUser(user.getId()).getUsername();
        String name = user.getUsername();
        if (!name.equals(preName) && findUser(name) != null) {
            return false;
        } else {
            userDao.update(user);
            return true;
        }
    }

}
