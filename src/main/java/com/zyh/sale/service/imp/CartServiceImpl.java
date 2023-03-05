package com.zyh.sale.service.imp;


import com.zyh.sale.mapper.CartMapper;
import com.zyh.sale.mapper.ProductMapper;
import com.zyh.sale.pojo.Cart;
import com.zyh.sale.service.CartService;
import com.zyh.sale.service.ex.*;
import com.zyh.sale.vo.CartVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author zyh
 * @create 2023-02-1-11:16
 */
@Service
public class CartServiceImpl implements CartService {

    @Resource
    private CartMapper cartMapper;
    @Resource
    private ProductMapper productMapper;

    @Override
    public void addToCart(Integer uid, Integer pid, Integer amount, String username) {
        Cart cart = cartMapper.findByUidAndPid(uid, pid);
        Date date = new Date();
        if(cart != null){
            Integer rows = cartMapper.updateNumByCid(cart.getCid(), amount, username, date);
            if(rows != 1) throw new UpdateException("修改数据时产生异常");

        }else{
            Cart cart1 = new Cart();
            cart1.setPid(pid);
            cart1.setUid(uid);
            cart1.setNum(amount);
            cart1.setPrice(productMapper.findById(pid).getPrice());
            cart1.setCreatedUser(username);
            cart1.setCreatedTime(date);
            cart1.setModifiedUser(username);
            cart1.setModifiedTime(date);
            Integer rows1 = cartMapper.insert(cart1);
            if(rows1 != 1) throw new InsertException("插入数据时产生异常");
        }
    }

    @Override
    public List<CartVO> getVOByUid(Integer uid) {
         return cartMapper.findVOByUid(uid);
    }

    @Override
    public Integer addNum(Integer cid, Integer uid, String username) {
        Cart result = cartMapper.findByCid(cid);
        if(result == null){
            throw new CartNotFoundException("数据不存在");
        }
        if(!result.getUid().equals(uid)){
            throw new AccessDeniedException("数据非法访问");
        }
        Integer num = result.getNum() + 1;
        Integer rows = cartMapper.updateNumByCid(cid, num,username,new Date());
        if(rows!=1) {
            throw new UpdateException("更新数据时产生异常");
        }
        return num;
    }
    @Override
    public Integer reduceNum(Integer cid, Integer uid, String username) {
        // 调用findByCid(cid)根据参数cid查询购物车数据
        Cart result = cartMapper.findByCid(cid);
        // 判断查询结果是否为null
        if (result == null) {
            // 是：抛出CartNotFoundException
            throw new CartNotFoundException("尝试访问的购物车数据不存在");
        }

        // 判断查询结果中的uid与参数uid是否不一致
        if (!result.getUid().equals(uid)) {
            // 是：抛出AccessDeniedException
            throw new AccessDeniedException("非法访问");
        }

        // 可选：检查商品的数量是否大于多少(适用于增加数量)或小于多少(适用于减少数量)
        // 根据查询结果中的原数量增加1得到新的数量num
        Integer num = result.getNum() - 1;
        //判断商品数量不能为负数
        if(num < 0){
            throw new InsertException("商品数量不能负数");
        }

        // 创建当前时间对象，作为modifiedTime
        Date now = new Date();
        // 调用updateNumByCid(cid, num, modifiedUser, modifiedTime)执行修改数量
        Integer rows = cartMapper.updateNumByCid(cid, num, username, now);
        if (rows != 1) {
            throw new InsertException("修改商品数量时出现未知错误，请联系系统管理员");
        }


        // 返回新的数量
        return num;
    }

    @Override
    public List<CartVO> getVOByCid(Integer uid, Integer[] cids) {
        List<CartVO> list = cartMapper.findVOByCid(cids);
        Iterator<CartVO> it = list.iterator();
        while(it.hasNext()){
            CartVO cartVO = it.next();
            if(!cartVO.getUid().equals(uid)){
                //非当前用户数据
                list.remove(cartVO);
            }
        }
        return list;
    }

    @Override
    public Integer deleteByUidAndCids(Integer uid, Integer[] cids) {
        List<CartVO> list = cartMapper.findVOByCid(cids);
//        Iterator<CartVO> it = list.iterator();
//        while(it.hasNext()){
//            CartVO cartVO = it.next();
//            if(!cartVO.getUid().equals(uid)){
//                //非当前用户数据
//                list.remove(cartVO);
//            }
//        }
        for(CartVO cartVO:list){
            System.out.println(cartVO);
            if(!cartVO.getUid().equals(uid)){
                throw new AddressNotFoundException("数据非法访问");
            }
            if(cartVO == null){
                throw new CartNotFoundException("数据不存在异常");
            }

        }
        Integer rows = cartMapper.deleteByUidAndCids(cids);
        if(rows == 0 ){
            throw new DeleteException("删除购物车数据时产生异常");
        }
        return rows;
    }



}
