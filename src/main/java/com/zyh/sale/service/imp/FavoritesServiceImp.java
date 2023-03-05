package com.zyh.sale.service.imp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zyh.sale.mapper.FavoritesMapper;
import com.zyh.sale.mapper.ProductMapper;
import com.zyh.sale.pojo.Favorites;
import com.zyh.sale.pojo.Product;
import com.zyh.sale.service.FavoritesService;
import com.zyh.sale.service.ex.DeleteException;
import com.zyh.sale.service.ex.InsertException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zyh
 * @version 1.0
 * @description: TODO
 * @date 2023/2/4 14:41
 */
@Service
public class FavoritesServiceImp implements FavoritesService {
    @Resource
    private FavoritesMapper favoritesMapper;
    @Resource
    private ProductMapper productMapper;

    //收藏功能
    @Override
    public void addFavorites(Integer uid, Integer pid) {
        Favorites favorite = new Favorites();
        Product product = productMapper.findById(pid);
        if(product == null){
            throw new InsertException("商品不存在");

        }
        //填充favorites对象空白字段
        favorite.setUid(uid);
        favorite.setPid(pid);
        favorite.setImage(product.getImage());
        favorite.setPrice(product.getPrice());
        favorite.setTitle(product.getTitle());
        favorite.setSellPoint(product.getSellPoint());
        favorite.setStatus(1);

//        int result = favoritesMapper.addFavorites(favorite);
//        if (result == 0){
//            throw new InsertException("服务器异常，收藏商品失败");
//        }
//        if(result==1){
//            throw new InsertException("请不要重复收藏商品");
//
//        }
    }

    @Override
    public PageInfo<Favorites> queryFavorites(Integer uid, Integer pageNum, Integer pageSize, Integer status) {
        //开启分页功能，pageNum是当前页，
        // pageSize是每页显示的数据量，
        // 这两个值都可以选择让前端传或者自己调整
        PageHelper.startPage(pageNum,pageSize);
        List<Favorites> favorites = favoritesMapper.queryFavoritesByUidAndStatus(uid, status);
        PageInfo<Favorites> pageInfo = new PageInfo<>(favorites);
        return pageInfo;



    }

    @Override
    public void deleteFavorites(Integer fid,Integer uid) {
        Favorites favorites = favoritesMapper.selectFavorites(fid);
        if (favorites == null){
            throw new InsertException("收藏商品不存在");
        }
        if(!favorites.getUid().equals(uid)){
            throw new DeleteException("数据非法访问");
        }

        Integer row = favoritesMapper.deleteFavoritesStatus(fid);
        if(row !=1){
            throw new DeleteException("删除数据时产生未知异常");
        }

    }
}
