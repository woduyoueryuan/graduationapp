package cqupt.weixin.app.service;

import cqupt.weixin.app.entity.*;

import java.util.List;

public interface CourseService {
    public List<Course> findCourseById(Course course);

    boolean addCourse(Courses courses);

    CourseInfoVo findCourseInfoById(String courseId);

    boolean addClass(Students students);

    boolean saveFile(CourseWare s);

    List<CourseWare> findAllFile();
    CourseWare findFile(String originalFilename);

    boolean addClasses(Classes classes);

    boolean courseAndClasses(CourseAndClassVo courseAndClassVo);

    boolean addTeacher(Teacher teacher);


    List<Teacher> findTeacherByName(Teacher teacher);

    boolean addTeacherAndCourse(TeacherAndCourseVo teacherAndCourseVo);

    boolean addClassAndTeacher(ClassAndTeacherVo classAndTeacher);

    List<Teacher> findAllTeacher();

    List<Courses> findCourseId(OpenId openId);

    List<Courses> findCourses(List<String> courseIdList);

    Classes findClassByOpenid(OpenId openId);

    Classes findClassName(Classes classByOpenid);

    List<Courses> findCourseIdByClassName(Classes className);

    boolean insertCourseInfo(CourseInfoVo courseInfoVo);

    Students findIsExistStu(Students students);

    boolean complementCourse(Teacher teacher);

}
