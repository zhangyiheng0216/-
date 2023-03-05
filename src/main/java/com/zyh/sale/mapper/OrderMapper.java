package com.zyh.sale.mapper;


import com.zyh.sale.pojo.Order;
import com.zyh.sale.pojo.OrderItem;
import com.zyh.sale.vo.OrderVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zyh
 * @create 2023-01-28-20:47
 */
// 订单持久层接口
    @Mapper
public interface OrderMapper {

    /**
     * 插入订单数据
     * @param order 订单数据
     * @return 受影响的行数
     */
    Integer insertOrder(Order order);

    /**
     * 插入订单商品数据
     * @param orderItem 订单商品数据
     * @return 受影响的行数
     */

    Integer insertOrderItem(OrderItem orderItem);

    //java 可以定义自定义类型数据 都属于引用类型数据， 可以存在集合里面，数组只能存基本类型数据 集合存引用类型
    //根据oid查询值对象
    List<OrderVo> queryOrderVoByUid(Integer uid);

    /**
     *通过uid查询订单编号
     */
    Integer getOidByUid(Integer uid);



}
