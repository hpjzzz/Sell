package com.imooc.sell.easypoi;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.poi.OrderPoi;
import com.imooc.sell.service.impl.OrderServiceImpl;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EasypoiTest {

	@Autowired
	private OrderServiceImpl orderService;

	@Test
	public void test() throws Exception {
		Student student = new Student();
		student.setId(1L);
		student.setName("张三");
		student.setSex("男");

		Student student2 = new Student();

		student2.setId(2L);
		student2.setName("李四");
		student2.setSex("男");

		List<Student> list = new ArrayList<>();
		list.add(student);
		list.add(student2);

		list.stream().forEach(e -> System.out.println(e));


		/**
		 * 进行相应的展出
		 *  参数1:一些基本配置(表头等)
		 *  参数2:导出的类型
		 *  参数3:导出的数据
		 */
		Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), Student.class, list);

		FileOutputStream fos = new FileOutputStream("emp.xls");
		workbook.write(fos);
		fos.close();

	}

	@Test
	public void testImport() throws Exception{
		Page<OrderDTO> list = orderService.findList(PageRequest.of(0, 10));

		List<OrderDTO> content = list.getContent();
		List<OrderPoi> poiList = new ArrayList<>();
		for (OrderDTO orderDTO : content) {
			OrderPoi orderPoi = new OrderPoi();
			BeanUtils.copyProperties(orderDTO, orderPoi);
			poiList.add(orderPoi);
		}
		poiList.stream().forEach(System.out::println);
		Workbook wor = ExcelExportUtil.exportExcel(new ExportParams("测试表","订单", ExcelType.XSSF), OrderPoi.class, poiList);
		FileOutputStream fos = new FileOutputStream("zzz.xlsx");
		wor.write(fos);
		fos.close();
	}

	@Test
	public void testOutput() throws Exception{

		ImportParams params = new ImportParams();
		params.setTitleRows(1);
		params.setHeadRows(1);

		List<OrderPoi> list = ExcelImportUtil.importExcel(new File("zzz.xlsx"), OrderPoi.class, params);

		list.stream().forEach(System.out::println);

	}


}
