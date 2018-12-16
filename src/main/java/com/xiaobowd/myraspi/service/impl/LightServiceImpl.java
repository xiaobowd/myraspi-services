package com.xiaobowd.myraspi.service.impl;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.xiaobowd.myraspi.gpio.GpioUtil;
import com.xiaobowd.myraspi.service.ILightService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
@Service
public class LightServiceImpl implements ILightService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LightServiceImpl.class);

    @Override
    public String turnLightOn() throws Exception{
            final GpioController gpio = GpioUtil.getGpioController();
            final GpioPinDigitalOutput light = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_11, "MyLight", PinState.HIGH);
            light.setShutdownOptions(true, PinState.LOW);
             light.blink(10,10) ;
             return "开灯成功!";
    }

    @Override
    public String turnLightOff() throws Exception{
        final GpioController gpio = GpioUtil.getGpioController();
        final GpioPinDigitalOutput light = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_11, "MyLight", PinState.HIGH);
        light.low();
        light.setShutdownOptions(true, PinState.LOW);
        light.blink(10, 10);
        return "关灯成功!";
    }
}
