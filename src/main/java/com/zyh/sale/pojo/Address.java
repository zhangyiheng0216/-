package com.zyh.sale.pojo;

import lombok.Data;

/**
 * @author yujiangsheng
 * @create 2022-04-06-16:31
 */
//收货地址数据的实体类
@Data
//Data 用在类上面 相当于 set get equals hashcode toString
public class Address extends com.zyh.sale.pojo.BaseEntity {
    private Integer aid;
    private Integer uid;
    private String name;
    private String provinceName;
    private String provinceCode;
    private String cityName;
    private String cityCode;
    private String areaName;
    private String areaCode;
    private String zip;
    private String address;
    private String phone;
    private String tel;
    private String tag;
    private Integer isDefault;
}
