package com.shooterj.sys.api;

import com.shooterj.common.pipeline.InstanceBuildContext;
import com.shooterj.common.pipeline.PipelineExecutor;
import com.shooterj.common.pipeline.PipelineRouteConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;


@RestController
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    PipelineExecutor pipelineExecutor;

    static {
        PipelineRouteConfig.PIPELINE_ROUTE_MAP.put(InstanceBuildContext.class,
                Arrays.asList(
                        InputDataPreChecker.class,
                        ModelInstanceCreator.class
                ));

        // 将来其他 Context 的管道配置
    }

    @GetMapping("/pipelineExecutor")
    public void test(){
        InstanceBuildContext data = new InstanceBuildContext();
        boolean success = pipelineExecutor.acceptSync(data);
    }

    @GetMapping("/")
    public String test_(){
        return "ok";
    }
}
