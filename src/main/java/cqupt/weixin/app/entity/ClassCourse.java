package cqupt.weixin.app.entity;

public class ClassCourse {
    private Integer id;
    private String classId;
    private String courseId;

    public ClassCourse(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "ClassCourse{" +
                "id=" + id +
                ", classId='" + classId + '\'' +
                ", courseId='" + courseId + '\'' +
                '}';
    }
}
