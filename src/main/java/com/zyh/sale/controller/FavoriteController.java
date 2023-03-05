package com.zyh.sale.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author zyh
 * @version 1.0
 * @description: TODO
 * @date 2023/2/2 14:50
 */
@Controller
public class FavoriteController {
    @GetMapping("/favorites")
    public String favorite(){
        return "favorites";
    }
}
