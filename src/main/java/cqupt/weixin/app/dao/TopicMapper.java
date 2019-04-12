package cqupt.weixin.app.dao;

import cqupt.weixin.app.entity.Score;
import cqupt.weixin.app.entity.Teacher;
import cqupt.weixin.app.entity.Topic;
import cqupt.weixin.app.entity.TopicResultVo;

import java.util.List;

public interface TopicMapper {
    Integer addTopic(Topic topic);

    String findClassByOpenId(String openId);

    List<Topic> findTopicByClass(String classByOpenId);

    Integer insertTopicResult(TopicResultVo topicResultVo);

    List<TopicResultVo> findTopicResult();

    Integer addScore(Score score);

    List<Teacher> isExist(Teacher openId);

    List<Score> checkResultInfo(Score score);

    List<Teacher> isTeach(String openId);
}
