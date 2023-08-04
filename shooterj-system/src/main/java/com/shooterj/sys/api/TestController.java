package com.shooterj.sys.api;

import com.shooterj.sys.domain.service.futrue.MultiBatchSave;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestController {

    @Resource
    MultiBatchSave multiBatchSave;

    @GetMapping("/test")
    public void multithreadBatchInsert(){
        multiBatchSave.multithreadBatchInsert();
    }


}
