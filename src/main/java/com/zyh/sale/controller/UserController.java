package com.zyh.sale.controller;

import com.zyh.sale.pojo.User;
import com.zyh.sale.service.ProductService;
import com.zyh.sale.service.UserService;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;

/**
 * @author zyh
 * @version 1.0
 * @description: TODO
 * @date 2023/1/31 10:28
 */
@Controller
public class UserController extends BaseController{
    @Autowired
    UserService userService;

    @GetMapping(value = {"/", "/login"})
    public String login(){
        return "login";
    }

    @PostMapping("/userLogin")
    public String userLogin(@Param("username") String username,@Param("password") String password, HttpSession session){
        User login = userService.login(username, password);
        //向session对象中完成数据的绑定（session全局的）
        session.setAttribute("uid", login.getUid());
        session.setAttribute("username", login.getUsername());
        session.setAttribute("user",login);
        return "redirect:/index";
    }
    @GetMapping("register")
    public String register(){
        return "register";
    }
    //跳转密码
    @GetMapping("/password")
    public String password(){
        return "password";
    }
    //修改密码

    @PostMapping("/updatePassword")

    public String updatePassword(@RequestParam("oldPassword")String oldPass, @RequestParam("newPassword") String newPass, HttpSession session){
        System.out.println("=================");
        System.out.println(oldPass);
        System.out.println(newPass);
        System.out.println(session.getAttribute("uid"));
        userService.changePassword((Integer) session.getAttribute("uid"),(String)session.getAttribute("username"),oldPass,newPass);
        return "redirect:/password";
    }
    @GetMapping("/userdata")
    public String userdata(HttpSession session){
        User user = userService.getByUid((Integer) session.getAttribute("uid"));
        session.setAttribute("user",user);
        return "userdata";
    }
    //修改数据
    @PostMapping("/updateData")
    public String updateData(User user ,HttpSession session) {
        userService.changeInfo((Integer) session.getAttribute("uid"),(String) session.getAttribute("username"),user);
        return "redirect:/userdata";
    }
    @GetMapping("/upload")
    public String upload(HttpSession session){
        User user = userService.getByUid((Integer) session.getAttribute("uid"));
        session.setAttribute("user",user);
        return "upload";
    }
    //修改头像
    @PostMapping("/updateAvatar")
    public String updateAvatar(@RequestParam("file") MultipartFile file, HttpSession session){
        userService.changAvatar((Integer) session.getAttribute("uid"), file,(String) session.getAttribute("username"));
        return "redirect:/upload";
    }
}
