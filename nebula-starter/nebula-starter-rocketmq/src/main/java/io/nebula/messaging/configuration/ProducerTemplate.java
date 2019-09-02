package io.nebula.messaging.configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.nebula.messaging.exception.ProduceException;
import io.nebula.messaging.support.RocketMQUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.*;
import org.apache.rocketmq.client.producer.selector.SelectMessageQueueByHash;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.core.AbstractMessageSendingTemplate;
import org.springframework.messaging.core.MessagePostProcessor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.Assert;
import org.springframework.util.MimeTypeUtils;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2019/1/14
 */
@Slf4j
public class ProducerTemplate extends AbstractMessageSendingTemplate<String> implements InitializingBean, DisposableBean {
    private final Map<String, TransactionMQProducer> cache = new ConcurrentHashMap();
    private DefaultMQProducer producer;
    private ObjectMapper objectMapper;
    private String charset = "UTF-8";
    private MessageQueueSelector messageQueueSelector = new SelectMessageQueueByHash();

    public ProducerTemplate() {
    }

    public DefaultMQProducer getProducer() {
        return this.producer;
    }

    public void setProducer(DefaultMQProducer producer) {
        this.producer = producer;
    }

    public ObjectMapper getObjectMapper() {
        return this.objectMapper;
    }

    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String getCharset() {
        return this.charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public MessageQueueSelector getMessageQueueSelector() {
        return this.messageQueueSelector;
    }

    public void setMessageQueueSelector(MessageQueueSelector messageQueueSelector) {
        this.messageQueueSelector = messageQueueSelector;
    }

    public SendResult syncSend(String destination, Message<?> message) {
        return this.syncSend(destination, message, (long) this.producer.getSendMsgTimeout());
    }

    public SendResult syncSend(String destination, Message<?> message, long timeout) {
        return this.syncSend(destination, message, timeout, 0);
    }

    public SendResult syncSend(String destination, Message<?> message, long timeout, int delayLevel) {
        if (!Objects.isNull(message) && !Objects.isNull(message.getPayload())) {
            try {
                long now = System.currentTimeMillis();
                org.apache.rocketmq.common.message.Message rocketMsg = RocketMQUtil.convertToRocketMessage(this.objectMapper, this.charset, destination, message);
                if (delayLevel > 0) {
                    rocketMsg.setDelayTimeLevel(delayLevel);
                }

                SendResult sendResult = this.producer.send(rocketMsg, timeout);
                long costTime = System.currentTimeMillis() - now;
                log.debug("send message cost: {} ms, msgId:{}", costTime, sendResult.getMsgId());
                return sendResult;
            } catch (Exception var12) {
                log.error("syncSend failed. destination:{}, message:{} ", destination, message);
                throw new MessagingException(var12.getMessage(), var12);
            }
        } else {
            log.error("syncSend failed. destination:{}, message is null ", destination);
            throw new IllegalArgumentException("`message` and `message.payload` cannot be null");
        }
    }

    public SendResult syncSend(String destination, Object payload) {
        return this.syncSend(destination, payload, (long) this.producer.getSendMsgTimeout());
    }

    public SendResult syncSend(String destination, Object payload, long timeout) {
        Message<?> message = this.doConvert(payload, (Map) null, (MessagePostProcessor) null);
        return this.syncSend(destination, message, timeout);
    }

    public SendResult syncSendOrderly(String destination, Message<?> message, String hashKey) {
        return this.syncSendOrderly(destination, message, hashKey, (long) this.producer.getSendMsgTimeout());
    }

    public SendResult syncSendOrderly(String destination, Message<?> message, String hashKey, long timeout) {
        if (!Objects.isNull(message) && !Objects.isNull(message.getPayload())) {
            try {
                long now = System.currentTimeMillis();
                org.apache.rocketmq.common.message.Message rocketMsg = RocketMQUtil.convertToRocketMessage(this.objectMapper, this.charset, destination, message);
                SendResult sendResult = this.producer.send(rocketMsg, this.messageQueueSelector, hashKey, timeout);
                long costTime = System.currentTimeMillis() - now;
                log.debug("send message cost: {} ms, msgId:{}", costTime, sendResult.getMsgId());
                return sendResult;
            } catch (Exception var12) {
                log.error("syncSendOrderly failed. destination:{}, message:{} ", destination, message);
                throw new MessagingException(var12.getMessage(), var12);
            }
        } else {
            log.error("syncSendOrderly failed. destination:{}, message is null ", destination);
            throw new IllegalArgumentException("`message` and `message.payload` cannot be null");
        }
    }

    public SendResult syncSendOrderly(String destination, Object payload, String hashKey) {
        return this.syncSendOrderly(destination, payload, hashKey, (long) this.producer.getSendMsgTimeout());
    }

    public SendResult syncSendOrderly(String destination, Object payload, String hashKey, long timeout) {
        Message<?> message = this.doConvert(payload, (Map) null, (MessagePostProcessor) null);
        return this.syncSendOrderly(destination, message, hashKey, (long) this.producer.getSendMsgTimeout());
    }

    public void asyncSend(String destination, Message<?> message, SendCallback sendCallback, long timeout) {
        if (!Objects.isNull(message) && !Objects.isNull(message.getPayload())) {
            try {
                org.apache.rocketmq.common.message.Message rocketMsg = RocketMQUtil.convertToRocketMessage(this.objectMapper, this.charset, destination, message);
                this.producer.send(rocketMsg, sendCallback, timeout);
            } catch (Exception var7) {
                log.info("asyncSend failed. destination:{}, message:{} ", destination, message);
                throw new MessagingException(var7.getMessage(), var7);
            }
        } else {
            log.error("asyncSend failed. destination:{}, message is null ", destination);
            throw new IllegalArgumentException("`message` and `message.payload` cannot be null");
        }
    }

    public void asyncSend(String destination, Message<?> message, SendCallback sendCallback) {
        this.asyncSend(destination, message, sendCallback, (long) this.producer.getSendMsgTimeout());
    }

    public void asyncSend(String destination, Object payload, SendCallback sendCallback, long timeout) {
        Message<?> message = this.doConvert(payload, (Map) null, (MessagePostProcessor) null);
        this.asyncSend(destination, message, sendCallback, timeout);
    }

    public void asyncSend(String destination, Object payload, SendCallback sendCallback) {
        this.asyncSend(destination, payload, sendCallback, (long) this.producer.getSendMsgTimeout());
    }

    public void asyncSendOrderly(String destination, Message<?> message, String hashKey, SendCallback sendCallback, long timeout) {
        if (!Objects.isNull(message) && !Objects.isNull(message.getPayload())) {
            try {
                org.apache.rocketmq.common.message.Message rocketMsg = RocketMQUtil.convertToRocketMessage(this.objectMapper, this.charset, destination, message);
                this.producer.send(rocketMsg, this.messageQueueSelector, hashKey, sendCallback, timeout);
            } catch (Exception var8) {
                log.error("asyncSendOrderly failed. destination:{}, message:{} ", destination, message);
                throw new MessagingException(var8.getMessage(), var8);
            }
        } else {
            log.error("asyncSendOrderly failed. destination:{}, message is null ", destination);
            throw new IllegalArgumentException("`message` and `message.payload` cannot be null");
        }
    }

    public void asyncSendOrderly(String destination, Message<?> message, String hashKey, SendCallback sendCallback) {
        this.asyncSendOrderly(destination, message, hashKey, sendCallback, (long) this.producer.getSendMsgTimeout());
    }

    public void asyncSendOrderly(String destination, Object payload, String hashKey, SendCallback sendCallback) {
        this.asyncSendOrderly(destination, payload, hashKey, sendCallback, (long) this.producer.getSendMsgTimeout());
    }

    public void asyncSendOrderly(String destination, Object payload, String hashKey, SendCallback sendCallback, long timeout) {
        Message<?> message = this.doConvert(payload, (Map) null, (MessagePostProcessor) null);
        this.asyncSendOrderly(destination, message, hashKey, sendCallback, timeout);
    }

    public void sendOneWay(String destination, Message<?> message) {
        if (!Objects.isNull(message) && !Objects.isNull(message.getPayload())) {
            try {
                org.apache.rocketmq.common.message.Message rocketMsg = RocketMQUtil.convertToRocketMessage(this.objectMapper, this.charset, destination, message);
                this.producer.sendOneway(rocketMsg);
            } catch (Exception var4) {
                log.error("sendOneWay failed. destination:{}, message:{} ", destination, message);
                throw new MessagingException(var4.getMessage(), var4);
            }
        } else {
            log.error("sendOneWay failed. destination:{}, message is null ", destination);
            throw new IllegalArgumentException("`message` and `message.payload` cannot be null");
        }
    }

    public void sendOneWay(String destination, Object payload) {
        Message<?> message = this.doConvert(payload, (Map) null, (MessagePostProcessor) null);
        this.sendOneWay(destination, message);
    }

    public void sendOneWayOrderly(String destination, Message<?> message, String hashKey) {
        if (!Objects.isNull(message) && !Objects.isNull(message.getPayload())) {
            try {
                org.apache.rocketmq.common.message.Message rocketMsg = RocketMQUtil.convertToRocketMessage(this.objectMapper, this.charset, destination, message);
                this.producer.sendOneway(rocketMsg, this.messageQueueSelector, hashKey);
            } catch (Exception var5) {
                log.error("sendOneWayOrderly failed. destination:{}, message:{}", destination, message);
                throw new MessagingException(var5.getMessage(), var5);
            }
        } else {
            log.error("sendOneWayOrderly failed. destination:{}, message is null ", destination);
            throw new IllegalArgumentException("`message` and `message.payload` cannot be null");
        }
    }

    public void sendOneWayOrderly(String destination, Object payload, String hashKey) {
        Message<?> message = this.doConvert(payload, (Map) null, (MessagePostProcessor) null);
        this.sendOneWayOrderly(destination, message, hashKey);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(this.producer, "Property 'producer' is required");
        this.producer.start();
    }

    @Override
    protected void doSend(String destination, Message<?> message) {
        SendResult sendResult = this.syncSend(destination, message);
        log.debug("send message to `{}` finished. result:{}", destination, sendResult);
    }

    @Override
    protected Message<?> doConvert(Object payload, Map<String, Object> headers, MessagePostProcessor postProcessor) {
        String content;
        if (payload instanceof String) {
            content = (String) payload;
        } else {
            try {
                content = this.objectMapper.writeValueAsString(payload);
            } catch (JsonProcessingException var7) {
                log.error("convert payload to String failed. payload:{}", payload);
                throw new RuntimeException("convert to payload to String failed.", var7);
            }
        }

        MessageBuilder<?> builder = MessageBuilder.withPayload(content);
        if (headers != null) {
            builder.copyHeaders(headers);
        }

        builder.setHeaderIfAbsent("contentType", MimeTypeUtils.TEXT_PLAIN);
        Message<?> message = builder.build();
        if (postProcessor != null) {
            message = postProcessor.postProcessMessage(message);
        }

        return message;
    }

    @Override
    public void destroy() {
        if (Objects.nonNull(this.producer)) {
            this.producer.shutdown();
        }

        Iterator var1 = this.cache.entrySet().iterator();

        while (var1.hasNext()) {
            Map.Entry<String, TransactionMQProducer> kv = (Map.Entry) var1.next();
            if (Objects.nonNull(kv.getValue())) {
                ((TransactionMQProducer) kv.getValue()).shutdown();
            }
        }

        this.cache.clear();
    }

    private String getTxProducerGroupName(String name) {
        return name == null ? "rocketmq_transaction_default_global_name" : name;
    }

    private TransactionMQProducer stageMQProducer(String name) throws MessagingException {
        name = this.getTxProducerGroupName(name);
        TransactionMQProducer cachedProducer = (TransactionMQProducer) this.cache.get(name);
        if (cachedProducer == null) {
            throw new MessagingException(String.format("Can not found MQProducer '%s' in cache! please define @RocketMQLocalTransactionListener class or invoke createOrGetStartedTransactionMQProducer() to create it firstly", name));
        } else {
            return cachedProducer;
        }
    }

    public TransactionSendResult sendMessageInTransaction(String txProducerGroup, String destination, Message<?> message, Object arg) throws MessagingException {
        try {
            TransactionMQProducer txProducer = this.stageMQProducer(txProducerGroup);
            org.apache.rocketmq.common.message.Message rocketMsg = RocketMQUtil.convertToRocketMessage(this.objectMapper, this.charset, destination, message);
            return txProducer.sendMessageInTransaction(rocketMsg, arg);
        } catch (MQClientException var7) {
            throw new ProduceException(var7);
        }
    }

    public void removeTransactionMQProducer(String txProducerGroup) throws MessagingException {
        txProducerGroup = this.getTxProducerGroupName(txProducerGroup);
        if (this.cache.containsKey(txProducerGroup)) {
            DefaultMQProducer cachedProducer = (DefaultMQProducer) this.cache.get(txProducerGroup);
            cachedProducer.shutdown();
            this.cache.remove(txProducerGroup);
        }

    }


}
