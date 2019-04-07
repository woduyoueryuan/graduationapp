package cqupt.weixin.app.controller;

import com.alibaba.fastjson.JSONObject;
import cqupt.weixin.app.entity.CodeVO;
import cqupt.weixin.app.model.WXSessionModel;
import cqupt.weixin.app.result.ResponseResult;
import cqupt.weixin.app.utils.HttpClientUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


@Controller
public class UserController {
    //用户登陆
    @RequestMapping(value="/login",method = RequestMethod.POST)
    public @ResponseBody
    ResponseResult<WXSessionModel> userLogin(@RequestBody CodeVO codeVO, HttpServletRequest request){
        //进行用户登陆。
        //GET https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code
        String code=codeVO.getCode();
        String url="https://api.weixin.qq.com/sns/jscode2session";
        Map<String,String> param=new HashMap<>();
        param.put("appid","wxbf08db95a7e4aff2");
        param.put("secret","91464d551b4c85412b20dbf71a4b4b3d");
        param.put("js_code",code);
        param.put("grant_type","authorization_code");
        String result;
        try {
           result = HttpClientUtil.doGet(url, param);
        }catch (Exception e){
            return new ResponseResult("-1","系统内部错误！");
        }
        WXSessionModel wxSessionModel= JSONObject.parseObject(result, WXSessionModel.class);
        HttpSession session = request.getSession();
        //把用户数据保存进session
        session.setAttribute("wxSessionModel",wxSessionModel);
        ResponseResult<WXSessionModel> responseResult=new ResponseResult();
        responseResult.setMsg("登陆成功！");
        responseResult.setCode("200");//200表示登陆成功！
        WXSessionModel wxSessionModel1=new WXSessionModel();
        wxSessionModel1.setOpenid(wxSessionModel.getOpenid());
        responseResult.setData(wxSessionModel1);
        //返回登陆成功的信息，每次登陆都要获取用户信息
        return responseResult;

    }
}
