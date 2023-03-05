package com.zyh.sale.service.imp;

import com.zyh.sale.mapper.OrderMapper;
import com.zyh.sale.pojo.Address;
import com.zyh.sale.pojo.Order;
import com.zyh.sale.pojo.OrderItem;
import com.zyh.sale.service.AddressService;
import com.zyh.sale.service.CartService;
import com.zyh.sale.service.OrderService;
import com.zyh.sale.service.ex.InsertException;
import com.zyh.sale.vo.CartVO;
import com.zyh.sale.vo.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author zyh
 * @version 1.0
 * @description: TODO
 * @date 2023/1/31 18:52
 */
@Service
public class OrderServiceImp implements OrderService {
    @Resource
    private OrderMapper orderMapper;
    @Autowired
    private AddressService addressService;
    @Autowired
    private CartService cartService;


    @Override
    public Order create(Integer aid, Integer uid, String username, Integer[] cids) {
        // 根据cids查询所勾选的购物车列表中的数据
        List<CartVO> list = cartService.getVOByCid(uid, cids);

        Date now = new Date();
        //计算商品的总价
        Long totalPrice = 0L;
        //创建订单详细数据
        for (CartVO c : list) {
            totalPrice += c.getRealPrice() * c.getNum();
        }
        // 查询收货地址数据
        Address address = addressService.getByAid(aid, uid);
        // 创建订单数据对象
        Order order = new Order();
        order.setUid(uid);
        // 补全数据：收货地址相关的6项
        order.setRecvName(address.getName());
        order.setRecvPhone(address.getPhone());
        order.setRecvProvince(address.getProvinceName());
        order.setRecvCity(address.getCityName());
        order.setRecvArea(address.getAreaName());
        order.setRecvAddress(address.getAddress());
        // 补全数据：totalPrice
        order.setTotalPrice(totalPrice);
        // 补全数据：status
        order.setStatus(0);
        // 补全数据：下单时间
        order.setOrderTime(now);
        // 补全数据：日志
        order.setCreatedUser(username);
        order.setCreatedTime(now);
        order.setModifiedUser(username);
        order.setModifiedTime(now);
        //查询数据
        Integer rows = orderMapper.insertOrder(order);
        if (rows != 1) {
            throw new InsertException("插入数据时产生异常");
        }


        // 遍历carts，循环插入订单商品数据
        for (CartVO cart : list) {
            // 创建订单商品数据
            OrderItem item = new OrderItem();
            // 补全数据：setOid(order.getOid())
            item.setOid(order.getOid());
            // 补全数据：pid, title, image, price, num
            item.setPid(cart.getPid());
            item.setTitle(cart.getTitle());
            item.setImage(cart.getImage());
            item.setPrice(cart.getRealPrice());
            item.setNum(cart.getNum());
            // 补全数据：4项日志
            item.setCreatedUser(username);
            item.setCreatedTime(now);
            item.setModifiedUser(username);
            item.setModifiedTime(now);
            // 插入订单商品数据
            Integer rows2 = orderMapper.insertOrderItem(item);
            if (rows2 != 1) {
                throw new InsertException("插入订单商品数据时出现未知错误，请联系系统管理员");
            }
        }


//        //删除购物车相应数据
//        cartService.deleteByUidAndCids(uid, cids);

        return order;


    }


    @Override
    public List<OrderVo> queryOrderVoByUid(Integer uid) {
        List<OrderVo> list = orderMapper.queryOrderVoByUid(uid);
        return list;
    }

    @Override
    public Integer getOidByUid(Integer uid) {
        Integer result = orderMapper.getOidByUid(uid);
        return uid;
    }
}
