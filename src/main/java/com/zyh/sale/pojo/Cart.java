package com.zyh.sale.pojo;

import lombok.Data;

/**
 * @author yujiangsheng
 * @create 2022-04-13-10:33
 */
@Data
public class Cart extends com.zyh.sale.pojo.BaseEntity {
       private Integer cid;
       private Integer uid;
       private Integer pid;
       private long price;
       private Integer num;
       private String title;
}
