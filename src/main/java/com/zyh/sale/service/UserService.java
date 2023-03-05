package com.zyh.sale.service;

import com.zyh.sale.pojo.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;


public interface UserService {
    //注册
    void insert(User user);
    //登录
    User login(String username,String password);
    //修改密码
    void changePassword(Integer uid,String username,String oldPassword,String newPassword);
    //查询用户数据
    User getByUid(Integer uid);
    //修改个人信息
    void changeInfo(Integer uid,String username,User user);
    //修改个人头像
    void changAvatar(Integer uid, MultipartFile avatar, String username);
}
