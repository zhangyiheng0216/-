package com.zyh.sale.controller;

import com.zyh.sale.pojo.Address;
import com.zyh.sale.pojo.District;
import com.zyh.sale.service.AddressService;
import com.zyh.sale.service.DistrictService;
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
 * @date 2023/2/2 15:54
 */
@Controller
public class AddressController {

    @Autowired
    AddressService addressService;
    @Autowired
    DistrictService districtService;

    //通过uid获取address
    @GetMapping("/address")
    public String address(HttpSession session){
        List<Address> byUid = addressService.getByUid((Integer) session.getAttribute("uid"));
        session.setAttribute("byUid",byUid);
        return "address";
    }

    //更新address
    @PostMapping("/updateAddress")
    public String updateAddress(HttpSession session,Address address){
        addressService.updateAddress(address, address.getAid(), (String)session.getAttribute("username"),(Integer)session.getAttribute("uid"));
        return "redirect:/address";
    }

    //删除地址
    @GetMapping("/deleteAddress")
    public String deleteAddress(HttpSession session,Integer aid){
        addressService.delete(aid,(Integer)session.getAttribute("uid"),(String)session.getAttribute("username"));
        return "redirect:/address";
    }
    //去添加地址页面
    @GetMapping("/addAddress")
    public String addAddress(HttpSession session,String paren){
        List<District> byParent = districtService.getByParent("86");
        session.setAttribute("provinces",byParent);
        return "addAddress";
    }

    //添加地址
    @PostMapping("addAddress")
    public String addAddress(Address address,HttpSession session){
        addressService.addNewAddress((Integer) session.getAttribute("uid"),(String)session.getAttribute("username"),address);
        return "redirect:address";
    }
}
