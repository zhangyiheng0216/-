package com.zyh.sale.service.imp;

import com.zyh.sale.mapper.UserMapper;
import com.zyh.sale.pojo.User;
import com.zyh.sale.service.UserService;
import com.zyh.sale.service.ex.*;
import com.zyh.sale.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;

/**
 * @author zyh
 * @version 1.0
 * @description: TODO
 * @date 2023/1/31 11:07
 */
@Service
public class UserServiceImp implements UserService {
    @Resource
    UserMapper userMapper;
    @Autowired
    FileUtils fileUtils;

    //注册
    @Override
    public void insert(User user) {
        String username = user.getUsername();
        // 调用 findByUsername(username) 判断用户是否被注册过
        User byUserName = userMapper.findByUserName(username);
        if(byUserName!=null){
            //抛出异常
            throw new UsernameDuplicatedException("用户名被占用异常");
        }

        //密码加密处理  ： MD5算法
        // （串 + passpword + 串） 连续加载三次
        // 串 == 盐值 ，就是一个随机的字符串
        String oldPassword = user.getPassword();
        //随机生成盐值
        String salt = UUID.randomUUID().toString().toUpperCase();
        // 将密码和盐值加密
        String md5Password = getMD5Password(oldPassword, salt);
        user.setPassword(md5Password);


        //补全数据
        user.setIsDelete(0);
        user.setCreatedUser(user.getUsername());
        user.setModifiedUser(user.getUsername());
        Date date = new Date();
        user.setCreatedTime(date);
        user.setModifiedTime(date);
        //记录盐值
        user.setSalt(salt);

        //执行注册业务功能的实现
        Integer rows = userMapper.insert(user);
        if(rows != 1){
            throw new InsertException("在注册时发生未知异常");
        }

    }

    //登录
    @Override
    public User login(String username, String password) {
        User result = userMapper.findByUserName(username);
        if (result == null) {
            throw new UserNotFoundException("用户数据不存在");
        }
        String md5Password = getMD5Password(password, result.getSalt());
        if(!md5Password.equals(result.getPassword())){
            throw  new PasswordNotMatchException("用户密码错误");
        }
        if(result.getIsDelete()==1){
            throw new UserNotFoundException("用户数据不存在");
        }
        //返回个别必要数据，提高系统性能
        User user = new User();
        user.setUid(result.getUid());
        user.setUsername(result.getUsername());
        user.setAvatar(result.getAvatar());
        return user;
    }


    // 修改用户密码
    @Override
    public void changePassword(Integer uid, String username, String oldPassword, String newPassword) {
        User result = userMapper.findByUid(uid);
        if(result == null || result.getIsDelete()==1) {
            throw  new UserNotFoundException("用户数据不存在");
        }
        //原始密码和输入密码比较
        String md5Password = getMD5Password(oldPassword, result.getSalt());
        if(!result.getPassword().equals(md5Password)){
            throw new PasswordNotMatchException("密码错误");
        }
        // 讲新密码设置到数据库
        String newMD5Password = getMD5Password(newPassword,result.getSalt());
        //判断新旧密码不能重复
        if(md5Password.equals(newMD5Password)){
            throw new PasswordNotMatchException("新旧密码重复");

        }
        Integer rows = userMapper.updatePasswordByUid(uid, newMD5Password, username, new Date());
        if(rows!=1){
            throw new UpdateException("更新时数据产生异常");
        }

    }

    //查询用户数据
    @Override
    public User getByUid(Integer uid) {
        User result = userMapper.findByUid(uid);
        if(result == null || result.getIsDelete()==1){
            throw new UserNotFoundException("用户数据不存在");
        }
//        User user = new User();
//        user.setUsername(result.getUsername());
//        user.setPhone(result.getPhone());
//        user.setEmail(result.getEmail());
//        user.setGender(result.getGender());

        return result;
    }
    //修改个人设置
    @Override
    public void changeInfo(Integer uid, String username, User user) {
        User result = userMapper.findByUid(uid);
        if(result == null || result.getIsDelete()==1){
            throw new UserNotFoundException("用户数据不存在");
        }
        user.setUid(uid);
        user.setModifiedUser(username);
        user.setModifiedTime(new Date());
        Integer rows = userMapper.updateInfoByUid(user);
        if(rows!=1){
            throw  new UpdateException("更新时产生未知异常");
        }
    }

    //修改个人头像
    @Override
    public void changAvatar(Integer uid, MultipartFile avatar, String username) {
        //查询当前用户数据是否存在
        User result = userMapper.findByUid(uid);
        if(result == null || result.getIsDelete().equals(1)){
            throw new UserNotFoundException("用户数据不存在");
        }
        System.out.println("=======================");
        System.out.println(avatar.getOriginalFilename());
        Integer rows = userMapper.updateAvatarByUid(uid, avatar.getOriginalFilename(), username, new Date());
        if(rows!=1){
            throw  new UpdateException("用户更新头像是产生未知异常");
        }
        fileUtils.saveFile(avatar);
    }


    // 定义MD5算法加密
    private String getMD5Password(String password , String salt){
        for(int i=0;i<3;i++) {
            password =  DigestUtils.md5DigestAsHex((salt + password + salt).getBytes(StandardCharsets.UTF_8)).toUpperCase();
        }
        return password;
    }
}
