package life.gjq.community.model;

public class User {
    private Integer id;
    private String user;
    private String name;
    private long gmtCreate;
    private long gmtModified;
    private String bio;
    private String token;

    @Override
    public String toString() {
        return "Aaa{" +
                "id=" + id +
                ", userId='" + user + '\'' +
                ", name='" + name + '\'' +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                ", bio='" + bio + '\'' +
                ", token='" + token + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public long getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(long gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
