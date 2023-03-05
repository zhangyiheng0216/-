package com.zyh.sale.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yujiangsheng
 * @create 2022-04-14-11:13
 */
//购物车数据的VO类 查询两张表不同字段的时候 需要单独创建一个实体类
@Data
public class CartVO implements Serializable {
    private Integer cid;
    private Integer uid;
    private Integer pid;
    private Long price;
    private Integer num;
    private String title;
    private String image;
    private Long realPrice;
}
