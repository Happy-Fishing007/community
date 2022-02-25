package life.gjq.community.dto;

import life.gjq.community.model.User;
import lombok.Data;

@Data
public class QuestionDTO {
    private long id;
    private String creator;
    private String title;
    private String description;
    private long getCreate;
    private long getModified;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private String tag;
    private User user;
}
