package com.imooc.sell.service.impl;

import com.imooc.sell.dataobject.ProductCategory;
import com.imooc.sell.repository.ProudctCategoryRepository;
import com.imooc.sell.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private ProudctCategoryRepository repository;
	@Override
	public ProductCategory findOne(Integer categoryId) {
		return repository.findById(categoryId).orElse(null);
	}

	@Override
	public List<ProductCategory> findAll() {
		return repository.findAll();
	}

	@Override
	public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
		return repository.findByCategoryTypeIn(categoryTypeList);
	}

	@Override
	public ProductCategory save(ProductCategory productCategory) {
		return repository.save(productCategory);
	}
}
