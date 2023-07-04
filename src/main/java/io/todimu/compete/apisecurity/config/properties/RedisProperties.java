package io.todimu.compete.apisecurity.config.properties;

import lombok.Data;

@Data
public class RedisProperties {
    private String host;
    private int port;
    private int database;
    private String username;
    private String password;
    private String timeout;
    private int timeToLive;
}