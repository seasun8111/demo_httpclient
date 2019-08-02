package com.luckybees.messager;

import com.luckybees.beichen.dto.MsgDTO;
import com.tiefan.iwu.preaprv.core.mq.extension.ExternalMXResVO;
import com.tiefan.iwu.preaprv.enums.CreditRuleEnum;
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
        ExternalMXResVO  externalMXResVO= new ExternalMXResVO();
        externalMXResVO.setMobile("1343243242");
        externalMXResVO.setName("vdfsfsd");
        externalMXResVO.setIdCard("234234234");
        externalMXResVO.setScore(232);
//        ex.setResult(CreditRuleEnum.PASS.toString());
        externalMXResVO.setRef("101f0ede013f3482");
        externalMXResVO.setTaskId("23232");
        externalMXResVO.setRequestNo("23");
        externalMXResVO.setErrMsg("");
        externalMXResVO.setIspDataExpire(1);
        externalMXResVO.setErrCode("");


        rabbitTemplate.convertAndSend(exchange, routingKey, externalMXResVO);

        log.info("发送一条消息,exchange:[{}],routingKey:[{}],message:[{}]", exchange, routingKey, externalMXResVO);

    }

}



