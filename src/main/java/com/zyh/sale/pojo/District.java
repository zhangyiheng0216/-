package com.zyh.sale.pojo;

import lombok.Data;

/**
 * @author yujiangsheng
 * @create 2022-04-07-15:20
 */
//省市区的数据实体类
@Data
public class District extends BaseEntity{
    private Integer id;
    private String parent;
    private String code;
    private String name;
}
