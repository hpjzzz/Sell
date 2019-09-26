package com.imooc.sell.service.impl;

import com.imooc.sell.dataobject.ProductInfo;
import com.imooc.sell.enums.ProductStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {

	@Autowired
	private ProductServiceImpl productService;
	@Test
	public void findOne() {
		ProductInfo one = productService.findOne("123456");
		Assert.assertEquals("123456", one.getProductId());
	}

	@Test
	public void findUpAll() {
		List<ProductInfo> upAll = productService.findUpAll();
		Assert.assertNotEquals(0, upAll.size());
	}

	@Test
	public void findAll() {
		PageRequest pageRequest = PageRequest.of(0,2);
		Page<ProductInfo> all = productService.findAll(pageRequest);
		System.out.println(all.getTotalElements());
		Assert.assertNotEquals(0,all.getTotalElements());
	}

	@Test
	public void save() {
		ProductInfo productInfo = new ProductInfo();
		productInfo.setProductId("123457");
		productInfo.setProductName("皮皮虾");
		productInfo.setProductPrice(new BigDecimal(3.2));
		productInfo.setProductStock(100);
		productInfo.setProductDescription("很好吃的虾");
		productInfo.setProductIcon("http://xxxx.jpg");
		productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
		productInfo.setCategoryType(2);

		ProductInfo save = productService.save(productInfo);
		Assert.assertNotNull(save);
	}
}