package cqupt.weixin.app.controller;

import cqupt.weixin.app.entity.Score;
import cqupt.weixin.app.entity.Teacher;
import cqupt.weixin.app.entity.Topic;
import cqupt.weixin.app.entity.TopicResultVo;
import cqupt.weixin.app.model.WXSessionModel;
import cqupt.weixin.app.result.ResponseResult;
import cqupt.weixin.app.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 添加题目
 */

@RestController
public class TopicController {
    @Autowired
    TopicService topicService;
    @RequestMapping("/addTopic")
    public ResponseResult addTopic(String topic,String topicBody,String classId,String openId){
        if("".equals(topic)||"".equals(topicBody)||"".equals(classId)||"".equals(openId)){
            return new ResponseResult("-1","请输入完整信息！");
        }
        //检测当前添加用户是否为老师
        try {


            List<Teacher> teach = topicService.isTeach(openId);
            if (teach.isEmpty()) {
                return new ResponseResult("-1", "你不是老师不能添加！");
            }
        }catch (Exception e){
            return new ResponseResult("-1","添加失败！");
        }

        Topic topicVo = new Topic();
        topicVo.setClassId(classId);
        topicVo.setOpenId(openId);
        topicVo.setTopic(topic);
        topicVo.setTopicBody(topicBody);
        boolean flag;
        try {
           flag= topicService.addTopic(topicVo);
        }catch (Exception e){
            return new ResponseResult("-1","添加题目失败!");
        }
        return new ResponseResult("200","添加题目成功！");
    }
    //查询我有哪些题目可以做
    @RequestMapping("/findAllTopic")
    public ResponseResult<List<Topic>> findAllTopic(@RequestBody WXSessionModel wxSessionModel){
        //根据openid在学生表中去查属于哪个班级，然后根据班级到topic表中去查有哪些题目
        //获取openId
        if(wxSessionModel==null){
            return new ResponseResult("-1","查询题目失败！");
        }
        String openId=wxSessionModel.getOpenid();
        //根据openid查找所属班级
        String classByOpenId;
        try {
             classByOpenId = topicService.findClassByOpenId(openId);

        }catch (Exception e){
            return new ResponseResult("-1","查询班级失败！");
        }
        //根据班级去topic查找对应的习题
        List<Topic> topicList;
        try {
            topicList = topicService.findTopicByClass(classByOpenId);
        }catch (Exception e){
            return new ResponseResult("-1","查找题目失败！");
        }
        return new ResponseResult<List<Topic>>("200","查找成功！",topicList);

    }
    @RequestMapping("/addTopicResult")
    public ResponseResult addTopicResult(@RequestBody TopicResultVo topicResultVo){
        try {

            boolean b = topicService.insertTopicResult(topicResultVo);
            if(b){
                return new ResponseResult("200","提交成功！");
            }else{
                return new ResponseResult("-1","提交失败！");
            }
        }catch (Exception e){

            return new ResponseResult("-1","提交失败!");
        }

    }
    //批改作业
    @RequestMapping("/findTopicResult")
    public ResponseResult findTopicResult(@RequestBody Teacher openId){
        //判断是否有权限在教室表中去查找有无此老师 根据openid
        if("".equals(openId)||openId==null){
            return new ResponseResult("-1","教师身份有疑！");
        }
        try {
            List<Teacher> exist = topicService.isExist(openId);
            if(exist.isEmpty()){
                return new ResponseResult("-1","您不是老师！不要瞎搞！");
            }
        }catch (Exception e){

        }
        List<TopicResultVo> resultVoList;
        try {
            resultVoList = topicService.findTopicResult();
        }catch (Exception e){
            return new ResponseResult("-1","查找作业失败！");
        }
        if(resultVoList.isEmpty()){
            return new ResponseResult("200","您没有作业要批改！");
        }

        return new ResponseResult("200","您辛苦了，这是您要改的作业",resultVoList);
    }
    @RequestMapping("/addScore")
    public ResponseResult addScore(@RequestBody Score score){
        if("".equals(score.getOpenId())||"".equals(score.getReview())||"".equals(score.getTopic())||"".equals(score.getScore())){
            return new ResponseResult("-1","请您补充完整！");
        }
        boolean b;
        try {

            b = topicService.addScore(score);
        }catch (Exception e){
            return new ResponseResult("-1","系统内部错误！");
        }
        if(b){
            return new ResponseResult("200","成功！");
        }else{
            return new ResponseResult("-1","失败！");
        }
    }
    @RequestMapping("/checkResultInfo")
    public ResponseResult<List<Score>> checkResultInfo(@RequestBody Score score){
        List<Score> scores = topicService.checkResultInfo(score);
        if(scores.isEmpty()){
            return new ResponseResult("200","你没有练习题！");
        }
        return  new ResponseResult<List<Score>>("200","你的检查结果如下！",scores);
    }
}
