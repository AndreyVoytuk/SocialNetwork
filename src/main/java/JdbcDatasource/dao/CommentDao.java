package JdbcDatasource.dao;

import JdbcDatasource.exceptions.DbFailException;
import bean.Comment;

import java.util.List;

public interface CommentDao {
    public void addComment(Comment comment) throws DbFailException;
    public List<Comment> getAllComment(int post) throws DbFailException;
    public void updateComment(Comment comment) throws DbFailException;
    public void removeComment(int id) throws DbFailException;
}
