package com.imooc.sell.controller;

import com.imooc.sell.dataobject.ProductCategory;
import com.imooc.sell.dataobject.ProductInfo;
import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.form.ProductForm;
import com.imooc.sell.service.CategoryService;
import com.imooc.sell.service.ProductService;
import com.imooc.sell.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 卖家端商品
 */
@Controller
@RequestMapping("/seller/product")
public class SellerProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;

	@GetMapping("/list")
	public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
							 @RequestParam(value = "size", defaultValue = "10") Integer size,
							 Map<String, Object> map){
		PageRequest request = PageRequest.of(page - 1, size);
		Page<ProductInfo> productInfoPage = productService.findAll(request);
		map.put("productInfoPage", productInfoPage);
		map.put("currentPage", page);
		map.put("size", size);
		return new ModelAndView("product/list", map);
	}

	/**
	 * 商品上架
	 * @param productId
	 * @param map
	 * @return
	 */
	@GetMapping("/on_sale")
	public ModelAndView onSale(@RequestParam("productId") String productId,
							   Map<String, Object> map) {
		try {
			productService.onSale(productId);
		} catch (SellException e) {
			map.put("msg", e.getMessage());
			map.put("url", "/sell/seller/product/list");
			return new ModelAndView("common/error",map);
		}

		map.put("url", "/sell/seller/product/list");

		return new ModelAndView("common/success", map);
	}

	/**
	 * 商品下架
	 * @param productId
	 * @param map
	 * @return
	 */
	@GetMapping("/off_sale")
	public ModelAndView offSale(@RequestParam("productId") String productId,
							   Map<String, Object> map) {
		try {
			productService.offSale(productId);
		} catch (SellException e) {
			map.put("msg", e.getMessage());
			map.put("url", "/sell/seller/product/list");
			return new ModelAndView("common/error",map);
		}

		map.put("url", "/sell/seller/product/list");

		return new ModelAndView("common/success", map);
	}

	/**
	 *
	 * @param productId
	 * @param map
	 * @return
	 */
	@GetMapping("/index")
	public ModelAndView index(@RequestParam(value = "productId", required = false) String productId,
								Map<String, Object> map) {
		if (!StringUtils.isEmpty(productId)) {
			ProductInfo productInfo = productService.findOne(productId);
			map.put("productInfo", productInfo);
		}
		//查询所有的类目
		List<ProductCategory> categoryList = categoryService.findAll();
		map.put("categoryList", categoryList);

		return new ModelAndView("product/index", map);
	}

	/**
	 * 保存/更新
	 * @param form
	 * @param bindingResult
	 * @param map
	 * @return
	 */
	@PostMapping("/save")
	public ModelAndView save(@Valid ProductForm form,
							 BindingResult bindingResult,
							 Map<String, Object> map) {
		if (bindingResult.hasErrors()) {
			map.put("msg", bindingResult.getFieldError().getDefaultMessage());
			map.put("url", "/sell/seller/product/index");
			return new ModelAndView("common/error",map);
		}

		ProductInfo productInfo = new ProductInfo();
		try {
			//如果id为空，则是新增
			if (!StringUtils.isEmpty(form.getProductId())){
				productInfo = productService.findOne(form.getProductId());
			} else {
				form.setProductId(KeyUtil.genUniqueKey());
			}
			BeanUtils.copyProperties(form, productInfo);

			productService.save(productInfo);
		} catch (SellException e) {
			map.put("msg", e.getMessage());
			map.put("url", "/sell/seller/product/index");
			return new ModelAndView("common/error",map);
		}


		map.put("url", "/sell/seller/product/list");
		return new ModelAndView("common/success", map);

	}
}
