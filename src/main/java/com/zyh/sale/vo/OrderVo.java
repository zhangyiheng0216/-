package com.zyh.sale.vo;



import lombok.Data;

import java.util.Date;
@Data
//订单的实体类 查询两张表不同字段的时候 需要单独创建一个实体类接受
public class OrderVo {
    private Integer oid;
    private Integer pid;
    private Integer status;
    private String image;
    private String title;
    private Long price;
    private Integer num;
    private Date orderTime;
    private Long totalPrice;
    private String recvName;

}
