package com.zyh.sale.controller;

import com.zyh.sale.pojo.Product;
import com.zyh.sale.service.CartService;
import com.zyh.sale.vo.CartVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author zyh
 * @version 1.0
 * @description: TODO
 * @date 2023/1/31 20:37
 */
@Controller
public class CartController extends BaseController{
    @Autowired
    CartService cartService;
    @GetMapping("/cart")
    public String cart(HttpSession session){
        List<CartVO> carts = cartService.getVOByUid((Integer) session.getAttribute("uid"));
        session.setAttribute("carts",carts);
        return "cart";
    }
    @PostMapping("/creatCart")
    public String creatCart(@Param("num") int num , HttpSession session){
        Product product = (Product) session.getAttribute("product");
        cartService.addToCart((Integer) session.getAttribute("uid"),product.getId(),num,(String) session.getAttribute("username"));
        List<CartVO> carts = cartService.getVOByUid((Integer) session.getAttribute("uid"));
        session.setAttribute("carts",carts);
        System.out.println("我可以");
        return "forward:/cart";
    }

    @PostMapping("/reduceNum")
    @ResponseBody
    public String reduceNum(@Param("cid") Integer cid,HttpSession session){
        Integer integer = cartService.reduceNum(cid, (Integer) session.getAttribute("uid"), (String) session.getAttribute("username"));
        return "添加成功";
    }

    @PostMapping("/addNum")
    @ResponseBody
    public String addNum(@Param("cid")Integer cid,HttpSession session){
        cartService.addNum(cid,(Integer)session.getAttribute("uid"),(String)session.getAttribute("username"));
        return "添加成功";
    }

}
