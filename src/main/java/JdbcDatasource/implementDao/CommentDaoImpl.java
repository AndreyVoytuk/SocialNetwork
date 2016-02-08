package JdbcDatasource.implementDao;

import JdbcDatasource.DatasourceConnection;
import JdbcDatasource.dao.CommentDao;
import JdbcDatasource.exceptions.DbFailException;
import bean.Comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentDaoImpl implements CommentDao {
    DatasourceConnection datasource = new DatasourceConnection();

    public void addComment(Comment comment) throws DbFailException {
        Connection connection = null;
        PreparedStatement statement = null;
        String insertQuery = "INSERT INTO comments(comment_id, comment_post_id, comment_author, comment_content) VALUES(?, ?, ?, ?)";

        try {
            connection = datasource.getConnection ();
            connection.setAutoCommit (false);
            statement = connection.prepareStatement (insertQuery);
            statement.setInt(1, comment.getCommentId ());
            statement.setInt(2, comment.getCommentPostId ());
            statement.setInt (3, comment.getCommentAuthor ());
            statement.setString (4, comment.getCommentContent ());
            statement.executeUpdate ();
            connection.commit ();
        } catch (SQLException e) {
            e.printStackTrace ();
        } finally {
            datasource.closePreparedStatement(statement);
            datasource.closeConnection(connection);
        }
    }
    public List<Comment> getAllComment(int post) throws DbFailException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Comment> comments = new ArrayList<> ();
        String selectQuery = "SELECT comment_id, comment_author, comment_content FROM comments WHERE comment_post_id = ?";

        try {
            connection = datasource.getConnection ();
            connection.setAutoCommit (false);
            statement = connection.prepareStatement (selectQuery);
            statement.setInt (1, post);
            resultSet = statement.executeQuery ();
            connection.commit ();
            while (resultSet.next ()) {
                int id = resultSet.getInt ("comment_id");
                int author = resultSet.getInt ("comment_author");
                String content= resultSet.getString ("comment_content");
                Comment comment= new Comment();
                comment.setCommentId (id);
                comment.setCommentAuthor (author);
                comment.setCommentContent (content);
                comments.add (comment);
            }
        } catch (SQLException e) {
            e.printStackTrace ();
        } finally {
            datasource.closePreparedStatement(statement);
            datasource.closeConnection (connection);
            datasource.closeResultSet(resultSet);
        }
        return comments;
    }
    public void updateComment(Comment comment) throws DbFailException {
        Connection connection = null;
        PreparedStatement statement = null;
        String updateQuery = "UPDATE comments SET comment_content = ? WHERE comment_id = ?";

        try {
            connection = datasource.getConnection ();
            connection.setAutoCommit (false);
            statement = connection.prepareStatement (updateQuery);
            statement.setString (1, "Good article");
            statement.setInt (2, comment.getCommentId ());
            statement.executeUpdate ();
            connection.commit ();
        } catch (SQLException e) {
            e.printStackTrace ();
        } finally {
            datasource.closePreparedStatement(statement);
            datasource.closeConnection(connection);
        }
    }
    public void removeComment(int id) throws DbFailException {
        Connection connection = null;
        PreparedStatement statement = null;
        String remove = "DELETE FROM comments WHERE comment_id = ?";

        try {
            connection = datasource.getConnection ();
            statement = connection.prepareStatement (remove);
            statement.setInt (1, id);
            statement.executeUpdate ();
        } catch (SQLException e) {
            e.printStackTrace ();
        } finally {
            datasource.closePreparedStatement(statement);
            datasource.closeConnection(connection);
        }
    }
}
