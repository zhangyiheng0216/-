package com.zyh.sale.mapper;
import com.zyh.sale.pojo.District;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author zyh
 * @create 2023-01-31-15:24
 */
@Mapper
public interface DistrictMapper {

    /**
     * 根据父代号查询区域信息
     * @param parent 父代号
     * @return  父区域下的列表区域
     */
    List<District> findByParent(String parent);

    String findNameByCode(String code);

}
