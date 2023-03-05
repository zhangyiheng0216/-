package com.zyh.sale.utils;

import com.zyh.sale.vo.CartVO;
import com.zyh.sale.vo.OrderVo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zyh
 * @version 1.0
 * @description: TODO
 * @date 2023/2/9 16:29
 */
@Component
public class OrdersUtiles {

    /**
     * 改变存储模式，使得order数据可以根据oid输出
     */
    public List<Map<Integer,List<OrderVo>>> changeTypeOrder(List<OrderVo> orderVos){
        List<Map<Integer,List<OrderVo>>> myList = new ArrayList<Map<Integer,List<OrderVo>>>();
        int oid = orderVos.get(0).getOid();
        Map<Integer,List<OrderVo>> map = new HashMap<Integer,List<OrderVo>>();
        List<OrderVo> orderVos1 = new ArrayList<OrderVo>();
        for (int i = 0; i < orderVos.size(); i++) {

            if (oid == orderVos.get(i).getOid()){
                OrderVo orderVo = new OrderVo();
                orderVo.setImage(orderVos.get(i).getImage());
                orderVo.setOid(orderVos.get(i).getOid());
                orderVo.setNum(orderVos.get(i).getNum());
                orderVo.setPid(orderVos.get(i).getPid());
                orderVo.setPrice(orderVos.get(i).getPrice());
                orderVo.setStatus(orderVos.get(i).getStatus());
                orderVo.setOrderTime(orderVos.get(i).getOrderTime());
                orderVo.setTitle(orderVos.get(i).getTitle());
                orderVo.setRecvName(orderVos.get(i).getRecvName());
                orderVo.setTotalPrice(orderVos.get(i).getTotalPrice());
                orderVos1.add(orderVo);
            }
            else{
                map.put(oid,orderVos1);
                myList.add(map);
                oid=orderVos.get(i).getOid();
                orderVos1 = new ArrayList<OrderVo>();
                map = new HashMap<Integer,List<OrderVo>>();
                i--;
            }
        }
        map.put(oid,orderVos1);
        myList.add(map);
        return myList;
    }
}
