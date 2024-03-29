package org.feather.skill.constants;

/**
 * @projectName: skill
 * @package: org.feather.skill.constants
 * @className: AuthorityConstant
 * @author: feather
 * @description:  授权需要使用的一些常量信息
 * @since: 14-Jan-24 11:10 AM
 * @version: 1.0
 */
public interface AuthorityConstant {
    /**
     * RSA  私钥，除了授权中心不暴露给任何客户端
     */
    String PRIVATE_KEY="MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCKjCN5vCD+0Cjv+vmpkuWw08VhC5LMkcMoI738lO3pivnzbvOVZXy4RUjZH0RycEUlkqjgAvGBo4KLajqHwRIwBopFiaVu5mOWtxnC6IJTw5tYxLCBm6bsvr0WvRk4Snnjy8WU84RZuRjyb3pfnQami/rh3mGqpwzgwtlv04p8ha5CbfEQdEDsMB5eaKOjn/Mqy+xoDzjUU9f+52VIB/XyUpJEaIwQQmfcW3n/JYMOe9zP7YFXkzXAZqmgirvKeLITRVsh0cXRlEesnAW0lYEfhznX6+qKYA9VwONo9uFgDZ+2nhOZakFJVOVEZokuMfCSyCpG/zcqTAb5KJkf6dpxAgMBAAECggEAPMudzY9mEyqYrCW/ZE/Xhq6G0DzZ3/aeYoC8n5dihra7U7z+GjJ8dgfBHbWAvY7SaNhgTlw8SZzMbgzhPizUpEpaZoUuGOn0DXCvVz3b4pHVGzDEGzuIFh7krpOv+2mC7xi9zMzG6PA2KDwOwOIQxwhpwK9qwA2psv8NluqGvHjsi/a1YAExDMC1x5hAYSgNzUMQTJY4RMKdrIVn4ubuCPbqp+687XnfRm7Jj1U6aoBZQnqlmEWV6bJHQwvu9jlyHBfIg41LFxT6Ai0jlUDlxQx8Ecx5XOeBxdz316CvornsK4OvRWQmV1k14r9cgjxEk3XHErd5ywvLvnNpmkV0oQKBgQDiqfRtcqY5lMw+t18rGr4iSWJumFbhBzxp5azs8RebKbv61FpJFXa/11zxF021t3N4vli91BkR81afGUAo4M8s6EUZ9nJArNQaRoXS1tU30p+FVyCKDehl3ktr2jSAjYV2a4pYdH/FXpqfz3cCwGjZjazqJUDUyP3iL/cJ7p8gZQKBgQCcep/Z+dznOVJeKcUEA7TMr1capc4yx0eV2BxKAvONC2EhWxIL0l+5LfpZ2Wo46Z858AopM0vdxHrEGlbt+jvdLQyj/HG1tXV7HqFNLTkFrqnGEqfZHLGAFBt9Uh1zbrpgr4t9ZCj1su9HpaK1ZhHPnI9KYeK8KOOLsHjBi1oDHQKBgQDFMmmWIu3dIMiFM9HXVX/foorBf0XupvX3h7tntOT9uFR0B/W4qbTTeJHCqqyQENekT64zEOjyxgsxha1dIyD7h+Q+jzudav9pELvyz7dWxqbEQiXsFed/YOOrJH8S7I0VlsAY7toOo5zQtpVqzm0qAOlUGC8MMQU4mNRLTtAgRQKBgQCbfst8i2w2JeMxCWbrT1eKEy+Gnn9iPF9piyDJd1dYsJcr01kCgGJ5HTRrtr50ocuFgoBGYuAgF1A7sUTzJTf52Pt3tbO1cwW0fEKj8613dRLmbsF24amNce3DQidb/wucarU9vJtrVQ2sb+9ZPX2zhotpyg3LFwLPXGUTR4wCDQKBgAzDX/PHmNqbYzCq4cYcgq77neE3KibEDKSFkJA2ebTSztX6tm8O8cqEeJr0VW3bYcn221WI2ykG1UoRWB6uCfv8nUjqyemUCxEln+V1bdtTTcvaE8uJyGifoU0Iojjv9g55JkFyykIotIoN42P9qTh6VQ8uFJILVQ06i7No/AXC";


    /**
     * 默认的 Token 超时时间, 一天
     */
   Integer DEFAULT_EXPIRE_DAY=1;
}
