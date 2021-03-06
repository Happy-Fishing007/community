package life.gjq.community.exception;

public enum  CustomizeErrorCode implements  ICustomizeErrorCode {

    QUESTION_NOT_FOUND(2001,"你找的问题不存在，要不要换一个试试"),
    TARGET_PARAM_NOT_FOUND(2002,"未选择任何问题或评论进行回复"),
    NO_LOGIN(2003,"当前操作需要登录，请登录后重试"),
    SYS_ERROR(2004,"服务器冒烟了，要不等会再试试"),
    TYPE_PARAM_WRONG(2005,"评论类型错误或不存在"),
    COMMENT_NOT_FOUND(2006,"回复的评论问题不存在了"),
    COMMENT_IS_EMPTY(2007,"回复框不能为空"),
    READ_NOTIFICATION_FALL(2008,"不能读别人的信息哦~"),
    NOTIFICATION_NOT_FOUND(2009,"消息跑路楼~")
    ;


    private Integer code;
    private String message;

    CustomizeErrorCode(Integer code ,String message) {
        this.code = code;
        this.message = message;
    }
    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }


}
