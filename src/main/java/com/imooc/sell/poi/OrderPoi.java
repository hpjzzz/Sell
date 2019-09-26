package com.imooc.sell.poi;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.imooc.sell.utils.serializer.Date2LongSerializer;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
@Data
public class OrderPoi implements Serializable {
	/** 订单id*/
	@Excel(name = "订单ID")
	private String orderId;
	/** 买家名字 */
	@Excel(name = "买家名字")
	private String buyerName;
	/** 买家电话 */
	@Excel(name = "买家电话")
	private String buyerPhone;
	/** 买家地址 */
	@Excel(name = "买家地址")
	private String buyerAddress;
	/** 买家微信openid */
	@Excel(name = "买家微信openid")
	private String buyerOpenid;
	/** 订单总金额 */
	@Excel(name = "订单总金额")
	private BigDecimal orderAmount;
	/** 订单状态 0是新订单 */
	@Excel(name = "订单状态")
	private Integer orderStatus;
	/** 支付状态 默认为0等待支付 */
	@Excel(name = "支付状态")
	private Integer payStatus;
	/** 创建时间 */
	@JsonSerialize(using = Date2LongSerializer.class)
	@Excel(name = "创建时间",format = "yyyy-MM-dd")
	private Date createTime;
	/** 更新时间 */
	@JsonSerialize(using = Date2LongSerializer.class)
	@Excel(name = "更新时间",format = "yyyy-MM-dd")
	private Date updateTime;
}
