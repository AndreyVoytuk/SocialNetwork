package JdbcDatasource.dao;

import JdbcDatasource.exceptions.DbFailException;
import bean.Like;

import java.util.List;

public interface LikeDao {
    public void addLike(Like like) throws DbFailException;
    public List<Like> getAllLike() throws DbFailException;
    public int getAmountLike(int post) throws DbFailException;
    public void removeLike(int likeId) throws DbFailException;
}
