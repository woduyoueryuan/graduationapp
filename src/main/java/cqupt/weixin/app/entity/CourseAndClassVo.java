package cqupt.weixin.app.entity;

public class CourseAndClassVo {
    private String className;
    private String courseId;

    public CourseAndClassVo() {
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "CourseAndClassVo{" +
                "className='" + className + '\'' +
                ", courseId='" + courseId + '\'' +
                '}';
    }
}
