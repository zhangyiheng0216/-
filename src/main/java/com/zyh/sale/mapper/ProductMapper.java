package com.zyh.sale.mapper;


import com.zyh.sale.pojo.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author zyh
 * @create 2023-01-22-22:42
 */

/** 处理商品数据的持久层接口 */
@Mapper
public interface ProductMapper {

    /**
     * 查询热销商品的前四名
     * @return 热销商品前四名的集合
     */

    List<Product> findHotList();

    /**
     * 根据商品id查询商品详情
     * @param id 商品id
     * @return 匹配的商品详情，如果没有匹配的数据则返回null
     */

    Product findById(Integer id);

    /**
     * 模糊查询
     */
    List<Product> find(String str);
}
