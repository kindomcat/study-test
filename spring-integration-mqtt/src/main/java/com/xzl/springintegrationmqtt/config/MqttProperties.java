package com.xzl.springintegrationmqtt.config;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import java.util.List;

/***
 * @descirption mqtt协议实体
 * @author pengyu
 * @date 2019/05/23
 * @version v0.0.1
 */
@Data
@Component
@ConfigurationProperties(prefix = "mqtt")
public class MqttProperties {
    private String username;
    private String password;
    private String urls;
    private List<String> subTopic ;
    private List<Integer> qosValues;
    private String pubTopic;
}
