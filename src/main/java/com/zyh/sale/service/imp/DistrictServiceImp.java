package com.zyh.sale.service.imp;

import com.zyh.sale.mapper.DistrictMapper;
import com.zyh.sale.pojo.District;
import com.zyh.sale.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zyh
 * @version 1.0
 * @description: TODO
 * @date 2023/1/31 19:02
 */
@Service
public class DistrictServiceImp implements DistrictService {
    @Autowired
    private DistrictMapper districtMapper;

    @Override
    public List<District> getByParent(String parent) {
        List<District> list = districtMapper.findByParent(parent);
        // 在进行网络数据传输时，为了避免无效数据传输，可以将无效数据设为null
        // 好处，节约流量。提升性能
        for(District d:list){
            d.setId(null);
            d.setParent(null);
        }
        return list;
    }

    @Override
    public String getNameByCode(String code) {
        return districtMapper.findNameByCode(code);
    }
}
