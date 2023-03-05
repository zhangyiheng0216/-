package com.zyh.sale.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yujiangsheng
 * @create 2022-04-16-20:42
 */
@Data
public class OrderItem  extends BaseEntity implements Serializable {
    private Integer id;
    private Integer oid;
    private Integer pid;
    private String title;
    private String image;
    private Long price;
    private Integer num;
}
