package bean;

public class Post {
    private int postId;
    private int postAuthor;
    private String postTitle;
    private String postDate;
    private String postContent;

    public int getPostId() {
        return postId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public int getPostAuthor() {
        return postAuthor;
    }

    public String getPostDate() {
        return postDate;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public void setPostAuthor(int postAuthor) {
        this.postAuthor = postAuthor;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", postTitle='" + postTitle + '\'' +
                ", postAuthor='" + postAuthor + '\'' +
                ", postDate='" + postDate + '\'' +
                ", postContent='" + postContent + '\'' +
                '}';
    }
}
