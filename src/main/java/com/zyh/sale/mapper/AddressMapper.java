package com.zyh.sale.mapper;
import com.zyh.sale.pojo.Address;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author zyh
 * @create 2022-01-31-16:38
 */
// 收获地址持久层接口
@Mapper
public interface AddressMapper {

    /**
     * 插入用户的收货地址
     * @param address 收货地址数据
     * @return  受影响的行数
     */
    Integer insert(Address address);

    /**
     * 更新用户收获地址
     * @param address 收货地址
     * @return
     */
    Integer updateAddress(Address address);

    /**
     * 统计用户收货地址的数量
     * @param uid 用户的id
     * @return  当前用户的收获地址总数
     */
    Integer countById(Integer uid);

    /**
     * 根据用户的id查询收货地址
     * @param uid 用户id
     * @return 收获地址列表
     */
    List<Address> findByUid(Integer uid);

    /**
     * 根据aid查询收货地址数据
     * @param aid 收获地址id
     * @return   收获地址数据
     */
    Address findByAid(Integer aid);
    /**
     * 将某用户的所有收货地址设置为非默认地址
     * @param uid 收货地址归属的用户id
     * @return 受影响的行数
     */
    Integer updateNonDefault(Integer uid);

    /**
     * 将指定的收货地址设置为默认地址
     * @param aid 收货地址id
     * @param modifiedUser 修改执行人
     * @param modifiedTime 修改时间
     * @return 受影响的行数
     */
    Integer updateDefaultByAid(@Param("aid") Integer aid, @Param("modifiedUser")String modifiedUser, @Param("modifiedTime")Date modifiedTime);

    /**
     * 根据收货地址id删除收货地址
     * @param aid 收货地址id
     * @return 受影响的行数
     */
    Integer deleteByAid(Integer aid);

    /**
     * 查询用户最后一次被修改的地址
     * @param uid 用户id
     * @return  收货地址
     */
    Address findLastModified(Integer uid);


}
