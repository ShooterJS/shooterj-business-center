package pipeine;

import com.shooterj.sys.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;

@SpringBootApplication
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class PipelineTest {


    @Autowired
    PipelineExecutor pipelineExecutor;

    static {
        PipelineRouteConfig.PIPELINE_ROUTE_MAP.put(InstanceBuildContext.class,
                Arrays.asList(
                        InputDataPreChecker.class,
                        ModelInstanceCreator.class
                ));
    }


    @Test
    public void buildModelInstance() {
        InstanceBuildContext data = new InstanceBuildContext();
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "shooterj");
        map.put("instanceName", "test");
        data.setFormInput(map);
        boolean success = pipelineExecutor.acceptSync(data);


    }

}
