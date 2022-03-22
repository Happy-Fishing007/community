package life.gjq.community.dto;


import life.gjq.community.model.User;
import lombok.Data;

@Data
public class CommentDTO {
    private Long id;
    private Long parentId;
    private Integer type;
    private Long commentator;
    private String comment;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer likeCount;
    private User user;
}
