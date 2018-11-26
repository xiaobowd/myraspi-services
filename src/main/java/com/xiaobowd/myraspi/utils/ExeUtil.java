package com.xiaobowd.myraspi.utils;


import com.xiaobowd.myraspi.controller.ImageController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.InputStreamReader;


/**
 * 执行外部命令的工具类
 *
 * @author xiaobowd
 */
public class ExeUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ImageController.class);


    /**
     * 执行系统命令, 返回执行结果
     *
     * @param cmd 需要执行的命令
     * @param dir 执行命令的子进程的工作目录, null 表示和当前主进程工作目录相同
     */
    public static String execCmd(String cmd, File dir) {
        StringBuilder result = new StringBuilder();

        Process process = null;
        BufferedReader bufferedReader = null;
        BufferedReader bufferErrorReader = null;

        try {
            String[] command = {"/bin/sh", "-c", cmd};
            LOGGER.info("开始执行命令:");
// 执行命令, 返回一个子进程对象（命令在子进程中执行）
            process = Runtime.getRuntime().exec(command, null, dir);

// 方法阻塞, 等待命令执行完成（成功会返回0）
            process.waitFor();

// 获取命令执行结果, 有两个结果: 正常的输出 和 错误的输出（PS: 子进程的输出就是主进程的输入）
            bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"));
            bufferErrorReader = new BufferedReader(new InputStreamReader(process.getErrorStream(), "UTF-8"));

// 读取输出
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                result.append(line).append('\n');
            }
            while ((line = bufferErrorReader.readLine()) != null) {
                result.append(line).append('\n');
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        } finally {
            closeStream(bufferedReader);
            closeStream(bufferErrorReader);

// 销毁子进程
            if (process != null) {
                process.destroy();
            }
        }

// 返回执行结果
        return result.toString();
    }

    private static void closeStream(Closeable stream) {
        if (stream != null) {
            try {
                stream.close();
            } catch (Exception e) {
// nothing
            }
        }
    }
}