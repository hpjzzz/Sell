package com.imooc.sell.service.impl;

import com.imooc.sell.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {

	@Autowired
	private CategoryServiceImpl service;
	@Test
	public void findOne() throws Exception{
		ProductCategory one = service.findOne(1);
		System.out.println(one);
		Assert.assertEquals(new Integer(1),one.getCategoryId());
	}

	@Test
	public void findAll() {
		List<ProductCategory> all = service.findAll();
		Assert.assertNotEquals(0,all.size());
	}

	@Test
	public void findByCategoryTypeIn() {
		List<ProductCategory> byCategoryTypeIn = service.findByCategoryTypeIn(Arrays.asList(1, 2, 3, 4));
		System.out.println(byCategoryTypeIn);
		Assert.assertNotEquals(0,byCategoryTypeIn.size());
	}

	@Test
	public void save() {
		ProductCategory category = new ProductCategory("扶她专享", 4);
		ProductCategory save = service.save(category);
		Assert.assertNotEquals(null, save);
	}
}