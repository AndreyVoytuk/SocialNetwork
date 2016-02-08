package JdbcDatasource.implementDao;

import JdbcDatasource.DatasourceConnection;
import JdbcDatasource.dao.PostDao;
import JdbcDatasource.exceptions.DbFailException;
import bean.Post;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PostDaoImpl implements PostDao {
    DatasourceConnection datasource = new DatasourceConnection();

    public Timestamp getTime(){
        Date date = new Date();
        return new Timestamp (date.getTime());
    }

    public void createPost(Post post) throws DbFailException {
        Connection connection = null;
        PreparedStatement statement = null;
        String insertQuery = "INSERT INTO posts(post_author, post_title, post_content, post_date) VALUES(?, ?, ?, ?)";

        try {
            connection = datasource.getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(insertQuery);
            statement.setInt (1, post.getPostAuthor());
            statement.setString(2, post.getPostTitle());
            statement.setString(3, post.getPostContent());
            statement.setTimestamp (4, getTime ());
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            datasource.closePreparedStatement(statement);
            datasource.closeConnection(connection);
        }
    }
    public Post getPostById(int id) throws DbFailException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Post post = new Post ();
        String selectQuery = "SELECT * FROM posts WHERE post_id = ?";

        try {
            connection = datasource.getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(selectQuery);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            connection.commit();
            while (resultSet.next()) {
                int postId = resultSet.getInt("post_id");
                int postAuthor = resultSet.getInt ("post_author");
                String postTitle = resultSet.getString("post_title");
                String postContent = resultSet.getString("post_content");
                String postDate = resultSet.getString("post_date");
                post.setPostId(postId);
                post.setPostAuthor(postAuthor);
                post.setPostTitle(postTitle);
                post.setPostContent(postContent);
                post.setPostDate(postDate);
            }
        } catch (SQLException e) {
            e.printStackTrace ();
        } finally {
            datasource.closeResultSet (resultSet);
            datasource.closePreparedStatement (statement);
            datasource.closeConnection (connection);
        }
        return post;
    }
    public List<Post> getAllPosts() throws DbFailException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String selectALL = "SELECT * FROM posts";
        List<Post> allPosts = new ArrayList<> ();

        try {
            connection = datasource.getConnection();
            statement = connection.prepareStatement(selectALL);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Post post = new Post();
                int postId = resultSet.getInt("post_id");
                int postAuthor = resultSet.getInt ("post_author");
                String postTitle = resultSet.getString("post_title");
                String postContent = resultSet.getString("post_content");
                String postDate = resultSet.getString("post_date");
                post.setPostId(postId);
                post.setPostAuthor(postAuthor);
                post.setPostTitle(postTitle);
                post.setPostContent(postContent);
                post.setPostDate(postDate);
                allPosts.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            datasource.closeResultSet (resultSet);
            datasource.closePreparedStatement (statement);
            datasource.closeConnection (connection);
        }
        return allPosts;
    }

    public List<Post> getAllPosts(int author) throws DbFailException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String selectALL = "SELECT * FROM posts WHERE post_author = ? ";
        List<Post> allPosts = new ArrayList<> ();

        try {
            connection = datasource.getConnection();
            statement = connection.prepareStatement(selectALL);
            statement.setInt(1, author);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Post post = new Post();
                int postId = resultSet.getInt("post_id");
                int postAuthor = resultSet.getInt ("post_author");
                String postTitle = resultSet.getString("post_title");
                String postContent = resultSet.getString("post_content");
                String postDate = resultSet.getString("post_date");
                post.setPostId(postId);
                post.setPostAuthor(postAuthor);
                post.setPostTitle(postTitle);
                post.setPostContent(postContent);
                post.setPostDate(postDate);
                allPosts.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            datasource.closeResultSet (resultSet);
            datasource.closePreparedStatement (statement);
            datasource.closeConnection (connection);
        }
        return allPosts;
    }

    public void updatePost(Post post) throws DbFailException {
        Connection connection = null;
        PreparedStatement statement = null;
        String updateQuery = "UPDATE posts SET post_content = ? WHERE post_id = ?";

        try {
            connection = datasource.getConnection ();
            statement = connection.prepareStatement (updateQuery);
            statement.setString (1, post.getPostContent ());
            statement.setInt (2, post.getPostId ());
            statement.executeUpdate ();
        } catch (SQLException e) {
            e.printStackTrace ();
        } finally {
            datasource.closePreparedStatement(statement);
            datasource.closeConnection(connection);
        }
    }
    public void removePost(int id) throws DbFailException {
        Connection connection = null;
        PreparedStatement statement = null;
        String remove = "DELETE FROM posts WHERE post_id = ?";

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
