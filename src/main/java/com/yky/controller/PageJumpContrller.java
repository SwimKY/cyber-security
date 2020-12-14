package com.yky.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author: yky
 * @CreateTime: 2020-10-21
 * @Description: 页面跳转
 */
@Controller
public class PageJumpContrller {

    @GetMapping("/breakingnews")
    public String breakingnews(){
        return "breakingnews";
    }

    @GetMapping("/search")
    public String search(){
        return "search";
    }
}
