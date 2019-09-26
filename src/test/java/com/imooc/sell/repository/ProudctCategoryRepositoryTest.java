package com.imooc.sell.repository;

import com.imooc.sell.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProudctCategoryRepositoryTest {

	@Autowired
	private ProudctCategoryRepository repository;

	@Test
	public void findOneTest(){
		ProductCategory productCategory = repository.findById(1).orElse(null);
		System.out.println(productCategory);
	}

	@Test
	@Transactional// 在测试中使用时，全部会回滚
	public void saveTest(){
		ProductCategory pr = new ProductCategory("男生最爱", 2);
		ProductCategory result = repository.save(pr);
		Assert.assertNotNull(result);
//		Assert.assertNotEquals(null,result);
	}

	@Test
	public void findByCategoryTypeInTest(){
		List<Integer> list = Arrays.asList(1,3,4);
		List<ProductCategory> byCategoryTypeIn = repository.findByCategoryTypeIn(list);
		Assert.assertNotEquals(0,byCategoryTypeIn);
	}
}