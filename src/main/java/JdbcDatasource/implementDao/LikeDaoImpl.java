package JdbcDatasource.implementDao;

import JdbcDatasource.DatasourceConnection;
import JdbcDatasource.dao.LikeDao;
import JdbcDatasource.exceptions.DbFailException;
import bean.Like;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LikeDaoImpl implements LikeDao {
    DatasourceConnection datasource = new DatasourceConnection();

    public void addLike(Like like) throws DbFailException {
        Connection connection = null;
        PreparedStatement statement = null;
        String insertQuery = "INSERT INTO likes(like_post, like_author) VALUES(?, ?)";

        try {
            connection = datasource.getConnection ();
            connection.setAutoCommit (false);
            statement = connection.prepareStatement (insertQuery);
            statement.setInt(1, like.getLikePost ());
            statement.setInt (2, like.getLikeAuthor ());
            statement.executeUpdate ();
            connection.commit ();
        } catch (SQLException e) {
            e.printStackTrace ();
        } finally {
            datasource.closePreparedStatement(statement);
            datasource.closeConnection(connection);
        }
    }

    public List<Like> getAllLike() throws DbFailException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Like> allLikes = new ArrayList<> ();
        String selectQuery = "SELECT like_id, like_author FROM likes";

        try {
            connection = datasource.getConnection ();
            connection.setAutoCommit (false);
            statement = connection.prepareStatement (selectQuery);
            resultSet = statement.executeQuery ();
            connection.commit ();
            while (resultSet.next ()) {
                int id = resultSet.getInt ("like_id");
                int author = resultSet.getInt ("like_author");
                Like like = new Like ();
                like.setLikeId (id);
                like.setLikeId (author);
                allLikes.add (like);
            }
        } catch (SQLException e) {
            e.printStackTrace ();
        } finally {
            datasource.closePreparedStatement(statement);
            datasource.closeConnection (connection);
            datasource.closeResultSet(resultSet);
        }
        return allLikes;
    }
    public int getAmountLike(int post) throws DbFailException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String selectQuery = "SELECT * FROM likes WHERE like_post = ?";
        int amount = 0;

        try {
            connection = datasource.getConnection ();
            connection.setAutoCommit (false);
            statement = connection.prepareStatement (selectQuery);
            statement.setInt (1, post);
            resultSet = statement.executeQuery ();
            connection.commit ();
            while (resultSet.next ()) {
                amount += 1;
            }
        } catch (SQLException e) {
            e.printStackTrace ();
        } finally {
            datasource.closePreparedStatement(statement);
            datasource.closeConnection (connection);
            datasource.closeResultSet(resultSet);
        }

        return amount;
    }
    public void removeLike(int likeId) throws DbFailException {
        Connection connection = null;
        PreparedStatement statement = null;
        String removeQuery = "DELETE FROM likes WHERE like_id = ?";

        try {
            connection = datasource.getConnection ();
            statement = connection.prepareStatement (removeQuery);
            statement.setInt (1, likeId);
            statement.executeUpdate ();
        } catch (SQLException e) {
            e.printStackTrace ();
        } finally {
            datasource.closePreparedStatement(statement);
            datasource.closeConnection(connection);
        }
    }
}
