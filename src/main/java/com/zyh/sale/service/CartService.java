package com.zyh.sale.service;

import com.zyh.sale.vo.CartVO;

import java.util.List;

public interface CartService {
    /**
     * 将商品添加到购物车中
     * @param uid 用户id
     * @param pid 商品id
     * @param amount 新增数量
     * @param username 用户名
     */
    void addToCart(Integer uid,Integer pid,Integer amount,String username);

    /*查询某用户的购物车数据*/
    List<CartVO> getVOByUid(Integer uid);

    /**
     * 更新用户的购物车数据的数量
     * @param cid
     * @param uid
     * @param username
     * @return 增加成功后新的数量
     */
    Integer addNum(Integer cid,Integer uid,String username);

    /*将购物车中某商品的数量减1*/
    Integer reduceNum(Integer cid, Integer uid, String username);

    List<CartVO> getVOByCid(Integer uid ,Integer[] cids);

    /*根据若干个购物车数据id查询详情的列表*/
    Integer deleteByUidAndCids(Integer uid , Integer[] cids);
}
