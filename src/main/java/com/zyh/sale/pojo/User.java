package com.zyh.sale.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yujiangsheng
 * @create 2022-03-27-0:03
 */

/** 用户数据的实体类 */
@Data
public class User extends BaseEntity implements Serializable {
    private Integer uid;
    private String username ;
    private String password ;
    private String salt;
    private String phone;
    private String email;
    private Integer gender;
    private String avatar;
    private Integer isDelete;
}
