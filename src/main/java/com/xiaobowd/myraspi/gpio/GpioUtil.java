package com.xiaobowd.myraspi.gpio;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;

public class GpioUtil {
    private static final GpioController GPIO_CONTROLLER = GpioFactory.getInstance();

    private static GpioUtil ourInstance = new GpioUtil();

    public static GpioUtil getInstance() {
        return ourInstance;
    }

    private GpioUtil() {
    }
    public static GpioController getGpioController(){
        return GPIO_CONTROLLER;
    }
}
