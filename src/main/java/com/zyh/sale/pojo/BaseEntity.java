package com.zyh.sale.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yujiangsheng
 * @create 2022-03-26-23:56
 */
@Data
public class BaseEntity implements Serializable {
    private String  createdUser;
    private Date createdTime;
    private String modifiedUser;
    private Date modifiedTime;
}
