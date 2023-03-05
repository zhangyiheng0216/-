package com.zyh.sale.mapper;

import com.zyh.sale.pojo.Favorites;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface FavoritesMapper {
    //新增收藏商品的抽象方法
    Integer addFavorites(Favorites favorites);

    //根据uid和收藏商品状态查询收藏的商品信息
    List<Favorites> queryFavoritesByUidAndStatus(Integer uid, Integer status);

    //根据收藏商品fid和用户uid取消对应商品收藏
    Integer deleteFavoritesStatus(Integer fid);

    Favorites  selectFavorites(Integer fid);
}
