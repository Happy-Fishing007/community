package life.gjq.community.dto;

import life.gjq.community.model.User;
import lombok.Data;

@Data
public class NotificationDTO {
    private Long id;
    private Long gmtCreate;
    private Integer status;
    private Long notifier;
    private Long outerId;
    private String notifierName;
    private String outerTitle;
    private String typeName;
    private Integer type;

}
