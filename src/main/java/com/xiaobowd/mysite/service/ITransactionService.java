package com.xiaobowd.mysite.service;

import com.xiaobowd.mysite.exception.MyException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * 交易记录service
 * 用于导入各种资金交易记录
 */
public interface ITransactionService {

    /**
     * 导入支付宝交易记录
     *
     * @param fileName 文件名
     * @param file 文件
     * @return 导入成功数量
     */
    long importAlipayTransactionRecords(String fileName, MultipartFile file) throws MyException;
}
