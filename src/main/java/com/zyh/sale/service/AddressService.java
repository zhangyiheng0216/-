package com.zyh.sale.service;

import com.zyh.sale.pojo.Address;

import java.util.List;

public interface AddressService {
    /**
     * 创建新的收货地址
     * @param uid 当前登录的用户的id
     * @param username 当前登录的用户名
     * @param address 用户提交的收货地址数据
     */
    void addNewAddress(Integer uid, String username,Address address);

    /**
     * 查询某用户的收货地址列表数据
     * @param uid 收货地址归属的用户id
     * @return 该用户的收货地址列表数据
     */
    List<Address> getByUid(Integer uid);

    /**
     * 设置默认收货地址
     * @param aid   收获地址id
     * @param uid   用户id
     * @param username  修改者
     */
    void setDefault(Integer aid,Integer uid,String username);

    /**
     * 删除收货地址
     * @param aid 收货地址id
     * @param uid 归属的用户id
     * @param username 当前登录的用户名
     */
    void delete(Integer aid,Integer uid,String username);

    /**
     * 根据收货地址数据的id，查询收货地址详情
     * @param aid 收货地址id
     * @param uid 归属的用户id
     * @return 匹配的收货地址详情
     */

    Address getByAid(Integer aid, Integer uid);

    /**
     * 更新用户收货地址
     * @param address
     * @param aid 收获地址id
     * @param username 修改人
     */
    void updateAddress(Address address,Integer aid, String username,Integer uid);


}
