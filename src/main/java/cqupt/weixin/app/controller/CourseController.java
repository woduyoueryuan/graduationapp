package cqupt.weixin.app.controller;

import cqupt.weixin.app.entity.*;
import cqupt.weixin.app.model.WXSessionModel;
import cqupt.weixin.app.result.ResponseResult;
import cqupt.weixin.app.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class CourseController {
    @Autowired
    CourseService courseService;
    @RequestMapping("/findCourseById")
    public @ResponseBody
    //我教的课
    ResponseResult<List<Courses>> findCourseById(@RequestBody WXSessionModel wxSessionModel,String index){
        List<Courses> coursesList=null;
        if(wxSessionModel.getOpenid()==null){
            return new ResponseResult("-1","没有获取到用户id");
        }
        if(index.equals("0")){
            OpenId openId = new OpenId();
            openId.setOpenId(wxSessionModel.getOpenid());
            //根据open_id 去teac_course表中查询对应的course_id 返回course_id
            List<Courses> courses = courseService.findCourseId(openId);
            if(courses.isEmpty()){
                return new ResponseResult("200","您没有教的课程！");

            }
            //根据course_id去courses表中查询相应课程
            List<String> courseIdList=new ArrayList();
           for(Courses n:courses){
               courseIdList.add(n.getCourseId());
           }
           //进行查询
            try {

                coursesList = courseService.findCourses(courseIdList);
            }catch(Exception e){
                return new ResponseResult("-1","查找教的课程失败！");
            }

        }
        if(index.equals("1")){
            //如果是查询学生的课程，根据学生所在的班级 找到课程id 根据课程ID查询就行了
            OpenId openId = new OpenId();
            openId.setOpenId(wxSessionModel.getOpenid());
            try {
                //根据openid去找到学生所在班级
                Classes classByOpenid = courseService.findClassByOpenid(openId);

                //根据class_id查找 className
                Classes className = courseService.findClassName(classByOpenid);
                //根据className去class_course中查找courseId
                List<Courses> courseIdByClassName = courseService.findCourseIdByClassName(className);
                //循环遍历初course_id在courses中进行查找。
                List<String> courseIdList=new ArrayList();
                for(Courses n:courseIdByClassName){
                    courseIdList.add(n.getCourseId());
                }
                coursesList = courseService.findCourses(courseIdList);
                System.out.println(coursesList);
            }catch (Exception e){
                return new ResponseResult("-1","查找学的课程失败！");
            }



        }

        return new ResponseResult<List<Courses>>("200","查找成功！",coursesList);
    }

    @RequestMapping("/addCourse")
    @ResponseBody
    public ResponseResult addCourse(String courseName,String classId,String teachName,Integer classroom, String openId){
        if("".equals(courseName)||"".equals(classId)||"".equals(teachName)){
            return new ResponseResult("-1","请输入完整信息！");
        }
              //关联教师和课程,先从数据库查找有么有这个教师
        Teacher teacher = new Teacher();
        teacher.setTeachName(teachName);
        List<Teacher> teacherByName = courseService.findTeacherByName(teacher);
        if(teacherByName.isEmpty()){
            return new ResponseResult("-1","还没有教师！");
        }

        //组装数据
        String courseId= UUID.randomUUID().toString().replaceAll("-","");
        String className=UUID.randomUUID().toString().replaceAll("-","");
        Courses courses = new Courses();
        Classes classes = new Classes();
        TeacherAndCourseVo teacherAndCourseVo = new TeacherAndCourseVo();
        ClassAndTeacherVo classAndTeacher = new ClassAndTeacherVo();
        teacherAndCourseVo.setCourseId(courseId);
        teacherAndCourseVo.setTeachId(teacherByName.get(0).getTeachId());
        teacherAndCourseVo.setOpenId(openId);
        courses.setCourseId(courseId);
        courses.setCourseName(courseName);
        classes.setClassId(classId);
        classes.setClassName(className);
        classAndTeacher.setClassName(className);
        classAndTeacher.setTeacId(teacherByName.get(0).getTeachId());
        try {
            //把课程信息加入到课程信息表中
            CourseInfoVo courseInfoVo=new CourseInfoVo();
            courseInfoVo.setClassId(classId);
            courseInfoVo.setClassroom(classroom);
            courseInfoVo.setCourseName(courseName);
            courseInfoVo.setTeachName(teachName);
            courseInfoVo.setCourseId(courseId);
            //0代表没有完成，1代表完成了。
            courseInfoVo.setIsComplete(0);
            boolean insertCourseInfo= courseService.insertCourseInfo(courseInfoVo);
            if(!insertCourseInfo){
                new ResponseResult("-1","添加课程信息失败！");
            }

            //关联教师课程
            boolean flag4 = courseService.addTeacherAndCourse(teacherAndCourseVo);
            //关联班级教师
            boolean flag5 = courseService.addClassAndTeacher(classAndTeacher);
            //添加进课程表
            boolean flag = courseService.addCourse(courses);
            //添加进班级表
            boolean flag2 = courseService.addClasses(classes);
            //关联课程和班级
            CourseAndClassVo courseAndClassVo = new CourseAndClassVo();
            courseAndClassVo.setClassName(className);
            courseAndClassVo.setCourseId(courseId);
            boolean flag3=courseService.courseAndClasses(courseAndClassVo);


            if(flag&&flag2&&flag3&&flag4&&flag5){
                return new ResponseResult("200","添加成功！");
            }else {
                return new ResponseResult("-1","添加失败！");
            }
        }catch (Exception e){
            return new ResponseResult("-1","添加失败！");
        }

    }
    @RequestMapping("/findCourseInfoById")
    @ResponseBody
    public ResponseResult<CourseInfoVo> findCourseInfoById(@RequestBody Courses course){
        CourseInfoVo courseInfoById;
        try {
            courseInfoById= courseService.findCourseInfoById(course.getCourseId());
        }catch (Exception e){
            return new ResponseResult("-1","系统内部错误！");
        }
        return new ResponseResult<CourseInfoVo>("200","查找成功！",courseInfoById);
    }
    @RequestMapping("/addClass")
    public @ResponseBody ResponseResult addClass(@RequestBody Students students){
        if(students==null){
            return new ResponseResult("-1","请输入完整信息！");
        }
        if("".equals(students.getClassId())||"".equals(students.getName())||"".equals(students.getSno())){
            return new ResponseResult("-1","请输入完整信息！");
        }
        //加入前先查看对应openid是不是已经加入过班级了，如果加入过则加入失败
        Students isExistStu = courseService.findIsExistStu(students);
        if(!(isExistStu==null)){
            return new ResponseResult("-1","你已经加入过班级！");
        }
        boolean addClass = courseService.addClass(students);
        if(addClass){
            return new ResponseResult("200","添加成功！");
        }else{
            return new ResponseResult("-1","添加失败");
        }
    }
    @RequestMapping("/findAllTeacher")
    public @ResponseBody ResponseResult<List<Teacher>> findAllTeacher(){
        List<Teacher> allTeacher;
        try {
            allTeacher = courseService.findAllTeacher();
            if(allTeacher.isEmpty()){
                return new ResponseResult("-1","现在还没有教师！");
            }
        }catch (Exception e){
            return new ResponseResult("-1","查失败询！");
        }
        return new ResponseResult<List<Teacher>>("200","查询成功！",allTeacher);

    }
}
