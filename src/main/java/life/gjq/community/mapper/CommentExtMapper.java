package life.gjq.community.mapper;

import life.gjq.community.model.Comment;

public interface CommentExtMapper {
   int   incLikeCount(Comment record);
}
