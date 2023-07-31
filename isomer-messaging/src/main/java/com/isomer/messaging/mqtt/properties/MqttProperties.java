package com.isomer.messaging.mqtt.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Description:
 *
 * @author likain
 * @since 2023/7/31 21:32
 */
@ConfigurationProperties(prefix = "mqtt")
public class MqttProperties {

    private boolean enable;
    private String host;
    private String username;
    private String password;
    private Integer timeout;
    private Integer keepAlive;

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public Integer getKeepAlive() {
        return keepAlive;
    }

    public void setKeepAlive(Integer keepAlive) {
        this.keepAlive = keepAlive;
    }
}
