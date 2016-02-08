package JdbcDatasource.implementDao;

import JdbcDatasource.DatasourceConnection;
import JdbcDatasource.dao.UserDao;
import JdbcDatasource.exceptions.DbFailException;
import bean.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    DatasourceConnection datasource = new DatasourceConnection();

    public void addUser(User user) throws DbFailException {
        Connection connection = null;
        PreparedStatement statement = null;
        String insertQuery = "INSERT INTO users(user_name, user_password, user_email, user_status) VALUES(?, ?, ?, ?)";

        try {
            connection = datasource.getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(insertQuery);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getUserPassword());
            statement.setString(3, user.getUserEmail ());
            statement.setString(4, user.getUserStatus ());
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            datasource.closePreparedStatement(statement);
            datasource.closeConnection(connection);
        }
    }
    public User findUserById(int id) throws DbFailException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        User user = new User ();
        String selectQuery = "SELECT * FROM users WHERE user_id = ?";

        try {
            connection = datasource.getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(selectQuery);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            connection.commit();
            while (resultSet.next()) {
                int userId = resultSet.getInt("user_id");
                String userName = resultSet.getString("user_name");
                String userPassword = resultSet.getString("user_password");
                String userEmail = resultSet.getString("user_email");
                String userStatus = resultSet.getString("user_status");
                user.setUserId(userId);
                user.setUserName(userName);
                user.setUserPassword(userPassword);
                user.setUserEmail (userEmail);
                user.setUserStatus (userStatus);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            datasource.closePreparedStatement(statement);
            datasource.closeConnection(connection);
            datasource.closeResultSet(resultSet);
        }
        return user;
    }

    public User gerUserByLogin(String email, String password) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        User user = new User ();
        String selectQuery = "SELECT * FROM users WHERE user_email = ? AND user_password = ?";

        try {
            connection = datasource.getConnection ();
            statement = connection.prepareStatement (selectQuery);
            statement.setString (1, email);
            statement.setString (2, password);
            resultSet = statement.executeQuery ();

            while (resultSet.next()) {
                int userId = resultSet.getInt("user_id");
                String userName = resultSet.getString("user_name");
                String userPassword = resultSet.getString("user_password");
                String userEmail = resultSet.getString("user_email");
                String userStatus = resultSet.getString("user_status");
                user.setUserId (userId);
                user.setUserName (userName);
                user.setUserPassword (userPassword);
                user.setUserEmail (userEmail);
                user.setUserStatus (userStatus);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            datasource.closePreparedStatement(statement);
            datasource.closeConnection(connection);
            datasource.closeResultSet(resultSet);
        }
        return user;
    }

    public List<User> getAllUsers() throws DbFailException {
        List<User> allUsers = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String selectALL = "SELECT * FROM users";

        try {
            connection = datasource.getConnection();
            statement = connection.prepareStatement(selectALL);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                allUsers.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            datasource.closePreparedStatement(statement);
            datasource.closeConnection(connection);
            datasource.closeResultSet(resultSet);
        }

        return allUsers;

    }
    public void removeUser(int id) throws DbFailException {
        Connection connection = null;
        PreparedStatement statement = null;
        String deleteQuery = "";
        /*Надо почитать по поводу Constraint in foreign key*/
    }

}
