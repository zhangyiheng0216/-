package com.zyh.sale.service.imp;


import com.zyh.sale.mapper.AddressMapper;
import com.zyh.sale.pojo.Address;
import com.zyh.sale.service.AddressService;
import com.zyh.sale.service.DistrictService;
import com.zyh.sale.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author yujiangsheng
 * @create 2022-04-06-17:01
 */
@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressMapper addressMapper;

    //收货地址依赖于IDistrictService的业务层接口
    @Autowired
    private DistrictService districtService;

    @Value("${user.address.max-count}")
    private Integer maxCount;

    @Override
    public void addNewAddress(Integer uid, String username, Address address) {
        Integer count = addressMapper.countById(uid);
        if(count>=maxCount){
            throw new AddressCountException("收货地址超出上限，无法添加！");
        }

        //对address对象中数据进行补全：省市区
        String ProvinceName =  districtService.getNameByCode(address.getProvinceCode());
        String CityName =  districtService.getNameByCode(address.getCityCode());
        String AreaName =  districtService.getNameByCode(address.getAreaCode());

        address.setProvinceName(ProvinceName);
        address.setCityName(CityName);
        address.setAreaName(AreaName);
        // 补全数据：将参数uid封装到参数address中
        address.setUid(uid);
        // 补全数据：根据以上统计的数量，得到正确的isDefault值(是否默认：0-不默认，1-默认)，并封装
        address.setIsDefault(count==0?1:0);  //1表示默认地址
        //补全四项日志
        address.setCreatedUser(username);
        address.setModifiedUser(username);
        address.setCreatedTime(new Date());
        address.setModifiedTime(new Date());
        Integer result = addressMapper.insert(address);
        if(result!=1){
            throw  new InsertException("添加收获地址时出现异常");
        }
    }

    @Override
    public List<Address> getByUid(Integer uid) {
        List<Address> list = addressMapper.findByUid(uid);
        for (Address address : list) {
            //清除不需要展示的数据
            address.setAreaCode(null);
        }
        return list;
    }

    @Override
    public void setDefault(Integer aid, Integer uid, String username) {
        Address result = addressMapper.findByAid(aid);
        if(result == null) {
            throw new AddressNotFoundException("收货地址不存在");
        }
        //检测当前获取到的收获地址的归属
        if(!result.getUid().equals(uid)){
            throw new AccessDeniedException("非法数据访问");
        }
        Integer rows = addressMapper.updateNonDefault(uid);
        if(rows == 0 ){
            throw new UpdateException("更新数据时产生异常");
        }
        rows = addressMapper.updateDefaultByAid(aid, username, new Date());
        if(rows != 1 ){
            throw new UpdateException("更新数据时产生异常");
        }
    }

    @Override
    public void delete(Integer aid, Integer uid, String username) {
        Address result = addressMapper.findByAid(aid);
        if(result == null){
            throw new AddressNotFoundException("收货地址数据不存在");
        }
        if(!result.getUid().equals(uid)){
            throw new AccessDeniedException("非法数据访问");
        }
        Integer rows = addressMapper.deleteByAid(aid);
        if(rows != 1){
            throw new DeleteException("删除数据时产生未知异常");
        }
        if(result.getIsDefault()==1){
            Address address = addressMapper.findLastModified(uid);
            rows = addressMapper.updateDefaultByAid(address.getAid(), username, new Date());
            if(rows!=1){
                throw new UpdateException("更新时产生异常");
            }
        }
    }

    @Override
    public Address getByAid(Integer aid ,Integer uid) {
        Address address = addressMapper.findByAid(aid);
        if(address == null){
            throw new AddressNotFoundException("收货地址数据不存在");

        }
        if(!address.getUid().equals(uid)){
            throw new AccessDeniedException("非法数据访问");
        }
        return address;
    }



    @Override
    public void updateAddress(Address address, Integer aid, String username,Integer uid) {
        Address result = addressMapper.findByAid(aid);

        if(result==null){
            throw new AddressNotFoundException("收货地址不存在");
        }
        if(!result.getUid().equals(uid)){
            throw new AddressNotFoundException("数据非法访问");

        }

        address.setModifiedUser(username);
        address.setModifiedTime(new Date());
        Integer rows = addressMapper.updateAddress(address);
        System.out.println("接受请求");
        if(rows!=1){
            throw new UpdateException("修改地址异常");
        }


    }



}
