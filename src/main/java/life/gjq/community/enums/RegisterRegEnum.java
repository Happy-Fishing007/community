package life.gjq.community.enums;

public enum RegisterRegEnum {
   REG_EMAIL("/^[0-9a-zA-Z_.-]+[@][0-9a-zA-Z_.-]+([.][a-zA-Z]+){1,2}$/"),
   REG_CODE("/^\\d{6}$/"),
   REG_NAME("/^[\\w\\u4e00-\\u9fa5]{4,12}$/"),
   REG_PASSWORD("/^.*(?=.{6,30})(?=.*\\d)(?=.*[a-z])(?=.*[!@#$%^&*? ]).*$/"),
    ;
    private String reg;
    RegisterRegEnum(String reg) {
        this.reg=reg;
    }

    public String getReg() {
        return reg;
    }
}
