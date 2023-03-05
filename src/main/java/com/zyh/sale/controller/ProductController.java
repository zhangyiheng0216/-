package com.zyh.sale.controller;

import com.zyh.sale.pojo.Product;
import com.zyh.sale.service.ProductService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author zyh
 * @version 1.0
 * @description: TODO
 * @date 2023/1/31 20:01
 */
@Controller
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("/search")
    public String search( String search, HttpSession session){
        List<Product> products = productService.find(search);
        session.setAttribute("search",products);
        return "search";
    }
//    @GetMapping("/get_product")
//    public String getProductByID(Integer id, HttpSession session){
//        Product product= productService.findById(id);
//        session.setAttribute("product",product);
//        return "product";
//    }

}
