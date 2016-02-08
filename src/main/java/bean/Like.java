package bean;

public class Like {
    private int LikeId;
    private int likePost;
    private int likeAuthor;

    public int getLikeId() {
        return LikeId;
    }

    public void setLikeId(int likeId) {
        LikeId = likeId;
    }

    public int getLikePost() {
        return likePost;
    }

    public void setLikePost(int likePost) {
        this.likePost = likePost;
    }

    public int getLikeAuthor() {
        return likeAuthor;
    }

    public void setLikeAuthor(int likeAuthor) {
        this.likeAuthor = likeAuthor;

    }

    @Override
    public String toString() {
        return "Like{" +
                "LikeId=" + LikeId +
                ", likePost=" + likePost +
                ", likeAuthor=" + likeAuthor +
                '}';
    }
}
