package com.luckybees.messager;

import com.luckybees.beichen.dto.MsgDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.UUID;


@Component
public class SenderWithCallback {
    Logger log= LoggerFactory.getLogger(SenderWithCallback.class);

    @Autowired
    RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void initRabbitTemplate() {
        // 设置生产者消息确认
        rabbitTemplate.setConfirmCallback(new RabbitConfirmCallback());
        rabbitTemplate.setReturnCallback(new RabbitReturnCallback());
    }


    public void send() {
        String exchange = RabbitConfig.EXCHANGE_DEFAULT;
        String routingKey = RabbitConfig.RK_BEICHEN_REPORT_RESULT;
        String message = LocalDateTime.now().toString() + "发送一条消息.";

        MsgDTO msg = new MsgDTO();
        HashMap decision= new HashMap<String,Object>();
        decision.put("score",232);
        decision.put("result","pass");
        msg.setDecision(decision);
        msg.setIdCard("3101139191992929233");
        msg.setMobile("13988383883");
        msg.setRef("12");
        msg.setName("小米");
        msg.setOrganizationKey("1");
        msg.setProductKey("1");
        msg.setSn(UUID.randomUUID().toString());
        msg.setTimestamp(java.util.Calendar.getInstance().getTimeInMillis());
        rabbitTemplate.convertAndSend(exchange, routingKey, msg);

        log.info("发送一条消息,exchange:[{}],routingKey:[{}],message:[{}]", exchange, routingKey, message);

    }

}



