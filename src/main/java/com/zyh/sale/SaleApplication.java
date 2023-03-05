package com.zyh.sale;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

//开启组件扫描
@ServletComponentScan("com.zyh.sale")
@MapperScan(basePackages = {"com.zyh.sale.mapper"})
@SpringBootApplication
public class SaleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SaleApplication.class, args);
	}

}
