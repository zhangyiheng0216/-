package com.zyh.sale.service.imp;

import com.zyh.sale.mapper.ProductMapper;
import com.zyh.sale.pojo.Product;
import com.zyh.sale.service.ProductService;
import com.zyh.sale.service.ex.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zyh
 * @version 1.0
 * @description: TODO
 * @date 2023/1/31 14:49
 */
@Service
public class ProductServiceImp implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> findHotList() {
        List<Product> hotList = productMapper.findHotList();
        for(Product product : hotList){
            product.setPriority(null);
            product.setCreatedUser(null);
            product.setCreatedTime(null);
            product.setModifiedUser(null);
            product.setModifiedTime(null);
        }
        return hotList;
    }

    @Override
    public Product findById(Integer id) {
        Product product = productMapper.findById(id);
        if(product == null){
            throw new ProductNotFoundException("商品信息不存在异常");
        }
        product.setPriority(null);
        product.setCreatedUser(null);
        product.setCreatedTime(null);
        product.setModifiedUser(null);
        product.setModifiedTime(null);
        return product;
    }

    @Override
    public List<Product> find(String str) {
        List<Product> products = productMapper.find(str);
        return products;
    }
}
