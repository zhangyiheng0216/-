package com.zyh.sale.controller;

import com.zyh.sale.pojo.Address;
import com.zyh.sale.pojo.Order;
import com.zyh.sale.service.AddressService;
import com.zyh.sale.service.CartService;
import com.zyh.sale.service.OrderService;
import com.zyh.sale.utils.OrdersUtiles;
import com.zyh.sale.vo.CartVO;
import com.zyh.sale.vo.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @author zyh
 * @version 1.0
 * @description: TODO
 * @date 2023/1/31 18:49
 */
@Controller
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    CartService cartService;
    @Autowired
    AddressService addressService;
    @Autowired
    OrdersUtiles ordersUtiles;

    @GetMapping("/orders")
    public String order(HttpSession session){
        List<OrderVo> byUid = orderService.queryOrderVoByUid((Integer) session.getAttribute("uid"));
        List<Map<Integer, List<OrderVo>>> maps = ordersUtiles.changeTypeOrder(byUid);
        session.setAttribute("orders",maps);
        return "orders";
    }
    @PostMapping("/creatOrder")
    public String creatOrder(Integer num, HttpSession session){

        return "orderConfirm";
    }

    @GetMapping("/orderInfo")
    public String orderInfo(){
        return "orderInfo";
    }

    //订单信息
    @PostMapping("/orderConfirm")
    public String orderConfirm( Integer[] cids,Integer aid,HttpSession session){

        List<CartVO> voByCid = cartService.getVOByCid((Integer) session.getAttribute("uid"), cids);
        List<Address> byUid = addressService.getByUid((Integer) session.getAttribute("uid"));
        session.setAttribute("cartByCid",voByCid);
        session.setAttribute("addressByUid",byUid);
        return "orderConfirm";
    }

    //支付
    @PostMapping("/payment")
    public String payment(Integer[] cids,Integer aid,HttpSession session){
        orderService.create(aid,(Integer) session.getAttribute("uid"),(String) session.getAttribute("username"),cids);
        Integer oid = orderService.getOidByUid((Integer) session.getAttribute("uid"));
        session.setAttribute("oid",oid);
        cartService.deleteByUidAndCids((Integer) session.getAttribute("uid"),cids);
        return "payment";

    }

    //支付结果
    @PostMapping("payResult")
    public String payResult(){
        return "paySuccess";
    }
}
