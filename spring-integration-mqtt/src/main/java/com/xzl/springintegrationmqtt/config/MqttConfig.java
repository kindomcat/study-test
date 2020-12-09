package com.xzl.springintegrationmqtt.config;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.endpoint.PollingConsumer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.PollableChannel;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Arrays;

/**
 * @author tonypy
 * 2019/4/21
 * MQTT接收消息处理配置
 */
@Configuration
public class MqttConfig {


    @Bean("mqttHandlerMessage")
    public AsyncTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setMaxPoolSize(100);
        taskExecutor.setCorePoolSize(24);
        taskExecutor.setThreadNamePrefix("receive-mqtt-pool");
        // 队列大小
        taskExecutor.setQueueCapacity(100);
        taskExecutor.initialize();
        return taskExecutor;
    }

    /**
     * mqtt消费者使用的通道
     */
    public static final String CHANNEL_NAME_IN = "mqttInboundChannel";
    /**
     * mqtt生产者使用的通道
     */
    public static final String CHANNEL_NAME_OUT = "mqttOutboundChannel";


    @Autowired
    private MqttProperties mqttProperties;

    /**
     * mqtt连接器
     */
    @Bean
    public MqttConnectOptions getMqttConnectOptions1() {
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setUserName(mqttProperties.getUsername());
        mqttConnectOptions.setPassword(mqttProperties.getPassword().toCharArray());
        mqttConnectOptions.setServerURIs( mqttProperties.getUrls().split(","));
        return mqttConnectOptions;
    }


    /**
     * mqtt客户端
     */
    @Bean
    public MqttPahoClientFactory mqttClientFactory1() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        factory.setConnectionOptions(getMqttConnectOptions1());
        return factory;
    }

    /**
     * mqtt消费者
     *
     * @return
     */
/*    @Bean(name = CHANNEL_NAME_IN)
    public QueueChannel consumerChannel() {
        return new QueueChannel();
    }*/

    @Bean(name = CHANNEL_NAME_IN)
    public DirectChannel consumerChannel() {
        return new DirectChannel();
    }

    /**
     * mqtt消费者处理器
     *
     * @return
     */
    @Bean
    public MessageProducer inbound1() {
        String[] strings = new String[mqttProperties.getSubTopic().size()];
        Integer[] ints = new Integer[mqttProperties.getQosValues().size()];
        mqttProperties.getSubTopic().toArray(strings);
        mqttProperties.getQosValues().toArray(ints);
        int[] its = Arrays.stream(ints).mapToInt(Integer::valueOf).toArray();
        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(MqttAsyncClient.generateClientId(), mqttClientFactory1(), strings);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setOutputChannel(consumerChannel());
        adapter.setRole(mqttProperties.getUrls());
        adapter.setQos(its);
        return adapter;
    }

}
