package com.zyh.sale.service;

import com.github.pagehelper.PageInfo;
import com.zyh.sale.pojo.Favorites;

public interface FavoritesService {
    //新增收藏商品的抽象方法
    void addFavorites(Integer uid, Integer pid);

    //查询收藏商品的抽象方法
    PageInfo<Favorites> queryFavorites(Integer uid, Integer pageNum, Integer pageSize, Integer status);

    //取消用户收藏
    void deleteFavorites(Integer fid,Integer uid);
}
