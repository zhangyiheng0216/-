package com.zyh.sale.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yujiangsheng
 * @create 2022-04-16-20:41
 */

/** 订单数据的实体类 */

@Data
public class Order extends BaseEntity implements Serializable {
    private Integer oid;
    private Integer uid;
    private String recvName;
    private String recvPhone;
    private String recvProvince;
    private String recvCity;
    private String recvArea;
    private String recvAddress;
    private Long totalPrice;
    private Integer status;
    private Date orderTime;
    private Date payTime;
}
