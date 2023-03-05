package com.zyh.sale.controller;

import com.zyh.sale.pojo.District;
import com.zyh.sale.service.CartService;
import com.zyh.sale.service.DistrictService;
import com.zyh.sale.service.FavoritesService;
import com.zyh.sale.utils.JsonResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author zyh
 * @version 1.0
 * @description: TODO
 * @date 2023/2/12 14:06
 */
@Controller
@RestController
public class AjaxController {

    @Autowired
    DistrictService districtService;
    @Autowired
    FavoritesService favoritesService;
    @Autowired
    CartService cartService;

    //获取市
    @PostMapping("/findCity")
    @ResponseBody
    public JsonResult<List<District>> getByParent(HttpSession session, @Param("parent") String parent){
        List<District> byParent = districtService.getByParent(parent);
        session.setAttribute("citys",byParent);
        JsonResult<List<District>> jsonResult = new JsonResult<List<District>>(200,byParent);
        System.out.println("==========================");
        System.out.println("parent");
        return jsonResult;
    }
    //获取区
    @PostMapping("/findArea")
    @ResponseBody
    public JsonResult<List<District>> findArea(HttpSession session,@Param("parent") String parent){
        List<District> byParent = districtService.getByParent(parent);
        session.setAttribute("areas",byParent);
        JsonResult<List<District>> jsonResult = new JsonResult<List<District>>(200,byParent);

        System.out.println("==========================");
        System.out.println("parent");
        return jsonResult;
    }
    //添加到喜欢
    @PostMapping("/addFavorite")
    @ResponseBody
    public JsonResult<String> addFavorite(HttpSession session,@Param("pid")Integer pid){
        favoritesService.addFavorites((Integer) session.getAttribute("uid"),pid);
        JsonResult<String> result = new JsonResult<String>(200,"添加到喜欢成功");
        return result;
    }
    //添加到购物车
    @PostMapping("/addCart")
    @ResponseBody
    public JsonResult<String> addCart(HttpSession session,@Param("pid")Integer pid){
        System.out.println("=================");
        System.out.println(pid);
        cartService.addToCart((Integer) session.getAttribute("uid"),pid,1,(String) session.getAttribute("username"));
        return new JsonResult<String>(200,"添加到购物车成功");
    }
}
