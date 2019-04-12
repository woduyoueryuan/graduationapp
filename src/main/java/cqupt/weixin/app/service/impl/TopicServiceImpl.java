package cqupt.weixin.app.service.impl;

import cqupt.weixin.app.dao.TopicMapper;
import cqupt.weixin.app.entity.Score;
import cqupt.weixin.app.entity.Teacher;
import cqupt.weixin.app.entity.Topic;
import cqupt.weixin.app.entity.TopicResultVo;
import cqupt.weixin.app.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {
    @Autowired
    TopicMapper topicMapper;
    @Override
    public boolean addTopic(Topic topic) {
        Integer integer = topicMapper.addTopic(topic);
        if(integer>0){
            return true;

        }else{

            return false;
        }
    }

    @Override
    public String findClassByOpenId(String openId) {
        return topicMapper.findClassByOpenId(openId);

    }

    @Override
    public List<Topic> findTopicByClass(String classByOpenId) {

        return topicMapper.findTopicByClass(classByOpenId);

    }

    @Override
    public boolean insertTopicResult(TopicResultVo topicResultVo) {
        Integer integer = topicMapper.insertTopicResult(topicResultVo);
        if(integer>0){
            return  true;
        }else{
           return false;
        }

    }

    @Override
    public List<TopicResultVo> findTopicResult() {

        List<TopicResultVo> topicResult = topicMapper.findTopicResult();
        return topicResult;
    }

    @Override
    public boolean addScore(Score score) {

        Integer integer = topicMapper.addScore(score);
        if(integer>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<Teacher> isExist(Teacher openId) {

        List<Teacher> exist = topicMapper.isExist(openId);
        return exist;
    }

    @Override
    public List<Score> checkResultInfo(Score score) {

        List<Score> scores = topicMapper.checkResultInfo(score);
        return scores;
    }

    @Override
    public List<Teacher> isTeach(String openId) {

        List<Teacher> teach = topicMapper.isTeach(openId);
        return teach;
    }
}
