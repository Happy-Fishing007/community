package life.gjq.community.model;


public class Question {

  private Integer id;
  private String creator;
  private String title;
  private String description;
  private long getCreate;
  private long getModified;
  private Integer commentCount;
  private Integer viewCount;
  private Integer likeCount;
  private String tag;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getCreator(String id) {
    return creator;
  }

  public void setCreator(String creator) {
    this.creator = creator;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public long getGetCreate() {
    return getCreate;
  }

  public void setGetCreate(long getCreate) {
    this.getCreate = getCreate;
  }

  public long getGetModified() {
    return getModified;
  }

  public void setGetModified(long getModified) {
    this.getModified = getModified;
  }

  public Integer getCommentCount() {
    return commentCount;
  }

  public void setCommentCount(Integer commentCount) {
    this.commentCount = commentCount;
  }

  public Integer getViewCount() {
    return viewCount;
  }

  public void setViewCount(Integer viewCount) {
    this.viewCount = viewCount;
  }

  public Integer getLikeCount() {
    return likeCount;
  }

  public void setLikeCount(Integer likeCount) {
    this.likeCount = likeCount;
  }

  public String getTag() {
    return tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }

  @Override
  public String toString() {
    return "Question{" +
            "id=" + id +
            ", creator=" + creator +
            ", title='" + title + '\'' +
            ", description='" + description + '\'' +
            ", getCreate=" + getCreate +
            ", getModified=" + getModified +
            ", commentCount=" + commentCount +
            ", viewCount=" + viewCount +
            ", likeCount=" + likeCount +
            ", tag='" + tag + '\'' +
            '}';
  }
}
