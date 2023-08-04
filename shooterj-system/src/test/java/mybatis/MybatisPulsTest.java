package mybatis;


import com.shooterj.sys.Application;
import com.shooterj.sys.infrastructure.persistence.entity.SysUserDO;
import com.shooterj.sys.infrastructure.persistence.mapper.SysUserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@SpringBootApplication
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class MybatisPulsTest {

    @Autowired
    SysUserMapper sysUserMapper;

    @Test
    public void test1(){

        SysUserDO db = new SysUserDO();
        db.setUserName("test");
        db.setId(UUID.randomUUID().toString());
        db.setMobile("18814114937");
        sysUserMapper.insert(db);
    }

}
