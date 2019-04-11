package cqupt.weixin.app.entity;

public class Courses {
    private Integer id;
    private String courseName;
    private String courseId;

    public Courses() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "Courses{" +
                "id=" + id +
                ", name='" + courseName + '\'' +
                ", courseId='" + courseId + '\'' +
                '}';
    }
}
