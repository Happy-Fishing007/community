package life.gjq.community.model;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String userId;
    private String name;
    private long gmtCreate;
    private long gmtModified;
    private String bio;
    private String token;
    private String avatarUrl;
}
