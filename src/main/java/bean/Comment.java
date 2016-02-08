package bean;

public class Comment {
    private int commentId;
    private int commentPostId;
    private int commentAuthor;
    private String commentContent;

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getCommentPostId() {
        return commentPostId;
    }

    public void setCommentPostId(int commentPostId) {
        this.commentPostId = commentPostId;
    }

    public int getCommentAuthor() {
        return commentAuthor;
    }

    public void setCommentAuthor(int commentAuthor) {
        this.commentAuthor = commentAuthor;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", commentPostId=" + commentPostId +
                ", commentAuthor=" + commentAuthor +
                ", commentContent='" + commentContent + '\'' +
                '}';
    }
}
