package cqupt.weixin.app.service.impl;

import cqupt.weixin.app.dao.CourseMapper;
import cqupt.weixin.app.entity.*;
import cqupt.weixin.app.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    CourseMapper courseMapper;

    @Override
    public List<Course> findCourseById(Course course) {
        return courseMapper.findCourseById(course);
    }

    @Override
    public boolean addCourse(Courses courses) {
        try {
            Integer integer = courseMapper.addCourse(courses);
            if (integer > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public CourseInfoVo findCourseInfoById(String courseId) {

        return courseMapper.findCourseInfoById(courseId);

    }

    @Override
    public boolean addClass(Students students) {
        Integer integer = courseMapper.addClass(students);
        if (integer > 0) {
            return true;
        } else {

            return false;
        }
    }

    @Override
    public boolean saveFile(CourseWare s) {

        Integer integer = courseMapper.saveFile(s);
        if (integer > 0) {
            return true;
        } else {

            return false;
        }
    }

    @Override
    public List<CourseWare> findAllFile() {
        return courseMapper.findAllFile();
    }

    @Override
    public CourseWare findFile(String originalFilename) {
        return courseMapper.findFile(originalFilename);

    }

    @Override
    public boolean addClasses(Classes classes) {
        try {
            Integer integer = courseMapper.addClasses(classes);
            if (integer > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean courseAndClasses(CourseAndClassVo courseAndClassVo) {
        try {
            Integer integer = courseMapper.courseAndClasses(courseAndClassVo);
            if (integer > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean addTeacher(Teacher teacher) {
        try {
            Integer integer = courseMapper.addTeacher(teacher);
            if (integer > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Teacher> findTeacherByName(Teacher teacher) {
        List<Teacher> teacherList = courseMapper.findTeacherByName(teacher);
        return teacherList ;
    }

    @Override
    public boolean addTeacherAndCourse(TeacherAndCourseVo teacherAndCourseVo) {
        try {
            Integer integer = courseMapper.addTeacherAndCourse(teacherAndCourseVo);
            if (integer > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean addClassAndTeacher(ClassAndTeacherVo classAndTeacher) {
        try {
            Integer integer = courseMapper.addClassAndTeacher(classAndTeacher);
            if (integer > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Teacher> findAllTeacher() {
       return courseMapper.findAllTeacher();

    }

    @Override
    public List<Courses> findCourseId(OpenId openId) {
        List<Courses> courseId = courseMapper.findCourseId(openId);
        return courseId;
    }

    @Override
    public List<Courses> findCourses(List<String> courseIdList) {
       return  courseMapper.findCourses(courseIdList);

    }

    @Override
    public Classes findClassByOpenid(OpenId openId) {
        return courseMapper.findClassByOpenid(openId);
    }

    @Override
    public Classes findClassName(Classes classByOpenid) {
        return courseMapper.findClassName(classByOpenid);
    }

    @Override
    public List<Courses> findCourseIdByClassName(Classes className) {
        return courseMapper.findCourseIdByClassName(className);
    }

    @Override
    public boolean insertCourseInfo(CourseInfoVo courseInfoVo) {
       Integer flag= courseMapper.insertCourseInfo(courseInfoVo);
       if(flag>0){
           return true;
       }else{

           return false;
       }
    }

    @Override
    public Students findIsExistStu(Students students) {

       return courseMapper.findIsExistStu(students);
    }

}