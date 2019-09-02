package io.nebula.messaging.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.nebula.messaging.properties.MessagingProperties;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2019/1/14
 */
@Configuration
@EnableConfigurationProperties(MessagingProperties.class)
public class RocketConfiguration {
    @Autowired
    private MessagingProperties mqProperties;
    @Autowired
    private ObjectMapper objectMapper;

    @Bean
    public ProducerTemplate producerTemplate() {
        ProducerTemplate template = new ProducerTemplate();
        template.setObjectMapper(objectMapper);
        template.setProducer(defaultMQProducer());
        return template;
    }

    @Bean
    public DefaultMQProducer defaultMQProducer() {
        DefaultMQProducer producer = new DefaultMQProducer(mqProperties.getProducerGroup());
        producer.setNamesrvAddr(mqProperties.getServerUrl());
//        producer.setSendMsgTimeout(mqProperties.getSendMessageTimeout());
//        producer.setRetryTimesWhenSendFailed(mqProperties.getRetryTimesWhenSendFailed());
//        producer.setRetryTimesWhenSendAsyncFailed(mqProperties.getRetryTimesWhenSendAsyncFailed());
//        producer.setMaxMessageSize(mqProperties.getMaxMessageSize());
//        producer.setCompressMsgBodyOverHowmuch(mqProperties.getCompressMessageBodyThreshold());
//        producer.setRetryAnotherBrokerWhenNotStoreOK(mqProperties.isRetryNextServer());

        return producer;
    }
}
