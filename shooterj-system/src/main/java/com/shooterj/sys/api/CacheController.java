package com.shooterj.sys.api;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: ShooterJ
 * @description
 * @date: 2023/8/12 21:17
 */

@RestController
@RequestMapping("/")
public class CacheController {

    @Cacheable(value = "get",key = "#key")
    @GetMapping("cache")
    public String get(String key){
        return "success";
    }
}
