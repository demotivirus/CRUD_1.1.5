package dao;

import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public interface UserDao {
    void addUser(User user);

    User getUser(long id);

    List<User> getAll();

    void deleteUser(Long id);

    void updateUser(User user);
}
