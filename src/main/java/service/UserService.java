package service;

import dao.UserHibernateDao;
import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import util.DBHelper;

import java.util.List;

public class UserService {
    private static UserService userService;
    private SessionFactory sessionFactory;

    private UserService(){}

    private UserService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static UserService getInstance(){
        if (userService == null)
            userService = new UserService(DBHelper.getInstatnce().getConfiguration());
        return userService;
    }

    public void addUser(User user) {
        Session session = sessionFactory.openSession();
        UserHibernateDao userDao = new UserHibernateDao(session);
        userDao.addUser(user);
        session.close();
    }

    public User getUser(long id){
        Session session = sessionFactory.openSession();
        UserHibernateDao userDao = new UserHibernateDao(session);
        return userDao.getUser(id);
    }

    public List<User> getAll() {
        Session session = sessionFactory.openSession();
        UserHibernateDao userDao = new UserHibernateDao(session);
        return userDao.getAll();
    }

    public void deleteUser(Long id) {
        Session session = sessionFactory.openSession();
        UserHibernateDao userDao = new UserHibernateDao(session);
        userDao.deleteUser(id);
    }

    public void updateUser(User user) {
        Session session = sessionFactory.openSession();
        UserHibernateDao userDao = new UserHibernateDao(session);
        userDao.updateUser(user);
    }
}
