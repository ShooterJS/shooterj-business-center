import com.shooterj.sys.Application;
import com.shooterj.sys.domain.customer.CustomerDTO;
import com.shooterj.sys.domain.customer.CustomerUpdater;
import com.shooterj.sys.domain.customer.service.ICustomerService;
import com.shooterj.sys.domain.template.objecttemplate.creator.ObjectTemplateCreator;
import com.shooterj.sys.domain.template.objecttemplate.service.IObjectTemplateService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TemplateServiceTest {

    @Autowired
    private IObjectTemplateService objectTemplateService;


    @Test
    public void testJpaAdd(){
        ObjectTemplateCreator creator = new ObjectTemplateCreator();
        creator.setCreateUser("system");
        creator.setCategoryCode("stake");
        creator.setCategoryId(1L);
        creator.setName("充电桩模板");
        creator.setDescription("充电桩模板");
        creator.setCode("stake_template");
        objectTemplateService.createObjectTemplate(creator);
    }

    @Autowired
    ICustomerService customerService;

    @Test
    public void testMybatisAdd(){
        CustomerDTO dto = new CustomerDTO();
        dto.setGrade("一年级");
        dto.setUsername("张三");
        customerService.createCustomer(dto);
    }

    @Test
    public void testMybatisUpdate(){
        CustomerUpdater dto = new CustomerUpdater();
        dto.setUsername("张三");
        dto.setId(1L);
        customerService.updateCustomer(dto);
    }
}
