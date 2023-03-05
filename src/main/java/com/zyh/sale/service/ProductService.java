package com.zyh.sale.service;

import com.zyh.sale.pojo.Product;

import java.util.List;

public interface ProductService {
    List<Product> findHotList();

    Product findById(Integer id);
    List<Product> find(String str);
}
