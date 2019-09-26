package com.imooc.sell.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.imooc.sell.dataobject.OrderDetail;
import com.imooc.sell.enums.OrderStatusEnum;
import com.imooc.sell.enums.PayStatusEnum;
import com.imooc.sell.utils.EnumUtil;
import com.imooc.sell.utils.serializer.Date2LongSerializer;
import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
//@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO implements Serializable {
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
	@Excel(name = "支付状态")
	private Date createTime;
	/** 更新时间 */
	@JsonSerialize(using = Date2LongSerializer.class)
	@Excel(name = "支付状态")
	private Date updateTime;
	@ExcelIgnore
	private List<OrderDetail> orderDetailList;

	@JsonIgnore
	public OrderStatusEnum getOrderStatusEnum() {
		return EnumUtil.getByCode(orderStatus, OrderStatusEnum.class);
	}
	@JsonIgnore
	public PayStatusEnum getPayStatusEnum() {
		return EnumUtil.getByCode(payStatus, PayStatusEnum.class);
	}
}
