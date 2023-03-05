package com.zyh.sale;

import com.mysql.cj.util.Util;
import com.zyh.sale.service.OrderService;
import com.zyh.sale.utils.OrdersUtiles;
import com.zyh.sale.vo.OrderVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
class SaleApplicationTests {
	@Autowired
	OrderService orderService;
	@Test
	void contextLoads() {
		List<OrderVo> byUid = orderService.queryOrderVoByUid(2);
		OrdersUtiles ordersUtiles = new OrdersUtiles();
		System.out.println(byUid.size());
		List<Map<Integer, List<OrderVo>>> maps = ordersUtiles.changeTypeOrder(byUid);
		System.out.println(maps.get(0).get(2).get(0).getTotalPrice());
//		for(int i=0 ; i< maps.size();i++){
//			for (int j = 0; j < maps.get(i).size(); j++) {
//
//			}
//		}
	}

}
