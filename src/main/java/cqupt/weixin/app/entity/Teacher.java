package cqupt.weixin.app.entity;

public class Teacher {
    private Integer id;
    private String teachName;
    private String teachId;
    private String openId;

    public Teacher() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTeachName() {
        return teachName;
    }

    public void setTeachName(String teachName) {
        this.teachName = teachName;
    }

    public String getTeachId() {
        return teachId;
    }

    public void setTeachId(String teachId) {
        this.teachId = teachId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", teachName='" + teachName + '\'' +
                ", teachId='" + teachId + '\'' +
                ", openId='" + openId + '\'' +
                '}';
    }
}
