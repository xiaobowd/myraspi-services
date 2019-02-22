package com.xiaobowd.mysite.bean;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 支付宝交易记录
 */

@Data
public class AlipayTransactionRecordVO {

    /**
     * 交易号
     */
    private String transactionId;
    /**
     * 商家订单号
     */
    private String businessId;

    /**
     * 交易创建时间
     */
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date transactionCreateDate;
    /**
     * 付款时间
     */
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date payedDate;
    /**
     * 最近修改时间
     */
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date lastUpdateDate;
    /**
     * 交易来源地
     */
    private String transactionSource;
    /**
     * 类型
     */
    private String transactionType;
/**
 * 交易对方
 */
    private String transactionTarget;

    /**
     * 商品名称
     */
    private String goodName;

    /**
     * 金额（元）
     */
    private Double amount;

    /**
     * 收/支
     */
    private Integer transactionDirection;

    /**
     * 交易状态
     */
    private String transactionStatus;

    /**
     * 服务费（元）
     */
    private Double serviceFee;

    /**
     * 成功退款（元）
     */
    private Double successfulRefund;


    /**
     * 备注
     */
    private String remark;
    /**
     * 资金状态
     */
    private Integer fundStatus;
}
