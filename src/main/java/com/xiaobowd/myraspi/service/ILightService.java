package com.xiaobowd.myraspi.service;

import org.springframework.stereotype.Service;

/**
 * 灯光控制服务
 */
public interface ILightService {

    /**
     * 开灯
     * @return 开灯结果
     * @throws Exception 异常
     */
    String turnLightOn() throws Exception;

    /**
     * 关灯
     * @return 关灯结果
     * @throws Exception 异常
     */
    String turnLightOff() throws Exception;
}
