package cn.curleyg.config;

import cn.curleyg.constant.UserMomentsConstant;
import cn.curleyg.entity.UserFollowing;
import cn.curleyg.entity.UserMoments;
import cn.curleyg.service.IUserFollowingService;
import cn.curleyg.utils.RedisUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;

/**
 * <p>
 *      RocketMQ配置类  TODO rocketMQ学习
 * </p>
 *
 * @author: Wang
 * @since: 2022/5/16 17:08 <br>
 */
@Configuration
public class RocketMQConfig {
    @Value("${rocketmq.name.server.address}")
    private String namesrvAddr;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private IUserFollowingService userFollowingService;

    @Bean("momentsProducer")
    public DefaultMQProducer momentsProducer() throws Exception{
        DefaultMQProducer producer = new DefaultMQProducer(UserMomentsConstant.GROUP_MOMENTS);
        producer.setSendMsgTimeout(15000);
        producer.setNamesrvAddr(namesrvAddr);
        producer.start();
        return producer;
    }

    @Bean("momentsConsumer")
    public DefaultMQPushConsumer momentsConsumer() throws Exception{
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(UserMomentsConstant.GROUP_MOMENTS);
        consumer.setNamesrvAddr(namesrvAddr);
        consumer.subscribe(UserMomentsConstant.TOPIC_MOMENTS, "*");
        consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
            MessageExt msg = msgs.get(0);
            if(msg == null){
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
            String bodyStr = new String(msg.getBody());
            UserMoments userMoment = JSONObject.toJavaObject(JSONObject.parseObject(bodyStr), UserMoments.class);
            Long userId = userMoment.getUserId();
            List<UserFollowing>fanList = userFollowingService.getUserFans(userId);
            for(UserFollowing fan : fanList){
                String key = "subscribed-" + fan.getUserId();
                String subscribedListStr = (String) redisUtil.get(key);
                List<UserMoments> subscribedList;
                if(StringUtils.isEmpty(subscribedListStr)){
                    subscribedList = new ArrayList<>();
                }else{
                    subscribedList = JSONArray.parseArray(subscribedListStr, UserMoments.class);
                }
                subscribedList.add(userMoment);
                redisUtil.set(key, JSONObject.toJSONString(subscribedList));
            }
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        consumer.start();
        return consumer;
    }

}
