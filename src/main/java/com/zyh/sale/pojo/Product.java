package com.zyh.sale.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yujiangsheng
 * @create 2022-04-12-22:39
 */
@Data
    public class Product extends BaseEntity implements Serializable {
    private Integer id;
    private Integer categoryId;
    private String itemType;
    private String title;
    private String sellPoint;
    private Long price;
    private Integer num;
    private String image;
    private Integer status;
    private Integer priority;
}
