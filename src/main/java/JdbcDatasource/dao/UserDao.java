package JdbcDatasource.dao;

import JdbcDatasource.exceptions.DbFailException;
import bean.User;

import java.util.List;

public interface UserDao {
    public void addUser(User user) throws DbFailException;
    public User findUserById(int id) throws DbFailException;
    public User gerUserByLogin(String email, String password);
    public List<User> getAllUsers() throws DbFailException;
    public void removeUser(int id) throws DbFailException;
}
