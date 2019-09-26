package com.imooc.sell.freemarke;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.io.File;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.HashMap;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FreemarkerUtil {
	//加载驱动
	@Autowired
	private DataSource dataSource;

	@Test
	public void creatDomain() throws Exception{
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);

		File f = new File("F:\\java res\\sell\\src\\main\\resources\\templates");

		cfg.setDirectoryForTemplateLoading(f);

		Template template = cfg.getTemplate("java2.ftl");

		//获取连接
		Connection connection = dataSource.getConnection();

		//预编译对象
		String sql = "select * from order_master";
		PreparedStatement pst = connection.prepareStatement(sql);

		//执行sql语句
		ResultSet rs = pst.executeQuery();
		//获取元数据
		ResultSetMetaData rsmd = rs.getMetaData();
		int count = rsmd.getColumnCount();
		/**
		 * 遍历元数据并封装属性
		 *
		 */
		HashMap<String, Object> clz = new HashMap<>();
		for (int i = 1; i<= count; i++){
			//存放字段信息
			HashMap<String, Object> field = new HashMap<>();
			//获取字段名
			String name = rsmd.getColumnLabel(i);
			//生成get/set名字
			String getName = "get"+name.substring(0,1).toUpperCase()+name.substring(1);
			String setName = "set"+name.substring(0,1).toUpperCase()+name.substring(1);
			//获取类型
			String type = rsmd.getColumnClassName(i);

			field.put("name", name);
			field.put("type", type);
			field.put("getName", getName);
			field.put("setName", setName);

			clz.put(name, field);
		}
		//第三个map封装所有字段
		HashMap<String, Object> table = new HashMap<>();
		table.put("className", "OrderMaster");
		table.put("table", clz);

		//生成页面
		String fileName = "OrderMaster.java";
		PrintWriter pw = new PrintWriter(new File(f, fileName),"UTF-8");
		template.process(table,pw);
		pw.close();

	}
}
