package com.imooc.sell.easypoi;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.io.Serializable;
@Data
public class Student implements Serializable {

	private Long id;
	@Excel(name = "用户名称")
	private String name;
	@Excel(name = "性别", replace = {"true_男","false_女"})
	private String sex;


}
