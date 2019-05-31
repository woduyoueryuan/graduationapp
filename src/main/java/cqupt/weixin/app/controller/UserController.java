package cqupt.weixin.app.controller;

import com.alibaba.fastjson.JSONObject;
import cqupt.weixin.app.entity.CodeVO;
import cqupt.weixin.app.entity.Teacher;
import cqupt.weixin.app.model.WXSessionModel;
import cqupt.weixin.app.result.ResponseResult;
import cqupt.weixin.app.service.CourseService;
import cqupt.weixin.app.utils.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


@Controller
public class UserController {
    @Autowired
    CourseService courseService;

    //用户登陆
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public @ResponseBody
    ResponseResult<WXSessionModel> userLogin(@RequestBody CodeVO codeVO, HttpSession httpSession) {
        //进行用户登陆。
        //GET https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code
        String code = codeVO.getCode();
        String url = "https://api.weixin.qq.com/sns/jscode2session";
        Map<String, String> param = new HashMap<>();
        param.put("appid", "wxbf08db95a7e4aff2");
        param.put("secret", "91464d551b4c85412b20dbf71a4b4b3d");
        param.put("js_code", code);
        param.put("grant_type", "authorization_code");
        String result;
        try {
            result = HttpClientUtil.doGet(url, param);
        } catch (Exception e) {
            return new ResponseResult("-1", "系统内部错误！");
        }
        WXSessionModel wxSessionModel = JSONObject.parseObject(result, WXSessionModel.class);
        //把用户数据保存进session
        httpSession.setAttribute("wxSessionModel", wxSessionModel);
        ResponseResult<WXSessionModel> responseResult = new ResponseResult();
        responseResult.setMsg("登陆成功！");
        responseResult.setCode("200");//200表示登陆成功！
        WXSessionModel wxSessionModel1 = new WXSessionModel();
        wxSessionModel1.setOpenid(wxSessionModel.getOpenid());
        responseResult.setData(wxSessionModel1);
        //返回登陆成功的信息，每次登陆都要获取用户信息
        return responseResult;

    }

    //加入老师
    @RequestMapping("/addTeacher")
    public @ResponseBody
    ResponseResult addTeacher(@RequestBody Teacher teacher) {
        if("".equals(teacher.getTeachName())){
            return new ResponseResult("-1","请输入教师名字！");
        }
        //查询是否有教师资格
        try{
            Teacher teach = courseService.isTeach(teacher);
            if(teach==null){
                return new ResponseResult("-1","你还没有资格，请获取资格！");
            }
        }catch (Exception e){
            return new ResponseResult("-1","系统内部错误！");
        }
        boolean addTeacher = courseService.addTeacher(teacher);
        if (addTeacher) {
            return new ResponseResult("200", "添加成功！");
        } else {
            return new ResponseResult("-1", "添加失败");
        }
    }
}