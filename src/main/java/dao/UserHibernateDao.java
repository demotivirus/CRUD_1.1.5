package dao;

import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.util.List;

public class UserHibernateDao implements UserDao{
    private Session session;

    public UserHibernateDao(Session session){
        this.session = session;
    }

    @Override
    public void addUser(User user) {
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
    }

//    public User logIn(User user){
//        Transaction transaction = session.beginTransaction();
//        Query query = session.createQuery("FROM User where name = :paramName and password = :paramPassword");
//        query.setParameter("paramName", user.getName());
//        query.setParameter("paramPassword", user.getPassword());
//        return user = (User) query.uniqueResult();
//    }

    @Override
    public User getUser(long id) {
        Transaction transaction = session.beginTransaction();
        return session.get(User.class, id);
    }

    @Override
    public List<User> getAll() {
        Transaction transaction = session.beginTransaction();
        List<User> list = session.createQuery("FROM User").list();
        transaction.commit();
        session.close();
        return list;
    }

    @Override
    public void deleteUser(Long id) {
        Transaction transaction = session.beginTransaction();
        User user = session.get(User.class, id);
        session.delete(user);
        transaction.commit();
        session.close();
    }

    @Override
    public void updateUser(User user) {
        Transaction transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();
        session.close();
    }
}
