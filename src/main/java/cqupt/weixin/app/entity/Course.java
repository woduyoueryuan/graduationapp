package cqupt.weixin.app.entity;

public class Course {
    private Integer courseId;
    private String courseName;
    private String openId;
    private Integer state;
    private Integer teachclass;
    private Integer classroom;
    private  Integer isComplete;
    private  String teacherName;
    public Course() {
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getTeachclass() {
        return teachclass;
    }

    public void setTeachclass(Integer teachclass) {
        this.teachclass = teachclass;
    }

    public Integer getClassroom() {
        return classroom;
    }

    public void setClassroom(Integer classroom) {
        this.classroom = classroom;
    }

    public Integer getIsComplete() {
        return isComplete;
    }

    public void setIsComplete(Integer isComplete) {
        this.isComplete = isComplete;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", openId='" + openId + '\'' +
                ", state=" + state +
                ", teachclass=" + teachclass +
                ", classroom=" + classroom +
                ", isComplete=" + isComplete +
                ", teacherName='" + teacherName + '\'' +
                '}';
    }
}
