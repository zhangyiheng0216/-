package com.zyh.sale.service;

import com.zyh.sale.pojo.Order;
import com.zyh.sale.vo.OrderVo;

import java.util.List;

public interface OrderService {
    /**
     * 创建订单
     * @param aid 收货地址的id
     * @param cids 即将购买的商品数据在购物车表中的id
     * @param uid 当前登录的用户的id
     * @param username 当前登录的用户名
     * @return 成功创建的订单数据
     */

    Order create(Integer aid, Integer uid, String username, Integer[] cids);

    //根据uid查询值对象
    List<OrderVo> queryOrderVoByUid(Integer uid);

    //根据uid查询订单编号
    Integer getOidByUid(Integer uid);

}
