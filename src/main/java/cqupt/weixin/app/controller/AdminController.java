package cqupt.weixin.app.controller;

import cqupt.weixin.app.entity.Admin;
import cqupt.weixin.app.entity.Teacher;
import cqupt.weixin.app.result.ResponseResult;
import cqupt.weixin.app.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;
    @RequestMapping("/login")
    public ResponseResult login(@RequestBody Admin admin){
        if(admin.getUsername().equals("")||admin.getPassword().equals("")){
            return new ResponseResult("-1","用户名密码不能为空！");
        }
        Admin loginAdmin = adminService.login(admin);
        if(loginAdmin!=null){
            return new ResponseResult("200","登陆成功！");
        }
        return new ResponseResult("-1","登陆失败！");
    }

    @RequestMapping("/zige")

    public ResponseResult zige(@RequestBody Teacher teacher){
        if("".equals(teacher.getTeachName())||teacher.getTeachName()==null){
            return new ResponseResult("-1","请输入教师名字！");
        }
        //System.out.println(teacher.getId()+"========================================");
        if(teacher.getId()==null||"".equals(teacher.getId())){
            return new ResponseResult("-1","请输入教师编号！");
        }
        String teachId= UUID.randomUUID().toString().replaceAll("-","");
        teacher.setTeachId(teachId);
        try{
            adminService.addTeacher(teacher);
            return new ResponseResult("200","添加成功！");
        }catch (Exception e){
            //d
            return new ResponseResult("-1","添加失败！");
        }
    }
}
