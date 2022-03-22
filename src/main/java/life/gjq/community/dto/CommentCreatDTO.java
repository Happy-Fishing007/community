package life.gjq.community.dto;

import lombok.Data;

@Data
public class CommentCreatDTO {

    private long parentId;
    private String content;
    private Integer type;
}
