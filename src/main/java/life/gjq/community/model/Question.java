package life.gjq.community.model;

import lombok.Data;

@Data
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


}
