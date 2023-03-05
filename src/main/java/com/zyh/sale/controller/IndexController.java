package com.zyh.sale.controller;

import com.zyh.sale.pojo.Product;
import com.zyh.sale.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author zyh
 * @version 1.0
 * @description: TODO
 * @date 2023/1/31 13:26
 */
@Controller
public class IndexController {
    @Autowired
    ProductService productService;

    @GetMapping("/index")
    public String index(HttpSession session){
        List<Product> hotList = productService.findHotList();
        session.setAttribute("hotLists" , hotList);
        return "index";
    }
    @GetMapping("/product_detail")
    public String productDetailByID(Integer id, HttpSession session){
        System.out.println(id);
        System.out.println("===============");
        Product product = productService.findById(id);
        session.setAttribute("product",product);
        return "product";
    }
}
