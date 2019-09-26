package com.imooc.sell.repository;

import com.imooc.sell.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProudctCategoryRepository extends JpaRepository<ProductCategory, Integer> {
	//根据类型数组来查 方法名必须为findBy(对应的字段名)TypeIn 无力吐槽
	List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
