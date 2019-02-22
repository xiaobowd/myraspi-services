create table T_ALIPAY_TRANSACTION_RECORDS
(
  transaction_id          varchar(100) not null
  comment '交易号'
    primary key,
  business_id             varchar(100) null
  comment '商家订单号',
  transaction_create_date datetime     null
  comment '交易创建时间',
  payed_date              datetime     null
  comment '付款时间',
  last_update_date        datetime     null
  comment '最近修改时间',
  transaction_source      varchar(100) null
  comment '交易来源地',
  transaction_type        varchar(100) null
  comment '类型',
  transaction_target      varchar(300) null
  comment '交易对方',
  good_name               varchar(300) null
  comment '商品名称',
  amount                  double       null
  comment '金额（元)',
  transaction_direction   int          null
  comment '收(1)/支(0)',
  transaction_status      varchar(100) null
  comment '交易状态',
  service_fee             double       null
  comment '服务费（元)',
  successful_refund       double       null
  comment '成功退款（元）',
  remark                  varchar(300) null
  comment '备注',
  fund_status             int          null
  comment '资金状态:已支出:0,已收入:1,资金转移 :2'
)
  comment '支付宝交易记录';

