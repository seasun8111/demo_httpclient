package com.luckybees.messager;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitConfig {


    public static final String QUEUE_BEICHEN_APRV_RESULT = "queue_beichen_aprv_result";
    public static final String QUEUE_BEICHEN_REPEAT = "queue_beichen_repeat";
    public static final String QUEUE_BEICHEN_REPORT_SAVED = "queue_beichen_report_saved";
    public static final String QUEUE_BEICHEN_DLX = "queue_beichen_dlx";
    public static final String RK_BEICHEN_REPORT_SAVED= "RK_BeichenReportSaved";
    public static final String RK_BEICHEN_REPORT_RESULT= "RK_BeichenReportResult";
    public static final String RK_REPORT_QUEUE= "RK_RepeatQueue";
    public static final String RK_DEADLETTER_REPORT_= "RK_DeadLetterQueue";
    public static final String EXCHANGE_DEFAULT ="exchange_beichen_default";




    //资源owner账户 ID 信息
    private static final long RESOURCE_OWNER_ID =1949057400426795L;

    @Autowired
    private RabbitProperties rabbitProperties;

    @Bean
    public ConnectionFactory connectionFactory() {
        com.rabbitmq.client.ConnectionFactory rabbitConnectionFactory =
                new com.rabbitmq.client.ConnectionFactory();
        rabbitConnectionFactory.setHost(rabbitProperties.getHost());
        rabbitConnectionFactory.setPort(rabbitProperties.getPort());
        rabbitConnectionFactory.setVirtualHost(rabbitProperties.getVirtualHost());
//      本地 rabbitmq
//        rabbitConnectionFactory.setUsername(rabbitProperties.getUsername());
//        rabbitConnectionFactory.setPassword(rabbitProperties.getPassword());

//      阿里云 rabbitmq
        AliyunCredentialsProvider credentialsProvider = new AliyunCredentialsProvider(
                rabbitProperties.getUsername(), rabbitProperties.getPassword(), RESOURCE_OWNER_ID);
        rabbitConnectionFactory.setCredentialsProvider(credentialsProvider);

        rabbitConnectionFactory.setAutomaticRecoveryEnabled(true);
        rabbitConnectionFactory.setNetworkRecoveryInterval(5000);
        ConnectionFactory connectionFactory = new CachingConnectionFactory(rabbitConnectionFactory);
        ((CachingConnectionFactory)connectionFactory).setPublisherConfirms(rabbitProperties.isPublisherConfirms());
        ((CachingConnectionFactory)connectionFactory).setPublisherReturns(rabbitProperties.isPublisherReturns());
        return connectionFactory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }


    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public DirectExchange defaultExchange() {
        return new DirectExchange(EXCHANGE_DEFAULT, true, false);
    }


    //报告保存后通知队列
    @Bean
    public Queue reportSavedQueue() {
        Map<String, Object> arguments = new HashMap<>(4);

        return new Queue(QUEUE_BEICHEN_REPORT_SAVED, true, false, false, arguments);
    }

    //aprv结果通知队列
    @Bean
    public Queue aprvResultQueue() {
        Queue queue = new Queue(QUEUE_BEICHEN_APRV_RESULT,true,false,false);
        return queue;
    }


    //重试队列
    @Bean
    public Queue repeatTradeQueue() {
        Queue queue = new Queue(QUEUE_BEICHEN_REPEAT,true,false,false);
        return queue;
    }


    //死信队列  -- 消息在死信队列上堆积，消息超时时，会把消息转发到重试队列，转发队列根据消息内容再把转发到指定的重试队列上
    @Bean
    public Queue deadLetterQueue() {
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-dead-letter-exchange", EXCHANGE_DEFAULT);
        arguments.put("x-dead-letter-routing-key", RK_REPORT_QUEUE);
        Queue queue = new Queue(QUEUE_BEICHEN_DLX,true,false,false,arguments);
        return queue;
    }

    //绑定报告队列
    @Bean
    public Binding beiChenReportSavedBinding() {
        return BindingBuilder.bind(reportSavedQueue()).to(defaultExchange()).with(RK_BEICHEN_REPORT_SAVED);
    }

    //绑定结果队列
    @Bean
    public Binding beiChenAprvResultBinding() {
        return BindingBuilder.bind(aprvResultQueue()).to(defaultExchange()).with(RK_BEICHEN_REPORT_RESULT);
    }


    //绑定重试队列
    @Bean
    public Binding  repeatTradeBinding() {
        return BindingBuilder.bind(repeatTradeQueue()).to(defaultExchange()).with(RK_REPORT_QUEUE);
    }


    //绑定死信队列
    @Bean
    public Binding  deadLetterBinding() {
        return BindingBuilder.bind(deadLetterQueue()).to(defaultExchange()).with(RK_DEADLETTER_REPORT_);
    }


}