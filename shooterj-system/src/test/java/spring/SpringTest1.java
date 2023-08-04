package spring;

import com.shooterj.common.ShooterjService;
import com.shooterj.sys.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootApplication
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class SpringTest1 {

    @Test
    public void test1(){
        System.out.println(ShooterjService.Channel.NAME);
    }
}
