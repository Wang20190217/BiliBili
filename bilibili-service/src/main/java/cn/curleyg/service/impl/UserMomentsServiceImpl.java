package cn.curleyg.service.impl;

import cn.curleyg.constant.UserMomentsConstant;
import cn.curleyg.entity.UserMoments;
import cn.curleyg.mapper.UserMomentsMapper;
import cn.curleyg.service.IUserMomentsService;
import cn.curleyg.utils.RedisUtil;
import cn.curleyg.utils.RocketMQUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 用户动态表 服务实现类
 * </p>
 *
 * @author Wang
 * @since 2022-05-16
 */
@Service
public class UserMomentsServiceImpl extends ServiceImpl<UserMomentsMapper, UserMoments> implements IUserMomentsService {
    @Autowired
    UserMomentsMapper userMomentsMapper;

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    RedisUtil redisUtil;

    @Override
    public void addUserMoments(UserMoments userMoments) throws Exception {
        userMoments.setCreateTime(new Date());
        userMomentsMapper.insert(userMoments);
        //获取到发布者对象
        DefaultMQProducer producer = (DefaultMQProducer) applicationContext.getBean("momentsProducer");
        Message message=new Message(UserMomentsConstant.TOPIC_MOMENTS, JSONObject.toJSONString(userMoments).getBytes(StandardCharsets.UTF_8));
        RocketMQUtil.syncSendMsg(producer, message);
    }

    @Override
    public List<UserMoments> getUserSubscribedMoments(Long userId) {
        String key = "subscribed-" + userId;
        String listStr = (String) redisUtil.get(key);
        return JSONArray.parseArray(listStr, UserMoments.class);
    }


}
