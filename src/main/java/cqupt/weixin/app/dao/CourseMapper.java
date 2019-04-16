package cqupt.weixin.app.dao;

import cqupt.weixin.app.entity.*;

import java.util.List;

public interface CourseMapper {
    //查询用户课程
    public List<Course> findCourseById(Course courses);

    Integer addCourse(Courses courses);
    CourseInfoVo findCourseInfoById(String courseId);

    Integer addClass(Students students);

    Integer saveFile(CourseWare s);

    List<CourseWare> findAllFile();
    CourseWare findFile(String originalFilename);


    Integer addClasses(Classes classes);

    Integer courseAndClasses(CourseAndClassVo courseAndClassVo);

    Integer addTeacher(Teacher teacher);

    List<Teacher> findTeacherByName(Teacher teacher);

    Integer addTeacherAndCourse(TeacherAndCourseVo teacherAndCourseVo);

    Integer addClassAndTeacher(ClassAndTeacherVo classAndTeacher);

    List<Teacher> findAllTeacher();

    List<Courses> findCourseId(OpenId openId);

    List<Courses> findCourses(List<String> courseIdList);

    Classes findClassByOpenid(OpenId openId);

    Classes findClassName(Classes classByOpenid);

    List<Courses> findCourseIdByClassName(Classes className);

    Integer insertCourseInfo(CourseInfoVo courseInfoVo);

    Students findIsExistStu(Students students);

    Integer complementCourse(Teacher teacher);

    List<Teacher> findTeach(Teacher teacher);
}
