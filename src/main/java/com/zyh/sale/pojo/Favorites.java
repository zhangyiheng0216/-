package com.zyh.sale.pojo;

import lombok.Data;

@Data
public class Favorites {
    private Integer fid;
    private Integer uid;
    private Integer pid;
    private String image;
    private Long price;
    private String title;
    private String sellPoint;
    private Integer status;


}
