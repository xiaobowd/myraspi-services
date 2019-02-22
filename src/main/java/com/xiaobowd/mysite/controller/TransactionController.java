package com.xiaobowd.mysite.controller;

import com.xiaobowd.mysite.exception.MyException;
import com.xiaobowd.mysite.service.ITransactionService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Log4j2
@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private ITransactionService transactionService;

    @PostMapping("/alipay")
    public long importAlipayTransactionRecords(@RequestParam("file") MultipartFile file) throws MyException {
        String fileName = file.getOriginalFilename();
        try {
            log.info("-----------开始导入支付宝交易记录---------------");
            long importRecords = transactionService.importAlipayTransactionRecords(fileName, file);
            log.info("-----------导入支付宝交易记录完成---------------");
            return  importRecords;
        } catch (Exception e) {
            log.info("-----------导入支付宝交易记录错误---------------");
            log.error(e);
            return -1;
        }
    }
}
