package JdbcDatasource.dao;

import JdbcDatasource.exceptions.DbFailException;
import bean.Post;

import java.util.List;

public interface PostDao {
    public void createPost(Post post) throws DbFailException;
    public Post getPostById(int id) throws DbFailException;
    public List<Post> getAllPosts() throws DbFailException;
    public List<Post> getAllPosts(int author) throws DbFailException;
    public void updatePost(Post post) throws DbFailException;
    public void removePost(int id) throws DbFailException;
}
