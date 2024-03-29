package com.imooc.sell.repository;

import com.imooc.sell.dataobject.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

	@Autowired
	private OrderMasterRepository repository;

	private final  String OPENID = "110110";
	@Test
	public void saveTest(){
		OrderMaster orderMaster = new OrderMaster();
		orderMaster.setOrderId("1234567");
		orderMaster.setBuyerName("师兄");
		orderMaster.setBuyerPhone("1234567898");
		orderMaster.setBuyerAddress("慕课网");
		orderMaster.setBuyerOpenid("110110");
		orderMaster.setOrderAmount(new BigDecimal(2.5));

		OrderMaster save = repository.save(orderMaster);
		Assert.assertNotNull(save);
	}

	@Test
	public void findByBuyerOpenid(){
		PageRequest pageRequest = PageRequest.of(0,3);
		Page<OrderMaster> byBuyerOpenid = repository.findByBuyerOpenid(OPENID, pageRequest);
		Assert.assertNotEquals(0, byBuyerOpenid.getTotalElements());
		System.out.println(byBuyerOpenid.getTotalElements());
	}
}