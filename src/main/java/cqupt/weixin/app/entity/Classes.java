package cqupt.weixin.app.entity;

public class Classes {
    private Integer id;
    private String className;
    private String classId;

    public Classes() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    @Override
    public String toString() {
        return "Classes{" +
                "id=" + id +
                ", name='" + className + '\'' +
                ", classId='" + classId + '\'' +
                '}';
    }
}
