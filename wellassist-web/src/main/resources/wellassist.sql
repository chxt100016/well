CREATE TABLE `wa_order_logistics_info` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '物理主键',
  `orderId` BIGINT(20) DEFAULT NULL COMMENT '订单ID',
  `delivery_time` DATE DEFAULT NULL COMMENT '提交订单时填写的发货时间',
  `receive_time` DATE DEFAULT NULL COMMENT '提交订单时填写的预计到达时间',
  `contact_Person` VARCHAR(20) DEFAULT NULL COMMENT '联系人',
  `contact_phone` CHAR(11) DEFAULT NULL COMMENT '联系电话',
  `address` VARCHAR(100) DEFAULT NULL COMMENT '配送地址',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8


CREATE TABLE `wa_vehicle_info` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '物理id',
  `order_id` BIGINT(20) DEFAULT NULL COMMENT '订单id',
  `name` VARCHAR(16) DEFAULT NULL COMMENT '司机姓名',
  `phone` CHAR(11) DEFAULT NULL COMMENT '司机电话',
  `car_code` VARCHAR(10) DEFAULT NULL COMMENT '车牌号',
  PRIMARY KEY (`id`)
) E