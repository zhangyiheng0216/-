package com.zyh.sale.mapper;


import com.zyh.sale.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * @author zyh
 * @create 2023-01-31-15:23
 */
//用户模块的持久层接口
@Mapper
public interface UserMapper {
    /**
     * 插入用户数据
     * @param   user 用户的数据
     * @return 受影响的行数（增删改）
     */
     Integer insert(User user);

    /**
     * 根据用户名查询用户数据
     * @param username 用户名
     * @return 匹配的用户数据，如果没有匹配的数据，则返回null
     */
     User findByUserName(String username);

    /**
     * 根据用户的uid来修改用户密码
     * @param   uid 用户的uid
     * @param   password 用户的新密码
     * @param   modifiedUser 修改用户的执行者
     * @param   modifiedTime 修改的实践
     * @return 受影响的行数（增删改）
     */
     Integer updatePasswordByUid(Integer uid, String password, String modifiedUser, Date modifiedTime);

    /**
     * 根据用户订单uid来查询用户
     * @param   uid 用户的uid
     * @return 找到返回用户对象，否则返回null
     */
    User findByUid(Integer uid);

    /**
     * 更新用户的数据信息
     * @param   user 用户的数据
     * @return 返回受影响的行数
     */
     Integer updateInfoByUid(User user);

    /**
     * 根据用户的uid来修改用户头像
     * @param   uid 用户的uid
     * @param   avatar 用户的头像
     * @param   modifiedUser 修改用户的执行者
     * @param   modifiedTime 修改的实践
     * @return 受影响的行数（增删改）
     */
    Integer updateAvatarByUid( Integer uid, String avatar, String modifiedUser, Date modifiedTime);


}
